import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import { CONTACTOS_COMPONENTES } from './componente.component';



@NgModule({
  declarations: [
    CONTACTOS_COMPONENTES
  ],
  imports: [
    CommonModule, FormsModule, RouterModule.forChild([]), MyCoreModule, CommonServicesModule, 
  ],
  exports:[
    CommonModule, FormsModule, MyCoreModule, CommonServicesModule,
  ],
  
})
export class ContactosModule { }
