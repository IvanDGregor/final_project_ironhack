import { Component, OnInit } from '@angular/core';
import { Lead } from '../models/lead';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthenticationService } from '../_services';
import { first } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-lead-list',
  templateUrl: './lead-list.component.html',
  styleUrls: ['./lead-list.component.scss'],
})
export class LeadListComponent implements OnInit {
  loading = false;
  username: 'salesrep';
  password: 'admin';

  // authorizationData = 'Basic ' + btoa('salesrep:admin');

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };

  leads: Lead[] = [];

  constructor(
    private authenticationService: AuthenticationService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loading = true;
    this.http
      .get<Lead[]>(`${environment.apiUrl}/leads`, this.httpOptions)
      .pipe(first())
      .subscribe((data) => {
        console.log(data);
        this.leads = data;
        this.loading = false;
      });
  }

  convertLead(id: number): void {
    this.router.navigate([`/convert-lead/${id}`]);
  }
}
