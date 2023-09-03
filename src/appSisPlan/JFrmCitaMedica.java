/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package appSisPlan;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sisPlanBLL.CategoriaEspecialidadesBLL;
import sisPlanBLL.CitaBLL;
import sisPlanBLL.DoctorBLL;
import sisPlanBLL.EspecialidadBLL;
import sisPlanBLL.PacienteBLL;
import sisPlanEntidades.CategoriaEspecialidades;
import sisPlanEntidades.Cita;
import sisPlanEntidades.Doctor;
import sisPlanEntidades.Especialidad;
import sisPlanEntidades.Paciente;
import sisPlanUtilitario.Formatos;
import sisPlanUtilitario.PilaCita;
import sisPlanUtilitario.UtilitarioVentana;

/**
 *
 * @author Daniela Fabiola
 */
public class JFrmCitaMedica extends javax.swing.JFrame {
    private DefaultTableModel oModeloTablaDatos;
    CitaBLL accionCita= new CitaBLL();
    DoctorBLL Doctor= new DoctorBLL();
    PacienteBLL paciente= new PacienteBLL();
   
    /**
     * Creates new form JFrmCitaMedica
     */
    public JFrmCitaMedica() throws ParseException {
        initComponents();
        cargarFechas();
        cargarHorasInicio();
        cargarCategoriaEspecialidad();
        cargarDoctores();
        inicializarJTable();
        cargarPaciente();
        cargarJTable();
        setSize(1600, 650);
        setLocationRelativeTo(null);
    }
    
    /**
    * Carga la lista de pacientes en un componente gráfico de lista.
    *
    * @throws ParseException Si ocurre un error al analizar fechas.
    */
   public void cargarPaciente() throws ParseException {
       // Obtener la lista de pacientes desde el objeto "paciente"
       List<Paciente> listaCategoria = paciente.getListaPacientes();

       // Crear un modelo de lista para pacientes
       DefaultListModel<Paciente> modeloPacientees = new DefaultListModel<>();

       // Agregar cada paciente al modelo de lista
       for (Paciente categoria : listaCategoria) {
           modeloPacientees.addElement(categoria);
       }

       // Crear una nueva instancia de JList utilizando el modelo de lista de pacientes
       JList<Paciente> jLstCategoria = new JList<>(modeloPacientees);

       // Establecer el modelo de la lista de pacientes en el componente gráfico jLstPaciente
       jLstPaciente.setModel(jLstCategoria.getModel());
   }

    
    /**
     * Inicializa el JTable y establece el modelo de datos con las cabeceras de columna.
     */
    private void inicializarJTable() {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0); // Elimina todas las filas de la tabla

        // Definir las cabeceras de columna para la tabla
        String[] arregloTitulosTabla = {"Fecha", "Hora", "Médico", "Paciente"};

        // Crear un nuevo modelo de tabla con las cabeceras definidas
        oModeloTablaDatos = new DefaultTableModel(arregloTitulosTabla, 0);

        // Establecer el modelo de tabla en el componente jTable
        jTable.setModel(oModeloTablaDatos);

        // Establecer la fuente deseada para las cabeceras de columna
        jTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 15));
    }


/**
 * Carga los datos del paciente buscado en la tabla jTable y resalta la fila correspondiente.
 * Busca el número de cédula en la lista de pacientes y marca la fila completa.
 * Ajusta la altura de la fila para mejorar la visualización.
 */     
     private void cargarPacienteBusqueda(){
        PacienteBLL pacienteBLL = new PacienteBLL();
        String numBuscado = pacienteBLL.buscarPaciente(jTxtPaciente.getText())!=null? pacienteBLL.buscarPaciente(jTxtPaciente.getText()).getCedula():"0";
        int indexPaciente = -1;
         // Cuando se marca cualquier celda, hace que se marque la fila completa
        jTable.setRowSelectionAllowed(true);
        
        // Ajustar el alto de la primera fila (cambia el valor según tus necesidades)
        int alturaFila = 40; // Altura deseada en píxeles
        
        if (numBuscado != null && !numBuscado.isEmpty()) {
            DefaultListModel<Paciente> model = (DefaultListModel<Paciente>) jLstPaciente.getModel();
            int size = model.getSize();
            
            for (int i = 0; i < size; i++) {
                jTable.setRowHeight(i, alturaFila);
                Paciente PacienteEnLista = model.getElementAt(i);
                if (String.valueOf(PacienteEnLista.getCedula()).equals(numBuscado)) {
                    indexPaciente= i;
                    break;
                }
            }

            if (indexPaciente != -1) {
                jLstPaciente.setSelectedIndex(indexPaciente);
                jLstPaciente.ensureIndexIsVisible(indexPaciente);
            }
        } 
     }
