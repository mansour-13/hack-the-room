import { Component, OnInit } from '@angular/core';
import * as ace from 'ace-builds';

@Component({
  selector: 'app-terminal',
  templateUrl: './terminal.component.html',
  styleUrls: ['./terminal.component.css']
})
export class TerminalComponent implements OnInit {
  eingabeText: string = "";
  ausgabeText: string = "";
  constructor() { }

  ngOnInit(): void {
  }

  analyseText() {
    this.ausgabeText = eval(this.eingabeText);
  }
}
