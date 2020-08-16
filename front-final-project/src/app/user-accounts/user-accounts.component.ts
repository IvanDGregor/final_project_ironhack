import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Account } from '../models/account';
import { AuthenticationService } from '../_services';
import { environment } from '../../environments/environment';
import { User } from '../_models';
import { Router } from '@angular/router';
import { Status } from '../models/status';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-accounts',
  templateUrl: './user-accounts.component.html',
  styleUrls: ['./user-accounts.component.scss']
})
export class UserAccountsComponent implements OnInit {
  loading = false;
  accounts: Account[] = [];
  user: User;
  isAdmin: boolean;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };
  returnUrl: '/user-accounts';

  
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
      .get<Account[]>(`${environment.apiUrl}/account/user/${this.user.userId}`, this.httpOptions)
      .subscribe((data) => {
        console.log(data);
        this.loading = false;
        this.accounts = data;
      });
  }

}
