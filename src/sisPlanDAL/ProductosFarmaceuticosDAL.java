/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import sisPlanEntidades.Producto;
import sisPlanUtilitario.Formatos;

public class ProductosFarmaceuticosDAL  extends BaseDAL {

    public ProductosFarmaceuticosDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\PRODUCTOS_FARMACEUTICOS.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public Producto buscarProducto(int pIdentificador) throws ParseException {
        Producto oProducto= null;

        //Definir objeto ArrayList para la lista de los Registros en formato String
        String vRegistro = this.buscarLinea(pIdentificador + "", this.numeroColumnaLlavePrimaria);

        //Descompone al linea en una estructura vectorizada
        //donde cada elemento del vector son los datos
        //de la línea separados por ";"
        String[] vDatosRegistro = vRegistro.split(";");
            
           //Crear la instancia de Departamento
           oProducto = new Producto(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1],
                                    vDatosRegistro[2], Integer.parseInt(vDatosRegistro[3]),  vDatosRegistro[4], Integer.parseInt(vDatosRegistro[5]), 
                                 Formatos.obtenerFecha(vDatosRegistro[6]),  vDatosRegistro[7], Integer.parseInt(vDatosRegistro[8]));
        return oProducto;
    }
    
    /**
     * Obtener la lista completa de las provincias
     * @return Objeto ArrayList con todos los objetos departamentos
     */
    public List<Producto> getListaProducto(String idForma) throws ParseException {
        //Definición de variable entidad
        Producto oProducto= null;

        //Definir objeto ArrayList para cargar los objetos
        List<Producto> oArrayListProducto = new ArrayList<>();
        
        //Crear los vectores para los datos e indices
        //para el filtro de registros por valores llave
        String[] vDatos = new String[1];
        
        vDatos[0] = idForma;
        
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
            
           //Crear la instancia de Producto
           oProducto = new Producto(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1],
                                    vDatosRegistro[2], Integer.parseInt(vDatosRegistro[3]),  vDatosRegistro[4], Integer.parseInt(vDatosRegistro[5]), 
                                 Formatos.obtenerFecha(vDatosRegistro[6]),  vDatosRegistro[7], Integer.parseInt(vDatosRegistro[8]));
        
            //Agregar la instancia a la colección
            oArrayListProducto.add(oProducto);
        }

        return oArrayListProducto;
    }
    
     
    public void agregarProducto(Producto pProducto) {
        if(existeProducto(String.valueOf(pProducto.getIdProducto()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un Producto con estos datos!");
        }else{
            this.agregarLinea(pProducto.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Producto agregado con exito!");
        }
    }

    public void modificarProducto(Producto pProducto) {
        if(!existeProducto(String.valueOf(pProducto.getIdProducto()))){
            JOptionPane.showMessageDialog(null, "¡Este Producto no existe!");
        }else{
            this.modificarLinea(String.valueOf(pProducto.getIdProducto()), 
                            this.numeroColumnaLlavePrimaria, 
                            pProducto.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarProducto(String pCodigo) {
         if(!existeProducto(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un Producto con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           0);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
        }
    }

    public Producto buscarProducto(String pCodigo) {
        Producto oProducto = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            //Crear la instancia de Producto
           oProducto = new Producto(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1],
                                    vDatosRegistro[2], Integer.parseInt(vDatosRegistro[3]),  vDatosRegistro[4], Integer.parseInt(vDatosRegistro[5]), 
                                 Formatos.obtenerFecha(vDatosRegistro[6]),  vDatosRegistro[7], Integer.parseInt(vDatosRegistro[8]));
        }
        return oProducto;
    }
    
    public Producto buscarProductoCedula(String pCodigo) {
        Producto oProducto = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          1);
        if (!vLineaRegistro.equals("")) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            //Crear la instancia de Producto
           oProducto = new Producto(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1],
                                    vDatosRegistro[2], Integer.parseInt(vDatosRegistro[3]),  vDatosRegistro[4], Integer.parseInt(vDatosRegistro[5]), 
                                 Formatos.obtenerFecha(vDatosRegistro[6]),  vDatosRegistro[7], Integer.parseInt(vDatosRegistro[8]));
               }
        return oProducto;
    }
    
    public boolean existeProducto(String pCodigo) {
          return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }
    
    public int getConsecutivo(){
        Producto oProducto= null;
        List<Producto> oListaProducto = new ArrayList<>();
        int max = 0;
        List<String> oListaRegistros = this.getListaRegistros();
        
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
            oProducto = new Producto(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1],
                                    vDatosRegistro[2], Integer.parseInt(vDatosRegistro[3]),  vDatosRegistro[4], Integer.parseInt(vDatosRegistro[5]), 
                                 Formatos.obtenerFecha(vDatosRegistro[6]),  vDatosRegistro[7], Integer.parseInt(vDatosRegistro[8]));
            oListaProducto.add(oProducto);
            
            // Encontrar el número más grande 
            int min = oProducto.getIdProducto();
            if (min > max) {
                max = min;
            }else{
                max=max;
            }
        }
        
        return max;
    }   
    
     public Producto getProductoPorNombre(String id){
        //Definición de variable entidad
        Producto oProducto = null;

        
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
           
             oProducto = new Producto(Integer.parseInt(vDatosRegistro[0]), vDatosRegistro[1],
                                    vDatosRegistro[2], Integer.parseInt(vDatosRegistro[3]),  vDatosRegistro[4], Integer.parseInt(vDatosRegistro[5]), 
                                 Formatos.obtenerFecha(vDatosRegistro[6]),  vDatosRegistro[7], Integer.parseInt(vDatosRegistro[8]));
            
        }

        return oProducto;
    }
}
