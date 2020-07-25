import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Contact } from '../models/contact';
import { AuthenticationService } from '../_services';
import { environment } from 'src/environments/environment';
import { User } from '../_models';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.scss'],
})
export class ContactListComponent implements OnInit {
  loading = false;
  users: User[] = [];
  username: 'salesrep';
  password: 'admin';
  user: User;
  isAdmin: boolean;

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
      .get<User[]>(`${environment.apiUrl}/users`, this.httpOptions)
      .subscribe((data) => {
        this.loading = false;
        console.log(data);
        this.users = data;
      });
  }
  goToRoute(route: string) {
    this.router.navigate([route]);
  }

  openDelete(id: string): void {
    this.http
      .delete<void>(
        `${environment.apiUrl}/user/${id}`
      )
      .subscribe(
        (data) => {
          console.log('delete');
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> User deleted!',
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
