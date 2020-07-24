import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Account } from '../models/account';
import { AuthenticationService } from '../_services';
import { environment } from '../../environments/environment';
import { User } from '../_models';
import { Router } from '@angular/router';
import { CreditCard } from '../models/creditCard';

@Component({
  selector: 'app-credit-card-list',
  templateUrl: './credit-card-list.component.html',
  styleUrls: ['./credit-card-list.component.scss']
})
export class CreditCardListComponent implements OnInit {
  loading = false;
  creditcards: CreditCard[] = [];
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
      .get<CreditCard[]>(`${environment.apiUrl}/credit-cards`, this.httpOptions)
      .subscribe((data) => {
        console.log(data);
        this.loading = false;
        this.creditcards = data;
      });
  }
  goToRoute(route: string) {
    this.router.navigate([route]);
  }
}

