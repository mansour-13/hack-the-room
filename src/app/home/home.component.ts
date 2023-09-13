import { Component } from '@angular/core';
import {Greeting, HelloWorldService} from "../hello-world.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  greeting ? : Greeting;
  greetingUser ?: Greeting;
  info ? : any;
  credentials : { username: string, password: string } = {
    username: '',
    password: ''
  };

  constructor(private helloWorldService : HelloWorldService) {
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

  sendLogin() {
    this.helloWorldService.postLogin(this.credentials).subscribe(
      {
        next: result => this.info = "✅ Logged-In",
        error: err => this.info = err
      }
    );
  }
}
