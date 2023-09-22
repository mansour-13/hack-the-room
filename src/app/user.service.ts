import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import { tap } from 'rxjs/operators'; // Import tap operator
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
export interface Highscore {
  userName: String,
  score : number
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private username?: string;

  // Step 1: Declare a private BehaviorSubject with initial value of null
  private userSubject = new BehaviorSubject<User | null>(null);

  constructor(private client: HttpClient, private authService: AuthService) {
    this.username = authService.getUsername();
  }

  // Step 3: Expose userSubject as public observable for other parts to subscribe
  get user$(): Observable<User | null> {
    return this.userSubject.asObservable();
  }

  getUserByUsername(username: string | undefined): Observable<User> {
    return this.client.get<User>(environment.baseUrl + "/user/" + username);
  }

  getScore():Observable<Highscore[]> {
    return this.client.get<Highscore[]>(environment.baseUrl + "/score");
  }

  updateUserLife(user: User): Observable<User> {
    return this.client.put<User>(environment.baseUrl + "/user/updateLife", user).pipe(
      tap(updatedUser => {
        // Step 2: Emit new user data via the BehaviorSubject
        this.userSubject.next(updatedUser);
      })
    );
  }

}
