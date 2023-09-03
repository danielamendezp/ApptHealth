
package appSisPlan;

import java.awt.Container;
import java.awt.Dimension;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import sisPlanBLL.CategoriaEnfermedadBLL;
import sisPlanBLL.CategoriaServicioMedicoBLL;
import sisPlanBLL.DoctorBLL;
import sisPlanBLL.EnfermedadBLL;
import sisPlanBLL.FormasFarmaceuticasBLL;
import sisPlanBLL.ProductoBLL;
import sisPlanBLL.RegistroPadecimientoBLL;
import sisPlanBLL.ServiciosMedicosBLL;
import sisPlanBLL.TitularFarmaceuticaBLL;
import sisPlanEntidades.CategoriaEnfermedades;
import sisPlanEntidades.CategoriaServicioMedico;
import sisPlanEntidades.Doctor;
import sisPlanEntidades.Enfermedad;
import sisPlanEntidades.FormasFarmaceuticas;
import sisPlanEntidades.Producto;
import sisPlanEntidades.RegistroPadecimiento;
import sisPlanEntidades.ServiciosMedicos;
import sisPlanEntidades.TitularFarmaceutica;
import sisPlanUtilitario.Pila;

/**
 *
 * @author Daniela Fabiola
 */
public class JDlgPadecimiento extends javax.swing.JDialog {
    int numExpediente =0;
    public JDlgPadecimiento(java.awt.Frame parent, boolean modal) throws ParseException {
        super(parent, modal);
        initComponents();
        cargarCategoriasEnfermedad();
        cargarCategoriasServiciosMedicos();
        cargarTitularFarmaceutica();
        cargarFormasFarmaceuticas();
        cargarDoctores();
        setSize(1200, 850);
        setLocationRelativeTo(null);
        setNumExpediente();
    }
/**
 * Asigna el número de expediente si la pila no está vacía.
 */
    public void setNumExpediente() {
        if(!Pila.pilaVacia()){
            numExpediente =Integer.parseInt(Pila.sacarUltimoElemento());
        }else{
            JOptionPane.showMessageDialog(null, "No se obtuvo ningun expediente");
            dispose();
        }
    }
    
    /**
 * Carga la lista de doctores en un componente gráfico de lista.
 * @throws ParseException Si ocurre un error al analizar fechas.
 */
    private void cargarDoctores() throws ParseException{
        jLstProducto.setPreferredSize(new Dimension(444,154));
        Container contenedor = jLstProducto.getParent(); // Obtén el contenedor que contiene el JComboBox
        contenedor.revalidate(); // Notificar al contenedor que recalcule el diseño
        //DefaultListModel<String> representa una lista de elementos se utiliza para mostrar datos en componentes de interfaz de usuario como JList.
        DoctorBLL Doctor= new DoctorBLL();
        List<Doctor> listaDoctores = Doctor.getListaDoctors();
        DefaultListModel<Doctor> modeloDoctor = new DefaultListModel<>();

        for (Doctor Doctor1 : listaDoctores) {
            modeloDoctor.addElement(Doctor1);
        }

        JList<Doctor> jLstDoctores= new JList<>(modeloDoctor);
        this.jLstDoctores.setModel(jLstDoctores.getModel());
    }

