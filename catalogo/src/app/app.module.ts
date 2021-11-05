import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { environment } from 'src/environments/environment';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonServicesModule } from './common-services';
import { MyCoreModule } from './lib/my-core';
import { AjaxWaitInterceptor, MainModule } from './main';
import { ERROR_LEVEL, LoggerService } from './lib/my-core';
import { AuthInterceptor, SecurityModule } from './security';
import { ActorComponent } from './actor/actor.component';
import { CategoriaComponent } from './categoria/categoria.component';
import { IdiomaComponent } from './idioma/idioma.component';
import { PeliculasComponent } from './peliculas/peliculas.component';

@NgModule({
  declarations: [
    AppComponent,
    ActorComponent,
    CategoriaComponent,
    IdiomaComponent,
    PeliculasComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonServicesModule,
    MyCoreModule,
    SecurityModule,
    MainModule,


  ],
  providers: [
    LoggerService,
    {provide: ERROR_LEVEL, useValue: environment.ERROR_LEVEL},
    {provide: HTTP_INTERCEPTORS, useClass: AjaxWaitInterceptor, multi: true,},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true,},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
