import { Component, Injectable, OnInit } from '@angular/core';
import { NotificationService, NotificationType } from '../common-services';

export interface Cliente {
  customer_id: number | null;
  store_id: number | null;
  first_name: string;
  last_name: string;
  email: string | null;
  address_id_1: string | null;
  address_id_2: string | null;
  active: boolean;
  create_date: string | null;
  last_update: string | null;
}

@Injectable({providedIn: 'root'})
export class ClienteViewModel {
  Listado: Array<Cliente> = [
    { customer_id: 1, store_id: 1, first_name: 'Francisco', last_name: 'Pérez', email: 'francisco@gmail', address_id_1: 'mercado, n1', address_id_2: '', active: true, create_date: '25-Mayo-2000', last_update: '02-12-2020' };
  ]
  Elemento: Cliente = { customer_id: 1, store_id: 1, first_name: 'Francisco', last_name: 'Pérez', email: 'francisco@gmail', address_id_1: 'mercado, n1', address_id_2: '', active: true, create_date: '25-Mayo-2000', last_update: '02-12-2020' };
  IsAdd = true;

  constructor(private notify: NotificationService) {
  }

  public add() {
    this.Elemento = { customer_id: 1, store_id: 1, first_name: 'Francisco', last_name: 'Pérez', email: 'francisco@gmail', address_id_1: 'mercado, n1', address_id_2: '', active: true, create_date: '25-Mayo-2000', last_update: '02-12-2020' };
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

  public delete(){
    if(!window.confirm('Seguro?')) return;
    this.notify.add('Borrado');
  }

  public cancel() {}

  public send() {
    this.notify.add((this.IsAdd ? 'Nuevos: ' : 'Modificados: ')
    + JSON.stringify(this.Elemento), NotificationType.info);
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
