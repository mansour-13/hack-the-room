import { Component } from '@angular/core';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials : { username: string, password: string } = {
    username: '',
    password: ''
  };
  info?: any;

  constructor(private authService : AuthService) {
  }

  sendLogin() {
    this.authService.postLogin(this.credentials).subscribe(
      {
        next: result => this.info = "✅ Logged-In", // TODO Here you could do a redirect to the home page
        error: err => this.info = err
      }
    );
  }

  sendLogout() {
    this.authService.postLogout().subscribe(
      {
        next: result => this.info = "🚪 Logged-Out!", // TODO Here you could do a redirect to the home page
        error: err => this.info = err
      }
    );
  }

}
