/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanUtilitario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Clase Utilitarios para funcionalidades para el despliegue
 * de objetos JFrame y JPanel
 * @author Ing. Daniela Mendez
 * @version 2.1
 * @since Mayo 2020
 */
public class UtilitarioVentana {
    
    /**
     * Método para Centrar una ventana tipo JFrame
     * Se basa en el alto y ancho del screen del Sistema Operativo
     * y el alto y ancho del objeto JFrame
     * Ver algoritmo que aplica para centrar a razón de las 
     * coordenadas X y Y de la pantalla del OS
     * @param pVentanaJFrame Objeto JFrame a Centrar
     * @param pEstadoMaximizado True para estado maximizado, False centrado
     */
    public static void centrarVentanaJFrame(JFrame pVentanaJFrame,
                                            boolean pEstadoMaximizado){
        //Leer las dimensiones de la ventana principal y del cliente
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = pVentanaJFrame.getSize();
        
        //Validar si el pEstadoMaximizado == true el modo 
        //extendido se asigna en 
        if (pEstadoMaximizado) {
            //Cambiar el estado del JFrame para que este maximizado
            pVentanaJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else{
            //Ajusta el alto y ancho del JFrame principal
            //si es mayor al tamaño del screen del OS cliente
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }

            //Ubicar en el eje X,Y la ventana JFrame
            pVentanaJFrame.setLocation( ( screenSize.width - frameSize.width ) / 2, 
                                        ( screenSize.height - frameSize.height ) / 2 );
        }
        
        //Al definir DO_NOTHING_ON_CLOSE implicará que el JFrame
        //deberá ser cerrado desde códigodo de programación.
        //el objetivo de esta restrinción es que se verifique el cierre de 
        //del jFrame con una confirmacion al usuario
        pVentanaJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        /*
          Cada contenedor de objetos en Java tiene asociado un objeto encargado de ordenar los componentes, 
          el cual es llamado “Layout Manager” o administrador de despliegue. 
          El GroupLayout destaca entre los demás administradores de despliegue porque nos permite 
          establecer de manera precisa la posición y el tamaño de cada componente dentro del contenedor. 
          La clase GroupLayout requiere de los métodos setHorizontalGroup y 
          setVerticalGroup para definir la ubicación y las dimensiones de los componentes. 
          Para utilizar estos métodos es importante que sepan la función de los objetos de tipo 
          SequentialGroup y los objetos de tipo ParallelGroup. 
          
          Referencias:
          https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
          https://docs.oracle.com/javase/tutorial/uiswing/layout/groupExample.html
        
          Para efectos de los ejemplos del curso, no se va emplear los comportamientos
          setHorizontalGroup y setVerticalGroup ya que dichos métodos permite el acomodo de los 
          componentes que se van asociado al grupo a nivel programado.
          Dado que los objetos JPanel y demás, se emplea el "dibujado" desde la misma herramienta
          no se va emplear está funcionalidad.
          Importante denotar, que el NetBeans emplea dichos comportamientos en los JFrame que
          creamos como elementos de Ventanas Principales.
        */
        GroupLayout layout = new GroupLayout(pVentanaJFrame.getContentPane());

        /*
        Estos son los métodos que no son necesarios definir para el JFrame
        Con el GroupLayout, se permite asociar los JInternaFrame que son creados
        dinámicamente. El JFrame no va contener objetos visuales como JButton y JLabel, 
        se va emplear como una ventana "Principal" con el menú de opciones para llamar a los
        diferentes JPanel que vamos a emplear en los ejercicios
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );
        */
        
        pVentanaJFrame.getContentPane().setLayout(layout);         
        
        pVentanaJFrame.setVisible(true);   
    }
    
    /**
     * Método para Centrar una ventana tipo JPanel con un objeto JInternalFrame
     * @param pVentanaPrincipal Objeto JFrame que contiene los JInternalFrame
     * @param pTituloVentana String del título del JInternalFrame
     * @param pPanel Instancia del objeto Panel a asociar en el JInternalFrame
     * @param pAdministradorEscritorio Instancia del Administrador de Escritorios
     */    
    public static void centrarVentanaJPanel(JFrame pVentanaPrincipal, 
                                            String pTituloVentana, 
                                            JPanel pPanel,
                                            JDesktopPane pAdministradorEscritorio) {        
        //Crear la instancia del JInternalFrame
        JInternalFrame oJInternalFrame = new JInternalFrame(pTituloVentana, false, true, false, false);    
        
        //Leer las dimensiones de la ventana principal y del cliente
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = pVentanaPrincipal.getSize();
        
        //Ajusta el alto y ancho del JFrame principal
        //si es mayor al tamaño del screen del OS cliente
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }        
        
