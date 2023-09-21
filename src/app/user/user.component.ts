import {Component, OnInit} from '@angular/core';
import {User, UserService} from "../user.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user?: User;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    const username = 'user1';
    this.userService.getUserByUsername(username).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }


    addScore() {
      this.userService.setScoreForLearnObjekt(500,1);
}
}
