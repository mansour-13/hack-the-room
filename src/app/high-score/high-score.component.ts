import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Highscore, User, UserService} from "../user.service";

@Component({
  selector: 'app-high-score',
  templateUrl: './high-score.component.html',
  styleUrls: ['./high-score.component.css']
})
export class HighScoreComponent implements OnInit {
  highScores?: Highscore[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadHighScores();
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
}


