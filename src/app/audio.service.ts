import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AudioService {
  private audio: HTMLAudioElement =new Audio("assets/sounds/background.mp3");

  play() {
    this.audio.play();
  }

  pause() {
    this.audio.pause();
  }
  setVolume(volume: number) {
    if (volume >= 0 && volume <= 1) {
      this.audio.volume = volume; // Set the volume between 0 and 1
    } else {
      console.error('Volume must be a value between 0 and 1.');
    }
  }

}
