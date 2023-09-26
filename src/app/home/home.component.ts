import {Component, OnInit} from '@angular/core';
import {Greeting, GreetingService} from "../greeting.service";
import {LoginComponent} from "../login/login.component";
import {AuthService} from "../auth.service";
import {User, UserService} from "../user.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  greeting ?: Greeting;
  greetingUser ?: Greeting;
  info ?: any;
  username: string | undefined;
  user: User | undefined;

  constructor(private helloWorldService: GreetingService,
              private authService: AuthService,
              private userService: UserService) {
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

  ngOnInit(): void {
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (user) => {
        this.user = user;
      });
  }



}
