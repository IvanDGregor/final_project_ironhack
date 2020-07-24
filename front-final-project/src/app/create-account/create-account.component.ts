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
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { User } from '../_models';
import { Status } from '../models/status';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss']
})
export class CreateAccountComponent implements OnInit {
  isValid = false;
  user: User;
  isAdmin: boolean;
  accountForm: FormGroup = new FormGroup({
    id: new FormControl(),
    secretKey: new FormControl(),
    userId: new FormControl(),
    balance: new FormControl(),
    status: new FormControl(),
  });
  loading = false;
  returnUrl = '/accounts';
  error = '';
  frozenStatus: Status = Status.Frozen;
  activeStatus: Status = Status.Active;
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
    this.accountForm = this.formBuilder.group({
      id: ['', Validators.required],
      secretKey: ['', Validators.required],
      userId: ['', Validators.required],
      balance: ['', Validators.required],
      status: ['', Validators.required],
    });

    this.accountForm.valueChanges.subscribe((input) => {
      this.isValid = !this.accountForm.invalid;
    });

    this.user = this.authenticationService.userValue;
    this.isAdmin =
      this.user.roles.find((role) => role.role === 'ROLE_ADMIN') !== undefined;
    console.log(this.isAdmin);
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.accountForm.controls;
  }

  onSubmit() {
    const newAccount = {
      id: this.f.id.value,
      secretKey: this.f.secretKey.value,
      userId: this.f.userId.value,
      balance: this.f.balance.value,
      status: this.f.status.value,
    };
    this.http
      .post<Account>(`${environment.apiUrl}/account`, newAccount, this.httpOptions)
      .subscribe(
        (data) => {
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Acount created!',
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
