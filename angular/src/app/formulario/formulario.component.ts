import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { NotificationService, NotificationType } from '../common-services';

export interface Persona {
  id: number | null;
  nombre: string;
  apellidos: string;
  correo: string | null;
  edad: number | null;
  dni: string | null;
}

@Injectable({providedIn: 'root'})
export class PersonasViewModel {
  Listado: Array<Persona> = [
    { id: 1, nombre: 'Pepito', apellidos: 'Grillo', correo: 'pepito@grillo', edad: 99, dni: '12345678Z' }
  ]
  Elemento: Persona = { id: null, nombre: '', apellidos: '', correo: null, edad: null, dni: null };
  IsAdd = true;

  constructor(private notify: NotificationService, private http: HttpClient) {

  }

  public list() {

  }

  public add() {
    this.Elemento = { id: null, nombre: '', apellidos: '', correo: null, edad: null, dni: null }
    this.IsAdd = true;
  }

  public edit() {
    this.http.get<Persona>(`http://loccalhost:4321/api/personas/${this.Elemento.id}`).subscribe(
      data=>{
        this.Elemento=data;
        this.IsAdd = false;
      },
      err=>this.notify.add(err.message)
    );
    //this.Elemento = this.Listado[0];
    //this.IsAdd = false;
  }

  public view() {
    this.Elemento = this.Listado[0];
    this.IsAdd = false;
  }

  public delete() {
    this.notify.add('Borrado');
  }

  public cancel() {

  }

  public send() {
    this.notify.add((this.IsAdd ? 'Nuevos: ' : 'Modificados: ') + JSON.stringify(this.Elemento), NotificationType.info);
  }
}

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.scss']
})
export class FormularioComponent implements OnInit {
  test: any = {}

  constructor(public vm: PersonasViewModel) { }

  ngOnInit(): void {
  }

}
