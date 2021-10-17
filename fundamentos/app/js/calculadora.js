class Calculadora{
  constructor(){
    this.pantalla_Arriba = "";
    this.pantalla_Abajo = "0";
    this.operador = "+";
    this.Operacion_arriba = 0;
    this.Operacion_abajo = 0; 
    this.actualizarPantalla();
    this.contadorRes = false
  }

  actualizarPantalla () {
    document.querySelector(".pantalla--arriba").textContent = this.pantalla_Arriba;
    document.querySelector(".pantalla--abajo").textContent = this.pantalla_Abajo;
  }

  calcular(valor) {
    this.operador = valor;
    let cadena = "+-*/"; 
    if (cadena.indexOf(this.operador) == -1) return;
    
    switch (this.operador) {
      case "+":
        this.Operacion_arriba += this.Operacion_abajo;              
      break;
      case "-":
        this.Operacion_arriba -= this.Operacion_abajo;
      break;
      case "*":
        this.Operacion_arriba *= this.Operacion_abajo;
      break;
      case "/":
        this.Operacion_arriba /= this.Operacion_abajo;
      break;
    }
    if (this.contadorRes === false){
      this.pantalla_Arriba = this.Operacion_arriba.toString() + this.operador;
    }else{
      this.pantalla_Arriba = this.Operacion_arriba.toString();
    }
     
    this.pantalla_Abajo = "0";
    this.Operacion_abajo = 0;
    this.contadorRes = false;
    this.actualizarPantalla();
}


borrarPantalla () {
  this.pantalla_Arriba = "";
    this.operador = "+";
    this.pantalla_Abajo = "0";
    this.Operacion_arriba = 0;
    this.Operacion_abajo = 0;
    this.actualizarPantalla();
}

corregir() {
  if (this.pantalla_Abajo.length > 1 && this.pantalla_Abajo != "0") {
    this.pantalla_Abajo = this.pantalla_Abajo.substring(0, this.pantalla_Abajo.length - 1);

  }else{
    this.pantalla_Abajo = "0";

  }

  this.actualizarPantalla();
  
}

escribirNumero(valor) {
  
    if (valor < "0" || valor > "9") {
      return;
    }
    if (this.pantalla_Abajo === "0") {
      this.pantalla_Abajo = valor;
    } else if (this.pantalla_Abajo === undefined) {
      this.pantalla_Abajo = valor;
    } else if (this.pantalla_Abajo.indexOf("+" || "-" || "*" || "/") == 0){
      this.pantalla_Abajo = this.pantalla_Abajo.substring(1, this.pantalla_Abajo.length);
      this.pantalla_Abajo += valor;
    }else{
      this.pantalla_Abajo += valor;
    }
    this.Operacion_abajo = parseFloat(this.pantalla_Abajo);
    this.actualizarPantalla();
}

escribirComa () {
  if (this.pantalla_Abajo.indexOf(".") === -1) {
    this.pantalla_Abajo += ".";
    this.Operacion_abajo = parseFloat(this.pantalla_Abajo);
  }
  this.actualizarPantalla();
}

resultado (){
  let signo = this.pantalla_Arriba.substring(this.pantalla_Arriba.length -1, this.pantalla_Arriba.length);
  let numero = parseFloat(this.pantalla_Arriba.substring(0, this.pantalla_Arriba.length-1));
  this.Operacion_arriba = numero;
  this.operador = signo
  this.contadorRes = true
  this.calcular(signo);
  
  
   
}

}
