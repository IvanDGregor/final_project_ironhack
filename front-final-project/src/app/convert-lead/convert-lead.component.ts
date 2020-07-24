import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Lead } from '../models/lead';
import { ActivatedRoute, Router } from '@angular/router';
import {
  FormGroup,
  FormBuilder,
  FormControl,
  Validators,
} from '@angular/forms';
import { ProductType } from '../models/productType';
import { AuthenticationService } from '../_services';
import { IndustryType } from '../models/industryType';
import { SalesRep } from '../models/salesrep';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-convert-lead',
  templateUrl: './convert-lead.component.html',
  styleUrls: ['./convert-lead.component.scss'],
})
export class ConvertLeadComponent implements OnInit {
  username: 'admin';
  password: 'admin';

  convertLeadForm: FormGroup = new FormGroup({
    productType: new FormControl(),
    quantity: new FormControl(),
    salesrep: new FormControl(),
    accountId: new FormControl(),
    industryType: new FormControl(),
    employeeCount: new FormControl(),
    city: new FormControl(),
    country: new FormControl(),
  });

  isShow = false;

  //Product enums

  hybrid_truck: ProductType = ProductType.Hybrid_Truck;
  flatbed_truck: ProductType = ProductType.Flatbed_Truck;
  box_truck: ProductType = ProductType.Box_Truck;

  //industry enums
  produce: IndustryType = IndustryType.Produce;
  ecommerce: IndustryType = IndustryType.Ecommerce;
  mannufacturing: IndustryType = IndustryType.Mannufacturing;
  medical: IndustryType = IndustryType.Medical;
  other: IndustryType = IndustryType.Other;

  productType: ProductType;

  returnUrl = '/leads';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.authenticationService.userValue.authdata}`,
    }),
  };
  lead: Lead;
  id: string;
  accounts: Account[];
  constructor(
    private authenticationService: AuthenticationService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    this.http
      .get<Lead>(`${environment.apiUrl}/lead/id/${this.id}`)
      .subscribe((data) => (this.lead = data));
    this.http
      .get<Account[]>(`${environment.apiUrl}/accounts`)
      .subscribe((account) => {
        this.accounts = account;
        console.log(account);
      });

    this.convertLeadForm = this.formBuilder.group({
      productType: ['', Validators.required],
      quantity: ['', Validators.required],
      salesrep: ['', Validators.required],
      accountId: [''],
      industryType: [''],
      employeeCount: [''],
      city: [''],
      country: [''],
    });
    /*this.convertLeadForm.controls['existingAccount'].valueChanges.subscribe(value => {

      this.toggleDisplay();
    });*/
  }
  // convenience getter for easy access to form fields
  get f() {
    return this.convertLeadForm.controls;
  }
  onSubmit() {
    console.log('saving');

    //do only one route get all data from AccountID or from form
    //OR check if other values are null and then send one route or the other depending on that
    if (this.isShow) {
      const convertLeadNoAcc = {
        productType: this.f.productType.value,
        quantity: this.f.quantity.value,
        industryType: this.f.industryType.value,
        employeeCount: this.f.employeeCount.value,
        city: this.f.city.value,
        country: this.f.country.value,
      };
      console.log(this.lead.id);
      this.http
        .put<Lead>(
          `${environment.apiUrl}/lead/convert/new_account/${this.lead.id}`,
          convertLeadNoAcc,
          this.httpOptions
        )
        .subscribe((data) => {
          console.log(data), this.router.navigate([this.returnUrl]);
        }, console.log);
    } else {
      const convertLeadAcc = {
        productType: this.f.productType.value,
        quantity: this.f.quantity.value,
        accountId: this.f.accountId.value,
      };
      console.log(this.lead.id);
      this.http
        .put<Lead>(
          `${environment.apiUrl}/lead/convert/existing_account/${this.lead.id}`,
          convertLeadAcc,
          this.httpOptions
        )
        .subscribe((data) => {
          console.log(data), this.router.navigate([this.returnUrl]);
        }, console.log);
    }
  }

  /*toggleDisplay(acc: boolean) {

    this.isShow = acc;
    console.log( this.isShow);
  }*/
  toggleDisplay() {
    this.isShow = !this.isShow;
    console.log(this.isShow);
  }
}
