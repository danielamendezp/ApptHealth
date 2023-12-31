/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package appSisPlan;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sisPlanBLL.CategoriaServicioMedicoBLL;
import sisPlanBLL.ServiciosMedicosBLL;
import sisPlanBLL.UsuarioBLL;
import sisPlanEntidades.CategoriaServicioMedico;
import sisPlanEntidades.ERolUsuario;
import sisPlanEntidades.ServiciosMedicos;
import sisPlanEntidades.Usuario;
import sisPlanUtilitario.UtilitarioVentana;

/**
 *
 * @author Daniela Fabiola
 */
public class jDlgMantenimientoUsuario extends javax.swing.JDialog {
    UsuarioBLL accionUsuario=new UsuarioBLL();
    /**
     * Creates new form jDlgMantenimientoServiciosMedicos
     */
    public jDlgMantenimientoUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarERolUsuario();
    }
    
    public void cargarERolUsuario(){
        jCboRolUsuario.addItem(ERolUsuario.ADMINISTRADOR);
        jCboRolUsuario.addItem(ERolUsuario.CITAS);
        jCboRolUsuario.addItem(ERolUsuario.DOCTOR);
        jCboRolUsuario.addItem(ERolUsuario.MANTENIMIENTO);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBotConsultar = new javax.swing.JButton();
        jBotEliminar = new javax.swing.JButton();
        jBotModificar = new javax.swing.JButton();
        jBotAgregar = new javax.swing.JButton();
        jCboRolUsuario = new javax.swing.JComboBox<>();
        jTxtUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTxtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jBotConsultar.setBackground(new java.awt.Color(255, 255, 255));
        jBotConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/Consultar.png"))); // NOI18N
        jBotConsultar.setText("Consultar");
        jBotConsultar.setFocusable(false);
        jBotConsultar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBotConsultar.setMaximumSize(new java.awt.Dimension(70, 60));
        jBotConsultar.setMinimumSize(new java.awt.Dimension(70, 60));
        jBotConsultar.setPreferredSize(new java.awt.Dimension(70, 60));
        jBotConsultar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBotConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotConsultarActionPerformed(evt);
            }
        });

        jBotEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBotEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/Eliminar.png"))); // NOI18N
        jBotEliminar.setText("Desactivar");
        jBotEliminar.setFocusable(false);
        jBotEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBotEliminar.setMaximumSize(new java.awt.Dimension(70, 60));
        jBotEliminar.setMinimumSize(new java.awt.Dimension(70, 60));
        jBotEliminar.setPreferredSize(new java.awt.Dimension(70, 60));
        jBotEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBotEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotEliminarActionPerformed(evt);
            }
        });

        jBotModificar.setBackground(new java.awt.Color(255, 255, 255));
        jBotModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/Modificar.png"))); // NOI18N
        jBotModificar.setText("Modificar");
        jBotModificar.setFocusable(false);
        jBotModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBotModificar.setMaximumSize(new java.awt.Dimension(70, 60));
        jBotModificar.setMinimumSize(new java.awt.Dimension(70, 60));
        jBotModificar.setPreferredSize(new java.awt.Dimension(70, 60));
        jBotModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBotModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotModificarActionPerformed(evt);
            }
        });

        jBotAgregar.setBackground(new java.awt.Color(255, 255, 255));
        jBotAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/Nuevo.png"))); // NOI18N
        jBotAgregar.setText("Agregar");
        jBotAgregar.setFocusable(false);
        jBotAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBotAgregar.setMaximumSize(new java.awt.Dimension(70, 60));
        jBotAgregar.setMinimumSize(new java.awt.Dimension(70, 60));
        jBotAgregar.setPreferredSize(new java.awt.Dimension(70, 60));
        jBotAgregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBotAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotAgregarActionPerformed(evt);
            }
        });

        jCboRolUsuario.setEditable(true);
        jCboRolUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jCboRolUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboRolUsuarioActionPerformed(evt);
            }
        });

        jTxtUsername.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Rol Usuario");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Username");

        jTxtNombre.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Nombre ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBotConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jBotAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jBotModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jBotEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCboRolUsuario, javax.swing.GroupLayout.Alignment.TRAILING, 0, 297, Short.MAX_VALUE)
                            .addComponent(jTxtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTxtUsername, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBotConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(jBotAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBotModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBotEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboRolUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBotConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotConsultarActionPerformed
       
        if(jTxtUsername.getText().length()>3){
            Usuario user = null;
            try {
                user = accionUsuario.buscarUsuarioUserName(jTxtUsername.getText());
            } catch (ParseException ex) {
                Logger.getLogger(jDlgMantenimientoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTxtNombre.setText(user.getNombreUsuario());
            jCboRolUsuario.setSelectedItem((ERolUsuario)ERolUsuario.buscsarNivel(user.getRolUsuario().getCodigo()));
        }
            
    }//GEN-LAST:event_jBotConsultarActionPerformed

    private void jBotEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotEliminarActionPerformed
         jDlgInactivarUsuarios user = null;
         user= new jDlgInactivarUsuarios(null, true);
         UtilitarioVentana.centrarVentanaJDialog(user);
    }//GEN-LAST:event_jBotEliminarActionPerformed

    private void jBotModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotModificarActionPerformed
        JDlgActualizarUsuario user = null;
        try {
            user= new JDlgActualizarUsuario(null, true);
        } catch (ParseException ex) {
            Logger.getLogger(jDlgMantenimientoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
       UtilitarioVentana.centrarVentanaJDialog(user);
    }//GEN-LAST:event_jBotModificarActionPerformed

    private void jBotAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotAgregarActionPerformed
        JDlgCrearUsuario user = null;
        try {
            user= new JDlgCrearUsuario(null, true);
        } catch (ParseException ex) {
            Logger.getLogger(jDlgMantenimientoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
       UtilitarioVentana.centrarVentanaJDialog(user);
    }//GEN-LAST:event_jBotAgregarActionPerformed

    private void jCboRolUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboRolUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboRolUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(jDlgMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDlgMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDlgMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDlgMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDlgMantenimientoUsuario dialog = new jDlgMantenimientoUsuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBotAgregar;
    private javax.swing.JButton jBotConsultar;
    private javax.swing.JButton jBotEliminar;
    private javax.swing.JButton jBotModificar;
    private javax.swing.JComboBox<ERolUsuario> jCboRolUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTxtNombre;
    private javax.swing.JTextField jTxtUsername;
    // End of variables declaration//GEN-END:variables
}
