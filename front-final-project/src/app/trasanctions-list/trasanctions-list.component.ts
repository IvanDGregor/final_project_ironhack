import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Contact } from '../models/contact';
import { AuthenticationService } from '../_services';
import { environment } from 'src/environments/environment';
import { User } from '../_models';
import { Router } from '@angular/router';
import { Transaction } from '../models/transaction';
import * as jsPDF from 'jspdf';

@Component({
  selector: 'app-trasanctions-list',
  templateUrl: './trasanctions-list.component.html',
  styleUrls: ['./trasanctions-list.component.scss']
})
export class TrasanctionsListComponent implements OnInit {

  @ViewChild('htmlData') htmlData: ElementRef;
  loading = false;
  transactions: Transaction[] = [];
  user: User;
  isAdmin: boolean;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.user = this.authenticationService.userValue;
    this.isAdmin =
      this.user.roles.find((role) => role.role === 'ROLE_ADMIN') !== undefined;
    console.log(this.isAdmin);
    this.loading = true;
    this.http
      .get<Transaction[]>(`${environment.apiUrl}/transactions`, this.httpOptions)
      .subscribe((data) => {
        this.loading = false;
        console.log(data);
        this.transactions = data;
      });
  }
  goToRoute(route: string) {
    this.router.navigate([route]);
  }

  public downloadPDF(): void {
    const DATA = this.htmlData.nativeElement;
    const doc = new jsPDF('p', 'pt', 'a4');

    const handleElement = {
      '#editor'(element, renderer){
        return true;
      }
    };
    doc.fromHTML(DATA.innerHTML, 15, 15, {
      width: 200,
      elementHandlers: handleElement
    });

    doc.save('transactions.pdf');
  }
}

