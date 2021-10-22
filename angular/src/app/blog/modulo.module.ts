import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MyCoreModule } from '../../lib/my-core/my-core.module';
import { CommonServicesModule } from '../common-services/common-services.module';
import { CommonComponentModule } from '../common-component/common-component.module';
import { BLOG_COMPONENTES } from './componente.component';
import {EditorModule} from 'primeng/editor';
import {InplaceModule} from 'primeng/inplace';
import { PaginatorModule } from 'primeng/paginator';



@NgModule({
  declarations: [
    BLOG_COMPONENTES,
  ],
  exports:[
    BLOG_COMPONENTES,
  ],
  imports: [
    CommonModule, FormsModule, RouterModule.forChild([]), MyCoreModule,
    CommonServicesModule, CommonComponentModule, InplaceModule, PaginatorModule,
    EditorModule,

  ]
})
export class BlogModule { }
