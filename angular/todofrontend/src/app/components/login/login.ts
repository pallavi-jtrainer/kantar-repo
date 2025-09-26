import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import { User } from '../../models/User';

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
  userService = inject(UserService);

  user?: User;

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
        this.router.navigate(['list/' + 0]);
      }
    } else {
      this.userService.getUserByUsername(this.username)
        .subscribe({
          next: (data) => {
            this.user = data;
            console.log(this.user.name);

            if (!this.user || this.user !== undefined) {
              if (this.password === this.user.password) {
                localStorage.setItem("logged", "true");
                let info = this.authService.login(this.username, this.password);
                if (info) {
                  this.router.navigate(['list/' + this.user.id]);
                }
              }
            }
          }, error: (err) => {
            console.log("Error: " + err);
          }
        })

      // alert('Invalid credentials');
    }
  }

  forRegistration() {
    this.router.navigate(['/register']);
  }
}
