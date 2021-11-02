import { HttpClient, HttpContext } from '@angular/common/http';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { RESTDAOService } from '../base-code/RESTDAOService';
import { ModoCRUD } from '../base-code/tipos';
import { NotificationService } from '../common-services';
import { LoggerService } from '../lib/my-core';
import { AuthService, AUTH_REQUIRED } from '../security';

@Injectable({
  providedIn: 'root',
})
  export class ActorDAOService extends RESTDAOService<any,any>{
    constructor(http: HttpClient){
      super(http, 'actor', {
        context: new HttpContext().set(AUTH_REQUIRED, true),
      });
    }
  }

  @Injectable({
    providedIn: 'root',
  })
  export class ActorViewModelService{
    protected modo: ModoCRUD = 'list';
    protected listado: Array<any> = [];
    protected elemento: any = {};
    protected idOriginal: any = null;
    protected listURL = '/actor';

    constructor(
      protected notify: NotificationService,
      protected out: LoggerService,
      protected dao: ActorDAOService,
      protected router: Router,
      public auth: AuthService,
    ){}

    public get Modo(): ModoCRUD{
      return this.modo;
    }

    public get Listado(): Array<any>{
      return this.Listado;
    }

    public get Elemento(): any{
      return this.elemento;
    }

    public list(): void{
      this.dao.query().subscribe(
        (data) =>{
          this.listado = data;
          this.modo= 'list';
        },
        (err) => this.notify.add(err.message)
      );
    }

    public add(): void{
      this.elemento = {};
      this.modo = 'add';
    }

    public edit(key: any): void{
      this.dao.get(key).subscribe(
        (data) =>{
          this.elemento = data;
          this.idOriginal= key;
          this.modo = 'edit';
        },
        (err) => this.notify.add(err.message)
      );
    }

    public delete(key: any): void{
      if (!window.confirm('¿Estás seguro?')){
        return;
      }
      this.dao.remove(key).subscribe(
        (data) => this.list(),
        (err) => this.notify.add(err.message)
      );
    }
    clear(){
      this.elemento={};
      this.idOriginal = null;
      this.listado = [];
    }

    public view(key: any): void{
      this.dao.get(key).subscribe(
        (data) =>{
          this.elemento = data;
          this.modo = 'view';
        },
        (err) => this.notify.add(err.message)
      );
    }

    public cancel(): void{
      this.elemento={};
      this.idOriginal = null;
      this.router.navigateByUrl(this.listURL);
    }


  }

