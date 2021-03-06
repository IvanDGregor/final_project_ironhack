import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CreateFormComponent } from './create-form/create-form.component';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';
import { AccountListComponent } from './account-list/account-list.component';
import { BasicAuthInterceptor, ErrorInterceptor } from './_helpers';
import { ToastrModule } from 'ngx-toastr';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { TrasanctionsListComponent } from './trasanctions-list/trasanctions-list.component';
import { CreateCreditCardComponent } from './create-credit-card/create-credit-card.component';
import { CreditCardListComponent } from './credit-card-list/credit-card-list.component';
import { OperationsComponent } from './operations/operations.component';
import { CreateTransferComponent } from './create-transfer/create-transfer.component';
import { UserSidebarComponent } from './user-sidebar/user-sidebar.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UsersListComponent } from './users-list/users-list.component';
import { UserAccountsComponent } from './user-accounts/user-accounts.component';
import { UserTransactionsComponent } from './user-transactions/user-transactions.component';
import { UserCreditCardsComponent } from './user-credit-cards/user-credit-cards.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    CreateFormComponent,
    LoginComponent,
    LayoutComponent,
    AccountListComponent,
    DashboardComponent,
    CreateUserComponent,
    CreateAccountComponent,
    TrasanctionsListComponent,
    CreateCreditCardComponent,
    CreditCardListComponent,
    OperationsComponent,
    CreateTransferComponent,
    UserSidebarComponent,
    UserDashboardComponent,
    UsersListComponent,
    UserAccountsComponent,
    UserTransactionsComponent,
    UserCreditCardsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
