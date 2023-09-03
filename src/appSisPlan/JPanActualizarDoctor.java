/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appSisPlan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import sisPlanBLL.CategoriaEspecialidadesBLL;
import sisPlanBLL.DivisionTerritorialBLL;
import sisPlanBLL.DoctorBLL;
import sisPlanBLL.EspecialidadBLL;
import sisPlanEntidades.Provincia;
import sisPlanEntidades.Canton;
import sisPlanEntidades.CategoriaEspecialidades;
import sisPlanEntidades.Distrito;
import sisPlanEntidades.Doctor;
import sisPlanEntidades.Especialidad;


/**
 *
 * @author Daniela Fabiola
 */
public class JPanActualizarDoctor extends javax.swing.JPanel {

private DivisionTerritorialBLL oDivisionTerritorialBLL = new DivisionTerritorialBLL();
Canton canton;
Distrito distrito;

    public JPanActualizarDoctor() {
        initComponents();
        this.cargarComboProvincias();
        cargarFechas();
        cargarCategoriaEspecialidad();
        //Agrupar botones para evitar multiples selecciones
        botGroupGenero.add(this.jRdoBotFemenino);
        botGroupGenero.add(this.jRdoBotMasculino);
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
    
    private void cargarCategoriaEspecialidad(){
        //DefaultListModel<String> representa una lista de elementos se utiliza para mostrar datos en componentes de interfaz de usuario como JList.
        CategoriaEspecialidadesBLL catEspecialidad= new CategoriaEspecialidadesBLL();
        List<CategoriaEspecialidades> listaCategoriaEspecialidades = catEspecialidad.getListaCategoriaEspecialidades();
        DefaultListModel<CategoriaEspecialidades> modeloCatEspecialidad = new DefaultListModel<>();

        for (CategoriaEspecialidades catEspecialidad1 : listaCategoriaEspecialidades) {
            modeloCatEspecialidad.addElement(catEspecialidad1);
        }

        JList<CategoriaEspecialidades> jLstEspecialidades= new JList<>(modeloCatEspecialidad);
        this.jLstCategoriaEspecialidad.setModel(jLstEspecialidades.getModel());
    }
    
    private void cargarEspecialidades(){
        
        /************************************************************************************
        ***                        AGREGAR A OCUPACIONES                                  ***
        *************************************************************************************/
        //DefaultListModel<String> representa una lista de elementos se utiliza para mostrar datos en componentes de interfaz de usuario como JList.
        EspecialidadBLL especialidad= new EspecialidadBLL();
        List<Especialidad> listaEspecialidades = especialidad.getListaEspecialidad(this.jLstCategoriaEspecialidad.getSelectedValue().getIdCategoria());
        DefaultListModel<Especialidad> modeloEspecialidad = new DefaultListModel<>();

        for (Especialidad especialidad1 : listaEspecialidades) {
            modeloEspecialidad.addElement(especialidad1);
        }

        JList<Especialidad> jLstEspecialidades= new JList<>(modeloEspecialidad);
        this.jLstEspecialidad.setModel(jLstEspecialidades.getModel());
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
        jTxtNombreDoctor = new javax.swing.JTextField();
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
        jLstEspecialidad = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLstCategoriaEspecialidad = new javax.swing.JList<>();
        jBotRegistrar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jTxtNumColegiado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(102, 0, 204));

        jTxtNombreDoctor.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jTxtCedula.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTxtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCedulaActionPerformed(evt);
            }
        });

        jCboAnnoNacimiento.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jCboMesNacimiento.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jCboDiaNacimiento.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jRdoBotFemenino.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jRdoBotFemenino.setText("F");

        jRdoBotMasculino.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
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
        jLabel1.setText("Nombre y Apellidos");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Número de Cédula");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Fecha de Nacimiento");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Día");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("Mes");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("Año");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Género");

        jTxtTelefonoHabitacion.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jTxtTelefonoCelular.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jTxtTelefonoOficina.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Teléfono Habitación");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Teléfono Celular");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Teléfono Oficina");

        jLstEspecialidad.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jLstEspecialidad);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("Dirección");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setText("Provincia");

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setText("Cantón");

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel14.setText("Distrito");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setText("Señas");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setText("Categoria Especialidad");

        jLstCategoriaEspecialidad.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLstCategoriaEspecialidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLstCategoriaEspecialidadFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jLstCategoriaEspecialidad);

        jBotRegistrar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jBotRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/actualizar_1.png"))); // NOI18N
        jBotRegistrar.setText("Actualizar");
        jBotRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotRegistrarActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setText("Número de Colegiado");

        jTxtNumColegiado.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTxtNumColegiado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNumColegiadoActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setText("Especialidad");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/salida.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel21MousePressed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/SICEMPEQUENNO.png"))); // NOI18N

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(28, 28, 28)
                                        .addComponent(jCboDiaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(237, 237, 237)
                                        .addComponent(jLabel4)
                                        .addGap(34, 34, 34)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jCboMesNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCboAnnoNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel5)
                                        .addGap(89, 89, 89)
                                        .addComponent(jLabel6))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(jTxtNumColegiado, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTxtNombreDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(237, 237, 237)
                        .addComponent(jRdoBotFemenino)
                        .addGap(18, 18, 18)
                        .addComponent(jRdoBotMasculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel16))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel12)
                                    .addComponent(jCboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(333, 333, 333))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel13)
                                    .addComponent(jCboCanton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jCboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(22, 22, 22))))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtTelefonoHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addComponent(jTxtTelefonoCelular)
                    .addComponent(jTxtTelefonoOficina))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBotRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(473, 473, 473)
                .addComponent(jLabel21)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
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
                                        .addComponent(jCboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(jTxtNumColegiado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtNombreDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jCboDiaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboMesNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboAnnoNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(5, 5, 5)
                        .addComponent(jTxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jRdoBotFemenino)
                            .addComponent(jRdoBotMasculino))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTxtTelefonoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtTelefonoCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtTelefonoOficina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jBotRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBotRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotRegistrarActionPerformed
        //VALIDACIONES 
        try {
            //**        Se genera el try catch para evitar ingresas datos vacios           **
            if (jTxtNombreDoctor.getText().isEmpty() && this.jTxtCedula.getText().isEmpty()
                && this.jTxtTelefonoCelular.getText().isEmpty() && jTxtNumColegiado.getText().isEmpty()
                && this.jTxtDireccion.getText().isEmpty() 
                && jLstEspecialidad.getSelectedValue() == null && jCboProvincia.getSelectedItem()==null
                && jLstCategoriaEspecialidad.getSelectedValue() == null && jCboCanton.getSelectedItem()==null && jCboDistrito.getSelectedItem()==null
                && (this.jRdoBotFemenino.isSelected() || this.jRdoBotMasculino.isSelected())) {
                
                JOptionPane.showMessageDialog(null, "Faltan Datos");
                throw new NullPointerException();
                
             //**************************************************************************************************** 
            }else{
                //**        Adquisicion de datos             ***
                
                
                    String nombre=this.jTxtNombreDoctor.getText();
                    String cedula=this.jTxtCedula.getText();
                    int numColegiado=Integer.parseInt(this.jTxtNumColegiado.getText());
                    String telefonoHabitacion=this.jTxtTelefonoHabitacion.getText();
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
                    Especialidad especialidad = jLstEspecialidad.getSelectedValue();
                    CategoriaEspecialidades catEspecialidad = jLstCategoriaEspecialidad.getSelectedValue();
                    
                    if(this.jTxtTelefonoHabitacion.getText().isEmpty() || this.jTxtTelefonoOficina.getText().isEmpty()){
                        telefonoHabitacion="null";
                        telefonoOficina="null";
                    }
                //**************************************************************************************************** 
                    
                //**              Instanciacion de Doctor             **
                    Doctor doctor= new Doctor(numColegiado, cedula, nombre, fechaNacimiento, genero, telefonoCelular, telefonoOficina, telefonoOficina, provincia, canton, distrito, sennas, especialidad,"12:00md - 1:00pm");
                    DoctorBLL accionDoctor= new DoctorBLL();
                    accionDoctor.modificarDoctor(doctor);
                //**************************************************************************************************** 
            }
            
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

    private void jLstCategoriaEspecialidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLstCategoriaEspecialidadFocusLost
         if (jLstCategoriaEspecialidad.getSelectedIndex() >= 0){
              cargarEspecialidades();
         }
       
    }//GEN-LAST:event_jLstCategoriaEspecialidadFocusLost

    private void jTxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCedulaActionPerformed
        DoctorBLL accionDoctor= new DoctorBLL();
        if(jTxtCedula.getText().length()==9){
            if(accionDoctor.buscarDoctorCedula(jTxtCedula.getText())!=null){
                
            Doctor Doctor=accionDoctor.buscarDoctor(this.jTxtCedula.getText()); 
            
           this.jCboProvincia.setSelectedItem(Doctor.getProvincia().getNumeroProvincia());
              jTxtNombreDoctor.setText(Doctor.getNombreApellidos());
              jTxtTelefonoCelular.setText(Doctor.getTelefonoCelular());
              jTxtDireccion.setText(Doctor.getSennas());
            if(!Doctor.getTelefonoCasa().equals("null")){
                 jTxtTelefonoHabitacion.setText(Doctor.getTelefonoCasa());
            }
           
             if(!Doctor.getTelefonoOficina().equals("null")){
                 jTxtTelefonoOficina.setText(Doctor.getTelefonoOficina());
            }
            canton=Doctor.getCanton();
            distrito=Doctor.getDistrito();
            
            
            // Supongamos que tienes un objeto Date llamado 'fecha'
            Date fecha = Doctor.getFechaNacimiento(); // Obtiene la fecha de nacimiento del Doctor

            // Crear un objeto Calendar y establecer la fecha en el objeto Date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);

            // Obtener el día, mes y año como enteros
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH); // Los meses en Calendar están basados en 0, por lo que se suma 1
            int anio = calendar.get(Calendar.YEAR);

            for(int i=0; i<74; i++){
                int variable=2023-i;
                if(variable==anio){
                    jCboAnnoNacimiento.setSelectedIndex(i);
                }
            }
            jCboDiaNacimiento.setSelectedIndex(dia-1);
            jCboMesNacimiento.setSelectedIndex(mes);

             if(Doctor.getGenero()=='F'){
                 jRdoBotFemenino.setSelected(true);
             }else{
                 jRdoBotMasculino.setSelected(true);
             }
            int indexEspecialidad=Doctor.getEspecialidad().getEspecialidadID()-1;
            jLstEspecialidad.setSelectedIndex(indexEspecialidad);
            jLstEspecialidad.ensureIndexIsVisible(indexEspecialidad);

            int indexCategoria=Doctor.getEspecialidad().getCategoriaEspecialidades()-1;
            jLstCategoriaEspecialidad.setSelectedIndex(indexCategoria);
            jLstCategoriaEspecialidad.ensureIndexIsVisible(indexCategoria);
         
            
            //Ubicar en el combo de Provincia
        for(int i=0; i < jCboProvincia.getItemCount(); i++){
            if ( ((Provincia)jCboProvincia.getItemAt(i)).getIdentificador() == 
                                               Doctor.getProvincia().getIdentificador() ){
                jCboProvincia.setSelectedIndex(i);
                this.cargarComboCantones();
                break;
            }
        }
        //Ubicar en el combo de Cantones
        for (int i = 0; i < jCboCanton.getItemCount(); i++) {
            if (((Canton)jCboCanton.getItemAt(i)).getIdentificador() == Doctor.getCanton().getIdentificador()) {
                jCboCanton.setSelectedIndex(i);
                this.cargarComboDistritos();
                break;
            }
        }
        //Ubicar en el combo de Distritos
        for (int i = 0; i < jCboDistrito.getItemCount(); i++) {
            if (((Distrito)jCboDistrito.getItemAt(i)).getIdentificador() == Doctor.getDistrito().getIdentificador()) {
                jCboDistrito.setSelectedIndex(i);
                break;
            }
        }
        
        
       }else{
            JOptionPane.showMessageDialog(null, "Persona no existe");
        }
            
       }
       
    }//GEN-LAST:event_jTxtCedulaActionPerformed

    private void jTxtNumColegiadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNumColegiadoActionPerformed
        DoctorBLL accionDoctor= new DoctorBLL();
        if(jTxtNumColegiado.getText().length()>3){
            if(accionDoctor.buscarDoctor(jTxtNumColegiado.getText())!=null){
                
            
            Doctor Doctor=accionDoctor.buscarDoctor(this.jTxtNumColegiado.getText()); 
            
           this.jCboProvincia.setSelectedItem(Doctor.getProvincia().getNumeroProvincia());
             jTxtCedula.setText(String.valueOf(Doctor.getCedula()));
              jTxtNombreDoctor.setText(Doctor.getNombreApellidos());
              jTxtTelefonoCelular.setText(Doctor.getTelefonoCelular());
              jTxtDireccion.setText(Doctor.getSennas());
            if(!Doctor.getTelefonoCasa().equals("null")){
                 jTxtTelefonoHabitacion.setText(Doctor.getTelefonoCasa());
            }
           
             if(!Doctor.getTelefonoOficina().equals("null")){
                 jTxtTelefonoOficina.setText(Doctor.getTelefonoOficina());
            }
            canton=Doctor.getCanton();
            distrito=Doctor.getDistrito();
            
            
        // Supongamos que tienes un objeto Date llamado 'fecha'
        Date fecha = Doctor.getFechaNacimiento(); // Obtiene la fecha de nacimiento del Doctor

        // Crear un objeto Calendar y establecer la fecha en el objeto Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        // Obtener el día, mes y año como enteros
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH); // Los meses en Calendar están basados en 0, por lo que se suma 1
        int anio = calendar.get(Calendar.YEAR);
        
        for(int i=0; i<74; i++){
            int variable=2023-i;
            if(variable==anio){
                jCboAnnoNacimiento.setSelectedIndex(i);
            }
        }
        jCboDiaNacimiento.setSelectedIndex(dia-1);
        jCboMesNacimiento.setSelectedIndex(mes);
        
         if(Doctor.getGenero()=='F'){
             jRdoBotFemenino.setSelected(true);
         }else{
             jRdoBotMasculino.setSelected(true);
         }
        int indexEspecialidad=Doctor.getEspecialidad().getEspecialidadID()-1;
        jLstEspecialidad.setSelectedIndex(indexEspecialidad);
        jLstEspecialidad.ensureIndexIsVisible(indexEspecialidad);
        
        int indexCategoria=Doctor.getEspecialidad().getCategoriaEspecialidades()-1;
        jLstCategoriaEspecialidad.setSelectedIndex(indexCategoria);
        jLstCategoriaEspecialidad.ensureIndexIsVisible(indexCategoria);
         
       }else{
            JOptionPane.showMessageDialog(null, "Persona no existe");
        }
            
       }
       
    }//GEN-LAST:event_jTxtNumColegiadoActionPerformed

    private void jLabel21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MousePressed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_jLabel21MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup botGroupGenero;
    private javax.swing.JButton jBotRegistrar;
    private javax.swing.JComboBox<String> jCboAnnoNacimiento;
    private javax.swing.JComboBox<Canton> jCboCanton;
    private javax.swing.JComboBox<String> jCboDiaNacimiento;
    private javax.swing.JComboBox<Distrito> jCboDistrito;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<CategoriaEspecialidades> jLstCategoriaEspecialidad;
    private javax.swing.JList<Especialidad> jLstEspecialidad;
    private javax.swing.JRadioButton jRdoBotFemenino;
    private javax.swing.JRadioButton jRdoBotMasculino;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTxtCedula;
    private javax.swing.JTextField jTxtDireccion;
    private javax.swing.JTextField jTxtNombreDoctor;
    private javax.swing.JTextField jTxtNumColegiado;
    private javax.swing.JTextField jTxtTelefonoCelular;
    private javax.swing.JTextField jTxtTelefonoHabitacion;
    private javax.swing.JTextField jTxtTelefonoOficina;
    // End of variables declaration//GEN-END:variables
}
