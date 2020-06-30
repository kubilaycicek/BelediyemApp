import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTypeAddComponent } from './userType-add.component';

describe('CategoryAddComponent', () => {
  let component: UserTypeAddComponent;
  let fixture: ComponentFixture<UserTypeAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserTypeAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserTypeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
