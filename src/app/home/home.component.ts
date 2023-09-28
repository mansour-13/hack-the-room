import {Component, OnInit} from '@angular/core';
import {Greeting} from "../greeting.service";
import {AuthService} from "../auth.service";
import {User, UserService} from "../user.service";
import {AudioService} from "../audio.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  greeting ?: Greeting;
  greetingUser ?: Greeting;
  info ?: any;
  username: string | undefined;
  user: User | undefined;

  constructor(private authService: AuthService,
              private audioService: AudioService,
              private userService: UserService) {
    this.username = authService.getUsername();
  }

  isLoggedIn(): boolean {
    return this.authService.getIsLoggedIn();
  }

  sendLogout() {
    this.authService.postLogout().subscribe(
      {
        next: result => {
          this.info = "🚪 Logged-Out!";
          this.authService.logout();
        },

        error: err => {
          this.info = err;
        }
      }
    );
  }

  ngOnInit(): void {
    this.audioService.setAudio(new Audio("assets/sounds/background.mp3"));
    this.audioService.play();
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (user) => {
        this.user = user;
      });
  }

  audio() {
    const audioButton = new Audio("assets/sounds/tür.mp3")
    audioButton.volume = 0.05;
    audioButton.play();
  }

  ngOnDestroy() {
    // Pause audio when the component is destroyed
    this.audioService.pause();
  }
}
