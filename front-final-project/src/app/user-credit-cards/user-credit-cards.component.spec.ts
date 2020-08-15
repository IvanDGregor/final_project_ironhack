import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCreditCardsComponent } from './user-credit-cards.component';

describe('UserCreditCardsComponent', () => {
  let component: UserCreditCardsComponent;
  let fixture: ComponentFixture<UserCreditCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCreditCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCreditCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
