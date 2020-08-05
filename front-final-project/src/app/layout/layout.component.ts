import { Component, OnInit } from '@angular/core';
import { User } from '../_models';
import { AuthenticationService } from '../_services';
import { Router } from '@angular/router';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  user: User;
  isAdmin: boolean;
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  ngOnInit(): void {
    
    this.user = this.authenticationService.userValue;
    this.isAdmin =
      this.user.roles.find((role) => role.role === 'ROLE_ADMIN') !== undefined;
    console.log(this.isAdmin);
    if (
      this.authenticationService.userValue.roles.find(
        (role) => role.role === 'ROLE_ADMIN'
      ) === undefined
    ) {
    }
  }
}
