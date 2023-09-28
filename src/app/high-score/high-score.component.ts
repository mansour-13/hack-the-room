import {Component, HostListener, OnInit} from '@angular/core';
import {Highscore, UserService} from "../user.service";

@Component({
  selector: 'app-high-score',
  templateUrl: './high-score.component.html',
  styleUrls: ['./high-score.component.css']
})
export class HighScoreComponent implements OnInit {
  highScores?: Highscore[];
  mouseMoved: boolean = false;
  mouseInactiveTimeout: any;

  constructor(private userService: UserService) {
    this.startMouseInactiveTimer();
  }

  ngOnInit(): void {
    this.loadHighScores();
  }

  audio() {
    const audioButton = new Audio("assets/sounds/tür.mp3")
    audioButton.volume = 0.05;
    audioButton.play();
  }

  private loadHighScores() {
    this.userService.getScore().subscribe(
      result => {
        this.highScores = result;
      },
      (error) => {
        console.error('Error fetching high scores:', error);
      }
    )
  }

  @HostListener('mousemove') onMouseMove() {
    this.mouseMoved = true;
    this.resetMouseInactiveTimer();
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
}


