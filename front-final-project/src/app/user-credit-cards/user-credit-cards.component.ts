import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Account } from '../models/account';
import { AuthenticationService } from '../_services';
import { environment } from '../../environments/environment';
import { User } from '../_models';
import { Router } from '@angular/router';
import { CreditCard } from '../models/creditCard';
import { Status } from '../models/status';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-credit-cards',
  templateUrl: './user-credit-cards.component.html',
  styleUrls: ['./user-credit-cards.component.scss']
})
export class UserCreditCardsComponent implements OnInit {
  loading = false;
  creditcards: CreditCard[] = [];
  user: User;
  isAdmin: boolean;
  returnUrl: '/user-credit-cards';


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  constructor(
    private toastr: ToastrService,
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.user = this.authenticationService.userValue;
    this.isAdmin =
      this.user.roles.find((role) => role.role === 'ROLE_USER') !== undefined;
    console.log(this.isAdmin);
    this.loading = true;
    this.http
      .get<CreditCard[]>(`${environment.apiUrl}/credit-card/user/${this.user.userId}`, this.httpOptions)
      .subscribe((data) => {
        console.log(data);
        this.loading = false;
        this.creditcards = data;
      });
  }
}
