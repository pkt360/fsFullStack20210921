import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemosComponent } from './demos/demos.component';
import { FormsModule } from '@angular/forms';
import { DinamicoComponent } from './dinamico/dinamico.component';
import { CalculadoraComponent } from './calculadora/calculadora.component';
import { LoggerService, MyCoreModule } from 'src/lib/my-core';
import { MainModule } from './main';

import { CommonServicesModule } from './common-services';
import { SecurityModule } from './security';
import { FormularioComponent } from './formulario/formulario.component';
import { CommonComponentComponent } from './common-component/common-component.component';
import { ClienteFormularioComponent } from './cliente-formulario/cliente-formulario.component';

@NgModule({
  declarations: [
    AppComponent,
    DemosComponent,
    DinamicoComponent,
    CalculadoraComponent,
    FormularioComponent,
    CommonComponentComponent,
    ClienteFormularioComponent,
  ],
  imports: [
    BrowserModule, FormsModule,
    AppRoutingModule, MyCoreModule, MainModule, CommonServicesModule, SecurityModule,
  ],
  providers: [
    LoggerService,

    { provide: LOCALE_ID, useValue: 'es-ES'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
