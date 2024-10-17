import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user.model'; // Create this model based on your User properties

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/users'; // Change to your API endpoint

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl+"/fetchAll");
  }

  getUserByName(userName: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/fetch/${userName}`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${user.userName}`, user);
  }

  deleteUser(userName: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${userName}`);
  }
}