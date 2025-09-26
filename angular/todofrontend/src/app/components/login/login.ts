import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  username: string = '';
  password: string = '';

  router = inject(Router);
  authService = inject(AuthService);

  onLogin() {
    // this.authService.login(this.username, this.password);
    // if (this.authService.isLoggedIn()) {
    //   this.router.navigate(['/list']);
    // } else {
    //   alert('Invalid credentials');
    // }

    if (this.username === 'admin' && this.password === 'password') {
      console.log('Login successful');
      let info = this.authService.login(this.username, this.password);
      console.log("auth service info: " + info);

      if (info) {
        this.router.navigate(['list']);
      }
    } else {
      alert('Invalid credentials');
    }
  }

}
