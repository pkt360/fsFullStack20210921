import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { LibrosComponent, LIBROS_COMPONENTES } from './componente.component';
import { CommonServicesModule } from 'src/app/common-services';
import { CommonComponentModule } from 'src/app/common-component';

@NgModule({
  declarations: [
    LIBROS_COMPONENTES,
  ],
  exports: [
    // LIBROS_COMPONENTES,
    LibrosComponent,
  ],
  imports: [
    CommonModule, FormsModule, RouterModule.forChild([]),
    MyCoreModule, CommonServicesModule, CommonComponentModule, MyCoreModule,
  ]
})
export class LibrosModule { }
