class calculadora {
  calculadora() {
    this.pantallaOperacion = "";
    this.operacion = "+";
    this.resultado = "0";
    this.contadorOperacion = 0;
  }
}

let calcular = function (valor) {
    //let r = this.resultado.length();
  if (this.contadorOperacion < 1) {
    acumular(valor);
  } else {
    let pantallaO = parseFloat(this.resultado);
    let res = parseFloat(this.pantallaOperacion);
    this.operacion = valor;
    alert("res:" + res);
    alert(pantallaO);
    let cadena = "+-*/=";
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
      default:
        res = "";
    }
    res = res.toString();
    this.pantallaOperacion = res;
    this.resultado = "0";
    actualizarPantalla_R(this.resultado);
    actualizarPantalla_O(this.operacion);
  }
};

let acumular = function (valor) {
  this.contadorOperacion = this.contadorOperacion + 1;
  pantallaOperacion = resultado;
  actualizarPantalla_R(valor);
};

let actualizarPantalla_O = function (valor) {
  document.querySelector(".pantalla--arriba").textContent = valor;
};

let actualizarPantalla_R = function (valor) {
  document.querySelector(".pantalla--abajo").textContent = valor;
};

let borrarPantalla = function () {
  this.pantallaOperacion = "";
  this.resultado = "0";
  actualizarPantalla_O(this.pantallaOperacion);
  actualizarPantalla_R(resultado);
};

let corregir = function () {
  if (this.resultado.length > 0 && this.resultado != "0") {
    this.resultado = this.resultado.substring(0, this.resultado.length - 1);
  }
  actualizarPantalla_O(this.pantallaOperacion);
  actualizarPantalla_R(resultado);
};

let escribirNumero = function (valor) {
  if (this.contadorOperacion > 0) {
    calcular();
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
  actualizarPantalla_O(this.pantallaOperacion);
  actualizarPantalla_R(this.resultado);
};

let escribirComa = function (valor) {
  if (resultado.indexOf(".") === -1) {
    resultado += ".";
  }
  actualizarPantalla_R(this.resultado);
};

let mostraResultado = function () {};
