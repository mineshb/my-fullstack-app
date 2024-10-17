import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { API_CONFIG } from '../api.config';
import { PaginatedResponse } from '../models/paginated-response.model';

const baseUrl = API_CONFIG.users;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  fetchAll(pageNo:number): Observable<PaginatedResponse<User>> {
    return this.http.get<PaginatedResponse<User>>(`${baseUrl}/fetchAll?page=${pageNo-1}`);
  }

  fetch(userName:string): Observable<User> {
    return this.http.get<User>(`${baseUrl}/fetch?userName=${userName}`);
  }

  create(user:User): Observable<any> {
    return this.http.post(`${baseUrl}/create`, user);
  }

  update(userName:string, user: User): Observable<any> {
    console.log(userName)
    return this.http.put(`${baseUrl}/update?userName=${userName}`, user);
  }

  delete(userName:string): Observable<any> {
    return this.http.delete(`${baseUrl}/delete?userName=${userName}`);
  }

  findByKeyword(name:string, pageNo:number): Observable<PaginatedResponse<User>> {
    return this.http.get<PaginatedResponse<User>>(`${baseUrl}/findByKeyword?keyword=${name}&page=${pageNo-1}`);
  }

}
