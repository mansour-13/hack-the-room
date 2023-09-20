import {Component, OnInit} from '@angular/core';
import {Level, LevelService} from "../level.service";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-learn-objekt',
  templateUrl: './learn-objekt.component.html',
  styleUrls: ['./learn-objekt.component.css']
})
export class LearnObjektComponent implements OnInit {
  level?: Level;
  imageUrl: string = "";

  constructor(private levelService: LevelService) {
    this.levelService.indexLevel = "1";
  }

  load() {
    this.levelService.getLevel().subscribe(
      result =>
        {this.level = result;
            this.imageUrl = environment.baseUrl + this.level.image;
      }
    );


  }

  ngOnInit(): void {
    this.load();

  }

  protected readonly environment = environment;
}
