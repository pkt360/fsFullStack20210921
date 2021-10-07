import { Component, OnInit } from '@angular/core';
import { LoggerService } from 'src/lib/my-core';
import { NotificationService } from '../common-services';

@Component({
  selector: 'app-demos',
  templateUrl: './demos.component.html',
  styleUrls: ['./demos.component.scss']
})
export class DemosComponent implements OnInit {
  private nombre: string = 'mundo';
  listado = [
    { id: 1, nombre: 'Madrid' },
    { id: 2, nombre: 'malaga' },
    { id: 3, nombre: 'SEVILLA' },
    { id: 4, nombre: 'ciudad real' },
  ]
  idProvincia: number = 2;

  resultado: string | null = null;
  visible = true;
  estetica = { importante: true, error: false, urgente: true };
  fontSize = 18;


  constructor(private log: LoggerService, public vm: NotificationService) {
    log.error('Es un error');
    log.warn('Es un warning');
    log.info('Es un info');
    log.log('Es un log');
   }

  public get Nombre(): string { return this.nombre; }
  public set Nombre(value: string) {
    if (this.nombre === value) return;
    this.nombre = value;
  }

  public saluda(): void {
    this.resultado = `Hola ${this.nombre}`;
  }

  despide(): void {
    this.resultado = `Adios ${this.nombre}`;
  }

  di(algo: string): void {
    this.resultado = `Dice ${algo}`;
  }

  cambia(): void {
    this.visible = !this.visible;
    this.estetica.importante = !this.estetica.importante;
    this.estetica.error = !this.estetica.error;
  }

  calcula(a: number, b: number): number { return a + b; }

  add(provincia: string): void {
    const id = this.listado.length === 0 ? 1 : (this.listado[this.listado.length - 1].id + 1);
    this.listado.push({ id, nombre: provincia });
    this.idProvincia = id;
  }

  ngOnInit(): void {
  }

}
