/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package appSisPlan;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sisPlanBLL.UsuarioBLL;
import sisPlanEntidades.ERolUsuario;
import sisPlanEntidades.Usuario;
import sisPlanUtilitario.UtilitarioVentana;

/**
 *
 * @author Daniela Fabiola
 */
public class jDlgUsuario extends javax.swing.JDialog {
int intento=0;
    /**
     * Creates new form jDlgUsuario
     */
    public jDlgUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jButton1 = new javax.swing.JButton();
        jTxtUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/Aceptar.png"))); // NOI18N
        jButton1.setText("Sign In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTxtUsername.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtUsernameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Password");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Crear Usuario");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jLabel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel3KeyPressed(evt);
            }
        });

        jPassword.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/usuario (4).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel3KeyPressed
       JDlgCrearUsuario oJDlgCrearUsuario=null;
        try {
            oJDlgCrearUsuario = new JDlgCrearUsuario(null, true);
        } catch (ParseException ex) {
            Logger.getLogger(jDlgUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        UtilitarioVentana.centrarVentanaJDialog(oJDlgCrearUsuario);
    }//GEN-LAST:event_jLabel3KeyPressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        JDlgCrearUsuario oJDlgCrearUsuario=null;
        try {
            oJDlgCrearUsuario = new JDlgCrearUsuario(null, true);
        } catch (ParseException ex) {
            Logger.getLogger(jDlgUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        UtilitarioVentana.centrarVentanaJDialog(oJDlgCrearUsuario);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UsuarioBLL usuario= new UsuarioBLL();
       intento+=1;
       char[] password = jPassword.getPassword();
       String passwordString = new String(password); // Convertir el array de caracteres a un String
       // Obtener la fecha y hora actual del sistema como LocalDateTime
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Convertir LocalDateTime a Instant
        Instant instant = fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant();
        // Crear un objeto Date a partir del Instant
        Date date = Date.from(instant);
        
    try {
        if(usuario.buscarUsuarioUserName(jTxtUsername.getText())!=null && usuario.buscarUsuarioContrasenna(passwordString)!=null&&usuario.buscarUsuarioEstado(jTxtUsername.getText())&&intento<4){            
             JOptionPane.showMessageDialog(null, "Ingreso exitoso");
            Usuario user=usuario.buscarUsuarioUserName(jTxtUsername.getText());
            Usuario actualizarFecha= new Usuario(user.getCodigo(), user.getIdUsuario(), user.getNombreUsuario(), user.getClaveUsuario(), user.getRolUsuario(), user.getEstadoUsuario(), date);
            usuario.modificarUsuario(actualizarFecha);
           intento=0;
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    } catch (ParseException ex) {
        Logger.getLogger(jDlgUsuario.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
       UsuarioBLL usuario= new UsuarioBLL();
       intento+=1;
       char[] password = jPassword.getPassword();
       String passwordString = new String(password); // Convertir el array de caracteres a un String
       // Obtener la fecha y hora actual del sistema como LocalDateTime
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Convertir LocalDateTime a Instant
        Instant instant = fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant();
        // Crear un objeto Date a partir del Instant
        Date date = Date.from(instant);
        
    try {
        if(usuario.buscarUsuarioUserName(jTxtUsername.getText())!=null && usuario.buscarUsuarioContrasenna(passwordString)!=null&&usuario.buscarUsuarioEstado(jTxtUsername.getText())&&intento<4){            
             JOptionPane.showMessageDialog(null, "Ingreso exitoso");
            Usuario user=usuario.buscarUsuarioUserName(jTxtUsername.getText());
            Usuario actualizarFecha= new Usuario(user.getCodigo(), user.getIdUsuario(), user.getNombreUsuario(), user.getClaveUsuario(), user.getRolUsuario(), user.getEstadoUsuario(), date);
            usuario.modificarUsuario(actualizarFecha);
            intento=0;
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
            if(intento>4){
                JOptionPane.showMessageDialog(null, "Sobrepaso la cantidad de intentos");
                System.exit(0);
            }
        }
    } catch (ParseException ex) {
        Logger.getLogger(jDlgUsuario.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jPasswordActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        jFrmMenuPrincipal menu= new jFrmMenuPrincipal();
        menu.setVisible(false);
        menu.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        jFrmMenuPrincipal menu= new jFrmMenuPrincipal();
        menu.setVisible(false);
        menu.dispose();
        System.exit(0);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jTxtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtUsernameActionPerformed
        jPassword.requestFocus();
    }//GEN-LAST:event_jTxtUsernameActionPerformed

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
            java.util.logging.Logger.getLogger(jDlgUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDlgUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDlgUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDlgUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDlgUsuario dialog = new jDlgUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JTextField jTxtUsername;
    // End of variables declaration//GEN-END:variables
}
