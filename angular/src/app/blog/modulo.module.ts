import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MyCoreModule } from '../../lib/my-core/my-core.module';
import { CommonServicesModule } from '../common-services/common-services.module';
import { CommonComponentModule } from '../common-component/common-component.module';
import { BlogListComponent, BlogAddComponent, BlogEditComponent, BlogViewComponent, BLOG_COMPONENTES } from './componente.component';
import { AuthGuard } from '../security/services/serguridad.service';
import {EditorModule} from 'primeng/editor';
import {InplaceModule} from 'primeng/inplace';
import { PaginatorModule } from 'primeng/paginator';

const routes: Routes = [
  {path: '', component: BlogListComponent},
  { path: 'add', component: BlogAddComponent, canActivate: [AuthGuard] },
  { path: ':id/edit', component: BlogEditComponent, canActivate: [AuthGuard] },
  { path: ':id', component: BlogViewComponent },
  { path: ':id/:kk', component: BlogViewComponent },
]


@NgModule({
  declarations: [
    BLOG_COMPONENTES,
  ],
  exports:[
    BLOG_COMPONENTES,
  ],
  imports: [
    PaginatorModule, CommonModule, FormsModule, RouterModule.forChild(routes), MyCoreModule,
    CommonServicesModule, CommonComponentModule, InplaceModule,
    EditorModule,

  ]
})
export class ModuloModule { }
