import { Injectable } from '@angular/core';
import * as moment from 'moment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private headers: string[];

  constructor(private http: HttpClient) {

  }

  login() {
    this.http.post<any>('http://localhost:8080/login', {}).subscribe(
      res => {
        this.setSession(res);
      },
      err => {
      },
    );
  }

  private setSession(authResult) {
    const expiresAt = moment().add(authResult.Expiration, 'second');

    localStorage.setItem('id_token', authResult.Authorization);
    localStorage.setItem('expires_at', JSON.stringify(expiresAt.valueOf()) );
  }

  logout() {
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getExpiration() {
    const expiration = localStorage.getItem('expires_at');
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);
  }

  test() {
    this.http.get<any>('http://localhost:8080/api/public/test').subscribe(
      res => {
      },
      err => {},
    );
  }

  static getAccessToken() {
    return localStorage.getItem('id_token');
  }
}
