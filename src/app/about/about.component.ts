import {Component, HostListener} from '@angular/core';
import {User, UserService} from "../user.service";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  user?: User;
  mouseMoved: boolean = false;
  mouseInactiveTimeout: any;

  constructor(private userService: UserService,
              private router: Router,
              private authService: AuthService) {
    this.startMouseInactiveTimer();
  }

  audio() {
    const audioButton = new Audio("assets/sounds/tür.mp3")
    audioButton.volume = 0.05;
    audioButton.play();
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

  fillLife(): void {
    if (this.user && this.user.life) {
      this.user.life = 3;
      this.userService.updateUserLife(this.user).subscribe(
        (response) => {
          console.log('User life updated:', response);
          this.router.navigate(['/escape-room']);
        },
        (error) => {
          console.error('Error updating user life:', error);
        }
      );
    }

  }

  startMouseInactiveTimer() {
    this.mouseInactiveTimeout = setInterval(() => {
      if (!this.mouseMoved) {
        // Maus war inaktiv, führen Sie Ihre Aktionen aus
      }
      this.mouseMoved = false; // Zurücksetzen auf inaktiv
    }, 1000); // Hier können Sie die Zeit in Millisekunden anpassen
  }

  resetMouseInactiveTimer() {
    clearTimeout(this.mouseInactiveTimeout);
    this.startMouseInactiveTimer();
  }

  @HostListener('mousemove') onMouseMove() {
    this.mouseMoved = true;
    this.resetMouseInactiveTimer();
  }

}
