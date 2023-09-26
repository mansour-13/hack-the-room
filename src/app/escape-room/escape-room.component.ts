import {Component, ViewChild, ElementRef, OnInit, OnDestroy} from '@angular/core';
import {AudioService} from "../audio.service";
//Use import instead of hard coding the intro text
import animationTextData from 'src/assets/animationText.json';
import {User, UserService} from "../user.service";
import {AuthService} from "../auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-escape-room',
  templateUrl: './escape-room.component.html',
  styleUrls: ['./escape-room.component.css']
})
export class EscapeRoomComponent implements OnInit, OnDestroy {

  isStarted: boolean = false;

  user: User | undefined;
  userSubscription?: Subscription;  // Declare a subscription
  buttonText = "Intro";
  selectedLevel: number = 0;

  constructor(private audioService: AudioService,
              private userService: UserService,
              private authService: AuthService) {
  }

  intro: string[] = animationTextData.articles[0].content;

  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;
  @ViewChild('audioPlayer2') audioPlayer2!: ElementRef<HTMLAudioElement>;
  showIntro: boolean = false;

  ngOnInit() {
    this.fetchUserData();
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );

    // Subscribe to user updates
    this.userSubscription = this.userService.user$.subscribe(user => {
      if (user) {
        this.user = user;
      }
    });

    // Subscribe to user updates
    this.userSubscription = this.userService.user$.subscribe(user => {
      if (user) {

        this.user = user;
      }
    });

    this.audioService.play();
    this.audioService.setVolume(0.3);
  }

  fetchUserData(): void {
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (response) => {
        // Update user data in escape-room component
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
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

  startMission() {
    this.isStarted = true;
  }

  displayHeartEmoji(life: number | undefined): string {

    if (life !== undefined) {
      return '❤️'.repeat(life) + '☠️'.repeat(3 - life);
    }
    return '';
  }

  displayStarEmoji(idxActualLearnObject: number | undefined): string {
    if (idxActualLearnObject !== undefined) {
      return '⭐️'.repeat(idxActualLearnObject);
    }
    return '';
  }

  setSelectedLevel(level: number) {
    this.selectedLevel = level;
  }
}

