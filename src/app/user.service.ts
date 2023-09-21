import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {AuthService} from "./auth.service";

export interface User {
  id: number;
  userName: string;
  password: string;
  active: boolean;
  roles: string;
  idxActualLearnObject: number;
  life: number;
  score: number;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private username?: string;

  constructor(private client: HttpClient, private authService: AuthService) {
    this.username = authService.getUsername();
  }

  getUserByUsername(username: string | undefined): Observable<User> {
    return this.client.get<User>(environment.baseUrl + "/user/" + username);
  }
}