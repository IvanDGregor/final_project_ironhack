import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from '../_services';
import { Stats } from '../models/stats';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-stats-report',
  templateUrl: './stats-report.component.html',
  styleUrls: ['./stats-report.component.scss'],
})
export class StatsReportComponent implements OnInit {
  employeeCount: Stats = { min: null, max: null, median: null, mean: null };
  oppsPerAccount: Stats = { min: null, max: null, median: null, mean: null };
  productQuantity: Stats = { min: null, max: null, median: null, mean: null };

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit(): void {
    this.http
      .get<number>(
        `${environment.apiUrl}/account/maxEmployeeCount`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.employeeCount.max = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/account/minEmployeeCount`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.employeeCount.min = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/account/meanEmployeeCount`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.employeeCount.mean = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/account/medianEmployeeCount`,
        this.httpOptions
      )
      .subscribe((data) => {
        console.log(data);
        this.employeeCount.median = data;
        console.log(this.employeeCount);
      }, console.log);

    // ops per account
    this.http
      .get<number>(
        `${environment.apiUrl}/opportunities/by_opportunity_states/max`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.oppsPerAccount.max = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/opportunities/by_opportunity_states/min`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.oppsPerAccount.min = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/opportunities/by_opportunity_states/mean`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.oppsPerAccount.mean = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/opportunities/by_opportunity_states/median`,
        this.httpOptions
      )
      .subscribe((data) => {
        console.log(data);
        this.oppsPerAccount.median = data;
        console.log(this.employeeCount);
      }, console.log);

    // product quantity
    this.http
      .get<number>(
        `${environment.apiUrl}/opportunities/by_quantity/max`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.productQuantity.max = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/opportunities/by_quantity/min`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.productQuantity.min = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/opportunities/by_quantity/mean`,
        this.httpOptions
      )
      .subscribe((data) => {
        this.productQuantity.mean = data;
      }, console.log);

    this.http
      .get<number>(
        `${environment.apiUrl}/opportunities/by_quantity/median`,
        this.httpOptions
      )
      .subscribe((data) => {
        console.log(data);
        this.productQuantity.median = data;
        console.log(this.employeeCount);
      }, console.log);
  }
}
