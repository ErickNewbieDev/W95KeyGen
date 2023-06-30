package llaveswindows;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author ErickNewbieDev
 */
public class Interfaz extends javax.swing.JFrame {
    
    //Inicia declaracion de atributos
    boolean servidor = false;
    BaseDatos BD = new BaseDatos();
    //Termina la declaracion de atributos
    
    public Interfaz() {//Constructor de la clase Interfaz
        initComponents();
        this.setTitle("W95 Key Generator V.0.5");
        jlbAviso.setVisible(false);
        jbtReintentar.setVisible(false);
    }//Termina constructor
    
    /*El metodo estado se encarga de inicializar la conexion con la base de datos en cuanto
    el programa arranca, asi si la conexion se logra o no el programa ya sabe que botones
    inutilizar y cuales no*/
    public void estado(){//Inicia metodo estado
        servidor = BD.conectar();//La variable servidor se iguala al valor que devuelve el metodo conectar
        /*Algunos botones se vuelven visibles o invisibles influidos por el valor de la variable
        servidor ya sea en su estado actual o su estado actual negado*/
        jbtReintentar.setVisible(!servidor);
        jbtReintentar.setEnabled(!servidor);
        jbtObtener.setEnabled(servidor);
        if(!servidor){//En caso que la conexion no se pueda establecer se correra offline
            JOptionPane.showMessageDialog(null, "El programa correra offline", "Error al conectar", 
            JOptionPane.INFORMATION_MESSAGE);
        }//Termina if
    }//Termina metodo estado
    
    /*Este metodo se encarga de realizar todas las acciones asignadas al boton de
    generar clave nueva*/
    void darLlave(){//Inicia metodo darLlave
        Random random = new Random();//Se instancia el metodo Random
        int condicion = random.nextInt(2);//Se genera un numero aleatorio entre 0 y 1
        jlbAviso.setVisible(true);//Se hace visible el aviso
        GeneradorClaves Gen = new GeneradorClaves();//Se crea el objeto Gen de la clase GenerarClaves
        switch(condicion){//Con base al numero generado aleatoriamente se elige si se generar una clave retail u OEM
            case 0://En caso que sea 0
                jtfClave.setText(Gen.crearClave());//Se genera una clave retail
                break;//Termina caso 0
            case 1://En caso que sea 1
                jtfClave.setText(Gen.crearOEM());//Se genera una clave OEM
                break;//Termina caso 1
        }//Termina switch
        
        if(servidor){//Si hay conexion con el servidor, se sube la clave a la base de datos
            BD.cargarDatos(jtfClave.getText());
        }//Termina if
    }//Termina metodo darLlave
    
    /*Este metodo se encarga de realizar todas las acciones asignadas al boton de
    obtener clave de la base de datos*/
    void obtenerLlave(){//Inicia metodo obtenerLlave
        Random random = new Random();//Se instancia el metodo Random
        String datos[] = BD.bajarDatos();//Se iguala el arreglo datos a los datos obtenidos de DB
        
        if(datos!=null){/*Si el arreglo datos tiene informacion entonces se elije
            un dato al azar de todos los del arreglo para mostrarse*/
            jlbAviso.setVisible(true);//El aviso se hace visible
            int azar = random.nextInt(datos.length);//Se elige el dato al azar
            jtfClave.setText(datos[azar]);//Se coloca el texto en la interfaz
        }//Termina if
    }//Termina metodo obtenerLlave
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtGenerar = new javax.swing.JButton();
        jbtObtener = new javax.swing.JButton();
        jlbAviso = new javax.swing.JLabel();
        jtfClave = new javax.swing.JTextField();
        jbtPDF = new javax.swing.JButton();
        jbtValidar = new javax.swing.JButton();
        jbtSalir = new javax.swing.JButton();
        jbtReintentar = new javax.swing.JButton();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(java.awt.SystemColor.textHighlight);
        jLabel1.setFont(new java.awt.Font("Dubai Medium", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido al gestor de claves de W95");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 406, -1));

        jbtGenerar.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jbtGenerar.setText("Generar una clave nueva");
        jbtGenerar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGenerarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 180, 40));

        jbtObtener.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jbtObtener.setText("Obtener una de la base de datos");
        jbtObtener.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtObtener.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtObtener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtObtenerActionPerformed(evt);
            }
        });
        jPanel1.add(jbtObtener, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 50, 210, 40));

        jlbAviso.setBackground(java.awt.Color.orange);
        jlbAviso.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jlbAviso.setText("Aquí tienes tu clave:");
        jlbAviso.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlbAviso.setOpaque(true);
        jPanel1.add(jlbAviso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, -1));

        jtfClave.setEditable(false);
        jtfClave.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        jtfClave.setForeground(new java.awt.Color(255, 0, 51));
        jtfClave.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfClave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jtfClave.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(jtfClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 370, -1));

        jbtPDF.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jbtPDF.setText("Generar PDF con las claves");
        jbtPDF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtPDF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPDFActionPerformed(evt);
            }
        });
        jPanel1.add(jbtPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 370, -1));

        jbtValidar.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jbtValidar.setText("Abrir validador de claves");
        jbtValidar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtValidar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jbtValidar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 370, -1));

        jbtSalir.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jbtSalir.setText("Salir del programa");
        jbtSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jbtSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 370, -1));

        jbtReintentar.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jbtReintentar.setText("Reintentar conexion");
        jbtReintentar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtReintentar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtReintentar.setEnabled(false);
        jbtReintentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtReintentarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtReintentar, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 100, 160, -1));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1336598.jpg"))); // NOI18N
        jPanel1.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-760, -120, 1250, 890));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtObtenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtObtenerActionPerformed
        //Se obtienen las llaves de la base de datos
        obtenerLlave();
    }//GEN-LAST:event_jbtObtenerActionPerformed

    private void jbtGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGenerarActionPerformed
        //Se genera una llave nueva
        darLlave();
    }//GEN-LAST:event_jbtGenerarActionPerformed

    private void jbtSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalirActionPerformed
        //Se sale del programa y se termina la conexion con la base de datos
        if(servidor){BD.desconectar();}
        System.exit(0);
    }//GEN-LAST:event_jbtSalirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Se intenta la conexion con la base de datos en cuanto se inicia el programa
        estado();
    }//GEN-LAST:event_formWindowOpened

    private void jbtReintentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtReintentarActionPerformed
        //Se intenta establecer de nuevo conexion con la base de datos
        estado();
        if(servidor){
        JOptionPane.showMessageDialog(null, "De nuevo en linea", "Exito",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbtReintentarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //Se desconecta de la base de datos al cerrar el program
        if(servidor){BD.desconectar();}
    }//GEN-LAST:event_formWindowClosing

    private void jbtPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPDFActionPerformed
        // TODO add your handling code here:
        CrearDocumentos docs = new CrearDocumentos();
        docs.setClaves(BD.bajarDatos());
        docs.generarPDF();
    }//GEN-LAST:event_jbtPDFActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);;
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtGenerar;
    private javax.swing.JButton jbtObtener;
    private javax.swing.JButton jbtPDF;
    private javax.swing.JButton jbtReintentar;
    private javax.swing.JButton jbtSalir;
    private javax.swing.JButton jbtValidar;
    private javax.swing.JLabel jlbAviso;
    private javax.swing.JTextField jtfClave;
    // End of variables declaration//GEN-END:variables
}
