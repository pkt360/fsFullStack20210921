import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MyCoreModule } from '../lib/my-core';
import { RouterModule } from '@angular/router';
import { CommonServicesModule } from '../common-services';
import { CommonComponentModule } from '../common-component';



@NgModule({
  declarations: [

  ],

  exports:[

  ],

  imports: [
    CommonModule, FormsModule, MyCoreModule, RouterModule.forChild([]),
    CommonServicesModule, CommonComponentModule
  ]
})
export class ActorModule { }
