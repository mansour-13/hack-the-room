import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualGameLevelComponent } from './actual-game-level.component';

describe('ActualGameLevelComponent', () => {
  let component: ActualGameLevelComponent;
  let fixture: ComponentFixture<ActualGameLevelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActualGameLevelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActualGameLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
