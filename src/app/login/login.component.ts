import {Component} from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

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
  private isLoading: boolean = false;

  constructor(private authService: AuthService, private router: Router) {
  }

  sendLogin() {
    this.authService.postLogin(this.credentials).subscribe(
      {
        next: result => {
          this.info = "✅ Logged-In";
          this.isLoading = false;
          this.router.navigate(["/"]);
        },
        error: err => {
          this.info = err;
          this.isLoading = false;
        }
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
