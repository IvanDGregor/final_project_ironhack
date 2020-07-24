import { Component, OnInit } from '@angular/core';
import { Opportunity } from '../models/opportunity';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { OpportunityStatus } from '../models/opportunityStatus';
import { FormControl } from '@angular/forms';
import { ThrowStmt } from '@angular/compiler';
import { AuthenticationService } from '../_services';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-opportunity-list',
  templateUrl: './opportunity-list.component.html',
  styleUrls: ['./opportunity-list.component.scss'],
})
export class OpportunityListComponent implements OnInit {
  loading = false;
  username: 'salesrep';
  password: 'admin';
  // status = new FormControl(['']);

  open: OpportunityStatus = OpportunityStatus.Open;
  lost: OpportunityStatus = OpportunityStatus.Closed_Lost;
  won: OpportunityStatus = OpportunityStatus.Closed_Won;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  opportunities: Opportunity[] = [];

  constructor(
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loading = true;
    this.http
      .get<Opportunity[]>(
        `${environment.apiUrl}/opportunities`,
        this.httpOptions
      )
      .subscribe((data) => {
        console.log(data);
        this.opportunities = data;
        this.loading = false;
      });

    /*this.status.valueChanges.subscribe((newStatus) => {
      console.log(newStatus);

    });*/
  }
  /*
  catchSelect() {
    // Pasamos el valor seleccionado a la variable verSeleccion
    this.selected = this.varSelected;
    console.log(this.selected, this.varSelected);
  }
    */

  openUpdate(id: number, opportunityStatus: OpportunityStatus): void {
    this.http
      .put<void>(
        `${environment.apiUrl}/opportunity/change_status?id=${id}`,
        { opportunityStatus },
        this.httpOptions
      )
      .subscribe(
        (data) => {
          console.log('updated');
          let updatedOpp = this.opportunities.find((opp) => opp.id === id);
          updatedOpp.opportunityStatus = opportunityStatus;
          updatedOpp.newStatus = undefined;
        },
        (error) => {
          console.log('error');
        }
      );
    /*
    this.clicked = true;
    this.opportunities.map(
      // tslint:disable-next-line: only-arrow-functions
      function (opp): void {
        console.log(opp);
        if (id === opp.id) {
          opp.opportunityStatus = status;
          console.log(opp);
        }
      }
    );
    */
  }
}
