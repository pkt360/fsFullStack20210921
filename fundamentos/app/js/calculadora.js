class calculadora{
    constructor(){
        this.pantallaOperacion = '0';
        this.operacion = '+';
        this.resultado = 0;
    }
}

calcular(operador){
    let pantallaOperacion = parseFloat(this.pantallaOperacion);
    let resultado = parseFloat(this.resultado);

    switch(this.operacion){
        case '+':
            resultado += pantallaOperacion;
            break;
        case '-':
            resultado -= pantallaOperacion;
            break;
        case '*':
            resultado *= pantallaOperacion;
            break;
        case '/':
            resultado /= pantallaOperacion;
            break;
    }

    this.operador = operador;
    this.resultado = resultado;
    this.pantallaOperacion = '0';
}

actualizarPantalla(){
    pantalla_o.textContent = this.pantallaOperacion;
    pantalla_r.textContent = this.resultado
}

borrarPantalla(){
    this.pantallaOperacion = 0;
    this.resultado = 0;
}

retroceso(){
    if(this.pantallaOperacion.length > 1 && this.pantallaOperacion != 0){
        this.pantallaOperacion = this.pantallaOperacion.substring(0, this.pantallaOperacion.length -1);
    }
}

escribirNumero(evento){
    if(this.pantallaOperacion == 0){
        this.pantallaOperacion = evento.target.value;
    }else{
        this.pantallaOperacion += evento.target.value;
    }
    actualizarPantalla();
}


mostrarOperacion(n){
    
}

mostraResultado(){

}