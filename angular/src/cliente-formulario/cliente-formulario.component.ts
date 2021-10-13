import { Component, Injectable, OnInit } from '@angular/core';
import { NotificationService, NotificationType } from '../common-services';

@Injectable({providedIn: 'root'})
export class ClienteViewModel {
  Listado: Array<any> = [
    { customer_id: 1, first_name: 'Pepito', last_name: 'Grillo', email: 'pepito@grillo', create_date: '2021-10-22' , last_update: (new Date('2021-10-22')).toISOString() }
  ]
  Elemento: any = { };
  IsAdd = true;

  constructor(private notify: NotificationService) {

  }

  public list() {

  }

  public add() {
    this.Elemento = { }
    this.IsAdd = true;
  }

  public edit() {
    this.Elemento = this.Listado[0];
    this.IsAdd = false;
  }

  public view() {
    this.Elemento = this.Listado[0];
    this.IsAdd = false;
  }

  public delete() {
    if(!window.confirm('¿Seguro?')) return;
    this.notify.add('Borrado');
  }

  public cancel() {

  }

  public send() {
    this.notify.add((this.IsAdd ? 'Nuevos: ' : 'Modificados: ') + JSON.stringify(this.Elemento), NotificationType.info);
  }
}

@Component({
  selector: 'app-cliente-formulario',
  templateUrl: './cliente-formulario.component.html',
  styleUrls: ['./cliente-formulario.component.scss']
})
export class ClienteFormularioComponent implements OnInit {

  constructor(public vm: ClienteViewModel) { }

  ngOnInit(): void {
  }

}
