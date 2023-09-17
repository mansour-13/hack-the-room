import { Component, OnInit, ElementRef, AfterViewInit } from '@angular/core';
import { AiService } from "../ai-service.service";

// Make sure to declare Ace if TypeScript complains about the missing type.
declare var ace: any;

@Component({
  selector: 'app-ace-editor',
  template: '<div id="editor" style="height: 200px"></div>' +
    '<button (click)="runCode()">Run</button>' +
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
    this.editor.setTheme('ace/theme/monokai');
    this.editor.session.setMode('ace/mode/javascript');
    this.editor.session.setUseWrapMode(true);
    this.editor.renderer.setShowGutter(true);
    this.editor.getSession().setTabSize(4);
    this.editor.getSession().setUseSoftTabs(true);
    this.editor.setValue(`// The encrypted passphrase is hidden within this function.
// Can you decode it and find your way out?
function escapeRoom() {
    let secret = [104, 101, 108, 108, 111, 95, 119, 111, 114, 108, 100];

    // Hint: Start here. The numbers above represent ASCII values. Convert them to characters to reveal the passphrase.
    // TODO: Write a function that decodes the secret and returns the passphrase as a string.

    return "???";  // This should return the decoded passphrase.
}

// Once you've solved the function, run the line below to check if you've found the correct passphrase.
console.log(escapeRoom());  // If correct, this will show the passphrase.
`);

    // let passphrase = secret.map(char => String.fromCharCode(char)).join('');
    // console.log(passphrase);
    // This is the solution to the escape room


  }
  // This version uses the eval() function to execute the code
  // runCode() {
  //   const code = this.editor.getValue();
  //   try {
  //     const result = eval(code); // Capture the return value from eval
  //     this.output = result ? String(result) : "Code executed successfully without a return value.";
  //   } catch (error) {
  //     this.output = "Error executing code: " + error;
  //   }
  // }

  // This version uses chatgpt function to execute the code
  runCode() {
    const code = this.editor.getValue();
    this.aiService.evaluateCode(code).subscribe(response => {
      this.output = response.result;
    });
  }
}

