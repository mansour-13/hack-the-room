import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  audio() {
    const audioButton = new Audio("assets/sounds/tür.mp3")
    audioButton.volume= 0.05;
    audioButton.play();
  }
}
