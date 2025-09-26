import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { AbstractControl, Form, FormBuilder, FormControl, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../models/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  private userService = inject(UserService);
  private fb = inject(NonNullableFormBuilder);
  private router = inject(Router);

  user: User = {
    name: '',
    username: '',
    email: '',
    password: ''
  };

  registerForm?: FormGroup<{
    name: FormControl<string>;
    username: FormControl<string>;
    email: FormControl<string>;
    password: FormControl<string>;
    confirmPassword: FormControl<string>;
  }>;

  ngOnInit() {
    this.registerForm = new FormGroup({
      name: this.fb.control('', [Validators.required, Validators.minLength(5)]),
      username: this.fb.control('', [Validators.required, Validators.minLength(5), Validators.maxLength(15)]),
      email: this.fb.control('', [Validators.required, Validators.email]),
      password: this.fb.control('', [Validators.required, Validators.minLength(6), Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$')]),
      confirmPassword: this.fb.control('', [Validators.required])
    }, { validators: Register.passwordMatchValidator });
  }

  // Custom validator
  static passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const group = control as FormGroup;

    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;

    return password && confirmPassword && password !== confirmPassword ? { mismatch: true } : null;
  }

  register() {

    if (this.registerForm && this.registerForm.valid) {
      // this.user.name = this.registerForm.value.name ?? '';
      // this.user.username = this.registerForm.value.username ?? '';
      // this.user.email = this.registerForm.value.email ?? '';
      // this.user.password = this.registerForm.value.password ?? '';
      const formValue = this.registerForm.getRawValue();
      this.user = {
        name: formValue.name,
        username: formValue.username,
        email: formValue.email,
        password: formValue.password
      }

      // console.log("User: " + this.user.name);

      if (!this.user.name || !this.user.username || !this.user.email || !this.user.password) {
        console.error('All fields are required.');
        return;
      }

      this.userService.registerUser(this.user).subscribe({
        next: (data) => {
          console.log('User registered successfully:', data);
        },
        error: (error) => {
          console.error('Error registering user:', error);
        }
      });

      this.router.navigate(['/login'])
    }
  }
}
