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
  levelScore: number[]
}
export interface Highscore {
  userName: String,
  score : number,
  levelScore: number[]
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private username?: string;

  constructor(private client: HttpClient, private authService: AuthService) {
    this.username = authService.getUsername();
  }

  getUserByUsername(username: string): Observable<User> {
    return this.client.get<User>(environment.baseUrl + "/user/" + username);
  }

  getScore():Observable<Highscore[]> {
    return this.client.get<Highscore[]>(environment.baseUrl + "/score");
  }

  setScoreForLearnObjekt(score: number, idxLearnObject: number) {
    const requestBody = {
      username: this.username,
      id_learnObject: idxLearnObject,
      score: score,
    };

    this.client.post(environment.baseUrl + "/score/"+this.username+"/"+idxLearnObject +"/"+score,requestBody);
  }
}
