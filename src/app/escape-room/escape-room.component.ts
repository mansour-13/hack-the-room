import {Component, ViewChild, ElementRef, OnInit, OnDestroy} from '@angular/core';
import {AudioService} from "../audio.service";
//Use import instead of hard coding the intro text
import animationTextData from 'src/assets/animationText.json';
import {UserService} from "../user.service";

@Component({
  selector: 'app-escape-room',
  templateUrl: './escape-room.component.html',
  styleUrls: ['./escape-room.component.css']
})
export class EscapeRoomComponent implements OnInit, OnDestroy  {

  isStarted: boolean = false;
  constructor(private audioService: AudioService, private userService: UserService) {}
  // intro: string = "\"Willkommen an Bord, Kadett!\"\n" +
  //   "\n" +
  //   "Die Türen der Raumstation gleiten geräuschlos auf und gewähren dir Zugang zu einem Universum voller Abenteuer und Entdeckungen. Hier beginnt deine Reise, dein Training, und deine Mission.\n" +
  //   "\n" +
  //   "\"Du hast den ersten Schritt gemacht, um Teil einer Elite zu werden, die das Unbekannte erforscht und das Unmögliche möglich macht.\"\n" +
  //   "\n" +
  //   "Deine Fähigkeiten und dein Wissen werden hier auf die Probe gestellt, während du dich auf eine Reise durch die Sterne begibst, die dich an die Grenzen deiner Vorstellungskraft führen wird.\n" +
  //   "\n" +
  //   "\"In den kommenden Tagen, Wochen und Monaten wirst du nicht nur die Geheimnisse des Universums entschlüsseln, sondern auch die tiefsten Teile deines eigenen Potenzials erforschen.\"\n" +
  //   "\n" +
  //   "Die Raumstation wird zu deinem Zuhause, die Crew wird zu deiner Familie, und das Universum wird zu deinem Spielplatz.\n" +
  //   "\n" +
  //   "\"Du bist bereit für diese Herausforderung. Du bist bereit für das Abenteuer deines Lebens. Willkommen an Bord, Kadett!\"\n" +
  //   "\n" +
  //   "Der Himmel ist nicht länger die Grenze. Es ist nur der Anfang.";

  intro: string[] = animationTextData.articles[0].content;

  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;
  @ViewChild('audioPlayer2') audioPlayer2!: ElementRef<HTMLAudioElement>;
  buttonText= "Intro";

  ngOnInit() {
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
    } else if(audio.played){
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
}
