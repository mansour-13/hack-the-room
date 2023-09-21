import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Level, LevelService} from "../level.service";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-actual-game-level',
  templateUrl: './actual-game-level.component.html',
  styleUrls: ['./actual-game-level.component.css']
})
export class ActualGameLevelComponent implements OnInit{
  levelId: number | undefined;
  levelData?: Level;
  imageUrl: string = "";

  constructor(
    private route: ActivatedRoute,
    private levelService: LevelService
  ) {}

  ngOnInit(): void {
    const levelIdParam = this.route.snapshot.paramMap.get('levelId');
    this.levelId = levelIdParam ? +levelIdParam : 0;

    if (this.levelId) {
      this.loadLevelData(this.levelId);
    }
  }

  loadLevelData(levelId: number): void {
    this.levelService.indexLevel = levelId.toString();  // Set the levelId in the service
    this.levelService.getLevel().subscribe(
      data =>
      {
      this.levelData = data;
        this.imageUrl = environment.baseUrl + this.levelData.image;
    });
  }

}
