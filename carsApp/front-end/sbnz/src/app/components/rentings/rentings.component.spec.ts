import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RentingsComponent } from './rentings.component';

describe('RentingsComponent', () => {
  let component: RentingsComponent;
  let fixture: ComponentFixture<RentingsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RentingsComponent]
    });
    fixture = TestBed.createComponent(RentingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
