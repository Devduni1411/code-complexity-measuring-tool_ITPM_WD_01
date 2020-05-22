import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from '../service/auth.service';
import {NbTokenService} from '@nebular/auth';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private tokenService: NbTokenService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const userToken = JSON.parse(localStorage.getItem('auth_app_token'));
    let userTokenValue;
    if (userToken) {
      userTokenValue = userToken['value'];
    } else {
      return next.handle(req);
    }
    const modifiedReq = req.clone({
      headers: req.headers.set('Authorization', userTokenValue),
    });
    return next.handle(modifiedReq);
  }
}
