import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AudioService} from "../audio.service";
//Use import instead of hard coding the intro text
import animationTextData from 'src/assets/animationText.json';
import {User, UserService} from "../user.service";
import {AuthService} from "../auth.service";
import {Subscription} from "rxjs";
import {Level, LevelService} from "../level.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-escape-room',
  templateUrl: './escape-room.component.html',
  styleUrls: ['./escape-room.component.css']
})
export class EscapeRoomComponent implements OnInit, OnDestroy {

  user?: User;
  userSubscription?: Subscription;  // Declare a subscription
  buttonText = "Intro";
  showTooltip: boolean = false;
  levels: Level[] = [];


  constructor(private audioService: AudioService,
              private userService: UserService,
              private authService: AuthService,
              private levelService: LevelService,
              private router: Router) {
  }

  intro: string[] = animationTextData.articles[0].content;

  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;



  ngOnInit() {
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (user) => {
        this.user = user;
        console.log('Component initialized.');
        console.log(this.user?.life);
        if (this.user && this.user?.life === 0) {
          console.log('this.user?.lif');
          alert("Deine Javascript-Kenntnisse reichten leider nicht aus um im Weltall zu überleben!")
          this.router.navigate(['/about']);
        }
      });

    this.audioService.play();
    this.audioService.setVolume(0.3);

    for (let i = 1; i <= 6; i++) {
      this.levelService.indexLevel = i.toString();
      this.levelService.getLevel().subscribe(
        (level) => {
          this.levels.push(level);
        },
        (error) => {
          console.error('Error fetching level data', error);
        }
      );
    }
  }


  ngOnDestroy() {
    // Pause audio when the component is destroyed
    this.audioService.pause();

    // Unsubscribe from user$ to prevent memory leaks
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
  }

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

}