        //Asociar el objeto Panel al JInternalFrame
        oJInternalFrame.add(pPanel, BorderLayout.CENTER);        
 
        //Definir que al cerrar el JInternalFrame se destruya el objeto de memoria
        oJInternalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        oJInternalFrame.pack();
        
        //Ubicar el JInternalFrame en el centro del Frame Principal
        //A partir del JDK 7 se debe hacer la ubicación posterior al pintado --> pack()
        //del JInternalFrame
        oJInternalFrame.setLocation( ( frameSize.width - oJInternalFrame.getSize().width )/2,
                                     ( frameSize.height - oJInternalFrame.getSize().height)/2);
                
        //Agregar el JInternalFrame el objeto Contenedor JDesktopPane        
        pAdministradorEscritorio.add(oJInternalFrame);
        
        //Mostrar el JInternalFrame
        oJInternalFrame.setVisible(true);    
    }

    /**
     * Método para Centrar una ventana tipo JDialog
     * Siempre se muestra en forma modal
     * @param pVentanaJDialog Objeto JDialog a Centrar
     */
    public static void centrarVentanaJDialog(JDialog pVentanaJDialog){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = pVentanaJDialog.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        pVentanaJDialog.setLocation( ( screenSize.width - frameSize.width ) / 2, 
                                     ( screenSize.height - frameSize.height ) / 2 );
        pVentanaJDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pVentanaJDialog.setModal(true);
        pVentanaJDialog.setVisible(true);   
    }
    
    /**
     * Recorre los objetos contenidos en un panel para cerrar el 
     * JFrame/JInternalFrame que lo contiene
     * Referencia: https://docs.oracle.com/javase/tutorial/uiswing/learn/index.html
     * @param oPanel Objeto Panel a buscar el objeto JFrame/JInternalFrame
     */
    public static void cerrarPanel(JPanel oPanel){
       //Leer el padre de un panel en 5 Niveles permite iterar por medio del 
       //método getComponent(x) el objeto JFrame/JInternalFrame que contiene el panel
       //Si existe invoca el dispose de dicho JInternalFrame
       //Cada programa que usa componentes Swing tiene al menos un contenedor de nivel superior. 
       //Este contenedor de nivel superior es la raíz de una jerarquía de contención: la jerarquía 
       //que contiene todos los componentes de Swing que aparecen dentro del contenedor de nivel superior
       for(int i=0; i < oPanel.getParent().getParent().getParent().getParent().getParent().getComponentCount(); i++){            
          if (oPanel.getParent().getParent().getParent().getParent().getParent().getComponent(i).getClass() == JInternalFrame.class){                    
             ((JInternalFrame)oPanel.getParent().getParent().getParent().getParent().getParent().getComponent(i)).dispose();
             return;
          }else if (oPanel.getParent().getParent().getParent().getParent().getParent().getComponent(i).getClass() == JFrame.class){
             ((JFrame)oPanel.getParent().getParent().getParent().getParent().getParent().getComponent(i)).dispose();
             return;
          }                
       }
    }
    
    /**
     * Buscar dentro de la información de LookAndFeelInfo del JRE del Java
     * para retornar el ClassName de un LookAndFeel
     * @param pETipoEstiloVentana Estilo a buscar
     * @return 
     */
    private static String buscarEstiloVentanas(ETipoEstiloVentana pETipoEstiloVentana){
        //Por defecto se indica como estilo el definido en el OS
        String vHilera = UIManager.getSystemLookAndFeelClassName();
        
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            
            // Si el getName corresponde con el código implica que ya encontró el estilo
            if ( pETipoEstiloVentana.getCodigo().equals(info.getName()) ) {
                vHilera = info.getClassName();
                break;
            }                
        }

        return vHilera;        
    }
    
    /**
     * Método que modifica el estilo de las ventanas del ambiente swing
     * @param pETipoEstiloVentana Tipo de estilo para las ventanas del OS
     */
    public static void aplicarEstiloVentas(ETipoEstiloVentana pETipoEstiloVentana) {
        try {            
            javax.swing.UIManager.setLookAndFeel( buscarEstiloVentanas(pETipoEstiloVentana) );
        } 
        //Esto es una forma de definción de catch multiple para una sola excepción
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException oError) {
            oError.printStackTrace();
        }
    }   
}
