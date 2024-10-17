import { Component } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {


  user: User = {
    userName: 'ng-user-001',
    firstName: 'ng-001',
    lastName: 'user-001',
    email: 'ng-user-001@hotmail.com'
  };
  submitted = false;

  constructor(private userService: UserService) { }

  saveUser(): void {
    const data = {
      userName: this.user.userName,
      email: this.user.email,
      firstName: this.user.firstName,
      lastName: this.user.lastName
    };

    this.userService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newUser(): void {
    this.submitted = false;
    this.user = {
      userName: '',
    firstName: '',
    lastName: '',
    email: ''
    };
  }
}