 /**
 * Carga las categorías de enfermedades en un JComboBox.
 */   
    public void cargarCategoriasEnfermedad(){
        jCboCategoriaEnfermedades.setPreferredSize(new Dimension(410, 31));
        Container contenedor = jCboCategoriaEnfermedades.getParent(); // Obtén el contenedor que contiene el JComboBox
        contenedor.revalidate(); // Notificar al contenedor que recalcule el diseño

        CategoriaEnfermedadBLL categoriaEnfermedad= new CategoriaEnfermedadBLL();
        List<CategoriaEnfermedades> listaCategoria=categoriaEnfermedad.getListaCategoriaEnfermedad();
        for(CategoriaEnfermedades categoria: listaCategoria){
            jCboCategoriaEnfermedades.addItem(categoria);
        }
    }
  /**
 * Carga las categorías de enfermedades ordenadas en función de un carácter.
 */  
    public void cargarCategoriasEnfermedadOrden(char[] caracter){
        CategoriaEnfermedadBLL categoriaEnfermedad= new CategoriaEnfermedadBLL();
        List<CategoriaEnfermedades> listaCategoria=categoriaEnfermedad.getListaCategoriaEnfermedadesORDENADO(caracter);
        for(CategoriaEnfermedades categoria: listaCategoria){
            jCboCategoriaEnfermedades.addItem(categoria);
        }
    }
 /**
 * Carga la lista de enfermedades en un componente gráfico de lista.
 */   
    private void cargarEnfermedades(){
         jLstPadecimiento.setPreferredSize(new Dimension(410,112));
        Container contenedor = jLstPadecimiento.getParent(); // Obtén el contenedor que contiene el JComboBox
        contenedor.revalidate(); // Notificar al contenedor que recalcule el diseño
        //DefaultListModel<String> representa una lista de elementos se utiliza para mostrar datos en componentes de interfaz de usuario como JList.
        EnfermedadBLL enfermedad= new EnfermedadBLL();
        List<Enfermedad> listaEnfermedades = enfermedad.getListaEnfermedades(((CategoriaEnfermedades)this.jCboCategoriaEnfermedades.getSelectedItem()).getIdCategoria());
        DefaultListModel<Enfermedad> modeloEnfermedad = new DefaultListModel<>();
         
        for (Enfermedad Enfermedad1 : listaEnfermedades) {
            modeloEnfermedad.addElement(Enfermedad1);
        }
        
        JList<Enfermedad> jLstEnfermedad= new JList<>(modeloEnfermedad);
        this.jLstPadecimiento.setModel(jLstEnfermedad.getModel());
    }
 /**
 * Carga las categorías de servicios médicos en un JComboBox.
 */   
    public void cargarCategoriasServiciosMedicos() {
        jCboCategoriaServicioMedico.setPreferredSize(new Dimension(410, 31));
        Container contenedor = jCboCategoriaServicioMedico.getParent(); // Obtén el contenedor que contiene el JComboBox
        contenedor.revalidate(); // Notificar al contenedor que recalcule el diseño
        CategoriaServicioMedicoBLL categoriaServicioMedico = new CategoriaServicioMedicoBLL();
        List<CategoriaServicioMedico> listaCategoria = categoriaServicioMedico.getListaCategoriaServicioMedico();
        for (CategoriaServicioMedico categoria : listaCategoria) {
            jCboCategoriaServicioMedico.addItem(categoria);
        }
    }
 /**
 * Carga la lista de servicios médicos en un componente gráfico de lista.
 */   
    private void cargarServiciosMedicos() {
        jLstServicioMedico.setPreferredSize(new Dimension(410,112));
        Container contenedor = jLstServicioMedico.getParent(); // Obtén el contenedor que contiene el JComboBox
        contenedor.revalidate(); // Notificar al contenedor que recalcule el diseño
        ServiciosMedicosBLL servicioMedico = new ServiciosMedicosBLL();
        List<ServiciosMedicos> listaServiciosMedicos = servicioMedico.getListaServiciosMedicos(String.valueOf(((CategoriaServicioMedico)this.jCboCategoriaServicioMedico.getSelectedItem()).getIdCategoria()));
        DefaultListModel<ServiciosMedicos> modeloServicioMedico = new DefaultListModel<>();

        for (ServiciosMedicos servicio : listaServiciosMedicos) {
            modeloServicioMedico.addElement(servicio);
        }

        JList<ServiciosMedicos> jLstServiciosMedicos = new JList<>(modeloServicioMedico);
        this.jLstServicioMedico.setModel(jLstServiciosMedicos.getModel());
    }
/**
 * Carga las formas farmacéuticas en un JComboBox.
 */    
    public void cargarFormasFarmaceuticas(){
        jCboFormaFarmaceutica.setPreferredSize(new Dimension(410, 31));
        Container contenedor = jCboFormaFarmaceutica.getParent(); // Obtén el contenedor que contiene el JComboBox
        contenedor.revalidate(); // Notificar al contenedor que recalcule el diseño
        FormasFarmaceuticasBLL formasFarmaceuticas = new FormasFarmaceuticasBLL();
        List<FormasFarmaceuticas> listaFormasFarmaceuticas = formasFarmaceuticas.getListaFormaFarmaceutica();

        for (FormasFarmaceuticas formaFarmaceutica : listaFormasFarmaceuticas) {
            jCboFormaFarmaceutica.addItem(formaFarmaceutica);
        }

    }
/**
 * Carga los titulares de farmacéuticas en un componente gráfico de lista.
 */
    public void cargarTitularFarmaceutica(){
        TitularFarmaceuticaBLL titularFarmaceutica = new TitularFarmaceuticaBLL();
        List<TitularFarmaceutica> listaTitularesFarmaceuticas = titularFarmaceutica.getListaTitularFarmaceuticas();
        DefaultListModel<TitularFarmaceutica> modeloTitularesFarmaceuticas = new DefaultListModel<>();

        for (TitularFarmaceutica titular : listaTitularesFarmaceuticas) {
            modeloTitularesFarmaceuticas.addElement(titular);
        }
    }
/**
 * Carga la lista de productos en un componente gráfico de lista.
 * @throws ParseException Si ocurre un error al analizar fechas.
 */
    public void cargarProductos() throws ParseException{
        jLstProducto.setPreferredSize(new Dimension(410,112));
        Container contenedor = jLstProducto.getParent(); // Obtén el contenedor que contiene el JComboBox
        contenedor.revalidate(); // Notificar al contenedor que recalcule el diseño
        ProductoBLL productoBLL = new ProductoBLL();
        List<Producto> listaProductos = productoBLL.getListaProductos(String.valueOf(((FormasFarmaceuticas)jCboFormaFarmaceutica.getSelectedItem()).getIdForma()));
        DefaultListModel<Producto> modeloProductos = new DefaultListModel<>();

        for (Producto producto : listaProductos) {
            modeloProductos.addElement(producto);
        }

        JList<Producto> jLstProductos = new JList<>(modeloProductos);
        jLstProducto.setModel(jLstProductos.getModel());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCboCategoriaEnfermedades = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLstPadecimiento = new javax.swing.JList<>();
        jCboOrden = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLstProducto = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCboFormaFarmaceutica = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jLstServicioMedico = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCboCategoriaServicioMedico = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLstDoctores = new javax.swing.JList<>();
        jTxtNumColegiado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Padecimiento");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Padecimiento"));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setToolTipText("Padecimiento");
        jPanel2.setAutoscrolls(true);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Padecimiento");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Categoria Padecimiento");

        jCboCategoriaEnfermedades.setEditable(true);
        jCboCategoriaEnfermedades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboCategoriaEnfermedadesItemStateChanged(evt);
            }
        });

        jLstPadecimiento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLstPadecimiento.setDoubleBuffered(true);
        jLstPadecimiento.setDragEnabled(true);
        jLstPadecimiento.setValueIsAdjusting(true);
        jLstPadecimiento.setVerifyInputWhenFocusTarget(false);
        jScrollPane3.setViewportView(jLstPadecimiento);

        jCboOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A-B", "C-D", "E-F", "G-H", "I-J", "K-L", "M-N", "O-P", "Q-R", "S-T", "U-V", "W-X", "Y-Z" }));
        jCboOrden.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboOrdenItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(308, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCboCategoriaEnfermedades, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCboOrden, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCboOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jCboCategoriaEnfermedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto Farmaceutico"));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLstProducto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLstProducto.setDoubleBuffered(true);
        jLstProducto.setDragEnabled(true);
        jLstProducto.setValueIsAdjusting(true);
        jScrollPane2.setViewportView(jLstProducto);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Categoria Producto");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Producto");

        jCboFormaFarmaceutica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboFormaFarmaceuticaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addComponent(jCboFormaFarmaceutica, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCboFormaFarmaceutica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addGap(7, 7, 7))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Servicio Médico"));
        jPanel3.setToolTipText("Padecimiento");

        jScrollPane6.setAutoscrolls(true);
        jScrollPane6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLstServicioMedico.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane6.setViewportView(jLstServicioMedico);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Servicio Médico");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Categoria Servicio Médico");

        jCboCategoriaServicioMedico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboCategoriaServicioMedicoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCboCategoriaServicioMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCboCategoriaServicioMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );

        jLstDoctores.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane4.setViewportView(jLstDoctores);

        jTxtNumColegiado.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTxtNumColegiado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNumColegiadoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Número Colegiado");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Médicos");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/salida.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/anadir (1).png"))); // NOI18N
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTxtNumColegiado, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jTxtNumColegiado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4)
                        .addGap(22, 22, 22))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CategoriaEnfermedades catEnfermedad= null;
        Enfermedad enfermedad=null;
        CategoriaServicioMedico catServicio=  null;
        ServiciosMedicos servicio=null;
        FormasFarmaceuticas forma=  null;
        Producto producto=null;
            
        if(jLstPadecimiento.getSelectedValue()!=null){
            catEnfermedad= ((CategoriaEnfermedades)this.jCboCategoriaEnfermedades.getSelectedItem());
            enfermedad=((Enfermedad)jLstPadecimiento.getSelectedValue());
        }
        
        if(jLstServicioMedico.getSelectedValue()!=null){
             catServicio= (CategoriaServicioMedico)jCboCategoriaServicioMedico.getSelectedItem();
             servicio= jLstServicioMedico.getSelectedValue();
        }
        
        if(jLstProducto.getSelectedValue()!=null){
             forma= (FormasFarmaceuticas)jCboFormaFarmaceutica.getSelectedItem();
             producto=jLstProducto.getSelectedValue();
        }
        
        RegistroPadecimientoBLL registro= new RegistroPadecimientoBLL();
        RegistroPadecimiento pRegistroPadecimiento = null;
        
        // Obtener la fecha y hora actual del sistema como LocalDateTime
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        // Convertir LocalDateTime a Instant
        Instant instant = fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant();
        // Crear un objeto Date a partir del Instant
        Date date = Date.from(instant);
        if(jLstDoctores.getSelectedIndex()>-1){
            try {
                pRegistroPadecimiento = new RegistroPadecimiento(registro.consecutivo(), numExpediente, enfermedad, producto, servicio, jLstDoctores.getSelectedValue(), date);
            } catch (ParseException ex) {
                Logger.getLogger(JDlgPadecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            registro.agregarRegistroPadecimiento(pRegistroPadecimiento);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTxtNumColegiadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNumColegiadoActionPerformed
        DoctorBLL doctorBLL = new DoctorBLL();
        String numColegiadoBuscado = jTxtNumColegiado.getText();
        int indexDoctor = -1;

        if (numColegiadoBuscado != null && !numColegiadoBuscado.isEmpty()) {
            DefaultListModel<Doctor> model = (DefaultListModel<Doctor>) jLstDoctores.getModel();
            int size = model.getSize();

            for (int i = 0; i < size; i++) {
                Doctor doctorEnLista = model.getElementAt(i);
                if (String.valueOf(doctorEnLista.getNumColegiado()).equals(numColegiadoBuscado)) {
                    indexDoctor = i;
                    break;
                }
            }

            if (indexDoctor != -1) {
                jLstDoctores.setSelectedIndex(indexDoctor);
                jLstDoctores.ensureIndexIsVisible(indexDoctor);
            }
        } 
    }//GEN-LAST:event_jTxtNumColegiadoActionPerformed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
       dispose();
    }//GEN-LAST:event_jLabel9MousePressed

    private void jCboCategoriaEnfermedadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboCategoriaEnfermedadesItemStateChanged
        if(jCboCategoriaEnfermedades.getSelectedIndex()>-1){
            cargarEnfermedades();
        }
    }//GEN-LAST:event_jCboCategoriaEnfermedadesItemStateChanged

    private void jCboCategoriaServicioMedicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboCategoriaServicioMedicoItemStateChanged
         if(jCboCategoriaServicioMedico.getSelectedIndex()>-1){
            cargarServiciosMedicos();
        }
    }//GEN-LAST:event_jCboCategoriaServicioMedicoItemStateChanged

    private void jCboOrdenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboOrdenItemStateChanged
       if(jCboOrden.getSelectedIndex()>-1){
           jCboCategoriaEnfermedades.removeAllItems();
           String seleccion=(String)jCboOrden.getSelectedItem();
            // Inicializar el arreglo de caracteres
            char[] caracter = new char[2];

            // Asignar valores a los elementos del arreglo
            caracter[0] = seleccion.charAt(0); // Aquí asumo que el primer carácter está en la posición 0
            caracter[1] = seleccion.charAt(2); // Aquí asumo que el segundo carácter está en la posición 2

             cargarCategoriasEnfermedadOrden(caracter);
       }
    }//GEN-LAST:event_jCboOrdenItemStateChanged

    private void jCboFormaFarmaceuticaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboFormaFarmaceuticaItemStateChanged
       if(jCboFormaFarmaceutica.getSelectedIndex()>0){
           try {
               cargarProductos();
           } catch (ParseException ex) {
               Logger.getLogger(JDlgPadecimiento.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }//GEN-LAST:event_jCboFormaFarmaceuticaItemStateChanged

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
            java.util.logging.Logger.getLogger(JDlgPadecimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgPadecimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgPadecimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgPadecimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgPadecimiento dialog = null;
                try {
                    dialog = new JDlgPadecimiento(new javax.swing.JFrame(), true);
                } catch (ParseException ex) {
                    Logger.getLogger(JDlgPadecimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JComboBox<CategoriaEnfermedades> jCboCategoriaEnfermedades;
    private javax.swing.JComboBox<CategoriaServicioMedico> jCboCategoriaServicioMedico;
    private javax.swing.JComboBox<FormasFarmaceuticas> jCboFormaFarmaceutica;
    private javax.swing.JComboBox<String> jCboOrden;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<Doctor> jLstDoctores;
    private javax.swing.JList<Enfermedad> jLstPadecimiento;
    private javax.swing.JList<Producto> jLstProducto;
    private javax.swing.JList<ServiciosMedicos> jLstServicioMedico;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTxtNumColegiado;
    // End of variables declaration//GEN-END:variables
}
