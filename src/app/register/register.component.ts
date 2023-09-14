import { Component } from '@angular/core';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  credentials : { username: string, password: string } = {
    username: '',
    password: ''
  };
  info?: any;

  constructor(private authService : AuthService) {
  }

  register() {
    this.authService.register(this.credentials).subscribe(
      {
        next: result => this.info = "✅ Registered", // TODO Here you could do a redirect to the home page
        error: err => this.info = err
      }
    );
  }
}
