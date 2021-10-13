class Calculadora{
  constructor(){
    this.pantalla_Operacion = "";
    this.operador = "+";
    this.pantalla_Resultado = "0";
    this.contadorOperacion = 0;
    this.cacheOperacion = 0;  
    this.actualizarPantalla_R(this.pantalla_Resultado);
    this.actualizarPantalla_O(this.pantalla_Operacion);
  }

 calcular(valor) {
    this.contadorOperacion+=1;
    if (this.contadorOperacion < 2){
      this.cacheOperacion += parseFloat(this.pantalla_Resultado);
      this.pantalla_Operacion =  this.cacheOperacion + this.operador;
      
      
      this.pantalla_Resultado = '0';
      this.actualizarPantalla_R(this.pantalla_Resultado);
      this.actualizarPantalla_O(this.pantalla_Operacion);
    }else{

    let pantallaO = parseFloat(this.pantalla_Resultado);
    let res = parseFloat(this.pantalla_Operacion);
    this.operador = valor;
   
    let cadena = "+-*/=";
    if (cadena.indexOf(this.operador) == -1) return;

    switch (this.operador) {
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
      default:
        res = this.cacheOperacion;
        alert(res);
    }
    res = res.toString();
    this.pantalla_Operacion = res;
    this.pantalla_Resultado = "0";
    this.actualizarPantalla_R(this.pantalla_Resultado);
    this.actualizarPantalla_O(this.operador);
  }
}

acumular(valor) {
  this.contadorOperacion = this.contadorOperacion + 1;
  this.pantalla_Operacion = this.pantalla_Resultado;
  this.actualizarPantalla_R(valor);
}

 actualizarPantalla_O (valor) {
  document.querySelector(".pantalla--arriba").textContent = valor;
}

actualizarPantalla_R (valor) {
  document.querySelector(".pantalla--abajo").textContent = valor;
}

borrarPantalla () {
  this.pantalla_Operacion = "";
    this.operador = "+";
    this.pantalla_Resultado = "0";
    this.contadorOperacion = 0;
    this.cacheOperacion = 0;  
    this.actualizarPantalla_R(this.pantalla_Resultado);
    this.actualizarPantalla_O(this.pantalla_Operacion);
}

corregir() {
  if (this.pantalla_Resultado.length > 0 && this.pantalla_Resultado != "0") {
    this.pantalla_Resultado = this.pantalla_Resultado.substring(0, this.pantalla_Resultado.length - 1);
  }
  this.actualizarPantalla_O(this.pantalla_Operacion);
  this.actualizarPantalla_R(this.pantalla_Resultado);
}

escribirNumero(valor) {
  
    if (valor < "0" || valor > "9") {
      return;
    }
    if (this.pantalla_Resultado === "0") {
      this.pantalla_Resultado = valor;
    } else if (this.pantalla_Resultado === undefined) {
      this.pantalla_Resultado = valor;
    } else if (this.pantalla_Resultado.indexOf("+" || "-" || "*" || "/") == 0){
      this.pantalla_Resultado = this.pantalla_Resultado.substring(1, this.pantalla_Resultado.length);
      this.pantalla_Resultado += valor;
    }else{
      this.pantalla_Resultado += valor;
    }
  
  this.actualizarPantalla_O(this.pantalla_Operacion);
  this.actualizarPantalla_R(this.pantalla_Resultado);
}

escribirComa () {
  if (this.pantalla_Resultado.indexOf(".") === -1) {
    this.pantalla_Resultado += ".";
  }
  this.actualizarPantalla_R(this.pantalla_Resultado);
}

resultado(){

}

}
