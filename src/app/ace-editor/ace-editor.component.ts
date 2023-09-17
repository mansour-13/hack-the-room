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
    this.editor.setValue(`function foo(items) {
      var x = "All this is syntax highlighted";
      return x;
    }`);
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
      this.output = response;
    });
  }
}

