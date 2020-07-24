import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../_services';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { first } from 'rxjs/operators';
import { SalesRep } from '../models/salesrep';
import { Lead } from '../models/lead';
import { InformationContact } from '../models/informationContact';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { User } from '../_models';


@Component({
  selector: 'app-create-lead',
  templateUrl: './create-lead.component.html',
  styleUrls: ['./create-lead.component.scss'],
})
export class CreateLeadComponent implements OnInit {
  isValid = false;
  user: User;
  isAdmin: boolean;
  leadForm: FormGroup = new FormGroup({
    name: new FormControl(),
    phone: new FormControl(),
    email: new FormControl(),
    company: new FormControl(),
    salesrep: new FormControl(),
  });
  salesreps: SalesRep[] = [];
  loading = false;
  returnUrl = '/leads';
  error = '';
  constructor(
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
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
    this.leadForm = this.formBuilder.group({
      name: ['', Validators.required],
      phone: [
        '',
        [Validators.required, Validators.pattern('^(6|7|9){1}([0-9]){8}$')],
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          ),
        ],
      ],
      company: ['', [Validators.required, Validators.pattern('[a-zA-Z]*')]],
      salesrep: ['', Validators.required],
    });

    this.leadForm.valueChanges.subscribe((input) => {
      this.isValid = !this.leadForm.invalid;
    });

    this.http.get<SalesRep[]>(`${environment.apiUrl}/SalesReps`).subscribe(
      (data) => {
        this.salesreps = data;
        console.log(data);
      },
      (error) => console.log(error)
    );
    this.user = this.authenticationService.userValue;
    this.isAdmin =
      this.user.roles.find((role) => role.role === 'ROLE_ADMIN') !== undefined;
    console.log(this.isAdmin);
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.leadForm.controls;
  }

  onSubmit() {
    const newLead = {
      informationContact: {
        name: this.f.name.value,
        phoneNumber: this.f.phone.value,
        email: this.f.email.value,
      },
      salesRepId: this.f.salesrep.value,
      company: this.f.company.value,
    };
    this.http
      .post<Lead>(`${environment.apiUrl}/lead`, newLead, this.httpOptions)
      .subscribe(
        (data) => {
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Lead created!',
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-success alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
          console.log(data), this.router.navigate([this.returnUrl]);
        },
        (error) => {
          this.toastr.error(
            `<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> ${error}`,
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-danger alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
        }
      );
  }

}
