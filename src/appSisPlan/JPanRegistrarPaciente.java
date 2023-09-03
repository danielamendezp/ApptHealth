
package appSisPlan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import sisPlanBLL.DivisionTerritorialBLL;
import sisPlanBLL.PacienteBLL;
import sisPlanBLL.ProfesionBLL;
import sisPlanEntidades.ENivelEscolaridad;
import sisPlanEntidades.Provincia;
import sisPlanEntidades.Canton;
import sisPlanEntidades.Distrito;
import sisPlanEntidades.EIngresos;
import sisPlanEntidades.Paciente;
import sisPlanEntidades.Profesion;


/**
 *
 * @author Daniela Fabiola
 */
public class JPanRegistrarPaciente extends javax.swing.JPanel {

private DivisionTerritorialBLL oDivisionTerritorialBLL = new DivisionTerritorialBLL();

    public JPanRegistrarPaciente() {
        initComponents();
        this.cargarComboProvincias();
        cargarFechas();
        cargarNivelEscolaridad();
        cargarIngresos();
        cargarOcupaciones();
        //Agrupar botones para evitar multiples selecciones
        botGroupGenero.add(this.jRdoBotFemenino);
        botGroupGenero.add(this.jRdoBotMasculino);
    }
    
    private void cargarNivelEscolaridad(){
        //AGREGAR A COMBO BOX DE ESCOLARIDAD
        //Se agregan manualmente los valores en los combo box
        
         for (ENivelEscolaridad oEstado : ENivelEscolaridad.values()){
            jCboEscolaridad.addItem(oEstado);
        }
        
    }
    private void cargarFechas(){
         /************************************************************************************
        ** *                          LLENAR VARIOS COMBO BOX                             ***
        *************************************************************************************/
        //Se agregan manualmente los valores en los combo box
        
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        for (String mese : meses) {
            this.jCboMesNacimiento.addItem(mese);
        }

        Integer[] annos = new Integer[74];
        for (int i = 0; i < 74; i++) {
            annos[i] = 2023 - i;
            this.jCboAnnoNacimiento.addItem(String.valueOf(annos[i]));
        }
        
        Integer[] dias = new Integer[31];
        for (int i = 0; i < 31; i++) {
            dias[i] = 1+i;
            this.jCboDiaNacimiento.addItem(String.valueOf(dias[i]));
        }
    }
    
    private void cargarIngresos(){
         /************************************************************************************
        ** *                       AGREGAR A LISTA DE INGRESOS                            ***
        *************************************************************************************/
//        DefaultListModel<String> representa una lista de elementos se utiliza para mostrar datos en componentes de interfaz de usuario como JList.
        DefaultListModel<EIngresos> model = new DefaultListModel<>();
        model.addElement(EIngresos.N1);
        model.addElement(EIngresos.N2);
        model.addElement(EIngresos.N3);
        model.addElement(EIngresos.N4);
        JList<EIngresos> list = new JList<>(model);
        
        this.jLstNivelIngresos.setModel(model);   
    }
    
    private void cargarOcupaciones(){
        
        /************************************************************************************
        ***                        AGREGAR A OCUPACIONES                                  ***
        *************************************************************************************/
        //DefaultListModel<String> representa una lista de elementos se utiliza para mostrar datos en componentes de interfaz de usuario como JList.
        ProfesionBLL profesion= new ProfesionBLL();
        List<Profesion> listaProfesiones = profesion.getListaProfesion();
        DefaultListModel<Profesion> modeloProfesiones = new DefaultListModel<>();

        for (Profesion profesione : listaProfesiones) {
            modeloProfesiones.addElement(profesione);
        }

        JList<Profesion> jLstProfesiones = new JList<>(modeloProfesiones);
        this.jLstProfesion.setModel(jLstProfesiones.getModel());
    }
    
     private void cargarComboProvincias() {
        // Limpiar el combo de selección de provincias
        jCboProvincia.removeAllItems();

        // Obtener la lista de provincias desde el objeto oDivisionTerritorialBLL
        List<Provincia> listaProvincias = this.oDivisionTerritorialBLL.getListaProvincias();

        // Iterar sobre la lista de provincias y agregar cada provincia al combo
        for (Provincia provincia : listaProvincias) {
            // Aquí, por defecto, se utilizará el método 'toString()' de la clase Provincia
            // para mostrar la representación de texto de cada provincia en el combo.
            // Si deseas mostrar un atributo específico, por ejemplo, el nombre de la provincia,
            // puedes usar 'provincia.getNombre()' en lugar de 'provincia.toString()'.
            jCboProvincia.addItem(provincia);
        }

        // Establecer la selección del combo en -1 para que no haya una opción preseleccionada.
        jCboProvincia.setSelectedIndex(-1);
    }


    /**
     * Cargar Cantones
     */
    private void cargarComboCantones() {
        //Limpiar el commbo
        jCboCanton.removeAllItems();

        //Iterar la colección devuelta
        for (Canton oCanton : oDivisionTerritorialBLL.getListaCantones(
                ((Provincia)jCboProvincia.getSelectedItem()).getNumeroProvincia())) {
            jCboCanton.addItem(oCanton);
        }
    }

