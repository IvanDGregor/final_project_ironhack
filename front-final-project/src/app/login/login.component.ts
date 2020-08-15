import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { AuthenticationService } from '../_services';
import { first } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';
import { User } from '../_models';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
  });
  invalidLogin = false;
  error = '';
  loading = false;
  returnUrlAdmin = '/dashboard';
  returnUrlUser = '/user-dashboard';
  disabled = false;
  user: User;

  constructor(
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    if (this.authenticationService.userValue && this.user.roles.find((role) => role.role === 'ROLE_ADMIN') !== undefined) {
      this.router.navigate(['/dashboard']);
    }
    else if (this.authenticationService.userValue && this.user.roles.find((role) => role.role === 'ROLE_USER') !== undefined){
      this.router.navigate(['/user-dashboard']);
    }
    // tslint:disable-next-line: member-ordering
    const signUpButton = document.getElementById('signUp');
    // tslint:disable-next-line: member-ordering
    const signInButton = document.getElementById('signIn');
    // tslint:disable-next-line: member-ordering
    const container = document.getElementById('container');
    // tslint:disable-next-line: semicolon
    signUpButton.addEventListener('click', () => {
      container.classList.add('right-panel-active');
      this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required],
      });
    });

    signInButton.addEventListener('click', () => {
      container.classList.remove('right-panel-active');
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.disabled = true;
    if (this.loginForm.invalid) {
      this.disabled = false;
      return;
    }
    this.loading = true;
    this.authenticationService
      .login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        (data) => {
          this.user = this.authenticationService.userValue;
          if(this.user.roles.find((role) => role.role === 'ROLE_USER')){
            console.log('user:' + this.user);
            this.router.navigate([this.returnUrlUser]);
          }
          else if(this.user.roles.find((role) => role.role === 'ROLE_ADMIN')){
            this.router.navigate([this.returnUrlAdmin]);
          }
          this.toastr.success(
            '<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Welcome to <b>GLOBAL BANK</b> - app.',
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-success alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
        },
        (error) => {
          this.error = error;
          this.toastr.error(
            `<span class="tim-icons icon-bell-55" [data-notify]="icon"></span> Invalid Username or Password combination`,
            '',
            {
              timeOut: 2000,
              enableHtml: true,
              toastClass: 'alert alert-danger alert-with-icon',
              positionClass: 'toast-top-center',
            }
          );
          this.disabled = false;
          this.loading = false;
        }
      );
  }
}
