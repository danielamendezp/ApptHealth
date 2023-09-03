
package appSisPlan;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sisPlanBLL.ExpedienteMedicoBLL;
import sisPlanBLL.PacienteBLL;
import sisPlanBLL.RegistroPadecimientoBLL;
import sisPlanEntidades.ExpedienteMedico;
import sisPlanEntidades.Paciente;
import sisPlanEntidades.RegistroPadecimiento;

/**
 *
 * @author Daniela Fabiola
 */
public class JPanEliminarPaciente extends javax.swing.JPanel {
 PacienteBLL accionPaciente= new PacienteBLL();
 ExpedienteMedicoBLL expediente=new ExpedienteMedicoBLL();
 RegistroPadecimientoBLL registros= new RegistroPadecimientoBLL();
 
    public JPanEliminarPaciente() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBotEliminar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLstPersonasAsociadas = new javax.swing.JList<>();
        jLabel19 = new javax.swing.JLabel();
        jTxtCedula = new javax.swing.JTextField();
        jTxtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Nombre y Apellidos");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Número de Cédula");

        jBotEliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jBotEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/papelera-de-reciclaje.png"))); // NOI18N
        jBotEliminar.setText("Eliminar");
        jBotEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotEliminarActionPerformed(evt);
            }
        });

        jLstPersonasAsociadas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane3.setViewportView(jLstPersonasAsociadas);

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setText("Personas Asociadas");

        jTxtCedula.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTxtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtCedulaKeyPressed(evt);
            }
        });

        jTxtNombre.setEditable(false);
        jTxtNombre.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/SICEMPEQUENNO.png"))); // NOI18N
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTxtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                    .addComponent(jTxtNombre)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBotEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel3)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jBotEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCedulaKeyPressed
        if(jTxtCedula.getText().length()==9){
            Paciente paciente=accionPaciente.buscarPaciente(this.jTxtCedula.getText()); 
            jTxtNombre.setText(paciente.getNombreApellidos());
        }
    }//GEN-LAST:event_jTxtCedulaKeyPressed

    private void jBotEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotEliminarActionPerformed
       if(jTxtCedula.getText().length()==9){
           ExpedienteMedico oExpediente = null;
           try {
               oExpediente = expediente.buscarExpedienteMedicoPorCedula(jTxtCedula.getText());
           } catch (ParseException ex) {
               Logger.getLogger(JPanEliminarPaciente.class.getName()).log(Level.SEVERE, null, ex);
           }
           RegistroPadecimiento oRegistro = null;
           try {
               oRegistro = registros.buscarRegistroPadecimientoPorExpediente(String.valueOf(oExpediente.getNumExpediente()));
           } catch (ParseException ex) {
               Logger.getLogger(JPanEliminarPaciente.class.getName()).log(Level.SEVERE, null, ex);
           }
           if(oExpediente!=null&&oRegistro!=null){
               accionPaciente.eliminarPaciente(this.jTxtCedula.getText());
               registros.eliminarRegistroPadecimiento(String.valueOf(oRegistro.getCodigo()));
               expediente.eliminarExpedienteMedico(String.valueOf(oExpediente.getNumExpediente()));
           }else{
               JOptionPane.showMessageDialog(null, "Datos erroneos");
           }
       }else{
               JOptionPane.showMessageDialog(null, "Datos erroneos");
       }
    }//GEN-LAST:event_jBotEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBotEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jLstPersonasAsociadas;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTxtCedula;
    private javax.swing.JTextField jTxtNombre;
    // End of variables declaration//GEN-END:variables
}