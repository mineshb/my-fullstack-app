import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrl: './users-list.component.css'
})
export class UsersListComponent implements OnInit {
  users: User[] = [];
  currentUser: User = {
    userName: '',
    email: '',
    firstName: '',
    lastName: ''
  };
  currentIndex: number = -1;
  name: string = '';
  pageNo: number = 1;
  itemsPerPage: number = 10;
  totalElements: number = 0;
  totalPages: number = 0;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  retrieveUsers(): void {
    this.userService.fetchAll(this.pageNo) 
      .subscribe({
        next: (data) => {
          console.log(data);
          this.users = data?.content as User[] || [];         
          this.totalElements = data?.totalElements || 0;
          this.totalPages = data?.totalPages || 0;
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

    this.userService.findByKeyword(this.name, this.pageNo)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.users = data?.content as User[] || [];          
          this.totalElements = data?.totalElements || 0;
          this.totalPages = data?.totalPages || 0;
        },
        error: (e) => console.error(e)
      });
  }

  handlePageChange(event:any) {
    this.pageNo = event;
    if(this.name != "")  {
      console.log("Search by Keyword, Page No = " + this.pageNo)
      this.searchName();
    } else {
      console.log("Regular Refresh, Page No = " + this.pageNo)
      this.retrieveUsers();
    }
  }

}
