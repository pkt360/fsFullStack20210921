import { Component, EventEmitter, Input, OnInit, Output, InjectionToken } from '@angular/core';
import { LoggerService } from 'src/lib/my-core';
import { NotificationService, NotificationType } from '../common-services';

@Component({
  selector: 'calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.scss']
})
export class CalculadoraComponent implements OnInit {
  public readonly Math = Math;

  private acumulado = 0;
  private operador = '+';
  private limpiar = true;
  private pantallaCalc = '0'
  private resumenCalc = '';

  constructor(private log: LoggerService, private notify: NotificationService) {
  //this.iniciar();
  }

  iniciar(): void {
    this.acumulado = 0;
    this.operador = '+';
    this.limpiar = true;
    this.pantallaCalc = '0';
    this.resumenCalc = '';

  }

  get Pantalla(): string { return this.pantallaCalc; }

  get Resumen(): string{return this.resumenCalc}

  @Input() inicializado: string | number = '0';
  @Output() actualizar: EventEmitter<any> = new EventEmitter();


  ponerDigito(value: number | string): void {
    if (typeof (value) !== 'string')
      value = value.toString();
    if (value.length != 1 || value < '0' || value > '9') {
      this.log.error('No es un valor numerico.');
      return;
    }
    if (this.limpiar || this.pantallaCalc == '0') {
      this.pantallaCalc = value;
      this.limpiar = false;
    } else
      this.pantallaCalc+= value;
  }

  ponerOperador(value: number | string): void {
    if (typeof value === "number" || (parseFloat(value).toString() == value)) {
      this.pantallaCalc = value.toString();
      this.limpiar = false;
    } else {
      this.log.error('Error en el valor introducido');
    }
  }

  borrar(): void {
    if (this.limpiar || this.pantallaCalc.length == 1 || this.pantallaCalc.length == 2 ) {
      this.pantallaCalc = '0';
      this.limpiar = true;
    } else
      this.pantallaCalc = this.pantallaCalc.substr(0, this.pantallaCalc.length - 1);
  }

  ponerComa(): void{
    if (this.limpiar) {
      this.pantallaCalc = '0.';
      this.limpiar = false;
    } else if (this.pantallaCalc.indexOf('.') === -1) {
      this.pantallaCalc += '.';
    } else {
      this.notify.add('No se puede poner la coma dos veces', NotificationType.warn)
    }
  }

  calcular(value: string): void{
    if ('+-*/='.indexOf(value) == -1) {
      this.log.error(`Este signo de oparación no es válido: ${value}`);
      return;
    }

    let operando = parseFloat(this.pantallaCalc);
    switch (this.operador) {
      case '+':
        this.acumulado += operando;
        break;
      case '-':
        this.acumulado -= operando;
        break;
      case '*':
        this.acumulado *= operando;
        break;
      case '/':
        this.acumulado /= operando;
        break;
    }

    this.resumenCalc = value == '=' ? '' : (`${this.acumulado} ${value}`);
    this.pantallaCalc = parseFloat(this.acumulado.toPrecision(12)).toString();
    this.actualizar.emit(this.acumulado);
    this.operador = value;
    this.limpiar = true;
  }

  ngOnInit(): void {

  }


}
