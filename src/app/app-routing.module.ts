import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {HighScoreComponent} from "./high-score/high-score.component";
// import {ValidationJSComponent} from "./validation-js/validation-js.component";
import {AnimationTextComponent} from "./animation-text/animation-text.component";

const routes: Routes = [
  {path : "", component: HomeComponent},
  {path : "about", component: AboutComponent},
  {path : "login", component: LoginComponent},
  {path:'highscore', component: HighScoreComponent},
  // {path: 'validation', component: ValidationJSComponent},
  {path: 'animation', component: AnimationTextComponent},
  {path : "register", component: RegisterComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
