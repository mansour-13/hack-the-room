import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Level, LevelService} from "../level.service";
import {AudioService} from "../audio.service";

@Component({
  selector: 'app-escape-room2',
  templateUrl: './escape-room2.component.html',
  styleUrls: ['./escape-room2.component.css']
})
export class EscapeRoom2Component implements OnInit, OnDestroy{

  // This variable is for the background image, currently saved in frontend/src/assets/pictures
  // Might be better to save it into the component, even though that could be redundant
  backgroundImage = 'assets/pictures/academy.jpg';

  // This variable saves the data from the GET request
  level?: Level;


  // This boolean is for the animation-text component, so that after the text clicked through, the next event can happen
  textCompleted: boolean = false;
  onTextCompleted() {
    this.textCompleted = true;
  }



  constructor(private levelService: LevelService, private audioService: AudioService) {
    this.levelService.indexLevel = "1";
  }

  @ViewChild('audioPlayer') audioPlayer!: ElementRef<HTMLAudioElement>;
  @ViewChild('audioPlayer2') audioPlayer2!: ElementRef<HTMLAudioElement>;
  buttonText= "Intro";


  load() {
    this.levelService.getLevel().subscribe(
      result => this.level = result
    );
  }

  ngOnInit(): void {
    this.load();
    this.audioService.play();
    this.audioService.setVolume(0.3);
  }

  ngOnDestroy() {
    // Pause audio when the component is destroyed
    this.audioService.pause();
  }


// Audio part
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

}
