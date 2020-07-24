import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSalesrepComponent } from './create-salesrep.component';

describe('CreateSalesrepComponent', () => {
  let component: CreateSalesrepComponent;
  let fixture: ComponentFixture<CreateSalesrepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateSalesrepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSalesrepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
