import {Component} from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";

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

  profileImageW = environment.baseUrl +"/images/spaceCadetW.jpeg";
  profileImageM = environment.baseUrl +"/images/spaceCadetM.jpeg";
  profileImageBS = environment.baseUrl +"/images/spaceCadetNB.jpeg";

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
          }, 1000);
        },
        error: err => {
          this.info = err.error;
        }
      }
    );
  }
}
