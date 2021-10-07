import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.scss']
})
export class CalculadoraComponent implements OnInit {

  pantallaOperacion: string = "";
  operacion: string = "+";
  resultado: string = "0";
  contadorOperacion: number = 0;

  //  result: string = '';

  constructor() { }

  actualizarPantalla_O(valor: string) {
    (".pantalla--arriba").textContent = valor;
  };

  actualizarPantalla_R(valor: string) {
    (".pantalla--abajo").textContent = valor;
  };

  escribirNumero(valor: string): void {
    if (this.contadorOperacion > 0) {
      this.calcular;
    } else {
      if (valor < "0" || valor > "9") {
        return;
      }
      if (valor === "0" && this.resultado === "0") {
        this.resultado = valor;
      } else if (this.resultado === undefined) {
        this.resultado = valor;
      } else {
        this.resultado += valor;
      }
    }
    this.actualizarPantalla_O(this.pantallaOperacion);
    this.actualizarPantalla_R(this.resultado);
  };

  calcular(valor: string):void {
    //let r = this.resultado.length();
  if (this.contadorOperacion < 1) {
    this.acumular(valor);
  } else {
    let pantallaO: number = parseFloat(this.resultado);
    let res: number = parseFloat(this.pantallaOperacion);
    let r: string
    this.operacion = valor;

    let cadena: string = "+-*/";
    if (cadena.indexOf(this.operacion) == -1) return;

    switch (this.operacion) {
      case "+":
        res += pantallaO;
        break;
      case "-":
        res -= pantallaO;
        break;
      case "*":
        res *= pantallaO;
        break;
      case "/":
        res /= pantallaO;
        break;
    }
    res = String(res);
    this.pantallaOperacion = res;

    this.resultado = "0";
    actualizarPantalla_R(this.resultado);
    actualizarPantalla_O(this.operacion);
  }
}

acumular (valor: string) {
  this.contadorOperacion = this.contadorOperacion + 1;
  this.pantallaOperacion = this.resultado;
  this.actualizarPantalla_R(valor);
};

/*
add(key: string): void{
  if (!isNaN(parseFloat(key))) return;
    if (this.result === '0')
      this.result+=key
    else this.result += key;
}
*/

  ngOnInit(): void {
  }

}
function parseString(res: any) {
  throw new Error('Function not implemented.');
}

