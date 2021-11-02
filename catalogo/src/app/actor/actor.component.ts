import { Component, OnInit } from '@angular/core';
import { ActorViewModelService } from './servicios.service';

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['./actor.component.scss']
})
export class ActorComponent implements OnInit {

  constructor (protected vm: ActorViewModelService){}
    public get VM(): ActorViewModelService{
      return this.vm;
    }

  ngOnInit(): void {
    this.vm.list();
  }

  //@Component
}


/*
export const actor-componentes = [

]

*/
