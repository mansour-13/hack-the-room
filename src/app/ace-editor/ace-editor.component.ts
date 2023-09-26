import {Component, OnInit, ElementRef, AfterViewInit, Input, EventEmitter, Output} from '@angular/core';
import { AiService } from "../ai-service.service";
import {UserService} from "../user.service";
import { Router } from '@angular/router';

// Make sure to declare Ace if TypeScript complains about the missing type.
declare var ace: any;

@Component({
  selector: 'app-ace-editor',
  template: '<div id="editor" style="height: 400px; width: auto"></div>' +
    '<button (click)="compareSolutionToUser()">Run</button>' +
    '<button (click)="getHint()">Hint</button>' +
    '<button (click)="getSolution()">Solution</button>' +
    '<button (click)="resetChallenge()">Reset</button>' +

    '<div id="output">{{output}}</div>',

  styleUrls: ['./ace-editor.component.css']
})
export class AceEditorComponent implements AfterViewInit {

  @Input() codeChallenge: string | undefined;
  @Input() codeSolution: string | undefined;
  @Output() codeChallengeSolved: EventEmitter<void> = new EventEmitter<void>();


  private editor: any;
  output: string = ""; // To store the output of the code

  // Use this constructor if you are not using the AI service
  // constructor() { }

  constructor(private aiService: AiService, private userService: UserService, private router: Router) { }

  ngAfterViewInit() {
    this.editor = ace.edit('editor');
    this.editor.setTheme('ace/theme/ambiance');
    this.editor.session.setMode('ace/mode/javascript');
    this.editor.session.setUseWrapMode(true);
    this.editor.renderer.setShowGutter(true);
    this.editor.getSession().setTabSize(4);
    this.editor.getSession().setUseSoftTabs(true);
    this.editor.setValue(this.codeChallenge);
  }

  resetChallenge() {
    this.editor.setValue(this.codeChallenge);
  }

  // This version uses chatgpt function to execute the code
  runCode() {
    const code = this.editor.getValue();
    this.aiService.evaluateCode(code).subscribe(response => {
      this.output = response.result;
    });
  }
    compareSolutionToUser() {
    const code = this.editor.getValue();
    console.log(this.editor.getValue());

    // starting to improve the api requests, by including some checks
      if (!code.trim()) { // Check if the code is empty or just whitespace
        this.output = "Please enter some code before running.";
        return;
      }

      this.aiService.getBinaryAnswerToCode(code, this.codeChallenge, this.codeSolution).subscribe(response => {
        this.output = response.result.toString(); // Convert the boolean to a string

        if (response.result === true) { // Check if the result is true
          this.codeChallengeSolved.emit();
        }

      });
  }

  getHint() {
    const code = this.editor.getValue();
    this.aiService.produceAHint(code).subscribe(response => {
      this.output = response.result;
    });
  }

  getSolution() {
    this.aiService.getSolutionToChallenge(this.codeChallenge).subscribe(response => {
      this.output = response.result;
    });
  }

  replaceSpecialCharsWithASCII(str: string): string {
    // Specify an array of special characters you wish to replace
    const specialChars = ['+'];

    return str.split('').map((char) => {
      if (specialChars.includes(char)) {
        return char.charCodeAt(0).toString();
      }
      return char;
    }).join('');
  }

  replacePlusWithPlaceholder(str: string): string {
    return str.replace(/\+/g, '%2B');
  }

  replacePlaceholderWithPlus(str: string): string {
    return str.replace(/__PLUS__/g, '+');
  }

  // Here with a replacement for all special characters
  // runCode() {
  //   let code = this.editor.getValue();
  //   code = this.replaceSpecialCharsWithASCII(code);
  //   this.aiService.evaluateCode(code).subscribe(response => {
  //     this.output = response.result;
  //   });
  // }

}
