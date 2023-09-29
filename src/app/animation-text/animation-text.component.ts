import {Component, Input, OnInit} from '@angular/core';
import {animate, style, transition, trigger} from "@angular/animations";
import {concatMap, delay, of, Subject} from "rxjs";
import {takeUntil} from 'rxjs/operators';
import {AudioService} from "../audio.service";

//This is the import for the JSON file in the assets folder
// import animationTextData from 'src/assets/animationText.json';

@Component({
  selector: 'app-animation-text',
  templateUrl: './animation-text.component.html',
  styleUrls: ['./animation-text.component.css'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({opacity: 0}),
        animate('500ms', style({opacity: 1})),
      ])
    ])
  ]
})
export class AnimationTextComponent implements OnInit {

  @Input() text: string[] | undefined; // This is the input from the parent component
  pauseResume$ = new Subject<boolean>();
  stop$ = new Subject<void>();
  buttonText = 'Read the Story';
  currentBlock = '';
  // currentWordIndex = 0;
  textBlockIndex = 0;
  textBlocks: string[] = [];
  displayText: string = '';
  pauseFlag: boolean = false;

  constructor(private audioService: AudioService) {
  }

  ngOnInit(): void {
    this.audioService.setAudio(new Audio("assets/sounds/intro.mp3"));
    if (this.text) {
      this.textBlocks = [...this.textBlocks, ...this.text];
      console.log(this.textBlocks);
    }
  }

  displayNextBlock() {
    if (this.buttonText === 'Mute') {
      this.audioService.setVolume(0);
      this.buttonText = 'Unmute'
    } else if (this.buttonText === 'Unmute') {
      this.audioService.setVolume(0.5);
      this.buttonText = 'Mute'
    } else {
      this.audioService.play();
      this.buttonText = 'Mute'
      // Reset the display text, toggle this line if you want to keep the previous blocks

      // Check if there are remaining text blocks
      this.displayText = '';

      // Check if there are remaining text blocks
      if (this.textBlockIndex < this.textBlocks.length) {

        // Set the current block
        this.currentBlock = this.textBlocks[this.textBlockIndex];

        // Split the block into words
        const words = this.currentBlock.split(' ');

        // Use RxJS to display words one by one with delay
        of(...words).pipe(
          concatMap(word => of(word).pipe(delay(350))),
          takeUntil(this.stop$)
        ).subscribe({
          next: word => {
            this.displayText += word + ' ';
          },
          complete: () => {
            // After all words are displayed, add a newline for clarity and move to the next block
            this.displayText += '\n';
            this.textBlockIndex++;
          }
        });
      }
    }
  }

  resetText() {
    this.buttonText = 'Read the Story';
    this.audioService.pause();
    this.audioService.setAudio(new Audio("assets/sounds/intro.mp3"));
    this.stop$.next(); // Stop the observable sequence
    this.textBlockIndex = 0;
    this.displayText = '';
  }

  allText() {
    this.audioService.pause();
    this.stop$.next(); // Stop the observable sequence
    this.displayText = this.textBlocks.join('\n');
  }

  ngOnDestroy() {
    // Pause audio when the component is destroyed
    this.audioService.pause();
  }

}
