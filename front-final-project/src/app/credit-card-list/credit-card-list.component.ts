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
  selector: 'app-credit-card-list',
  templateUrl: './credit-card-list.component.html',
  styleUrls: ['./credit-card-list.component.scss']
})
export class CreditCardListComponent implements OnInit {
  loading = false;
  creditcards: CreditCard[] = [];
  user: User;
  isAdmin: boolean;

  active: Status = Status.Active;
  frozen: Status = Status.Frozen;
  returnUrl: '/credit-cards';

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
  openUpdate(id: string, pin: string, userId: string, status: Status): void {
    this.http
      .put<void>(
        `${environment.apiUrl}/credit-card/${id}`,
        {  id, pin, userId, status},
        this.httpOptions
      )
      .subscribe(
        (data) => {
          console.log('updated');
          const updatedCreditCard = this.creditcards.find((creditCard) => creditCard.id === id);
          updatedCreditCard.id = id;
          updatedCreditCard.pin = pin;
          updatedCreditCard.userId = userId;
          updatedCreditCard.status = status;
          updatedCreditCard.newStatus = undefined;
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Credit Card updated!',
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
          `${environment.apiUrl}/credit-card/${id}`
        )
        .subscribe(
          (data) => {
            console.log('deleted');
            window.location.reload();
            this.toastr.success(
              '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Credit Card deleted!',
              '',
              {
                timeOut: 2000,
                enableHtml: true,
                toastClass: 'alert alert-success alert-with-icon',
                positionClass: 'toast-top-center',
              }
            );
          },
          (error) => {
            console.log('error');
          }
        );
      }
}

