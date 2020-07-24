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
  selector: 'app-create-salesrep',
  templateUrl: './create-salesrep.component.html',
  styleUrls: ['./create-salesrep.component.scss'],
})
export class CreateSalesrepComponent implements OnInit {
  isValid = false;
  user: User;
  isAdmin: boolean;
  salesRepForm: FormGroup = new FormGroup({
    name: new FormControl(),
    phone: new FormControl(),
    email: new FormControl(),
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
    this.user = this.authenticationService.userValue;
    this.isAdmin =
      this.user.roles.find((role) => role.role === 'ROLE_ADMIN') !== undefined;
    console.log(this.isAdmin);
    if (
      this.authenticationService.userValue.roles.find(
        (role) => role.role === 'ROLE_ADMIN'
      ) === undefined
    ) {
      this.router.navigate(['/create']);
    }
    this.salesRepForm = this.formBuilder.group({
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
    });

    this.salesRepForm.valueChanges.subscribe((input) => {
      this.isValid = !this.salesRepForm.invalid;
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.salesRepForm.controls;
  }

  onSubmit() {
    console.log('saving');
    const newSalesRep = {
      informationContact: {
        name: this.f.name.value,
        phoneNumber: this.f.phone.value,
        email: this.f.email.value,
      },
    };
    this.http
      .post<SalesRep>(
        `${environment.apiUrl}/SalesRep/Create`,
        newSalesRep,
        this.httpOptions
      )
      .subscribe((data) => {
        this.toastr.success(
          '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Account created!',
          '',
          {
            timeOut: 2000,
            enableHtml: true,
            toastClass: 'alert alert-success alert-with-icon',
            positionClass: 'toast-top-center',
          }
        );
        console.log(data), this.router.navigate([this.returnUrl]);
      }, console.log);
  }
  goToRoute(route: string) {
    this.router.navigate([route]);
  }
}
