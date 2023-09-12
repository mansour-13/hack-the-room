import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

// The interface needs to be exported, so that it can be imported in other parts of the application
export interface Greeting{
  message: string,
  audience: string
}
@Injectable({
  providedIn: 'root'
})
export class HelloWorldService {
  baseUrl = "http://localhost:8080";
  constructor(private client : HttpClient) { }

  getGreeting() : Observable<Greeting>{
    return this.client.get<Greeting>(this.baseUrl + "/greeting");
  }
}
