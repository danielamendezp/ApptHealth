/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package appSisPlan;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import sisPlanBLL.FormasFarmaceuticasBLL;
import sisPlanBLL.ProductoBLL;
import sisPlanBLL.TitularFarmaceuticaBLL;
import sisPlanEntidades.FormasFarmaceuticas;
import sisPlanEntidades.Producto;
import sisPlanEntidades.TitularFarmaceutica;
import sisPlanUtilitario.ValidadorFormatos;


/**
 *
 * @author Daniela Fabiola
 */
public class jDlgMantenimientoProductosFarmaceuticos extends javax.swing.JDialog {
    ProductoBLL accionProducto=new ProductoBLL();
    FormasFarmaceuticasBLL forma=new FormasFarmaceuticasBLL();
    TitularFarmaceuticaBLL titular=new TitularFarmaceuticaBLL();
    /**
     * Creates new form jDlgMantenimientoProductosFarmaceuticos
     */
    public jDlgMantenimientoProductosFarmaceuticos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarFormasFarmaceuticas();
        cargarTitularFarmaceutica();
    }
    
    /**
     * Carga las formas farmacéuticas en un JComboBox.
     */
    public void cargarFormasFarmaceuticas() {
        // Crear una instancia del BLL para manejar las formas farmacéuticas
        FormasFarmaceuticasBLL formasFarmaceuticas = new FormasFarmaceuticasBLL();

        // Obtener la lista de formas farmacéuticas
        List<FormasFarmaceuticas> listaFormasFarmaceuticas = formasFarmaceuticas.getListaFormaFarmaceutica();

        // Agregar cada forma farmacéutica al JComboBox
        for (FormasFarmaceuticas formaFarmaceutica : listaFormasFarmaceuticas) {
            jCboFormaFarmaceutica.addItem(formaFarmaceutica);
        }
    }

    /**
     * Carga los titulares de farmacéuticas en un JComboBox.
     */
    public void cargarTitularFarmaceutica() {
        // Crear una instancia del BLL para manejar los titulares de farmacéuticas
        TitularFarmaceuticaBLL titularFarmaceutica = new TitularFarmaceuticaBLL();

        // Obtener la lista de titulares de farmacéuticas
        List<TitularFarmaceutica> listaTitularesFarmaceuticas = titularFarmaceutica.getListaTitularFarmaceuticas();

        // Agregar cada titular de farmacéutica al JComboBox
        for (TitularFarmaceutica titular : listaTitularesFarmaceuticas) {
            jCboTitularFarmaceutica.addItem(titular);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCboFormaFarmaceutica = new javax.swing.JComboBox<>();
        jTxtDescripcion = new javax.swing.JTextField();
        jBotConsultar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jBotEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jBotModificar = new javax.swing.JButton();
        jBotAgregar = new javax.swing.JButton();
        jCboTitularFarmaceutica = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTxtConcentracion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtFracciones = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtNumRegistro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtPresentacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jCboFormaFarmaceutica.setEditable(true);
        jCboFormaFarmaceutica.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jTxtDescripcion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Forma Farmacéutica");

        jBotEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBotEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/Eliminar.png"))); // NOI18N
        jBotEliminar.setText("Eliminar");
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

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Descripción");

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

        jCboTitularFarmaceutica.setEditable(true);
        jCboTitularFarmaceutica.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Titular Farmacéutica");

        jTxtConcentracion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Concentración");

        jTxtFracciones.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Fracciones");

        jTxtNumRegistro.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Numero Registro");

        jTxtPresentacion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Presentación");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCboFormaFarmaceutica, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jBotConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jBotAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jBotModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBotEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtFracciones, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCboTitularFarmaceutica, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtConcentracion, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBotModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jBotAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBotConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBotEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboFormaFarmaceutica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCboTitularFarmaceutica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTxtConcentracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTxtFracciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTxtNumRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTxtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBotConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotConsultarActionPerformed
        Producto producto=accionProducto.buscarProductoPorNombre(jTxtDescripcion.getText());
       if(producto!=null){
        jCboFormaFarmaceutica.setSelectedItem(producto.getFormasFarmaceuticas());
        jCboTitularFarmaceutica.setSelectedItem(producto.getTitularFarmaceutica());
        jTxtConcentracion.setText(producto.getConcentracion());
        jTxtFracciones.setText(producto.getConcentracion());
        jTxtNumRegistro.setText(producto.getNumeroRegistro());
        jTxtPresentacion.setText(producto.getPresentacion());
       }else{
            JOptionPane.showMessageDialog(null, "Faltan Datos");
        }
    }//GEN-LAST:event_jBotConsultarActionPerformed

    private void jBotEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotEliminarActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if(option>1){
            Producto producto=accionProducto.buscarProductoPorNombre(jTxtDescripcion.getText());
            if(producto!=null){
            accionProducto.eliminarProducto(String.valueOf(producto.getIdProducto()));
            }else{
                JOptionPane.showMessageDialog(null, "Faltan Datos");
            }
        }
    }//GEN-LAST:event_jBotEliminarActionPerformed

    private void jBotModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotModificarActionPerformed
       // Obtener la fecha actual en formato LocalDate
        LocalDate currentDate = LocalDate.now();

        // Convertir LocalDate a Date
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(ValidadorFormatos.validarEntero(jTxtFracciones.getText())){
        Producto producto=accionProducto.buscarProductoPorNombre(jTxtDescripcion.getText());
        if(producto!=null&&jTxtDescripcion.getText().length()>0&&jTxtConcentracion.getText().length()>0&&((FormasFarmaceuticas)jCboFormaFarmaceutica.getSelectedItem()).getIdForma()>0
                &&jTxtPresentacion.getText().length()>0&&Integer.parseInt(jTxtFracciones.getText())>0&& jTxtNumRegistro.getText().length()>0 && 
                ((TitularFarmaceutica)jCboTitularFarmaceutica.getSelectedItem()).getIdTitular()>0){
         Producto oProducto= new Producto(producto.getIdProducto(), jTxtDescripcion.getText(), jTxtConcentracion.getText(), 
                 ((FormasFarmaceuticas)jCboFormaFarmaceutica.getSelectedItem()).getIdForma(),jTxtPresentacion.getText() ,Integer.parseInt(jTxtFracciones.getText()), 
                 date, jTxtNumRegistro.getText(), ((TitularFarmaceutica)jCboTitularFarmaceutica.getSelectedItem()).getIdTitular());
         
         accionProducto.modificarProducto(oProducto);
        }else{
            JOptionPane.showMessageDialog(null, "Faltan Datos");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Fracciones escrito incorrectamente");
        }
    }//GEN-LAST:event_jBotModificarActionPerformed

    private void jBotAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotAgregarActionPerformed
        // Obtener la fecha actual en formato LocalDate
        LocalDate currentDate = LocalDate.now();

        // Convertir LocalDate a Date
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(jTxtDescripcion.getText().length()>0&&jTxtConcentracion.getText().length()>0&&((FormasFarmaceuticas)jCboFormaFarmaceutica.getSelectedItem()).getIdForma()>0
                &&jTxtPresentacion.getText().length()>0&&Integer.parseInt(jTxtFracciones.getText())>0&& jTxtNumRegistro.getText().length()>0 && 
                ((TitularFarmaceutica)jCboTitularFarmaceutica.getSelectedItem()).getIdTitular()>0){
            
        
        Producto oProducto= new Producto(accionProducto.consecutivo(), jTxtDescripcion.getText(), jTxtConcentracion.getText(), 
                 ((FormasFarmaceuticas)jCboFormaFarmaceutica.getSelectedItem()).getIdForma(),jTxtPresentacion.getText() ,Integer.parseInt(jTxtFracciones.getText()), 
                 date, jTxtNumRegistro.getText(), ((TitularFarmaceutica)jCboTitularFarmaceutica.getSelectedItem()).getIdTitular());
         accionProducto.agregarProducto(oProducto);
         }else{
            JOptionPane.showMessageDialog(null, "Faltan Datos");
        }
    }//GEN-LAST:event_jBotAgregarActionPerformed

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
            java.util.logging.Logger.getLogger(jDlgMantenimientoProductosFarmaceuticos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDlgMantenimientoProductosFarmaceuticos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDlgMantenimientoProductosFarmaceuticos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDlgMantenimientoProductosFarmaceuticos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDlgMantenimientoProductosFarmaceuticos dialog = new jDlgMantenimientoProductosFarmaceuticos(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<FormasFarmaceuticas> jCboFormaFarmaceutica;
    private javax.swing.JComboBox<TitularFarmaceutica> jCboTitularFarmaceutica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTxtConcentracion;
    private javax.swing.JTextField jTxtDescripcion;
    private javax.swing.JTextField jTxtFracciones;
    private javax.swing.JTextField jTxtNumRegistro;
    private javax.swing.JTextField jTxtPresentacion;
    // End of variables declaration//GEN-END:variables
}
