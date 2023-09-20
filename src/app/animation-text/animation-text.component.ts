import {Component, OnInit} from '@angular/core';
import {animate, style, transition, trigger} from "@angular/animations";
import {HttpClient} from "@angular/common/http";
import {concatMap, delay, of} from "rxjs";

import { Input } from '@angular/core';

//This is the import for the JSON file in the assets folder
// import animationTextData from 'src/assets/animationText.json';

@Component({
  selector: 'app-animation-text',
  templateUrl: './animation-text.component.html',
  styleUrls: ['./animation-text.component.css'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('500ms', style({ opacity: 1 })),
      ])
    ])
  ]
})
export class AnimationTextComponent implements OnInit {

  @Input() intro: string[] | undefined; // This is the input from the parent component

  currentBlock = '';
  // currentWordIndex = 0;
  textBlockIndex = 0;

  textBlocks: string[] = [];
  displayText: string = '';

  // This version uses the JSON file through an API call
  // private API_URL = "http://localhost:3000/articles"; // Adjust the URL accordingly

  // constructor(private http: HttpClient) { }

  // This version uses the JSON file through an API call
  // ngOnInit(): void {
  //   this.http.get<any>(this.API_URL).subscribe(data => {
  //     if (data && data.length > 0) {
  //       data.forEach((article: { content: any; }) => {
  //         if (article.content) {
  //           this.textBlocks = [...this.textBlocks, ...article.content];
  //           console.log(this.textBlocks);
  //         }
  //       });
  //     }
  //   });
  // }

  // This version uses the JSON file in the assets folder
  // ngOnInit(): void {
  //   const articles = animationTextData.articles;
  //
  //   if (articles && articles.length > 0) {
  //     articles.forEach((article: { content: any[]; }) => {
  //       if (article.content) {
  //         this.textBlocks = [...this.textBlocks, ...article.content];
  //         console.log(this.textBlocks);
  //       }
  //     });
  //   }
  // }

  // This version takes an input from the parent component
  // ngOnInit(): void {
  //   if (this.textData && this.textData.articles) {
  //     this.textData.articles.forEach((article: { content: any[]; }) => {
  //       if (article.content) {
  //         this.textBlocks = [...this.textBlocks, ...article.content];
  //         console.log(this.textBlocks);
  //       }
  //     });
  //   }
  // }

  ngOnInit(): void {
    if (this.intro) {
      this.textBlocks = [...this.textBlocks, ...this.intro];
      console.log(this.textBlocks);
    }
  }

  displayNextBlock() {

    // Reset the display text, toggle this line if you want to keep the previous blocks
    this.displayText = '';

    // Check if there are remaining text blocks
    if (this.textBlockIndex < this.textBlocks.length) {
      // Set the current block
      this.currentBlock = this.textBlocks[this.textBlockIndex];

      // Split the block into words
      const words = this.currentBlock.split(' ');

      // Use RxJS to display words one by one with delay
      of(...words).pipe(
        concatMap(word => of(word).pipe(delay(200)))
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
