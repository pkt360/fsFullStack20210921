import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ActorViewModelService } from './servicios.service';

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['./actor.component.scss'],
})
export class ActorComponent implements OnInit {

  constructor (protected vm: ActorViewModelService){}
    public get VM(): ActorViewModelService{
      return this.vm;
    }

  ngOnInit(): void {
    this.vm.list();
  }
}

@Component({
  selector: 'app-actor-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls:['./actor.component.scss'],
})
export class ActorAddComponent implements OnInit{

  constructor(protected vm: ActorViewModelService){

  }
  public get VM(): ActorViewModelService {
    return this.vm;
  }
  ngOnInit(): void{
    this.vm.add()
  }
}
  @Component({
    selector: 'app-actor-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls:['./actor.component.scss'],
  })
  export class ActorListComponent implements OnInit{

    public page: number = 0;
    constructor(protected vm: ActorViewModelService){

    }
    public get VM(): ActorViewModelService {
      return this.vm;
    }
    ngOnInit(): void{
      this.vm.list()
    }
  }
    @Component({
      selector: 'app-actor-edit',
      templateUrl: './tmpl-form.component.html',
      styleUrls: ['./actor.component.scss'],
    })
    export class ActorEditComponent implements OnInit, OnDestroy {
      private obs$: any;
      constructor(
        protected vm: ActorViewModelService,
        protected route: ActivatedRoute,
        protected router: Router
      ) {}
      public get VM(): ActorViewModelService {
        return this.vm;
      }
      ngOnInit(): void {
        this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
          const id = parseInt(params?.get('id') ?? '');
          if (id) {
            this.vm.edit(id);
          } else {
            this.router.navigate(['/404.html']);
          }
        });
      }
      ngOnDestroy(): void {
        this.obs$.unsubscribe();
      }
    }
    @Component({
      selector: 'app-actor-view',
      templateUrl: './tmpl-view.component.html',
      styleUrls: ['./actor.component.scss'],
    })
    export class ActorViewComponent implements OnInit, OnDestroy {
      private obs$: any;
      constructor(protected vm: ActorViewModelService, protected route: ActivatedRoute, protected router: Router) {}
      public get VM(): ActorViewModelService {
        return this.vm;
      }
      ngOnInit(): void {
        this.obs$ = this.route.paramMap.subscribe((params: ParamMap) => {
          const id = parseInt(params?.get('id') ?? '');
          if (id) {
            this.vm.view(id);
          } else {
            this.router.navigate(['/404.html']);
          }
        });
      }
      ngOnDestroy(): void {
        this.obs$.unsubscribe();
      }
    }

  export const ACTOR_COMPONENTES = [
    ActorComponent,
    ActorListComponent,
    ActorAddComponent,
    ActorEditComponent,
    ActorViewComponent,
  ];
