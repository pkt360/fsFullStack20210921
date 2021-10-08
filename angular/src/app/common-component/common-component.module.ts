import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ShowErrorsMessagesComponent } from './show-errors-messages/show-errors-messages.component';
import { FormButtonsComponent } from './form-buttons/form-buttons.component';


@NgModule({
  declarations:[
    ShowErrorsMessagesComponent,
    FormButtonsComponent,
  ],
  exports: [
    ShowErrorsMessagesComponent,
    FormButtonsComponent,
  ],
  imports: [
    CommonModule,
  ]
})

export class CommonComponentModule {}
