import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Level, LevelService} from "../level.service";
import {environment} from "../../environments/environment";
import {AudioService} from "../audio.service";
import {User, UserService} from "../user.service";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-actual-game-level',
  templateUrl: './actual-game-level.component.html',
  styleUrls: ['./actual-game-level.component.css']
})
export class ActualGameLevelComponent implements OnInit, OnDestroy {

  levelId: number | undefined;

  levelData?: Level;

  imageUrl: string = "";

  user: User | undefined;

  constructor(
    private route: ActivatedRoute,
    private levelService: LevelService,
    private audioService: AudioService,
    private userService: UserService,
    private authService: AuthService,
    private router: Router
  ) {
  }

  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;
  @ViewChild('audioPlayer2') audioPlayer2!: ElementRef<HTMLAudioElement>;
  buttonText = "Intro";

  ngOnInit(): void {
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (user) => {
        this.user = user;
      });

    const levelIdParam = this.route.snapshot.paramMap.get('levelId');
    this.levelId = levelIdParam ? +levelIdParam : 0;

    if (this.levelId) {
      this.loadLevelData(this.levelId);
    }

    this.audioService.play();
    this.audioService.setVolume(0.3);
  }

  ngOnDestroy() {
    // Pause audio when the component is destroyed
    this.audioService.pause();
  }

  loadLevelData(levelId: number): void {
    this.levelService.indexLevel = levelId.toString();  // Set the levelId in the service
    this.levelService.getLevel().subscribe(
      data => {
        this.levelData = data;
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

  handleTimeout() {
    alert("You didn't solve the level.");
    if (this.user && this.user.life) {
      this.user.life -= 1;
      this.userService.updateUserLife(this.user).subscribe(
        (response) => {
          console.log('User life updated:', response);
        },
        (error) => {
          console.error('Error updating user life:', error);
        }
      );
    }
    this.router.navigate(['/escape-room']);
  }
}
