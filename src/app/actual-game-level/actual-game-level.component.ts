import {Component, ElementRef, NgZone, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Level, LevelService} from "../level.service";
import {environment} from "../../environments/environment";
import {AudioService} from "../audio.service";
import {User, UserService} from "../user.service";
import {AuthService} from "../auth.service";
import {TimerComponent} from "../timer/timer.component";

@Component({
  selector: 'app-actual-game-level',
  templateUrl: './actual-game-level.component.html',
  styleUrls: ['./actual-game-level.component.css']
})
export class ActualGameLevelComponent implements OnInit, OnDestroy {

  levelId?: number;

  levelData?: Level;

  imageUrl: string = "";

  user?: User;

  size: number = 0;

  constructor(
    private route: ActivatedRoute,
    private levelService: LevelService,
    private audioService: AudioService,
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private zone:NgZone
  ) {}

  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;
  @ViewChild('audioPlayer2') audioPlayer2!: ElementRef<HTMLAudioElement>;
  @ViewChild(TimerComponent) timerComponent!: TimerComponent;
  buttonText= "Intro";

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
    const levelIdParam = this.route.snapshot.paramMap.get('levelId');
    this.levelId = levelIdParam ? +levelIdParam : 0;

    if (this.levelId) {
      this.loadLevelData(this.levelId);
    }

    this.audioService.play();
    this.audioService.setVolume(0.3);
    console.log('Level Id:', this.levelId);
  }

  ngOnDestroy() {
    // Pause audio when the component is destroyed
    this.audioService.pause();
  }

  loadLevelData(levelId: number): void {
    this.levelService.indexLevel = levelId.toString();  // Set the levelId in the service
    this.levelService.getLevel().subscribe(
      data =>
      {
      this.levelData = data;
      this.size = this.levelData.story.length;
      this.imageUrl = environment.baseUrl + this.levelData.image;
    });
  }

  // Audio part
  playAudio() {
    const audio: HTMLAudioElement = this.audioPlayer.nativeElement;
    if (audio.paused) {
      // If audio is paused, play it
      audio.play();
      this.buttonText = 'Pause';
    } else if (audio.played) {
      // If audio is playing, pause it
      audio.pause();
      this.buttonText = 'Intro';
    }
  }

  audioEnded() {
    this.buttonText = 'Intro'
  }

  handleTimeout(text: string = "You didn't solve the level.") {
    alert(text);
    if (this.user && this.user.life) {
      this.user.life -= 1;

      this.userService.updateUserLife(this.user).subscribe(
          (response) => {
            console.log('User life updated:', response);
            this.router.navigate(['/escape-room']);
          },
          (error) => {
            console.error('Error updating user life:', error);
            this.router.navigate(['/escape-room']);
          }
        );
    }
    // this.router.navigate(['/escape-room']);
  }

  handleCodeChallengeSuccess() {
    const remainingTime = this.timerComponent.getRemainingTime();
    console.log('Remaining time:', remainingTime);
    console.log('Set Time:', this.levelData?.timeLimit);

    if (this.user && this.user.life > 0 && this.levelData) {
      const score = this.computeScore(this.levelData.timeLimit, remainingTime);
      console.log('Score:', score);
      this.zone.run(() => {
        // @ts-ignore
        this.addScore(score, this.user.idxActualLearnObject - 1)
      });
    }

    alert("You solved the level!");

    if (this.user && this.user.idxActualLearnObject <= this.levelId!) {
      this.user.idxActualLearnObject += 1;
      // Szenario: User hat alle Level gelöst
      if (this.user.idxActualLearnObject > 6) {
        alert("Glückwunsch, du hast alle Level gelöst!");
        this.router.navigate(['/high-score']);
        return;
      }
      this.userService.updateUserIdxActualLearnObject(this.user).subscribe(
        (response) => {
          console.log('User idxActualLearnObject updated:', response);
        },
        (error) => {
          console.error('Error updating user idxActualLearnObject:', error);
        }
      );
    }
    this.router.navigate(['/escape-room']);
  }
  // previous computeScore Method
  // computeScore(timeLimit: number, neededTime: number): number {
  //
  //   if (neededTime > 10) {
  //     return Math.round(((timeLimit - neededTime) * 1000 / 6) / timeLimit - 10);
  //   }
  //   return 100;
  // }

  computeScore(timeLimit: number, timeRemaining: number): number {

    console.log('Time limit:', timeLimit);
    console.log('Time remaining:', timeRemaining);

    let timeTaken = timeLimit - timeRemaining;

    console.log('Time taken:', timeTaken);

    // If time taken is 10 seconds or less, award maximum points
    if (timeTaken <= 10) {
      return 100;
    }

    // Otherwise, calculate the score proportionally to the time taken
    let score = 100 * (timeRemaining / timeLimit);

    // Ensure the score isn't more than 100 or less than 0
    score = Math.min(100, score);
    score = Math.max(0, score);

    console.log('Score:', score);

    // Round the score to the nearest integer
    return Math.round(score);

  }

  addScore(score: number, idxLearnObject: number) {
    this.userService.setScoreForLearnObjekt(score,idxLearnObject, this.user?.userName);
  }

  getLevelScore(): number {

    console.log('Fetching score for levelId:', this.levelId);
    if (this.user && this.user.levelScore) {
      const score = this.user.levelScore[this.levelId! - 1];
      console.log('Score:', score);

      return score;
    }
    console.log("undefined");
    return 0;
  }





}

