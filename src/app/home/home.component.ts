import { Component } from '@angular/core';
import {Greeting, GreetingService} from "../greeting.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  greeting ? : Greeting;
  greetingUser ?: Greeting;
  info ? : any;

  constructor(private helloWorldService : GreetingService) {
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

}
