import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AudioService {
  private _audio: HTMLAudioElement =new Audio("assets/sounds/background.mp3");
  play() {
    this._audio.play();
  }

  setAudio(value: HTMLAudioElement) {
    this._audio = value;
  }

  pause() {
    this._audio.pause();
  }
  setVolume(volume: number) {
    if (volume >= 0 && volume <= 1) {
      this._audio.volume = volume; // Set the volume between 0 and 1
    } else {
      console.error('Volume must be a value between 0 and 1.');
    }
  }
}
