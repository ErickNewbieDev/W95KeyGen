package llaveswindows;

import java.util.Random;

/**
 *
 * @author ErickNewbieDev
 */
public class GeneradorClaves {
    //Declaracion de atriutos
    private Random random;
    private int numeros[], suma;
    private String clave;
    //Termina la declaracion de atributos
    
    public GeneradorClaves(){//Constructor de la clase
        random = new Random();
        numeros = new int [12];
        suma = 0;
        clave = "";
    }//Termina constructor
    
    
    //Este metodo se encarga de la creacion de las claves retail
    public String crearClave(){//Inicia metodo crearClave
        /*El formato que deben llevar las claves retail es:
        XXX-XXXXXXX
        Solo se deben cumplir dos condiciones:
        Los primeros tres numeros no deben ser conformados por el mismo numero, a menos que
        se trate del numero uno.
        Loa siguientes siete numeros pueden ser cualquier numero aleatorio del 1 al 9 mientras que
        al sumar los 7 numeros y dividirlos entre 7 de un numero entero.*/
        /*Se generan los primeros 9 numeros aleatorios y se guardan en un arreglo*/
        for(int i=0;i<9;i++){//Un ciclo for que se repite 9 veces
            numeros[i] = random.nextInt(10);//A cada valor de los numeros se le asigna un numero aleatorio del 0 al 9
            if(i>2){//En cuanto se comienza a generar la segunda parte de los numeros se suman en una sola variable
            suma += numeros[i];
            }//Termina if
        }//Termina for
        
        // Generar el ultimo numero
        /*Se calcula el residuo de la división de la variable suma entre 7.
        Se resta el resultado del residuo anterior de 7. Esto asegura que el valor asignado estará en el rango de 0 a 6.
        */
        numeros[9] = 7 - ((suma) % 7);
        if (numeros[9] < 0) {//En caso que el numero obtenido sea menor que 0
            numeros[9] += 7;//Se le suma un 7 para evitar errores
        }//Termina if
        
        /*Ahora para revisar si la clave esta en una lista negra, se comparan los 3 primeros numeros 
        para saber si son iguales, y en caso de que sean iguales se repite la generacion.
        Sin embargo, en caso de que la suma de los 3 numeros iguales sea diferente de 3 entonces no es necesario
        pues si la suma es 3 significa que los 3 numeros son 1, y este numero no está en la lista negra*/
        if (numeros[0] == numeros[1] && numeros[1] == numeros[2] && numeros[0]+numeros[1]+numeros[2]!=3){
        crearClave();//Se vuelve a llamar al propio metodo por recursividad
        }
        
        //Construir la cadena con el formato XXX-XXXXXXX
        clave = String.format("%d%d%d-%d%d%d%d%d%d%d", numeros[0], numeros[1], numeros[2], numeros[3], numeros[4],
                numeros[5], numeros[6], numeros[7], numeros[8], numeros[9]);
        return clave;//Se retorna el valor de la cadena
    }//Termina metodo crearClave
    
    
    public String crearOEM(){//Inicia metodo crearOEM
    /*El formato que deben llevar las claves OEM es:
    XXXXX-OEM-XXXXXXX-XXXXX
    En este caso se deben cumplir mas condiciones, en este caso son 4:
    Los primeros 5 numeros son una fecha en formato DDDAA donde los primeros 3 numeros son un numero del 001
    al 365 pues parece tratarse del dia del año en el que se genero la clave, mientras que los 2 numeros que siguen
    son el año en que se genero desde el 95 hasta el 2002 (02).
    
    La palabra OEM siempre esta ahi por defecto.
        
    Los siguientes 7 numeros siguen las mismas reglas que las claves retail, pueden ser cualquier numero aleatorio del
    1 al 9 mientras que al sumar los 7 numeros y dividirlos entre 7 de un numero entero la unica diferencia es que en 
    estas claves deben tener por primer numero el numero cero.
    
    Los ultimos 5 discos pueden ser 5 valores cuales quiera, mientras sean numeros y solo numeros.
    */
    String DDDYY = "";//Declaramos la variable DDDYY
    int dia = random.nextInt(365) + 1;//Generamos el dia que va desde el 1 al 365.
    int anio = random.nextInt(5) + 95;//Y generamos el año que va desde el 95 al 2002, en este caso solo del 95 al 99.
        if(dia<100){//Si el dia del año es menor que 100, por ejemplo 89
            DDDYY += "0";//Se le agrega un 0 para que sea 089 y cuadre con el formato
            if(dia<10){//Pero si ademas de ser menor que 100 es menor que 10 se agrega otro cero para que cuadre con el formato
            DDDYY += "0";//Por ejemplo, si tuvieramos el numero 9 ahora seria 009
            }//Termina if
        }//Termina if
    /*Se combina el valor del dia y el año en la variable DDDYY*/
    DDDYY += String.valueOf(dia);
    DDDYY += String.valueOf(anio);
    
    /*El primero de estos numeros siempre debe ser cero*/
    numeros[0] = 0;//Se iguala el primer valor a cero
    /*Se generan los primeros 6 numeros aleatorios y se guardan en un arreglo*/
    for(int i=1;i<6;i++){//Un ciclo for que se repite 6 veces
        numeros[i] = random.nextInt(10);//A cada valor de los numeros se le asigna un numero aleatorio del 0 al 9
            suma += numeros[i];//Se suman en una sola variable los numeros generados
        }//Termina for
    
        // Generar el ultimo numero
        /*Se calcula el residuo de la división de la variable suma entre 7.
        Se resta el resultado del residuo anterior de 7. Esto asegura que el valor asignado estará en el rango de 0 a 6.
        */
        numeros[6] = 7 - ((suma) % 7);
        if (numeros[6] < 0) {//En caso que el numero obtenido sea menor que 0
            numeros[6] += 7;//Se le suma un 7 para evitar errores
        }//Termina if
        
        for(int i=7; i<12; i++){//Un ciclo for que se repite 5 veces
            numeros[i] = random.nextInt(10);//A cada valor de los numeros se le asigna un numero aleatorio del 0 al 9
        }//Termina for
        
        //Construir la cadena con el formato XXXXX-OEM-XXXXXXX-XXXXX
        clave = DDDYY+String.format("-OEM-%d%d%d%d%d%d%d-%d%d%d%d%d",numeros[0],numeros[1],numeros[2],numeros[3],numeros[4],
                numeros[5],numeros[6],numeros[7],numeros[8],numeros[9],numeros[10],numeros[11]); 
        return clave;//Se retorna el valor de la cadena
    }//Termina metodo crearOEM
}//Termina la clase GeneradorClaves
