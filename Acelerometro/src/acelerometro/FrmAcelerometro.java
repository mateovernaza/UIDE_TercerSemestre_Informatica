/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acelerometro;

/**
 *
 * @author Mateo
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.*;
import javax.swing.Timer;

public class FrmAcelerometro extends javax.swing.JFrame {

//    Se inicializan las variables necesarias
    private CommPortIdentifier idPort;
    private SerialPort puertoSerial;
    public OutputStream salida;
    public InputStream entrada;
    private String nombre;
    public int dato , nBytes;
    int posicion , contador , verificar;
    String codigo = "";
    String valor_x = "";
    String valor_y = "";
    String valor_z = "";
    byte[] bufferLectura = new byte[21];
    
    Timer temporizador = new Timer(100 , new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            
//            Recibe el codigo que viene de arduino
            try 
            {
                while(entrada.available() > 0)
                {
                    nBytes = entrada.read( bufferLectura );
                }
                codigo = new String(bufferLectura).trim();
            } 
            catch( IOException i ) {}
            System.out.println(codigo);
            
//            Se analiza cada caracter del codigo
            for (posicion = 0 ; posicion < codigo.length() ; posicion += 1)
            {
                System.out.println(codigo.charAt(posicion));
                
//                Se verifica si hay una letra x seguida de un grupo de caracteres que sean menos de 6, que terminen en una "y" y que no se pase de la
//                longitud del codigo, si cumple las condiciones se imprime en el text field txtx el valor entre estas letras
                if(codigo.charAt(posicion) == 'x')
                {
                    contador = posicion;
                    verificar = 0;
                    while(contador < posicion + 6 && contador < codigo.length() - 1 && verificar == 0)
                    {
                        contador += 1;
                        if(codigo.charAt(contador) == 'y')
                        {
                            valor_x = codigo.substring(posicion + 1 , contador);
                            verificar = 1;
                        }
                    }
                    if(verificar == 1)
                    {
                        txtx.setText(valor_x);
                        System.out.println(valor_x);
                    }
                }

//                Se verifica si hay una letra y seguida de un grupo de caracteres que sean menos de 6, que terminen en una "z" y que no se pase de la
//                longitud del codigo, si cumple las condiciones se imprime en el text field txty el valor entre estas letras
                if(codigo.charAt(posicion) == 'y')
                {
                    contador = posicion;
                    verificar = 0;
                    while(contador < posicion + 6 && contador < codigo.length() - 1 && verificar == 0)
                    {
                        contador += 1;
                        if(codigo.charAt(contador) == 'z')
                        {
                            valor_y = codigo.substring(posicion + 1 , contador);
                            verificar = 1;
                        }
                    }
                    if(verificar == 1)
                    {
                        txty.setText(valor_y);
                        System.out.println(valor_y);
                    }
                }
                
//                Se verifica si hay una letra z seguida de un grupo de caracteres que sean menos de 6, que terminen en una "t" y que no se pase de la
//                longitud del codigo, si cumple las condiciones se imprime en el text field txtz el valor entre estas letras
                if(codigo.charAt(posicion) == 'z')
                {
                    contador = posicion;
                    verificar = 0;
                    while(contador < posicion + 6 && contador < codigo.length() - 1 && verificar == 0)
                    {
                        contador += 1;
                        if(codigo.charAt(contador) == 't')
                        {
                            valor_z = codigo.substring(posicion + 1 , contador);
                            verificar = 1;
                        }
                    }
                    if(verificar == 1)
                    {
                        txtz.setText(valor_z);
                        System.out.println(valor_z);
                    }
                }
            }
        }
    });
    public FrmAcelerometro()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtx = new javax.swing.JTextField();
        txty = new javax.swing.JTextField();
        txtz = new javax.swing.JTextField();
        lblx = new javax.swing.JLabel();
        lbly = new javax.swing.JLabel();
        lblz = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtxActionPerformed(evt);
            }
        });

        lblx.setText("x");

        lbly.setText("y");

        lblz.setText("z");

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblx)
                            .addComponent(lbly)
                            .addComponent(lblz))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtx, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(txty)
                            .addComponent(txtz)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btnIniciar)))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblx))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbly))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblz))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnIniciar)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtxActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        
//        Se abre la comunicacion entre java y arduino
        try
        {
            nombre = "COM6";//Este es el nombre del puerto del arduino debe ser cambiado seg?n corresponda
            idPort = CommPortIdentifier.getPortIdentifier(nombre);
            puertoSerial = (SerialPort) idPort.open("Comunicacion Serial" , 2000);
            entrada = puertoSerial.getInputStream();
            salida = puertoSerial.getOutputStream();
            System.out.println("Puerto " + nombre + " iniciado ...");
     
            try 
            {//los valores que se encuentran a continuaci?n son los par?metros de la comunicaci?n serial, deben ser los mismos en el arduino
                puertoSerial.setSerialPortParams( 9600 ,
                SerialPort.DATABITS_8 ,
                SerialPort.STOPBITS_1 ,
                SerialPort.PARITY_NONE );
            } catch( UnsupportedCommOperationException e ) {}
    
        } 
        catch (Exception e) 
        {
            System.out.println("Error en iniciarPuerto() \n" + e);
        }
        
//        Se inicia el temporizador
        temporizador.start();
    }//GEN-LAST:event_btnIniciarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAcelerometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAcelerometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAcelerometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAcelerometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAcelerometro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JLabel lblx;
    private javax.swing.JLabel lbly;
    private javax.swing.JLabel lblz;
    private javax.swing.JTextField txtx;
    private javax.swing.JTextField txty;
    private javax.swing.JTextField txtz;
    // End of variables declaration//GEN-END:variables
}
