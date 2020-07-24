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
import { Transfer } from '../models/transfer';

@Component({
  selector: 'app-create-transfer',
  templateUrl: './create-transfer.component.html',
  styleUrls: ['./create-transfer.component.scss']
})
export class CreateTransferComponent implements OnInit {
  isValid = false;
  user: User;
  isAdmin: boolean;
  transferForm: FormGroup = new FormGroup({
    accountSenderId: new FormControl(),
    accountReceiverId: new FormControl(),
    amount: new FormControl(),
    secret_key: new FormControl(),
  });
  loading = false;
  returnUrl = '/operations';
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
    this.transferForm = this.formBuilder.group({
      accountSenderId: ['', Validators.required],
      accountReceiverId: ['', Validators.required],
      amount: ['', Validators.required],
      secret_key: ['', Validators.required],
    });

    this.transferForm.valueChanges.subscribe((input) => {
      this.isValid = !this.transferForm.invalid;
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.transferForm.controls;
  }

  onSubmit() {
    console.log('saving');
    const newTransfer = {
      accountSenderId: this.f.accountSenderId.value,
      accountReceiverId: this.f.accountReceiverId.value,
      amount: this.f.amount.value,
      secret_key: this.f.secret_key.value,
    };
    this.http
      .put<Transfer>(
        `${environment.apiUrl}/transaction/transfer`,
        newTransfer,
        this.httpOptions
      )
      .subscribe((data) => {
        this.toastr.success(
          '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Transfer Done!',
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

