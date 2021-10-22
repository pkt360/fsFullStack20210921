import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LibrosComponent } from './libros';
import { ContactosAddComponent, ContactosComponent, ContactosEditComponent, ContactosListComponent, ContactosViewComponent } from './contactos/componente.component';
import { DemosComponent } from './demos/demos.component';
import { HomeComponent, PageNotFoundComponent } from './main';
import { LibrosAddComponent } from './libros/componente.component';
import { AuthGuard } from './security/services/serguridad.service';
import { BlogListComponent, BlogAddComponent, BlogEditComponent, BlogViewComponent } from './blog/componente.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', component: HomeComponent},
  {path: 'inicio', component: HomeComponent, data: {pageTitle: 'Inicio'}},
  {path: 'demos', component: DemosComponent, data: {pageTitle: 'Demos'}},
  {path: 'chisme/de/hacer/numeros', component: DemosComponent, data: {pageTitle: 'Calculadora'}},
  { path: 'contactos', children: [
    { path: '', component: ContactosListComponent},
    { path: 'add', component: ContactosAddComponent},
    { path: ':id/edit', component: ContactosEditComponent},
    { path: ':id', component: ContactosViewComponent},
    { path: ':id/:kk', component: ContactosViewComponent},
  ]},
  { path: 'libros', children: [
    { path: '', component: LibrosComponent },
    { path: 'add', component: LibrosComponent },
    { path: ':id/edit', component: LibrosComponent },
    { path: ':id', component: LibrosComponent },
    { path: ':id/:kk', component: LibrosComponent },
  ]},
  { path: 'blog', component: BlogListComponent},
  { path: 'blog/add', component: BlogAddComponent, canActivate: [AuthGuard]},
  { path: 'blog/:id/edit', component: BlogEditComponent, canActivate: [AuthGuard]},
  { path: 'blog/:id', component: BlogViewComponent},


  { path: 'config', loadChildren: () => import('./config/config.module').then(mod => mod.ConfigModule)},
  { path: '404.html', component: PageNotFoundComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
