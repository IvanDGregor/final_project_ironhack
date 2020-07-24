import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthenticationService } from '../_services';
import Chart from 'chart.js';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  public canvas: any;
  public ctx;
  public datasets: any;
  public data: any;
  public myChartData;
  public opportunityChart;
  public clicked = true;
  public clicked1 = false;
  public clicked2 = false;
  public clicked3 = false;

  salesreps: string[] = [];
  leadsBySalesreps: number[] = [];

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  constructor(
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.http
      .get<any>(
        `${environment.apiUrl}/opportunities/by_industry/count_lead`,
        this.httpOptions
      )
      .subscribe((results) => {
        console.log(results);
        const industries = [];
        const leadCount = [];
        results.forEach((result) => {
          industries.push(result[0]);
          leadCount.push(result[1]);
        });
        this.updateTable(leadChart, industries, leadCount);
      });

    this.retrieveAll();

    var gradientBarChartConfiguration: any = {
      maintainAspectRatio: false,
      legend: {
        display: false,
      },

      tooltips: {
        backgroundColor: '#f5f5f5',
        titleFontColor: '#333',
        bodyFontColor: '#666',
        bodySpacing: 4,
        xPadding: 12,
        mode: 'nearest',
        intersect: 0,
        position: 'nearest',
      },
      responsive: true,
      scales: {
        yAxes: [
          {
            gridLines: {
              drawBorder: false,
              color: 'rgba(29,140,248,0.1)',
              zeroLineColor: 'transparent',
            },
            ticks: {
              suggestedMin: 0,
              suggestedMax: 0,
              padding: 20,
              fontColor: '#9e9e9e',
            },
          },
        ],

        xAxes: [
          {
            gridLines: {
              drawBorder: false,
              color: 'rgba(29,140,248,0.1)',
              zeroLineColor: 'transparent',
            },
            ticks: {
              padding: 20,
              fontColor: '#9e9e9e',
            },
          },
        ],
      },
    };

    this.canvas = document.getElementById('LeadChart');
    this.ctx = this.canvas.getContext('2d');
    var gradientStroke = this.ctx.createLinearGradient(0, 230, 0, 50);

    gradientStroke.addColorStop(1, 'rgba(29,140,248,0.2)');
    gradientStroke.addColorStop(0.4, 'rgba(29,140,248,0.0)');
    gradientStroke.addColorStop(0, 'rgba(29,140,248,0)'); //blue colors

    const leadChart = new Chart(this.ctx, {
      type: 'bar',
      responsive: true,
      legend: {
        display: false,
      },
      data: {
        labels: [],
        datasets: [
          {
            label: 'Contacts',
            fill: true,
            backgroundColor: gradientStroke,
            hoverBackgroundColor: gradientStroke,
            borderColor: '#1f8ef1',
            borderWidth: 2,
            borderDash: [],
            borderDashOffset: 0.0,
            data: [],
          },
        ],
      },
      options: gradientBarChartConfiguration,
    });

    this.canvas = document.getElementById('OpportunityChart');
    this.ctx = this.canvas.getContext('2d');
    var gradientStroke = this.ctx.createLinearGradient(0, 230, 0, 50);

    gradientStroke.addColorStop(1, 'rgba(233,32,16,0.2)');
    gradientStroke.addColorStop(0.4, 'rgba(233,32,16,0.0)');
    gradientStroke.addColorStop(0, 'rgba(233,32,16,0)'); //red colors

    this.opportunityChart = new Chart(this.ctx, {
      type: 'bar',
      responsive: true,
      legend: {
        display: false,
      },
      data: {
        labels: [],
        datasets: [
          {
            label: 'Opportunities',
            fill: true,
            backgroundColor: gradientStroke,
            hoverBackgroundColor: gradientStroke,
            pointBackgroundColor: '#ec250d',
            pointBorderColor: 'rgba(255,255,255,0)',
            pointHoverBackgroundColor: '#ec250d',
            borderColor: '#ec250d',
            borderWidth: 2,
            borderDash: [],
            borderDashOffset: 0.0,
            data: [],
          },
        ],
      },
      options: gradientBarChartConfiguration,
    });
  }
  public updateTable(chart, industries, leadBySalesreps) {
    chart.data.labels = industries;
    chart.data.datasets[0].data = leadBySalesreps;
    chart.update();
  }

  public retrieveOpen() {
    this.http
      .get<any>(
        `${environment.apiUrl}/opportunities/by_industry/count_open_opportunity`,
        this.httpOptions
      )
      .subscribe((results) => {
        console.log(results);
        let industries = [];
        let opportunityCount = [];
        results.forEach((result) => {
          industries.push(result[0]);
          opportunityCount.push(result[1]);
        });
        this.updateTable(this.opportunityChart, industries, opportunityCount);
      });
  }

  public retrieveClosedWon() {
    this.http
      .get<any>(
        `${environment.apiUrl}/opportunities/by_industry/count_closed_won_opportunity`,
        this.httpOptions
      )
      .subscribe((results) => {
        console.log(results);
        let industries = [];
        let opportunityCount = [];
        results.forEach((result) => {
          industries.push(result[0]);
          opportunityCount.push(result[1]);
        });
        this.updateTable(this.opportunityChart, industries, opportunityCount);
      });
  }

  public retrieveClosedLost() {
    this.http
      .get<any>(
        `${environment.apiUrl}/opportunities/by_industry/count_closed_lost_opportunity`,
        this.httpOptions
      )
      .subscribe((results) => {
        console.log(results);
        let industries = [];
        let opportunityCount = [];
        results.forEach((result) => {
          industries.push(result[0]);
          opportunityCount.push(result[1]);
        });
        this.updateTable(this.opportunityChart, industries, opportunityCount);
      });
  }
  public retrieveAll() {
    this.http
      .get<any>(
        `${environment.apiUrl}/opportunities/by_industry/count_opportunity`,
        this.httpOptions
      )
      .subscribe((results) => {
        console.log(results);
        let industries = [];
        let opportunityCount = [];
        results.forEach((result) => {
          industries.push(result[0]);
          opportunityCount.push(result[1]);
        });
        this.updateTable(this.opportunityChart, industries, opportunityCount);
      });
  }
}
