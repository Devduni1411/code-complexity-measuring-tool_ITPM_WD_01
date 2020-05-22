import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CoreUrlService} from '../url-service/core-url.service';
import {User} from '../../model/user';

@Injectable({
  providedIn: 'root',
})
export class UserApiService {

  USER_URL = `${this.coreUrlService.BASE_URL}/user`;
  USERS_URL = `${this.coreUrlService.BASE_URL}/users`;

  constructor(private coreUrlService: CoreUrlService, private  http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.USERS_URL);
  }

  getUsersById(id: number): Observable<User> {
    return this.http.get<User>(this.USERS_URL + '/' + id);
  }

  postNewUser(user: User): Observable<User> {
    return this.http.post<User>(this.USERS_URL, user);
  }

  deleteUser(id: number): any {
    return this.http.delete(this.USER_URL + '/' + id);
  }

  putUser(user: User): Observable<User> {
    return this.http.put<User>(this.USERS_URL + '/' + user.id, user);
  }

  getLoggedInUser(): Observable<User> {
    return this.http.get<User>(this.USERS_URL + '/me');
  }
}
