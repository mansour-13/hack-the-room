import {Component} from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {UserService} from "../user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  credentials: { username: string, password: string, profileImage: string } = {
    username: '',
    password: '',
    profileImage: ''
  };
  info?: any;
  confirmPassword = '';
  userService?: UserService;

  profileImageW = environment.baseUrl + "/images/spaceCadetW.jpg";
  profileImageM = environment.baseUrl + "/images/cpaseCadetM.jpg";
  profileImageBS = environment.baseUrl + "/images/spaceCadetNB.jpg";

  selectedAvatar?: string;

  constructor(private authService: AuthService, private router: Router,) {

  }

  register() {
    if (this.credentials.password !== this.confirmPassword) {
      this.info = 'Passwords do not match';
      return;
    }

    if (!this.credentials.profileImage) {
      this.info = "Please select an avatar.";
      return;
    }

    this.authService.register(this.credentials).subscribe(
      {
        next: result => {
          this.info = "Registered Successfully";
          setTimeout(() => {
            this.router.navigate(["/login"]);
          }, 500);
        },
        error: err => {
          this.info = err.error;
        }
      }
    );
  }

  selectAvatar(avatar: string) {
    this.credentials.profileImage = avatar;
  }

}
