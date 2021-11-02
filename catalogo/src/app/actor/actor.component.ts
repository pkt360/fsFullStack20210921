import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['./actor.component.scss']
})
export class ActorComponent implements OnInit {

  constructor (protected vm: ActorViewModelService){
    public get VM(): ActorViewModelService{
      return this.vm;
    }
  }

  ngOnInit(): void {
    this.vm.list();
  }

}


/*
export const actor-componentes = [

]

*/
