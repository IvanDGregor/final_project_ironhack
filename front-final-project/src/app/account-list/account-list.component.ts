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
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.scss'],
})
export class AccountListComponent implements OnInit {
  loading = false;
  accounts: Account[] = [];
  user: User;
  isAdmin: boolean;
  active: Status = Status.Active;
  frozen: Status = Status.Frozen;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };
  returnUrl: '/accounts';

  constructor(
    private toastr: ToastrService,
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
      .get<Account[]>(`${environment.apiUrl}/accounts`, this.httpOptions)
      .subscribe((data) => {
        console.log(data);
        this.loading = false;
        this.accounts = data;
      });
  }
  goToRoute(route: string) {
    this.router.navigate([route]);
  }

  openUpdate(id: string, balance: number, status: Status, secretKey: string, userId: string): void {
    this.http
      .put<void>(
        `${environment.apiUrl}/account/${id}`,
        {  id, balance, status, secretKey, userId},
        this.httpOptions
      )
      .subscribe(
        (data) => {
          console.log('updated');
          let updatedAccount = this.accounts.find((account) => account.id === id);
          updatedAccount.id = id;
          updatedAccount.balance = balance;
          updatedAccount.status = status;
          updatedAccount.secretKey = secretKey;
          updatedAccount.userId = userId;
          updatedAccount.newStatus = undefined;
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Account updated!',
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-success alert-with-icon',
              positionClass: 'toast-top-center',
            }
            );
          window.location.reload();
        },
        (error) => {
          console.log('error');
        }
      );
    }

    openDelete(id: string): void {
      this.http
        .delete<void>(
          `${environment.apiUrl}/account/${id}`
        )
        .subscribe(
          (data) => {
            console.log('updated');
            this.toastr.success(
              '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Account deleted!',
              '',
              {
                timeOut: 2000,
                enableHtml: true,
                toastClass: 'alert alert-success alert-with-icon',
                positionClass: 'toast-top-center',
              }
            );
            window.location.reload();
          },
          (error) => {
            console.log('error');
          }
        );
      }
}
