import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
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
import { SalesrepReportComponent } from './salesrep-report/salesrep-report.component';
import { ProductReportComponent } from './product-report/product-report.component';
import { IndustryReportComponent } from './industry-report/industry-report.component';
import { CountryReportComponent } from './country-report/country-report.component';
import { CityReportComponent } from './city-report/city-report.component';
import { StatsReportComponent } from './stats-report/stats-report.component';
const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login',
  },
  { path: 'login', component: LoginComponent },
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
