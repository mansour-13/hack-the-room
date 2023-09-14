import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {EscapeRoomComponent} from "./escape-room/escape-room.component";

const routes: Routes = [
  {path : "", component: HomeComponent},
  {path : "about", component: AboutComponent},
  {path : "login", component: LoginComponent},
  {path : "register", component: RegisterComponent},
  {path:'escape-room', component:EscapeRoomComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
