import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesrepReportComponent } from './salesrep-report.component';

describe('SalesrepReportComponent', () => {
  let component: SalesrepReportComponent;
  let fixture: ComponentFixture<SalesrepReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalesrepReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesrepReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
