import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrl: './users-list.component.css'
})
export class UsersListComponent implements OnInit {
  users?: User[];
  currentUser: User = {
    userName: '',
    email: '',
    firstName: '',
    lastName: ''
  };
  currentIndex = -1;
  name = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  retrieveUsers(): void {
    this.userService.fetchAll() 
      .subscribe({
        next: (data) => {
          this.users = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveUsers();
    this.currentUser = {
      userName: '',
      email: '',
      firstName: '',
      lastName: ''
    };
    this.currentIndex = -1;
  }

  setActiveUser(user: User, index: number): void {
    this.currentUser = user;
    this.currentIndex = index;
  }

  searchName(): void {
    this.currentUser = {
      userName: '',
      email: '',
      firstName: '',
      lastName: ''
    };
    this.currentIndex = -1;

    this.userService.findByName(this.name)
      .subscribe({
        next: (data) => {
          this.users = Array.isArray(data) ? data : [data];;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

}
