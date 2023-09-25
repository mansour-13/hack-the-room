import {Component, Input, OnInit} from '@angular/core';
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


  displayHeartEmoji(life: number | undefined): string {
    if (life !== undefined) {
      return '❤️'.repeat(life)+ '☠️'.repeat(3 - life);
    }
    return '';
  }

  displayStarEmoji(idxActualLearnObject: number | undefined): string {
    if (idxActualLearnObject !== undefined) {
      return '⭐️'.repeat(idxActualLearnObject);
    }
    return '';
  }

}
