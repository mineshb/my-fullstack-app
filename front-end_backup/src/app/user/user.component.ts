import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from './user.model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  users: User[] = [];
  selectedUser: User = { userName: '', email: '', firstName: '', lastName: '' }; // Initial empty user

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

  addUser(): void {
    this.userService.createUser(this.selectedUser).subscribe(() => {
      this.loadUsers();
      this.resetForm();
    });
  }

  updateUser(): void {
    this.userService.updateUser(this.selectedUser).subscribe(() => {
      this.loadUsers();
      this.resetForm();
    });
  }

  deleteUser(id: number): void {
    this.userService.deleteUser(id).subscribe(() => {
      this.loadUsers();
    });
  }

  selectUser(user: User): void {
    this.selectedUser = { ...user };
  }

  resetForm(): void {
    this.selectedUser = { userName: '', email: '', firstName: '', lastName: '' };
  }
}
