import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CountryReportComponent } from './country-report.component';

describe('CountryReportComponent', () => {
  let component: CountryReportComponent;
  let fixture: ComponentFixture<CountryReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CountryReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CountryReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
