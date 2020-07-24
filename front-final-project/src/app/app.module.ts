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
import { LeadListComponent } from './lead-list/lead-list.component';
import { OpportunityListComponent } from './opportunity-list/opportunity-list.component';
import { CreateFormComponent } from './create-form/create-form.component';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';
import { ContactListComponent } from './contact-list/contact-list.component';
import { AccountListComponent } from './account-list/account-list.component';
import { BasicAuthInterceptor, ErrorInterceptor } from './_helpers';
import { ConvertLeadComponent } from './convert-lead/convert-lead.component';
import { CreateLeadComponent } from './create-lead/create-lead.component';
import { CreateSalesrepComponent } from './create-salesrep/create-salesrep.component';
import { SalesrepReportComponent } from './salesrep-report/salesrep-report.component';
import { CountryReportComponent } from './country-report/country-report.component';
import { CityReportComponent } from './city-report/city-report.component';
import { IndustryReportComponent } from './industry-report/industry-report.component';
import { ProductReportComponent } from './product-report/product-report.component';
import { StatsReportComponent } from './stats-report/stats-report.component';
import { ToastrModule } from 'ngx-toastr';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    LeadListComponent,
    OpportunityListComponent,
    CreateFormComponent,
    LoginComponent,
    LayoutComponent,
    ContactListComponent,
    AccountListComponent,
    ConvertLeadComponent,
    CreateLeadComponent,
    CreateSalesrepComponent,
    SalesrepReportComponent,
    CountryReportComponent,
    CityReportComponent,
    IndustryReportComponent,
    ProductReportComponent,
    StatsReportComponent,
    DashboardComponent,
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
