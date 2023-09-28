import {Component, HostListener, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {User, UserService} from "../user.service";
import {AudioService} from "../audio.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info ?: any;
  username: string | undefined;
  user: User | undefined;
  mouseMoved: boolean = false;
  mouseInactiveTimeout: any;

  constructor(private authService: AuthService,
              private audioService: AudioService,
              private userService: UserService) {
    this.username = authService.getUsername();
    this.startMouseInactiveTimer();
  }

  ngOnInit(): void {
    this.audioService.setAudio(new Audio("assets/sounds/darcSpaceAmbiente.wav"));
    this.audioService.switschLoop(true);
    this.audioService.play();
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (user) => {
        this.user = user;
      });
  }

  ngOnDestroy() {
    // Pause audio when the component is destroyed
    this.audioService.pause();
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

  audio() {
    const audioButton = new Audio("assets/sounds/tür.mp3")
    audioButton.volume = 0.02;
    audioButton.play();
  }

  startMouseInactiveTimer() {
    this.mouseInactiveTimeout = setInterval(() => {
      this.mouseMoved = false;
    }, 800);
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
