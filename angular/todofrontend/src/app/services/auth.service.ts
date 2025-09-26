import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = signal<boolean>(false);

  login(username: string, password: string): boolean {
    // dummy login, replace with real API call
    if (username === 'admin' && password === 'password') {
      this.loggedIn.set(true);
      return true;
    } else {
      let dummy = localStorage.getItem("logged");
      if (dummy) {
        this.loggedIn.set(true);
        return true;
      }
    }
    return false;
  }

  logout() {
    this.loggedIn.set(false);
  }

  isLoggedIn(): boolean {
    return this.loggedIn();
  }
}