    /**
     * Cargar Distritos
     */
    private void cargarComboDistritos() {
        //Limpiar el commbo
        jCboDistrito.removeAllItems();

        //Iterar la colección devuelta
        for (Distrito oDistrito :
             oDivisionTerritorialBLL.getListaDistritos( 
                     ((Provincia)jCboProvincia.getSelectedItem()).getNumeroProvincia(),
                     ((Canton)jCboCanton.getSelectedItem()).getNumeroCanton() ) ) {
            jCboDistrito.addItem(oDistrito);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botGroupGenero = new javax.swing.ButtonGroup();
        jTxtNombrePaciente = new javax.swing.JTextField();
        jTxtCedula = new javax.swing.JTextField();
        jCboAnnoNacimiento = new javax.swing.JComboBox<>();
        jCboMesNacimiento = new javax.swing.JComboBox<>();
        jCboDiaNacimiento = new javax.swing.JComboBox<>();
        jRdoBotFemenino = new javax.swing.JRadioButton();
        jRdoBotMasculino = new javax.swing.JRadioButton();
        jCboCanton = new javax.swing.JComboBox<>();
        jCboProvincia = new javax.swing.JComboBox<>();
        jCboDistrito = new javax.swing.JComboBox<>();
        jTxtDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTxtTelefonoHabitacion = new javax.swing.JTextField();
        jTxtTelefonoCelular = new javax.swing.JTextField();
        jTxtTelefonoOficina = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLstNivelIngresos = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLstProfesion = new javax.swing.JList<>();
        jLabel17 = new javax.swing.JLabel();
        jCboEscolaridad = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jBotRegistrar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(102, 0, 204));

        jTxtNombrePaciente.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jTxtCedula.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jCboAnnoNacimiento.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jCboMesNacimiento.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jCboDiaNacimiento.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jRdoBotFemenino.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jRdoBotFemenino.setForeground(new java.awt.Color(0, 0, 0));
        jRdoBotFemenino.setText("F");

        jRdoBotMasculino.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jRdoBotMasculino.setForeground(new java.awt.Color(0, 0, 0));
        jRdoBotMasculino.setText("M");

        jCboCanton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jCboCanton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCboCantonFocusLost(evt);
            }
        });

        jCboProvincia.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jCboProvincia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCboProvinciaFocusLost(evt);
            }
        });

        jCboDistrito.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jTxtDireccion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre y Apellidos");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Número de Cédula");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Fecha de Nacimiento");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Día");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Mes");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Año");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Género");

        jTxtTelefonoHabitacion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jTxtTelefonoCelular.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jTxtTelefonoOficina.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Teléfono Habitación");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Teléfono Celular");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Teléfono Oficina");

        jLstNivelIngresos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jLstNivelIngresos);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Dirección");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Provincia");

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Cantón");

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Distrito");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Señas");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Profesión");

        jLstProfesion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(jLstProfesion);

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Nivel de Ingresos");

        jCboEscolaridad.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Nivel de Escolaridad");

        jBotRegistrar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jBotRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/agregar-usuario_1.png"))); // NOI18N
        jBotRegistrar.setText("Registrar");
        jBotRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotRegistrarActionPerformed(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/salida.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel19MousePressed(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/SICEMPEQUENNO.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTxtTelefonoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtTelefonoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(95, 95, 95)
                                            .addComponent(jLabel5))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(211, 211, 211)
                                            .addComponent(jLabel6))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jCboDiaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jRdoBotFemenino)
                                            .addGap(49, 49, 49)
                                            .addComponent(jRdoBotMasculino))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jCboMesNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCboAnnoNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jTxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtTelefonoOficina, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(153, 153, 153)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jLabel13)
                                        .addComponent(jCboCanton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jCboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel12)
                                    .addComponent(jCboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jCboEscolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBotRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(465, 465, 465)
                        .addComponent(jLabel19)))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jCboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jCboCanton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addComponent(jLabel15)
                        .addGap(3, 3, 3)
                        .addComponent(jTxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel16))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jCboEscolaridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel19)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBotRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel3)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel7)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTxtTelefonoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTxtTelefonoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTxtTelefonoOficina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(186, 186, 186))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTxtNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jTxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCboDiaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboMesNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboAnnoNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRdoBotFemenino)
                            .addComponent(jRdoBotMasculino))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBotRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotRegistrarActionPerformed
        
        try {
            //**        Se genera el try catch para evitar ingresas datos vacios           **
            if (jTxtNombrePaciente.getText().isEmpty() && this.jTxtCedula.getText().isEmpty() 
                && this.jTxtTelefonoCelular.getText().isEmpty() 
                && this.jTxtDireccion.getText().isEmpty()
                && jLstNivelIngresos.getSelectedValue() == null && jCboProvincia.getSelectedItem()==null
                && jLstProfesion.getSelectedValue() == null && jCboCanton.getSelectedItem()==null && jCboDistrito.getSelectedItem()==null
                && (this.jRdoBotFemenino.isSelected() || this.jRdoBotMasculino.isSelected())) {
                
                JOptionPane.showMessageDialog(null, "Faltan Datos");
                throw new NullPointerException();
                
             //**************************************************************************************************** 
            }else{
                if(jTxtCedula.getText().length()>10){
                    JOptionPane.showMessageDialog(null, "Cédula erronea");
                }else{
                    
               
                //**        Adquisicion de datos             ***
                
                
                    String nombre=this.jTxtNombrePaciente.getText();
                    String cedula=this.jTxtCedula.getText();
                    String telefonoCasa=this.jTxtTelefonoHabitacion.getText();
                    String telefonoCelular=this.jTxtTelefonoCelular.getText();
                    String telefonoOficina=this.jTxtTelefonoOficina.getText();
                    String sennas=this.jTxtDireccion.getText();
                    int dia=Integer.parseInt(this.jCboDiaNacimiento.getItemAt(this.jCboDiaNacimiento.getSelectedIndex()));
                    int mes = this.jCboMesNacimiento.getSelectedIndex();
                    int anno=Integer.parseInt(this.jCboAnnoNacimiento.getItemAt(this.jCboAnnoNacimiento.getSelectedIndex()));
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, dia);
                    calendar.set(Calendar.MONTH, mes); 
                    calendar.set(Calendar.YEAR, anno);
                    Date fechaNacimiento = calendar.getTime();
                    char genero = 'F';
                    if (this.jRdoBotFemenino.isSelected()) {
                        genero = 'F';
                    } else if (this.jRdoBotMasculino.isSelected()) {
                        genero = 'M';
                    }
                    Provincia provincia= this.jCboProvincia.getItemAt(jCboProvincia.getSelectedIndex());
                    Canton canton= this.jCboCanton.getItemAt(jCboCanton.getSelectedIndex());
                    Distrito distrito= this.jCboDistrito.getItemAt(jCboDistrito.getSelectedIndex());
                    Profesion profesion = jLstProfesion.getSelectedValue();
                    ENivelEscolaridad escolaridad = (ENivelEscolaridad) this.jCboEscolaridad.getSelectedItem();
                    EIngresos ingresos = (EIngresos) this.jLstNivelIngresos.getSelectedValue();
                    
                    if(this.jTxtTelefonoHabitacion.getText().isEmpty() || this.jTxtTelefonoOficina.getText().isEmpty()){
                        telefonoCasa="null";
                        telefonoOficina="null";
                    }
                //**************************************************************************************************** 
                    
                //**              Instanciacion de Paciente             **
                    Paciente paciente=new Paciente(cedula, nombre, fechaNacimiento, genero, telefonoCelular, telefonoCasa, telefonoOficina, provincia, canton, distrito, sennas,  escolaridad, ingresos, profesion);
                    PacienteBLL accionPaciente= new PacienteBLL();
                    accionPaciente.agregarPaciente(paciente);
                //**************************************************************************************************** 
            } }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Faltan Datos");
            return;
        }
    }//GEN-LAST:event_jBotRegistrarActionPerformed

    private void jCboProvinciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboProvinciaFocusLost
        // TODO add your handling code here:
        if (jCboProvincia.getSelectedIndex() >= 0){
            cargarComboCantones();
        }
    }//GEN-LAST:event_jCboProvinciaFocusLost

    private void jCboCantonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCboCantonFocusLost
         if (jCboCanton.getSelectedIndex() >= 0){
            cargarComboDistritos();
        }
    }//GEN-LAST:event_jCboCantonFocusLost

    private void jLabel19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MousePressed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_jLabel19MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup botGroupGenero;
    private javax.swing.JButton jBotRegistrar;
    private javax.swing.JComboBox<String> jCboAnnoNacimiento;
    private javax.swing.JComboBox<Canton> jCboCanton;
    private javax.swing.JComboBox<String> jCboDiaNacimiento;
    private javax.swing.JComboBox<Distrito> jCboDistrito;
    private javax.swing.JComboBox jCboEscolaridad;
    private javax.swing.JComboBox<String> jCboMesNacimiento;
    private javax.swing.JComboBox<Provincia> jCboProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<EIngresos> jLstNivelIngresos;
    private javax.swing.JList<Profesion> jLstProfesion;
    private javax.swing.JRadioButton jRdoBotFemenino;
    private javax.swing.JRadioButton jRdoBotMasculino;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTxtCedula;
    private javax.swing.JTextField jTxtDireccion;
    private javax.swing.JTextField jTxtNombrePaciente;
    private javax.swing.JTextField jTxtTelefonoCelular;
    private javax.swing.JTextField jTxtTelefonoHabitacion;
    private javax.swing.JTextField jTxtTelefonoOficina;
    // End of variables declaration//GEN-END:variables
}