/**
 * Carga los datos en la tabla jTable utilizando las horas obtenidas de cargarHorasInicio().
 * Limpia el modelo existente en lugar de crear uno nuevo.
 * Agrega filas al modelo de la tabla con los datos correspondientes.
 *
 * @throws ParseException si ocurre un error al analizar las fechas.
 */
    private void cargarJTable() throws ParseException {
        // Limpiar el modelo existente en lugar de crear uno nuevo
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0); // Esto eliminará todas las filas de la tabla

        List<String> horasDisponibles = cargarHorasInicio();
        for (String hora : horasDisponibles) {
            String[] vDatosTabla = new String[4]; // Mueve esta declaración dentro del bucle
            vDatosTabla[1] = hora;
            vDatosTabla[0] = (String) jCboFecha.getSelectedItem();
            // Agregar la instancia al objeto modelo
            model.addRow(vDatosTabla);
        }
    }
/**
 * Carga los datos en la tabla jTable utilizando las horas obtenidas de cargarHorasLista().
 * Limpia el modelo existente en lugar de crear uno nuevo.
 * Agrega filas al modelo de la tabla con los datos correspondientes.
 *
 * @throws ParseException si ocurre un error al analizar las fechas.
 */
    private void cargarJTableIni() throws ParseException {
        // Limpiar el modelo existente en lugar de crear uno nuevo
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0); // Esto eliminará todas las filas de la tabla

        List<String> horasDisponibles = cargarHorasLista();
        for (String hora : horasDisponibles) {
            String[] vDatosTabla = new String[4]; // Mueve esta declaración dentro del bucle
            vDatosTabla[1] = hora;
            vDatosTabla[0] = (String) jCboFecha.getSelectedItem();
            // Agregar la instancia al objeto modelo
            model.addRow(vDatosTabla);
        }
    }
    
    private void cargarJTableOcupados() throws ParseException {  
       DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0); // Esto eliminará todas las filas de la tabla
        
       if(jLstDoctor.getSelectedIndex()>=0){ 
            String valorCelda = "";
            // Definir objeto Vector para cargar datos
            String[] vDatosTabla = new String[4];
            List<String> horasDisponibles = cargarHorasLista();
            //Limpiar los rows
            this.oModeloTablaDatos.setRowCount(0);
            List<Cita> listaCita = accionCita.getListaFechaDoctor((String)jCboFecha.getSelectedItem(), String.valueOf(jLstDoctor.getSelectedValue().getNumColegiado()));
            if(listaCita!=null){
                for (String hora : horasDisponibles) {
                    vDatosTabla[1] = hora;
                    vDatosTabla[0] = (String)jCboFecha.getSelectedItem();
                    // Agregar la instancia al objeto modelo
                    oModeloTablaDatos.addRow(vDatosTabla);
                }
                
                // Utilizar un bucle for con variable de tipo int
                for (int fila = 0; fila < horasDisponibles.size(); fila++) {
                    // Acceder al valor de la celda en la fila y columna especificadas
                    valorCelda = (String) jTable.getValueAt(fila, 1);
                    vDatosTabla[2] = ""; // Inicializar la columna de doctor
                    vDatosTabla[3] = ""; // Inicializar la columna de paciente
                     for (int i = 0; i < listaCita.size(); i++) {
                         Cita cita = listaCita.get(i);
                         if(valorCelda.equals(cita.getHora())){
                            vDatosTabla[2] = cita.getDoctor().getNombreApellidos();
                            vDatosTabla[3] = cita.getPaciente().getNombreApellidos();
                        } else {
                            vDatosTabla[2] = cita.getDoctor().getNombreApellidos();
                            if(vDatosTabla[3].isEmpty()){
                                vDatosTabla[3] = "";
                            }
                        }
                     }

                    oModeloTablaDatos.setValueAt(vDatosTabla[2], fila, 2); // Actualizamos la columna de doctor
                    oModeloTablaDatos.setValueAt(vDatosTabla[3], fila, 3); // Actualizamos la columna de paciente
                }

                jTable.setModel(oModeloTablaDatos);
            }else{
                cargarJTable();
            }
       }
    }

    
  /**
    * Carga las categorías de especialidades en un JComboBox.
    */
   private void cargarCategoriaEspecialidad() {
       CategoriaEspecialidadesBLL catEspecialidad = new CategoriaEspecialidadesBLL();
       List<CategoriaEspecialidades> listaCategoriaEspecialidades = catEspecialidad.getListaCategoriaEspecialidades();

       for (CategoriaEspecialidades cateEspecialidad : listaCategoriaEspecialidades) {
           this.jCboCategoriaEspecialidad.addItem(cateEspecialidad);
       }
   }

   /**
    * Carga la lista de especialidades en un componente gráfico de lista.
    */
   private void cargarEspecialidades() {
       EspecialidadBLL especialidad = new EspecialidadBLL();
       List<Especialidad> listaEspecialidades = especialidad.getListaEspecialidad(((CategoriaEspecialidades)this.jCboCategoriaEspecialidad.getSelectedItem()).getIdCategoria());
       DefaultListModel<Especialidad> modeloEspecialidad = new DefaultListModel<>();

       for (Especialidad especialidad1 : listaEspecialidades) {
           modeloEspecialidad.addElement(especialidad1);
       }

       JList<Especialidad> jLstEspecialidades = new JList<>(modeloEspecialidad);
       this.jLstEspecialidad.setModel(jLstEspecialidades.getModel());
   }

   /**
    * Carga las fechas en un JComboBox.
    */
   public void cargarFechas() {
       LocalDate fechaActual = LocalDate.now();
       LocalDate fechaFutura = fechaActual.plusMonths(3);

       while (fechaActual.isBefore(fechaFutura.plusDays(1))) {
           DayOfWeek diaSemana = fechaActual.getDayOfWeek();

           // Excluir fines de semana (Sábado: DayOfWeek.SATURDAY, Domingo: DayOfWeek.SUNDAY)
           if (diaSemana != DayOfWeek.SATURDAY && diaSemana != DayOfWeek.SUNDAY) {
               jCboFecha.addItem(Formatos.conFormatoFecha(Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant())));
           }

           fechaActual = fechaActual.plusDays(1);
       }
   }

    
    /**
    * Carga las horas disponibles en un JComboBox, teniendo en cuenta el horario de almuerzo del médico seleccionado.
    */
   public void cargarHoras() {
       // Borra todos los elementos actuales del combo box
       jCboHora.removeAllItems();

       DoctorBLL accionDoctor = new DoctorBLL();
       List<String> horas = new ArrayList<>();

       // Verifica si se ha seleccionado un médico
       if (jLstDoctor.getSelectedIndex() > 0) {
           String doctorNumColegiado = String.valueOf(jLstDoctor.getSelectedValue().getNumColegiado());

           // Obtener el horario de almuerzo del médico
           String doctor = accionDoctor.buscarDoctor(doctorNumColegiado).getHoraAlmuerzo();
           char[] rangoAlmuerzo = new char[2];
           rangoAlmuerzo[0] = doctor.charAt(0);
           rangoAlmuerzo[1] = doctor.charAt(1);
           String hora = "";

           // Determinar la hora de almuerzo del médico
           if (Character.isDigit(rangoAlmuerzo[0]) && Character.isDigit(rangoAlmuerzo[1])) {
               hora = String.valueOf(rangoAlmuerzo[0] + rangoAlmuerzo[1]);
           } else {
               hora = String.valueOf(rangoAlmuerzo[0]);
           }

           // Definir el rango de horas disponibles
           LocalTime horaInicio = LocalTime.of(8, 0);
           LocalTime horaFin = LocalTime.of(17, 0);
           DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm");

           // Agregar horas al combo box, excluyendo la hora de almuerzo
           while (horaInicio.isBefore(horaFin)) {
               if (!horaInicio.equals(hora)) {
                   String horaString = horaInicio.format(formato);
                   this.jCboHora.addItem(horaString);
                   horaInicio = horaInicio.plusMinutes(30);
               } else {
                   horaInicio = horaInicio.plusMinutes(60);
               }
           }
       }
   }

    
    /**
    * Carga las horas disponibles en un JComboBox, con un salto durante el horario de almuerzo.
    *
    * @return Una lista de cadenas que representan las horas disponibles.
    */
   public List<String> cargarHorasInicio() {
       // Borra todos los elementos actuales del combo box
       jCboHora.removeAllItems();

       // Definir la hora desde y hasta la cual se realizará el salto
       LocalTime horaSalto = LocalTime.of(11, 30); // Hora desde la que se realizará el salto
       LocalTime horaFinSalto = LocalTime.of(13, 0); // Hora hasta la que se realizará el salto
       LocalTime horaInicio = LocalTime.of(8, 0);
       LocalTime horaFin = LocalTime.of(17, 0);
       DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm");
       List<String> horas = new ArrayList<>();

       // Agregar horas al combo box y a la lista de horas, con el salto durante el horario de almuerzo
       while (horaInicio.isBefore(horaFin)) {
           String horaString = horaInicio.format(formato);
           this.jCboHora.addItem(horaString);
           horas.add(horaString);

           // Realizar el salto durante el horario de almuerzo
           if (horaInicio.equals(horaSalto)) {
               horaInicio = horaFinSalto; // Saltar a la hora de fin de salto
           } else {
               horaInicio = horaInicio.plusMinutes(30);
           }
       }

       return horas;
   }

  /**
 * Carga una lista de horas disponibles en el JComboBox para programar una cita.
 * Las horas especiales se ajustan según la disponibilidad del doctor.
 *
 * @return Una lista de cadenas que representa las horas disponibles.
 */
    public List<String> cargarHorasLista() {
        jCboHora.removeAllItems(); // Borra todos los elementos del combo box
        DoctorBLL accionDoctor = new DoctorBLL();
        List<String> horas = new ArrayList<>();

        if (jLstDoctor.getSelectedIndex() >=0) {
            String doctor = accionDoctor.buscarDoctor(String.valueOf(jLstDoctor.getSelectedValue().getNumColegiado())).getHoraAlmuerzo();
            char[] rangoAlmuerzo = new char[2];
            rangoAlmuerzo[0] = doctor.charAt(0);
            rangoAlmuerzo[1] = doctor.charAt(1);
            LocalTime horaEspecial = LocalTime.of(11, 30);
            LocalTime horaFinSalto =  LocalTime.of(13, 0);
                    
            if (rangoAlmuerzo[0] == '1' && rangoAlmuerzo[1] == '2') {
                horaEspecial = LocalTime.of(11, 30);
                horaFinSalto = LocalTime.of(13, 0); // 1:00 PM
            } else {
                if (rangoAlmuerzo[0] == '1' && !Character.isDigit(rangoAlmuerzo[1])) {
                    horaEspecial = LocalTime.of(12, 30);
                    horaFinSalto = LocalTime.of(14, 0); // 2:00 PM
                }
            }
            LocalTime horaInicio = LocalTime.of(8, 0);
            LocalTime horaFin = LocalTime.of(17, 0);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm");

            while (horaInicio.isBefore(horaFin)) {
                String horaString = horaInicio.format(formato);
                this.jCboHora.addItem(horaString);
                horas.add(horaString);
                if (horaInicio.equals(horaEspecial)) {
                    horaInicio = horaFinSalto; // Saltar a la hora de fin de salto
                } else {
                    horaInicio = horaInicio.plusMinutes(30);
                }
            }
        }
        
        return horas;
    }
   /**
 * Carga la lista de doctores en un JList para su selección.
 * La dimensión y el diseño del contenedor se ajustan para mostrar la lista correctamente.
 * Se utiliza un DefaultListModel para mostrar los datos en el componente JList.
 *
 * @throws ParseException si ocurre un error al analizar las fechas.
 */ 
    private void cargarDoctores() throws ParseException{
        jLstDoctor.setPreferredSize(new Dimension(393,198));
        Container contenedor = jLstDoctor.getParent(); // Obtén el contenedor que contiene el JComboBox
        contenedor.revalidate(); // Notificar al contenedor que recalcule el diseño
        //DefaultListModel<String> representa una lista de elementos se utiliza para mostrar datos en componentes de interfaz de usuario como JList.
        DoctorBLL Doctor= new DoctorBLL();
        List<Doctor> listaDoctores = Doctor.getListaDoctors();
        DefaultListModel<Doctor> modeloDoctor = new DefaultListModel<>();

        for (Doctor Doctor1 : listaDoctores) {
            modeloDoctor.addElement(Doctor1);
        }

        JList<Doctor> jLstDoctores= new JList<>(modeloDoctor);
        this.jLstDoctor.setModel(jLstDoctores.getModel());
    }
 
    /**
 * Carga la lista de doctores según una especialidad seleccionada en otro JList.
 * Se utiliza un DefaultListModel para mostrar los datos en el componente JList.
 * La lista de doctores se filtra por la especialidad seleccionada.
 *
 * @throws ParseException si ocurre un error al analizar las fechas.
 */
    private void cargarDoctoresEspecialidad() throws ParseException{
        jLstDoctor.removeAll();
        //DefaultListModel<String> representa una lista de elementos se utiliza para mostrar datos en componentes de interfaz de usuario como JList.
        DoctorBLL Doctor= new DoctorBLL();
        List<Doctor> listaDoctores = Doctor.getListaDoctorEspecialidad(String.valueOf(jLstEspecialidad.getSelectedValue().getEspecialidadID()));
        DefaultListModel<Doctor> modeloDoctor = new DefaultListModel<>();
        for (Doctor Doctor1 : listaDoctores) {
            modeloDoctor.addElement(Doctor1);
        }

        JList<Doctor> jLstDoctores= new JList<>(modeloDoctor);
        this.jLstDoctor.setModel(jLstDoctores.getModel());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jCboFecha = new javax.swing.JComboBox<>();
        jCboHora = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jBtoRegistrar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLstEspecialidad = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jLstDoctor = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jTxtPaciente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLstPaciente = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jCboCategoriaEspecialidad = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jBtoEliminar = new javax.swing.JButton();
        jBtoModificar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jFrmInicio = new javax.swing.JMenu();
        jFrmPaciente = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jTable.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable);

        jCboFecha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jCboFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboFechaItemStateChanged(evt);
            }
        });

        jCboHora.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Fecha");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Hora");

        jBtoRegistrar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jBtoRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/cita-medica.png"))); // NOI18N
        jBtoRegistrar.setText("Registrar");
        jBtoRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtoRegistrarActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setText("Categoria Especialidad");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setText("Especialidad");

        jLstEspecialidad.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLstEspecialidad.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jLstEspecialidadValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(jLstEspecialidad);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Médico");

        jLstDoctor.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLstDoctor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLstDoctorFocusLost(evt);
            }
        });
        jLstDoctor.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jLstDoctorValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(jLstDoctor);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Paciente");

        jTxtPaciente.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTxtPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtPacienteActionPerformed(evt);
            }
        });

        jLstPaciente.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(jLstPaciente);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/actualizar_1.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jCboCategoriaEspecialidad.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jCboCategoriaEspecialidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCboCategoriaEspecialidadItemStateChanged(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/salida.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        jBtoEliminar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jBtoEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/Eliminar.png"))); // NOI18N
        jBtoEliminar.setText("Eliminar");
        jBtoEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtoEliminarActionPerformed(evt);
            }
        });

        jBtoModificar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jBtoModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/editar.png"))); // NOI18N
        jBtoModificar.setText("Modificar");
        jBtoModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtoModificarActionPerformed(evt);
            }
        });

        jMenuBar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jFrmInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/pagina-de-inicio.png"))); // NOI18N
        jFrmInicio.setText("Inicio");
        jFrmInicio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jFrmInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jFrmInicioMousePressed(evt);
            }
        });
        jFrmInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFrmInicioActionPerformed(evt);
            }
        });
        jMenuBar1.add(jFrmInicio);

        jFrmPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sisPlanImagenes/paciente.png"))); // NOI18N
        jFrmPaciente.setText("Pacientes");
        jFrmPaciente.setFocusable(false);
        jFrmPaciente.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jFrmPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFrmPacienteActionPerformed(evt);
            }
        });
        jMenuBar1.add(jFrmPaciente);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel3)
                                .addGap(118, 118, 118)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCboFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jCboHora, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(117, 117, 117)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jBtoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jBtoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jBtoRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(37, 37, 37))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addContainerGap(463, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jCboCategoriaEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(102, 102, 102))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel16)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCboCategoriaEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jBtoRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtoModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel9)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCboFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCboFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboFechaItemStateChanged
       
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
                cargarJTable();
            } catch (ParseException ex) {
                Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jCboFechaItemStateChanged

    private void jLstEspecialidadValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jLstEspecialidadValueChanged
         if (jLstEspecialidad.getSelectedIndex() >=0){
             try {
                 cargarDoctoresEspecialidad();
             } catch (ParseException ex) {
                 Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }//GEN-LAST:event_jLstEspecialidadValueChanged

    private void jBtoRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtoRegistrarActionPerformed
       DoctorBLL accionDoctor=new DoctorBLL();
       if(jLstDoctor.getSelectedIndex()>=0&&jLstPaciente.getSelectedIndex()>=0){
        if(accionCita.existeCitaFechaHoraDoctor((String)jCboHora.getSelectedItem(),(String)jCboFecha.getSelectedItem(),  String.valueOf(jLstDoctor.getSelectedValue().getNumColegiado())) &&
               accionCita.existeCitaFechaPaciente((String)jCboFecha.getSelectedItem(),  jLstPaciente.getSelectedValue().getCedula())){
           Cita cita = null;
            try {
                cita = new Cita(accionCita.consecutivo(), Formatos.obtenerFecha((String)jCboFecha.getSelectedItem()), (String)jCboHora.getSelectedItem(), jLstDoctor.getSelectedValue(), jLstPaciente.getSelectedValue());
            } catch (ParseException ex) {
                Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
           accionCita.agregarCita(cita);
        }else{
           JOptionPane.showMessageDialog(null, "Ya existe una cita con estos datos");
       }}else{
           JOptionPane.showMessageDialog(null, "Faltan datos por seleccionar");
       }
        
    }//GEN-LAST:event_jBtoRegistrarActionPerformed

    private void jTxtPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtPacienteActionPerformed
        if(jTxtPaciente.getText().length()==9){
            cargarPacienteBusqueda();
        }
    }//GEN-LAST:event_jTxtPacienteActionPerformed

    private void jLstDoctorValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jLstDoctorValueChanged
       
        if(jLstDoctor.getSelectedIndex()>=0){
            cargarHorasLista();
            try {
                cargarJTableIni();
            } catch (ParseException ex) {
                Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cargarJTableOcupados();
                
            } catch (ParseException ex) {
                Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cargarJTableOcupados();
            } catch (ParseException ex) {
                Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLstDoctorValueChanged

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        if(jLstDoctor.getSelectedIndex()>=0){
            try {
                cargarJTableOcupados();
            } catch (ParseException ex) {
                Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                cargarJTable();
            } catch (ParseException ex) {
                Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel5MousePressed

    private void jFrmInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFrmInicioActionPerformed
       jFrmMenuPrincipal menu=new jFrmMenuPrincipal();
       menu.setVisible(true);
      this.setVisible(false);
    }//GEN-LAST:event_jFrmInicioActionPerformed

    private void jFrmPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFrmPacienteActionPerformed
       JFrmPersonas personas= new JFrmPersonas();
       personas.setVisible(true);
      this.setVisible(false);
    }//GEN-LAST:event_jFrmPacienteActionPerformed

    private void jCboCategoriaEspecialidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCboCategoriaEspecialidadItemStateChanged
        if (jCboCategoriaEspecialidad.getSelectedIndex() >= 0){
            cargarEspecialidades();
        }
    }//GEN-LAST:event_jCboCategoriaEspecialidadItemStateChanged

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        jFrmMenuPrincipal menu=new jFrmMenuPrincipal();
       menu.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jLabel9MousePressed

    private void jFrmInicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFrmInicioMousePressed
        jFrmMenuPrincipal menu=new jFrmMenuPrincipal();
       menu.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jFrmInicioMousePressed

    private void jBtoEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtoEliminarActionPerformed
        
           try {
               jDlgCitaPaciente cita = new jDlgCitaPaciente(null, true);
               cita.setVisible(true);
           } catch (ParseException ex) {
               Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
           }
    }//GEN-LAST:event_jBtoEliminarActionPerformed

    private void jBtoModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtoModificarActionPerformed
 
       if(!PilaCita.pilaVacia()){
           String codigo=PilaCita.sacarUltimoElemento();
           Cita oCita= accionCita.buscarCita(codigo);
             if(oCita!=null){
                 if(jLstDoctor.getSelectedIndex()>=0&&jLstPaciente.getSelectedIndex()>=0){
                     Cita cita = null;
                     cita = new Cita(Integer.parseInt(codigo), Formatos.obtenerFecha((String)jCboFecha.getSelectedItem()), (String)jCboHora.getSelectedItem(), jLstDoctor.getSelectedValue(), jLstPaciente.getSelectedValue());
                     accionCita.modificarCita(cita);
                 
             }else{
                JOptionPane.showMessageDialog(null, "Faltan datos por seleccionar");
            }}else{
                JOptionPane.showMessageDialog(null, "No se encontro la cita");
            }
       }else{
            jDlgCitaPaciente cita = null;
           try {
               cita = new jDlgCitaPaciente(null, true);
           } catch (ParseException ex) {
               Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
           }
           UtilitarioVentana.centrarVentanaJDialog(cita);
           this.setVisible(false);
       }
    }//GEN-LAST:event_jBtoModificarActionPerformed

    private void jLstDoctorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLstDoctorFocusLost
       
    }//GEN-LAST:event_jLstDoctorFocusLost

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
            java.util.logging.Logger.getLogger(JFrmCitaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrmCitaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrmCitaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrmCitaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrmCitaMedica().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(JFrmCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtoEliminar;
    private javax.swing.JButton jBtoModificar;
    private javax.swing.JButton jBtoRegistrar;
    private javax.swing.JComboBox<CategoriaEspecialidades> jCboCategoriaEspecialidad;
    private javax.swing.JComboBox<String> jCboFecha;
    private javax.swing.JComboBox<String> jCboHora;
    private javax.swing.JMenu jFrmInicio;
    private javax.swing.JMenu jFrmPaciente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<Doctor> jLstDoctor;
    private javax.swing.JList<Especialidad> jLstEspecialidad;
    private javax.swing.JList<Paciente> jLstPaciente;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTxtPaciente;
    // End of variables declaration//GEN-END:variables

    private boolean isDigitString(char c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
