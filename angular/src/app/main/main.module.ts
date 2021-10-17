import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { NotificationComponent } from './notification/notification.component';
import { CommonServicesModule } from '../common-services';
import { NotificationModalComponent } from './notification-modal/notification-modal.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HeaderComponent } from './header/header.component';
import { SecurityModule } from '../security';
import { RouterModule } from '@angular/router';
import { AjaxWaitComponent } from './ajax-wait';




@NgModule({
  declarations: [
    HomeComponent,
    NotificationComponent,
    NotificationModalComponent,
    PageNotFoundComponent,
    HeaderComponent,
    AjaxWaitComponent,
  ],
  exports: [
    HomeComponent,
    NotificationComponent,
    NotificationModalComponent,
    PageNotFoundComponent,
    HeaderComponent,
    AjaxWaitComponent,
  ],
  imports: [
    CommonModule, CommonServicesModule, SecurityModule, RouterModule.forChild([]) /*Con forChild sólo carga las rutas 1 vez*/
  ]
})
export class MainModule {
  constructor( @Optional() @SkipSelf() parentModule: MainModule) {
    if (parentModule) {
      const msg = `MainModule has already been loaded.
        Import MainModule once, only, in the root AppModule.`;
      throw new Error(msg);
    }
  }
}
