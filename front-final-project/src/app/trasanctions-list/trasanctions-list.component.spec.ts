import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrasanctionsListComponent } from './trasanctions-list.component';

describe('TrasanctionsListComponent', () => {
  let component: TrasanctionsListComponent;
  let fixture: ComponentFixture<TrasanctionsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrasanctionsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrasanctionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
