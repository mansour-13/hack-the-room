import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-high-score',
  templateUrl: './high-score.component.html',
  styleUrls: ['./high-score.component.css']
})
export class HighScoreComponent implements OnInit {
  private readonly API_URL = "http://localhost:3000/highscores";

  highScores?: HighScoreDTO[];

  constructor(private service: HttpClient) { }

  ngOnInit(): void {
    this.loadHighScores();
  }

  private loadHighScores() {
    this.service.get<HighScoreDTO[]>(this.API_URL).subscribe(
      result => {
        /* Carl explain this this */
        result.sort((a, b) => this.sortHighScores(a, b));
        this.highScores = result;
      },
      (error) => {
        console.error('Error fetching high scores:', error);
      }
    )
  }

  private sortHighScores(a: HighScoreDTO, b: HighScoreDTO): number {
    if (a.score === b.score) {
      // If scores are equal, sort by time in ascending order
      return this.timeToSeconds(a.time) - this.timeToSeconds(b.time);
    }
    // Sort by score in descending order
    return b.score - a.score;
  }

  // Helper function to convert MM-SS format to total seconds
  private timeToSeconds(time: string): number {
    const [minutes, seconds] = time.split('-').map(Number);
    return minutes * 60 + seconds;
  }

}

interface HighScoreDTO {
  id: number;
  username: string;
  score: number;
  time: string;
}
