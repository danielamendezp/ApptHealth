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

public class UtilitarioVentana_1 {

    public static void centrarVentanaJFrame(JFrame pVentanaJFrame,
                                            boolean pEstadoMaximizado){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = pVentanaJFrame.getSize();
        
        if (pEstadoMaximizado) {
            pVentanaJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else{
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }

            pVentanaJFrame.setLocation( ( screenSize.width - frameSize.width ) / 2, 
                                        ( screenSize.height - frameSize.height ) / 2 );
        }
        pVentanaJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        
        GroupLayout layout = new GroupLayout(pVentanaJFrame.getContentPane());

        pVentanaJFrame.getContentPane().setLayout(layout);         
        
        pVentanaJFrame.setVisible(true);   
    }
    
    public static void centrarVentanaJPanel(JFrame pVentanaPrincipal, 
                                            String pTituloVentana, 
                                            JPanel pPanel,
                                            JDesktopPane pAdministradorEscritorio) {     
        JInternalFrame oJInternalFrame = new JInternalFrame(pTituloVentana, false, true, false, false);    
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = pVentanaPrincipal.getSize();
        
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }        
        
        oJInternalFrame.add(pPanel, BorderLayout.CENTER);        
 
        oJInternalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        oJInternalFrame.pack();
        
        oJInternalFrame.setLocation( ( frameSize.width - oJInternalFrame.getSize().width )/2,
                                     ( frameSize.height - oJInternalFrame.getSize().height)/2);
                     
        pAdministradorEscritorio.add(oJInternalFrame);
        
        oJInternalFrame.setVisible(true);    
    }
    
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
    
    public static void cerrarPanel(JPanel oPanel){
     
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
    
   
    private static String buscarEstiloVentanas(ETipoEstiloVentana pETipoEstiloVentana){
       
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
    public static void aplicarEstiloVentas(ETipoEstiloVentana pETipoEstiloVentana) {
        try {            
            javax.swing.UIManager.setLookAndFeel( buscarEstiloVentanas(pETipoEstiloVentana) );
        } 
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException oError) {
            oError.printStackTrace();
        }
    }   
}
