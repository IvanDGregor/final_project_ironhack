import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Contact } from '../models/contact';
import { AuthenticationService } from '../_services';
import { environment } from 'src/environments/environment';
import { User } from '../_models';
import { Router } from '@angular/router';

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
}
