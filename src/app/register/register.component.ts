import {Component} from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  credentials: { username: string, password: string } = {
    username: '',
    password: ''
  };
  info?: any;
  confirmPassword = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  register() {
    if (this.credentials.password !== this.confirmPassword) {
      this.info = 'Passwords do not match';
      return;
    }
    this.authService.register(this.credentials).subscribe(
      {
        next: result => {
          this.info = "Registered Successfully";
          setTimeout(() => {
            this.router.navigate(["/login"]);
          }, 3000);
        },
        error: err => {
          this.info = err.error;
        }
      }
    );
  }
}
