import {Component, ViewChild, ElementRef, OnInit, OnDestroy} from '@angular/core';
import {AudioService} from "../audio.service";
//Use import instead of hard coding the intro text
import animationTextData from 'src/assets/animationText.json';
import {User, UserService} from "../user.service";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-escape-room',
  templateUrl: './escape-room.component.html',
  styleUrls: ['./escape-room.component.css']
})
export class EscapeRoomComponent implements OnInit, OnDestroy {

  isStarted: boolean = false;

  constructor(private audioService: AudioService,
              private userService: UserService,
              private authService: AuthService) {
  }

  intro: string[] = animationTextData.articles[0].content;

  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;
  @ViewChild('audioPlayer2') audioPlayer2!: ElementRef<HTMLAudioElement>;
  buttonText = "Intro";
  user: User | undefined;
  showIntro: boolean = false;

  ngOnInit() {
    const username = this.authService.getUsername();
    this.userService.getUserByUsername(username).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
    this.audioService.play();
    this.audioService.setVolume(0.3);
  }

  ngOnDestroy() {
    // Pause audio when the component is destroyed
    this.audioService.pause();
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

}

