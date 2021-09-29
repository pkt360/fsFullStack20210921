// Ejercicio 1
function ejercicio_1(){
    let numero = prompt ("Introduce un numero");

    aleatorio = (Math.random() * numero);
    redondeado = Math.round(aleatorio);
    alert(redondeado);
}

// Ejercicio 2
function ejercicio_2(){
var aleatorio = (Math.random() * 100);
var redondeado = Math.round(aleatorio);
var c = 10
do{
    var numero = prompt("Adivina el número");
    c --;
    if (numero == redondeado){
        var acertado = 1;
    }
    else{
        if (numero > redondeado){
            alert("Lo siento, " + numero + " no es. Has escrito un número mayor, te quedan: " + c +" intentos");
        }
        else{
            alert("Lo siento, " + numero + " no es. Has escrito un número menor, te quedan: " + c +" intentos");
        }    
    }
   
}while(numero != redondeado && c > 0);

if (acertado == 1){
    console.log("Felicidades, has acertado, el número es: " + acertado);
}
else{
    console.log("Lo siento, no has acertado, el número era: " + acertado);
}

}





//Ejercicio_3
//Crear una función que devuelva un array con el numero de elementos indicado, inicializados al valor suministrado

function ejercico_3(){
    var numero = prompt("Introduce el número de arrays");
   
    array = Array(numero) = new Array[numero];
    document.write();
   
    /* for (let i = 0; i < numero; i++){
        Array(i) = new Array[i];
     
    }
    */
}



//Ejercicio_4
//Crear una función que devuelva un determinado número de números primos.
function ejercicio_4(){
    let numero = prompt("¿Cuántos números primos quieres?");
    calcula(numero);

    function primo(num){
        if(num == 0 || num == 1) {
            return false;
        }
        for (let i =2; i < num; i++){
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }

    function calcula(){
        for (let i = 0; i < numero; i++ ){
            let n = primo(i);
            if (n == true){
                document.write("El número " + i + " es primo <br/>");
            }
        }        
    }
}


//Ejercicio_5
//Crear una función que valide un NIF
function ejercicio_5(){
    let dni = prompt("Introduce un dni");

    regex = /^\d{1,8}[A-Z]$/;

    validar(regex, dni);

    function validar(regex, dni){
        if (regex.test(dni)){
            var aviso ="El dni es válido";
        }
        else{
            var aviso = "El dni introducido no es válido";
        }
        console.log(aviso);
    }
}


//ejercici_6   
//Definir una función que determine si la cadena de texto que se le pasa como parámetro es un palíndromo, es decir, si se lee de la misma forma desde la izquierda y desde la derecha. Ejemplo de palíndromo complejo: "La ruta nos aporto otro paso natural". 
function ejercicio_6(){
 
        let cadena = prompt("Escribe un palíndromo");
        function espacios(){
            return cadena.split(" ").join("");
        }

        function invertir(){
            let cadenaInvertida = "";
            for (let i = cadena.length-1; i>=0; i--){
                cadenaInvertida += cadena.charAt(i);
            }
            return cadenaInvertida
        }

        let cadenaPal = espacios(cadena).toUpperCase();
        document.write(cadenaPal === invertir(cadenaPal));
}

ejercicio_3();