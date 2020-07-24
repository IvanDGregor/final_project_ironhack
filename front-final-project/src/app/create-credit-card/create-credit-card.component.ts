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
import { CreditCard } from '../models/creditCard';

@Component({
  selector: 'app-create-credit-card',
  templateUrl: './create-credit-card.component.html',
  styleUrls: ['./create-credit-card.component.scss']
})
export class CreateCreditCardComponent implements OnInit {
  isValid = false;
  user: User;
  isAdmin: boolean;
  creditCardForm: FormGroup = new FormGroup({
    id: new FormControl(),
    pin: new FormControl(),
    userId: new FormControl(),
    status: new FormControl(),
  });
  loading = false;
  returnUrl = '/credit-cards';
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
    this.creditCardForm = this.formBuilder.group({
      id: ['', Validators.required],
      pin: ['', Validators.required],
      userId: ['', Validators.required],
      status: ['', Validators.required],
    });

    this.creditCardForm.valueChanges.subscribe((input) => {
      this.isValid = !this.creditCardForm.invalid;
    });

    this.user = this.authenticationService.userValue;
    this.isAdmin =
      this.user.roles.find((role) => role.role === 'ROLE_ADMIN') !== undefined;
    console.log(this.isAdmin);
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.creditCardForm.controls;
  }

  onSubmit() {
    const newCreditCard = {
      id: this.f.id.value,
      pin: this.f.pin.value,
      userId: this.f.userId.value,
      status: this.f.status.value,
    };
    this.http
      .post<CreditCard>(`${environment.apiUrl}/credit-card`, newCreditCard, this.httpOptions)
      .subscribe(
        (data) => {
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Credit Card created!',
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
