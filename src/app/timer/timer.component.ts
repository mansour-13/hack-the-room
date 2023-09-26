import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.css']
})
export class TimerComponent implements OnInit, OnDestroy {

  @Input()
  duration: number = 60;
  minutes: number = 0;
  seconds: number = 0;
  private intervalID: any;

  // Emit an event to the parent component
  @Output() timeOut: EventEmitter<void> = new EventEmitter();

  ngOnInit(): void {
    this.minutes = Math.floor(this.duration / 60);
    this.seconds = this.duration % 60;
    this.startTimer();
  }

  ngOnDestroy(): void {
    this.stopTimer();
  }


  private startTimer() {
    this.intervalID = setInterval(() => {
      if (this.minutes > 0 || this.seconds > 0) {
        if (this.seconds === 0) {
          this.minutes--;
          this.seconds = 59;
        } else {
          this.seconds--;
        }
      } else {
        this.stopTimer();
        // Emit an event to the parent component
        this.timeOut.emit();
      }
    }, 1000);
  }

  private stopTimer() {
    if (this.intervalID) {
      clearInterval(this.intervalID);
    }
  }

  public getRemainingTime(): number {
    return this.minutes * 60 + this.seconds;
  }
}
