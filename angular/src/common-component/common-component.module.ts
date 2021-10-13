import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormButtonsComponent } from './form-buttons/form-buttons.component';
import { ShowErrorsMessagesComponent } from './show-errors-messages/show-errors-messages.component';
import { CardComponent } from './card/card.component';



@NgModule({
  declarations: [
    FormButtonsComponent,
    ShowErrorsMessagesComponent,
    CardComponent,
  ],
  exports: [
    FormButtonsComponent,
    ShowErrorsMessagesComponent,
    CardComponent,
  ],
  imports: [
    CommonModule
  ]
})
export class CommonComponentModule { }
