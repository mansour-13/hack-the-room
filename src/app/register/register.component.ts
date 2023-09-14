import { Component } from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

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
  private isLoading: boolean = false;

  constructor(private authService : AuthService, private router: Router) {
  }

  register() {
    this.authService.register(this.credentials).subscribe(
      {
        next: result => {
          this.info = "✅ Registered Successfully";
          this.isLoading = false;
          setTimeout(() => {
            this.router.navigate(["/login"]);}, 3000);
          },
        error: err => {
          this.info = err;
          this.isLoading = false;
        }
      }
    );
  }
}
