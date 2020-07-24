import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IndustryReportComponent } from './industry-report.component';

describe('IndustryReportComponent', () => {
  let component: IndustryReportComponent;
  let fixture: ComponentFixture<IndustryReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IndustryReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IndustryReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
