import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {EscapeRoomComponent} from "./escape-room/escape-room.component";
import {HighScoreComponent} from "./high-score/high-score.component";
// import {ValidationJSComponent} from "./validation-js/validation-js.component";
import {AnimationTextComponent} from "./animation-text/animation-text.component";
import {AceEditorComponent} from "./ace-editor/ace-editor.component";

const routes: Routes = [
  {path : "", component: HomeComponent},
  {path : "about", component: AboutComponent},
  {path : "login", component: LoginComponent},
  {path:'escape-room', component:EscapeRoomComponent},
  {path:'high-score', component: HighScoreComponent},
  // {path: 'validation', component: ValidationJSComponent},
  {path: 'animation', component: AnimationTextComponent},
  {path : "register", component: RegisterComponent},
  {path : "ace-editor", component: AceEditorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
