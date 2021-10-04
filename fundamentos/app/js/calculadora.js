class calculadora{

     calculadora(){
        this.pantallaOperacion = 0;
        this.operacion = '+';
        this.resultado = '0';
    }
     
}
         
let calcular = function(valor){
    let pantallaO = parseFloat(this.resultado);
    let res = parseFloat(this.pantallaOperacion);
    this.operacion = valor;
    alert(pantallaO);
    switch(this.operacion){
        case '+': res += pantallaO;      
            break;
        case '-': res -= pantallaO;
            break;
        case '*': res *= pantallaO;
            break;
        case '/': res /= pantallaO;
            break;
        default: res = pantallaO;
    }
    res = res.toString();
    this.pantallaOperacion = res;
    this.resultado = 0;
    
    actualizarPantalla_R();
    actualizarPantalla_O();
}

let actualizarPantalla_O = function(){
    
    document.querySelector('.pantalla--abajo').textContent = this.resultado;
}

let actualizarPantalla_R = function(){
    document.querySelector('.pantalla--arriba').textContent = this.pantallaOperacion;
}

let borrarPantalla = function(){
    this.pantallaOperacion = 0;
    this.resultado = 0;
    actualizarPantalla_O(); 
    actualizarPantalla_R();
}

let corregir = function(){
    if(this.resultado.length > 1 && this.resultado != 0){
        this.resultado = this.resultado.substring(0, this.resultado.length -1);
    }
    actualizarPantalla_O();
    actualizarPantalla_R();
}

let escribirNumero = function(valor){
        let r = valor.replace("undefined", "");
        if (r === '0'){
           this.resultado = r;
        }
        else{
            this.resultado += r;
        }
        
        this.resultado += r; 
    actualizarPantalla_O();
    actualizarPantalla_R();
}


let mostraResultado = function(){

}


