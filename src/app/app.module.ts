import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {CookieInterceptor} from "./cookie.interceptor";
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EscapeRoomComponent } from './escape-room/escape-room.component';
import {AudioService} from "./audio.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HighScoreComponent} from "./high-score/high-score.component";
import {AnimationTextComponent} from "./animation-text/animation-text.component";
import { AceEditorComponent } from './ace-editor/ace-editor.component';
import { TimerComponent } from './timer/timer.component';
import { TerminalComponent } from './terminal/terminal.component';
import { LearnObjektComponent } from './learn-objekt/learn-objekt.component';
import { UserComponent } from './user/user.component';
import { ActualGameLevelComponent } from './actual-game-level/actual-game-level.component';

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    EscapeRoomComponent,
    RegisterComponent,
    HighScoreComponent,
    AnimationTextComponent,
    TerminalComponent,
    LearnObjektComponent,
    AceEditorComponent,
    TimerComponent,
    UserComponent,
    ActualGameLevelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CookieInterceptor,
      multi: true,
    },AudioService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
