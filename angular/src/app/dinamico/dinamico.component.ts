import { Component, OnInit } from '@angular/core';
import { CalculadoraComponent } from '../calculadora/calculadora.component';
import { DemosComponent } from '../demos/demos.component';
import { FormularioComponent } from '../formulario/formulario.component';
import { HomeComponent } from '../main';

@Component({
  selector: 'app-dinamico',
  templateUrl: './dinamico.component.html',
  styleUrls: ['./dinamico.component.scss']
})
export class DinamicoComponent implements OnInit {
  menu = [
    { texto: 'formulario', icono: 'fas fa-user-tie', componente: FormularioComponent },
    { texto: 'inicio', icono: 'fas fa-home', componente: HomeComponent },
    { texto: 'demos', icono: 'fas fa-chalkboard-teacher', componente: DemosComponent },
    { texto: 'calculadora', icono: 'fas fa-calculator', componente: CalculadoraComponent },
  ];

  actual = this.menu[0].componente;

  constructor() { }

  seleccionar(indice: number): void {
    this.actual = this.menu[indice].componente;
  }

  ngOnInit(): void {
  }

}
