import { Injectable } from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class RedirectionService {

  constructor(private router: Router) { }

  LOGIN_PATH = '/auth/login';

  redirectToLogin() {
    this.router.navigateByUrl(this.LOGIN_PATH);
  }
}
