import {Component, OnInit} from '@angular/core';
import {User, UserService} from "../user.service";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user?: User;

  constructor(private userService: UserService,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }
  computeScore(timeLimit: number, neededTime: number): number{
    if(neededTime > 10){
      return Math.round(((timeLimit-neededTime)*1000/6)/timeLimit-10);
    }
    return 100;
}

    addScore(score: number, idxLearnObject: number) {
      this.userService.setScoreForLearnObjekt(score,idxLearnObject);
}
}
