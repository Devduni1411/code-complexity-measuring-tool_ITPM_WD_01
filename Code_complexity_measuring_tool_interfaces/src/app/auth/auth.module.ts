import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbPasswordAuthStrategy, NbAuthModule } from '@nebular/auth';
import { LoginComponent } from './login/login.component';
import {FormsModule} from "@angular/forms";
import {NbAlertModule, NbButtonModule, NbCheckboxModule, NbInputModule} from "@nebular/theme";

@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    NbAuthModule.forRoot({
      strategies: [
        NbPasswordAuthStrategy.setup({
          name: 'email',
        }),
      ],
      forms: {},
    }),
    FormsModule,
    NbCheckboxModule,
    NbInputModule,
    NbAlertModule,
    NbButtonModule,
  ],
})
export class AuthModule { }
