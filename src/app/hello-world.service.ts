import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
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

  getGreetingUser() : Observable<Greeting>{
    return this.client.get<Greeting>(environment.baseUrl + "/greeting-user");
  }

  postLogin(credentials: { username: string, password: string }) : Observable<string> {
      let httpParams = new HttpParams()
        .append("username", credentials.username)
        .append("password", credentials.password);

      let headers = new HttpHeaders()
        .append("Content-Type","application/x-www-form-urlencoded");

      return this.client.post<string>(environment.baseUrl + "/login",{},{
        headers: headers,
        params: httpParams,
        withCredentials: true // needed to that the cookie from the reponse is stored
      });
  }
}
