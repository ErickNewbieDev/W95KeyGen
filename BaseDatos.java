package llaveswindows;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ErickNewbieDev
 */
public class BaseDatos {
    //Inicia declaracion de atributos
    private Connection conexion;
    private Statement sentencia;
    private PreparedStatement sentenciapreparada;
    private boolean verificacion;
    private String query, valores[];
    //Termina la declaracion de atributos
    
    public BaseDatos(){//Constructor de la clase BaseDatos
        conexion = null;
        sentencia = null;
        sentenciapreparada = null;
        query = "";
        verificacion = false;
    }//Termina constructor
    
    //Este metodo se encarga de intentar la conexion a la base de datos
    public boolean conectar(){//Inicia metodo Conectar
    try {//Inicia try
        /*Si se logra establecer la conexion el boleano verificacion se convierte en true*/
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/w95k", "root", "");
        verificacion = true;
    }//Termina try
    catch (ClassNotFoundException | SQLException e) {//Inicia catch
        /*Si no se logra establecer la conexion el boleano verificacion se convierte en false
        y se le informa al usuario que no hubo una conexion exitosa*/
        /*JOptionPane.showMessageDialog(null, "Imposible establecer conexion con la base de datos"
                +"\nError:"+ e.getMessage(), "SIN CONEXION", JOptionPane.ERROR_MESSAGE);*/
        verificacion = false;
    }//Termina catch
    //Se retorna el valor de la variable verificacion
    return verificacion;
    }//Termina metodo Conectar
    
    //Este metodo se encarga de intentar la desconexion de la base de datos
    public boolean desconectar(){//Inicia metodo Conectar
    try{//Inicia try
        /*Si se logra una conexion exitosa, se cierra la misma y el boleano verifiacion
        se convierte en false*/
        conexion.close();
        verificacion = false;
    }//Termina try
    catch (SQLException ex) {//Inicia catch
        /*En caso de no lograr la conexion el boleano se convierte en true y se le informa al
        usuario que no fue posible desconectarse de lal base de datos*/
        JOptionPane.showMessageDialog(null, "Imposible terminar la conexion con la base de datos"
                +"\nError:"+ ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        verificacion = true;
    }//Termina catch
    //Se retorna el valor de la variable verificacion
    return verificacion;
    }//Termina metodo Desonectar
    
    //Este metodo se encarga de descargar la informacion de la base de datos
    public String[] bajarDatos() {//Inicia metodo bajarDatos
        //La variable query se encarga de determinar la accion
        query = "SELECT claves FROM claves95";
        try {
            /*Se intenta leer el contenido de la base de datos, en primera 
            instancia para contar la cantiad de registros que hay en la base
            de datos y asi preparar el arreglo al tamaño adecuado y
            possteriormente se lee la informacion para almacenarla en el arreglo*/
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);
            
            //Se cuenta el numero de filas que hay en la base de datos
            int conteoFilas = 0;
            if (resultado.last()) {
                conteoFilas = resultado.getRow();
                resultado.beforeFirst();
            }
            
            //Crear el arreglo con el tamaño adecuado
            valores = new String[conteoFilas];
            
            //Obtener los valores de la columna especificada y guardarlos en el arreglo
            int i = 0;
            while (resultado.next()) {
                valores[i] = resultado.getString("claves");
                i++;
            }
            resultado.close();//Se cierra el resultset
            sentencia.close();//Se cierra el sentence
        }//Termia try
        catch (SQLException ex){//Inicia catch
            /*En caso que el programa se encuentre con algun problema al intentar leer los datos
            se le informara al usuario*/
            JOptionPane.showMessageDialog(null, "Error al obtener los datos"
                    + "\n(Codigo de error: "+ex.getErrorCode()+")", "ERROR", JOptionPane.ERROR_MESSAGE);
        }//Termina catch
        //Retorna el arreglo lleno de los valores obtenidos de la base de datos
        return valores;
    }//Termina metodo bajarDatos
    
    //Este metodo se encarga de agregar los registros a la base de datos
    public void cargarDatos(String clave){//Inicia metodo cargarDatos
        /*El metodo recibe como parametro el dato que se guardara en la base
        de datos, en este caso la clave de activacion, el query se encarga de
        guardar la instruccion a realizar y se ejecuta la accion.*/
        query = "INSERT INTO claves95 (claves) values (?)";
        try{//Inicia try
            /*El try hace la prueba de enviar los datos a la base de datos*/
            sentenciapreparada = conexion.prepareStatement(query);
            sentenciapreparada.setString (1, clave);
            sentenciapreparada.execute();
            sentenciapreparada.close();
        }//Termina try
        catch(SQLException ex){//Inicia catch
            /*En caso de algun error, se le informa al usuario*/
            JOptionPane.showMessageDialog(null, "Imposible cargar los datos a la base de datos"
                +"\nError:"+ ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }//Termina catch
    }//Termina metodo cargarDatos
}//Termina clase BaseDatos
