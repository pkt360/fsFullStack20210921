import { Component, OnInit } from '@angular/core';
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

}


/*
export const actor-componentes = [

]

*/
