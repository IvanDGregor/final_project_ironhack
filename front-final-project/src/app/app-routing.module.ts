import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LeadListComponent } from './lead-list/lead-list.component';
import { OpportunityListComponent } from './opportunity-list/opportunity-list.component';
import { CreateFormComponent } from './create-form/create-form.component';
import { LoginComponent } from './login/login.component';
import { ContactListComponent } from './contact-list/contact-list.component';
import { AccountListComponent } from './account-list/account-list.component';
import { ConvertLeadComponent } from './convert-lead/convert-lead.component';
import { CreateLeadComponent } from './create-lead/create-lead.component';
import { CreateSalesrepComponent } from './create-salesrep/create-salesrep.component';
import { AuthGuard } from './_helpers';
import { ProductReportComponent } from './product-report/product-report.component';
import { IndustryReportComponent } from './industry-report/industry-report.component';
import { CountryReportComponent } from './country-report/country-report.component';
import { CityReportComponent } from './city-report/city-report.component';
import { StatsReportComponent } from './stats-report/stats-report.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { SalesrepReportComponent } from './salesrep-report/salesrep-report.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { CreateCreditCardComponent } from './create-credit-card/create-credit-card.component';
import { CreditCardListComponent } from './credit-card-list/credit-card-list.component';
import { TrasanctionsListComponent } from './trasanctions-list/trasanctions-list.component';
import { OperationsComponent } from './operations/operations.component';
import { CreateTransferComponent } from './create-transfer/create-transfer.component';
const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login',
  },
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard',
    component: DashboardComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'leads',
    component: LeadListComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'convert-lead/:id',
    component: ConvertLeadComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'opportunities',
    component: OpportunityListComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'contacts',
    component: ContactListComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'accounts',
    component: AccountListComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'credit-cards',
    component: CreditCardListComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'transactions',
    component: TrasanctionsListComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'operations',
    component: OperationsComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'create',
    component: CreateFormComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'create-lead',
    component: CreateLeadComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'create-user',
    component: CreateUserComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'create-transfer',
    component: CreateTransferComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'create-account',
    component: CreateAccountComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'create-credit-card',
    component: CreateCreditCardComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'create-salesrep',
    component: CreateSalesrepComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'report/salesrep',
    component: SalesrepReportComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'report/product',
    component: ProductReportComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'report/industry',
    component: IndustryReportComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'report/country',
    component: CountryReportComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'report/city',
    component: CityReportComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'report/stats',
    component: StatsReportComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
