import {Component} from '@angular/core';
import {AuthService} from "../auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials: { username: string, password: string } = {
    username: '',
    password: ''
  };
  info?: any;

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) {
  }

  sendLogin() {
    this.authService.postLogin(this.credentials).subscribe(
      {
        next: result => {
          this.info = "✅ Logged-In";
          this.authService.login();
          this.router.navigate(["/"]);
        },
        error: err => {
          this.info = "Invalid Username or Password";
        }
      }
    );
  }

}
