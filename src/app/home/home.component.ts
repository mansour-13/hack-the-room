import { Component } from '@angular/core';
import {Greeting, HelloWorldService} from "../hello-world.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  greeting ? : Greeting;

  constructor(private helloWorldService : HelloWorldService) {
  }


  getGreeting() {
    this.helloWorldService.getGreeting().subscribe(
      result => this.greeting = result
    );
  }
}
