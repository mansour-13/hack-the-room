import {Component, OnInit} from '@angular/core';
import {Greeting, GreetingService} from "../greeting.service";
import {LoginComponent} from "../login/login.component";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  greeting ?: Greeting;
  greetingUser ?: Greeting;
  info ?: any;
  username: string | undefined;

  constructor(private helloWorldService: GreetingService, private authService: AuthService) {
    this.username = authService.getUsername();
  }

  isLoggedIn(): boolean {
    return this.authService.getIsLoggedIn();
  }


  getGreeting() {
    this.helloWorldService.getGreeting().subscribe(
      result => this.greeting = result
    );
  }

  getGreetingUser() {
    this.helloWorldService.getGreetingUser().subscribe(
      {
        next: result => this.greetingUser = result,
        error: err => this.info = err
      }
    );
  }

  sendLogout() {
    this.authService.postLogout().subscribe(
      {
        next: result => {
          this.info = "🚪 Logged-Out!";
          this.authService.logout();
        },

        error: err => {
          this.info = err;
        }
      }
    );
  }



}
