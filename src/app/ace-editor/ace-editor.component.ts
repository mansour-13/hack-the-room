import { Component, OnInit, ElementRef, AfterViewInit } from '@angular/core';
import { AiService } from "../ai-service.service";

// Make sure to declare Ace if TypeScript complains about the missing type.
declare var ace: any;

@Component({
  selector: 'app-ace-editor',
  template: '<div id="editor" style="height: 200px"></div>' +
    '<button (click)="runCode()">Run</button>' +
    '<button (click)="getHint()">Hint</button>' +

    '<div id="output">{{output}}</div>',

  styleUrls: ['./ace-editor.component.css']
})
export class AceEditorComponent implements AfterViewInit {

  private editor: any;
  output: string = ""; // To store the output of the code

  // Use this constructor if you are not using the AI service
  // constructor() { }

  constructor(private aiService: AiService) { }

  ngAfterViewInit() {
    this.editor = ace.edit('editor');
    this.editor.setTheme('ace/theme/ambiance');
    this.editor.session.setMode('ace/mode/javascript');
    this.editor.session.setUseWrapMode(true);
    this.editor.renderer.setShowGutter(true);
    this.editor.getSession().setTabSize(4);
    this.editor.getSession().setUseSoftTabs(true);
    this.editor.setValue(`
    // Hint: Start here. The numbers above represent ASCII values. Convert them to characters to reveal the passphrase.
    // The encrypted passphrase is hidden within this function.
// Can you decode it and find your way out?
function escapeRoom() {
    let secret = [104, 101, 108, 108, 111, 95, 119, 111, 114, 108, 100];


    // TODO: Write a function that decodes the secret and returns the passphrase as a string.

    return "???";  // This should return the decoded passphrase.
}

// Once you've solved the function, run the line below to check if you've found the correct passphrase.
console.log(escapeRoom());  // If correct, this will show the passphrase.
`);



  }


  // This version uses chatgpt function to execute the code
  runCode() {
    const code = this.editor.getValue();
    this.aiService.evaluateCode(code).subscribe(response => {
      this.output = response.result;
    });
  }
  getHint() {
    const code = this.editor.getValue();
    this.aiService.produceAHint(code).subscribe(response => {
      this.output = response.result;
    });
  }
}
