import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AudioService {

  private _audio: HTMLAudioElement =new Audio("assets/sounds/intro1.mp3");


  play() {
    this._audio.play();
  }

  switschLoop(onOf: boolean = false): void{
    this._audio.loop = onOf;
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
