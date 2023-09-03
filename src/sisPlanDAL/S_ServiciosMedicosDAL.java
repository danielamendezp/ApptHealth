/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sisPlanEntidades.ServiciosMedicos;

public class S_ServiciosMedicosDAL  extends BaseDAL {

    public S_ServiciosMedicosDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\SERVICIOS_MEDICOS.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public ServiciosMedicos buscarServiciosMedicos(int pIdentificador) {
        ServiciosMedicos oServiciosMedicos= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
        
        //Crear la instancia de Departamento
        oServiciosMedicos = new ServiciosMedicos(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], Double.parseDouble(vDatosRegistro[2]),
                                       Integer.parseInt(vDatosRegistro[3]));
        return oServiciosMedicos;
    }
    
    /**
     * Obtener la lista completa de las provincias
     * @return Objeto ArrayList con todos los objetos departamentos
     */
     public List<ServiciosMedicos> getListaServiciosMedicos(String idCategoria) {
        //Definición de variable entidad
        ServiciosMedicos oServiciosMedicos= null;

        //Definir objeto ArrayList para cargar los objetos
        List<ServiciosMedicos> oArrayListServiciosMedicos = new ArrayList<>();
        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        vDatos[0] = idCategoria;
        
        //Posiciones dentro del archivo 
        int[] vIndices = new int[1];
        vIndices[0] = 3;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            //Crear la instancia 
            oServiciosMedicos = new ServiciosMedicos(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], Double.parseDouble(vDatosRegistro[2]),
                                       Integer.parseInt(vDatosRegistro[3]));
            
            //Agregar la instancia a la colección
            oArrayListServiciosMedicos.add(oServiciosMedicos);
        }

        return oArrayListServiciosMedicos;
    }
     
     
    public void agregarServiciosMedicos(ServiciosMedicos pServiciosMedicos) {
        if(existeServiciosMedicos(String.valueOf(pServiciosMedicos.getIdServicio()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un Servicios Medicos con estos datos!");
        }else{
            this.agregarLinea(pServiciosMedicos.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Servicios Medicos agregado con exito!");
        }
    }

    public void modificarServiciosMedicos(ServiciosMedicos pServiciosMedicos) {
        if(!existeServiciosMedicos(String.valueOf(pServiciosMedicos.getIdServicio()))){
            JOptionPane.showMessageDialog(null, "¡Este servicio no existe!");
        }else{
            this.modificarLinea(String.valueOf(pServiciosMedicos.getIdServicio()), 
                            this.numeroColumnaLlavePrimaria, 
                            pServiciosMedicos.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarServiciosMedicos(String pCodigo) {
         if(!existeServiciosMedicos(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un Servicios Medicos con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           0);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
        }
    }

    public ServiciosMedicos buscarServiciosMedicos(String pCodigo) {
        ServiciosMedicos oServiciosMedicos = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            oServiciosMedicos = new ServiciosMedicos(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], Double.parseDouble(vDatosRegistro[2]),Integer.parseInt(vDatosRegistro[3]));
        }
        return oServiciosMedicos;
    }
    
    
    public boolean existeServiciosMedicos(String pCodigo) {
          return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }
    
    public int getConsecutivo(){
        ServiciosMedicos oServiciosMedicos= null;
        List<ServiciosMedicos> oListaServiciosMedicos = new ArrayList<>();
        int max = 0;
        List<String> oListaRegistros = this.getListaRegistros();
        
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            oServiciosMedicos = new ServiciosMedicos(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], Double.parseDouble(vDatosRegistro[2]),Integer.parseInt(vDatosRegistro[3]));
            oListaServiciosMedicos.add(oServiciosMedicos);
            
            // Encontrar el número más grande 
            int min = oServiciosMedicos.getIdServicio();
            if (min > max) {
                max = min;
            }else{
                max=max;
            }
        }
        
        return max;
    }   
    
     public ServiciosMedicos getServiciosMedicosPorNombre(String id){
        //Definición de variable entidad
        ServiciosMedicos oServiciosMedicos = null;

        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        vDatos[0] = id;
        
        //Posiciones dentro del archivo 
        int[] vIndices = new int[1];
        vIndices[0] = 1;
        
        //Definir objeto ArrayList para la lista de los Registros en formato String
        List<String> oListaRegistros = this.buscarLineasPorLlaves(vDatos, vIndices);

        //Iterar la colección con un foreach de Java
        for (String vRegistro : oListaRegistros) {
            //Descompone al linea en una estructura vectorizada
            //donde cada elemento del vector son los datos
            //de la línea separados por ";"
            String[] vDatosRegistro = vRegistro.split(";");
            
            //Crear la instancia 
           
            oServiciosMedicos = new ServiciosMedicos(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1], Double.parseDouble(vDatosRegistro[2]),Integer.parseInt(vDatosRegistro[3]));
            
        }

        return oServiciosMedicos;
    }
}

