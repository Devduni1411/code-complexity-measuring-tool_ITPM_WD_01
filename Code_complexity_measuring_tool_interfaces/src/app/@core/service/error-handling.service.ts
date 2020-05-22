import {Injectable} from '@angular/core';
import {NbToastrService} from '@nebular/theme';

@Injectable({
  providedIn: 'root',
})
export class ErrorHandlingService {

  constructor() {
  }

  public static handle(error, toastrService: NbToastrService) {
    toastrService.danger('Connection problem with server');
  }
}
