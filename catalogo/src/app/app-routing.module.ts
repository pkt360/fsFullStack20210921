import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActorAddComponent, ActorEditComponent, ActorListComponent, ActorViewComponent } from './actor/actor.component';

const routes: Routes = [
{path: '', pathMatch: 'full', component: HomeComponent},
/*  {path: 'peliculas', children:[
    { path: '', component: PeliculasListComponent },
    { path: 'add', component: PeliculasAddComponent },
    { path: ':id/edit', component: PeliculasEditComponent },
    { path: ':id', component: PeliculasViewComponent },
    { path: ':id/:kk', component: PeliculasViewComponent },
]}, */
{ path: 'actor', children: [
  { path: '', component: ActorListComponent },
  { path: 'add', component: ActorAddComponent },
  { path: ':id/edit', component: ActorEditComponent },
  { path: ':id', component: ActorViewComponent },
  { path: ':id/:kk', component: ActorViewComponent },
]},/*
{ path: 'categoria', children: [
  { path: '', component: CategoriaListComponent },
  { path: 'add', component: CategoriaAddComponent },
  { path: ':id/edit', component: CategoriaEditComponent },
  { path: ':id', component: CategoriaViewComponent },
  { path: ':id/:kk', component: CategoriaViewComponent },
]},
{ path: 'idioma', children: [
  { path: '', component: IdiomaListComponent },
  { path: 'add', component: IdiomaAddComponent },
  { path: ':id/edit', component: IdiomaEditComponent },
  { path: ':id', component: IdiomaViewComponent },
  { path: ':id/:kk', component: IdiomaViewComponent },
]},*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
