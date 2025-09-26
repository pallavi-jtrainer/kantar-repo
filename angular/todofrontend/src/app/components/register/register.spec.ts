import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Register } from './register';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { NonNullableFormBuilder, ReactiveFormsModule } from '@angular/forms';
import { of } from 'rxjs';

describe('Register', () => {
  let component: Register;
  let fixture: ComponentFixture<Register>;
  let mockService: jasmine.SpyObj<UserService>;
  let router: Router;
  let fb: NonNullableFormBuilder;

  beforeEach(async () => {
    mockService = jasmine.createSpyObj<UserService>('UserService', ['registerUser']);

    mockService.registerUser.and.returnValue(of({
      id: 3, name: 'something', username: 'something', email: 'some@kk.c', password: 'some123'
    }))
    await TestBed.configureTestingModule({
      imports: [Register, ReactiveFormsModule],
      providers: [
        { provide: UserService, useValue: mockService }
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(Register);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be created with 5 controls', () => {
    expect(component.registerForm?.controls.name).toBeTruthy();
    expect(component.registerForm?.controls.username).toBeTruthy();
    expect(component.registerForm?.controls.email).toBeTruthy();
    expect(component.registerForm?.controls.password).toBeTruthy();
    expect(component.registerForm?.controls.confirmPassword).toBeTruthy();

  });

  it('should create a user', () => {
    component.registerForm?.setValue({
      name: 'something', username: 'something', email: 'some@kk.c', password: 'some123', confirmPassword: 'some123'
    })
    component.register();
    expect(mockService.registerUser).toHaveBeenCalledWith(component.user);
    expect(component.user.name).toEqual('something');
  });
});
