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
import { Role } from '../models/role';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {
  isValid = false;
  user: User;
  isAdmin: boolean;
  userForm: FormGroup = new FormGroup({
    userId: new FormControl(),
    username: new FormControl(),
    surname: new FormControl(),
    password: new FormControl(),
    date_birth: new FormControl(),
    role: new FormControl(),
  });
  salesreps: SalesRep[] = [];
  loading = false;
  returnUrl = '/contacts';
  error = '';
  userRole: Role = Role.User;
  adminRole: Role = Role.Admin;
  public filterDateFrom: Date;
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
    this.userForm = this.formBuilder.group({
      userId: ['', Validators.required],
      username: ['', Validators.required],
      surname: ['', Validators.required],
      password: ['', Validators.required],
      date_birth: ['', Validators.required],
      role: ['', Validators.required],
    });

    this.userForm.valueChanges.subscribe((input) => {
      this.isValid = !this.userForm.invalid;
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.userForm.controls;
  }

  onSubmit() {
    console.log('saving');
    const newUser = {
        userId: this.f.userId.value,
        username: this.f.username.value,
        surname: this.f.surname.value,
        password: this.f.password.value,
        date_birth: this.f.date_birth.value,
        role: this.f.role.value,
    };
    this.http
      .post<User>(
        `${environment.apiUrl}/user`,
        newUser,
        this.httpOptions
      )
      .subscribe((data) => {
        this.toastr.success(
          '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> User created!',
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
  dateChanged(eventDate: string): Date | null {
    return !!eventDate ? new Date(eventDate) : null;
  }
}
