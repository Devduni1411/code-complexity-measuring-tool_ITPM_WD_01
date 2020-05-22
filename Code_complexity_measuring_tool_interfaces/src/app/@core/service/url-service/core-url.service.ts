import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CoreUrlService {
  private _BASE_URL_LOCAL = 'http://localhost:8090/';
  private static _BASE_URL: '';

  constructor() {
  }

  get BASE_URL(): string {
    return this._BASE_URL_LOCAL;
  }

  static AUTH_URL() {
    return '';
  }
}
