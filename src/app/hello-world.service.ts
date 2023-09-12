import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { environment } from './../environments/environment';


// The interface needs to be exported, so that it can be imported in other parts of the application
export interface Greeting{
  message: string,
  audience: string
}
@Injectable({
  providedIn: 'root'
})
export class HelloWorldService {
  constructor(private client : HttpClient) { }

  getGreeting() : Observable<Greeting>{
    return this.client.get<Greeting>(environment.baseUrl + "/greeting");
  }
}
