/*
 * Universidad Técnica Nacional - UTN
 * Carrera de Ingeniería del Software
 * Curso ISW-311 Programación II
 *  Ing. Daniela Mendez
 */
package sisPlanDAL;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sisPlanBLL.DoctorBLL;
import sisPlanBLL.EnfermedadBLL;
import sisPlanBLL.ProductoBLL;
import sisPlanBLL.ServiciosMedicosBLL;
import sisPlanEntidades.Enfermedad;
import sisPlanEntidades.Producto;
import sisPlanEntidades.RegistroPadecimiento;
import sisPlanEntidades.ServiciosMedicos;
import sisPlanUtilitario.Formatos;

public class PS_RegistroPadecimientoDAL  extends BaseDAL {

    public PS_RegistroPadecimientoDAL(){
        //Rutas para los archivos de datos
        this.rutaArchivoDatos = ".\\src\\sisPlanDatos\\RegistroPadecimiento.dat";
        this.numeroColumnaLlavePrimaria = 0;
        this.oManejadorArchivosDAL = new ManejadorArchivosDAL();        
    }

    public void agregarRegistroPadecimiento(RegistroPadecimiento pRegistroPadecimiento) {
        if(existeRegistroPadecimiento(String.valueOf(pRegistroPadecimiento.getCodigo()))){
            JOptionPane.showMessageDialog(null, "¡Ya existe un RegistroPadecimiento con estos datos!");
        }else{
            this.agregarLinea(pRegistroPadecimiento.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡RegistroPadecimiento agregado con exito!");
        }
        
    }

    public void modificarRegistroPadecimiento(RegistroPadecimiento pRegistroPadecimiento) {
        if(existeRegistroPadecimiento(String.valueOf(pRegistroPadecimiento.getCodigo()))){
            JOptionPane.showMessageDialog(null, "¡No existe un RegistroPadecimiento con estos datos!");
        }else{
            this.modificarLinea(String.valueOf(pRegistroPadecimiento.getCodigo()), 
                            this.numeroColumnaLlavePrimaria, 
                            pRegistroPadecimiento.toStringArchivo());
            JOptionPane.showMessageDialog(null, "¡Actualizacion Exitosa!");
        }
    }

    public void eliminarRegistroPadecimiento(String pCodigo) {
         if(!existeRegistroPadecimiento(pCodigo)){
            JOptionPane.showMessageDialog(null, "¡No existe un RegistroPadecimiento con estos datos!");
        }else{
             this.eliminarLinea(pCodigo,
                           this.numeroColumnaLlavePrimaria);
             JOptionPane.showMessageDialog(null, "¡Eliminacion Exitosa!");
         }
    }
    
    
    public RegistroPadecimiento buscarRegistroPadecimiento(String pCodigo) throws ParseException {
        RegistroPadecimiento oRegistroPadecimiento = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          this.numeroColumnaLlavePrimaria);
        if (vLineaRegistro != null) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
            //Crear la instancia 
            ProductoBLL producto= new ProductoBLL();
            Producto product=null;
            EnfermedadBLL enfermedad= new EnfermedadBLL();
            Enfermedad padecimiento= null;
            ServiciosMedicosBLL serviciosmedicos= new ServiciosMedicosBLL();
            ServiciosMedicos servicio=null;
            DoctorBLL doctor=new DoctorBLL();
            
            //VERIFICACIONES DE SI NO TIENE REGISTROS
            if(vDatosRegistro[3].equals("null")){
                product=null;
            }else{
                product=producto.buscarProducto(Integer.parseInt(vDatosRegistro[3]));
            }
            
            if(vDatosRegistro[2].equals("null")){
                padecimiento=null;
            }else{
                padecimiento=enfermedad.buscarEnfermedad(vDatosRegistro[2]);
            }
            
            if(vDatosRegistro[4].equals("null")){
                servicio=null;
            }else{
                servicio=serviciosmedicos.buscarServiciosMedicos(Integer.parseInt(vDatosRegistro[4]));
            }
            
            oRegistroPadecimiento = new RegistroPadecimiento(Integer.parseInt(vDatosRegistro[0]), Integer.parseInt(vDatosRegistro[1]),
                    padecimiento,  product, servicio, doctor.buscarDoctor(vDatosRegistro[5]), Formatos.obtenerFecha(vDatosRegistro[6]));
        }
        return oRegistroPadecimiento;
    }
    
    public RegistroPadecimiento buscarRegistroPadecimientoPorExpediente(String pCodigo) throws ParseException {
        RegistroPadecimiento oRegistroPadecimiento = null;
        String vLineaRegistro = this.buscarLinea(pCodigo, 1);

        if (vLineaRegistro != null) {
            String[] vDatosRegistro = vLineaRegistro.split(";");

            
                ProductoBLL producto = new ProductoBLL();
                Producto product = null;
                EnfermedadBLL enfermedad = new EnfermedadBLL();
                Enfermedad padecimiento = null;
                ServiciosMedicosBLL serviciosmedicos = new ServiciosMedicosBLL();
                ServiciosMedicos servicio = null;
                DoctorBLL doctor = new DoctorBLL();

                if (vDatosRegistro[3].equals("null")) {
                    product = null;
                } else {
                    product = producto.buscarProducto(Integer.parseInt(vDatosRegistro[3]));
                }

                if (vDatosRegistro[2].equals("null")) {
                    padecimiento = null;
                } else {
                    padecimiento = enfermedad.buscarEnfermedad(vDatosRegistro[2]);
                }

                if (vDatosRegistro[4].equals("null")) {
                    servicio = null;
                } else {
                    servicio = serviciosmedicos.buscarServiciosMedicos(Integer.parseInt(vDatosRegistro[4]));
                }

                oRegistroPadecimiento = new RegistroPadecimiento(
                    Integer.parseInt(vDatosRegistro[0]),
                    Integer.parseInt(vDatosRegistro[1]),
                    padecimiento,
                    product,
                    servicio,
                    doctor.buscarDoctor(vDatosRegistro[5]),
                    Formatos.obtenerFecha(vDatosRegistro[6])
                );
            }
        

        return oRegistroPadecimiento;
    }

    
    public RegistroPadecimiento buscarRegistroPadecimientoPorDoctor(String pCodigo) throws ParseException {
        RegistroPadecimiento oRegistroPadecimiento = null;
        String vLineaRegistro;
        vLineaRegistro = this.buscarLinea(pCodigo, 
                                          5);
        if (vLineaRegistro != null) {
            String[] vDatosRegistro = vLineaRegistro.split(";");
             //Crear la instancia 
            ProductoBLL producto= new ProductoBLL();
            Producto product=null;
            EnfermedadBLL enfermedad= new EnfermedadBLL();
            Enfermedad padecimiento= null;
            ServiciosMedicosBLL serviciosmedicos= new ServiciosMedicosBLL();
            ServiciosMedicos servicio=null;
            DoctorBLL doctor=new DoctorBLL();
            
            //VERIFICACIONES DE SI NO TIENE REGISTROS
            if(vDatosRegistro[3].equals("null")){
                product=null;
            }else{
                product=producto.buscarProducto(Integer.parseInt(vDatosRegistro[3]));
            }
            
            if(vDatosRegistro[2].equals("null")){
                padecimiento=null;
            }else{
                padecimiento=enfermedad.buscarEnfermedad(vDatosRegistro[2]);
            }
            
            if(vDatosRegistro[4].equals("null")){
                servicio=null;
            }else{
                servicio=serviciosmedicos.buscarServiciosMedicos(Integer.parseInt(vDatosRegistro[4]));
            }
            
            oRegistroPadecimiento = new RegistroPadecimiento(Integer.parseInt(vDatosRegistro[0]), Integer.parseInt(vDatosRegistro[1]),
                    padecimiento,  product, servicio, doctor.buscarDoctor(vDatosRegistro[5]), Formatos.obtenerFecha(vDatosRegistro[6]));
        }
        return oRegistroPadecimiento;
    }
    
    public List<RegistroPadecimiento> getListaRegistroPadecimientosPorAproximacion(String[] pValores) throws ParseException {
        //Variable para la instancia Entidad
        RegistroPadecimiento oRegistroPadecimiento= null;
        
        List<RegistroPadecimiento> oListaRegistroPadecimientos = new ArrayList<>();
        
        int[] vIndices = new int[2];
        vIndices[0] = 0;
        vIndices[1] = 1;
        
        //Validar las dimensiones de las estructuras
        if (pValores.length != vIndices.length) {
            ManejadorErrorSistemaDAL.registrarError("SP-1201",
                                                    "Error de tamaños de estructuras",
                                                    "Los tamaños de los vectores de datos e indices son diferentes, debe verificar.", 
                                                    this.getClass().getName(), 
                                                    Thread.currentThread().getStackTrace()[2].getMethodName());            
        }else {
            List<String> oListaRegistros = this.buscarLineasPorAproximacion(pValores, 
                                                                            vIndices);
            for (String vRegistro : oListaRegistros) {
                String[] vDatosRegistro = vRegistro.split(";");
            //Crear la instancia 
            ProductoBLL producto= new ProductoBLL();
            Producto product=null;
            EnfermedadBLL enfermedad= new EnfermedadBLL();
            Enfermedad padecimiento= null;
            ServiciosMedicosBLL serviciosmedicos= new ServiciosMedicosBLL();
            ServiciosMedicos servicio=null;
            DoctorBLL doctor=new DoctorBLL();
            
            //VERIFICACIONES DE SI NO TIENE REGISTROS
            if(vDatosRegistro[3].equals("null")){
                product=null;
            }else{
                product=producto.buscarProducto(Integer.parseInt(vDatosRegistro[3]));
            }
            
            if(vDatosRegistro[2].equals("null")){
                padecimiento=null;
            }else{
                padecimiento=enfermedad.buscarEnfermedad(vDatosRegistro[2]);
            }
            
            if(vDatosRegistro[4].equals("null")){
                servicio=null;
            }else{
                servicio=serviciosmedicos.buscarServiciosMedicos(Integer.parseInt(vDatosRegistro[4]));
            }
            
            oRegistroPadecimiento = new RegistroPadecimiento(Integer.parseInt(vDatosRegistro[0]), Integer.parseInt(vDatosRegistro[1]),
                    padecimiento,  product, servicio, doctor.buscarDoctor(vDatosRegistro[5]), Formatos.obtenerFecha(vDatosRegistro[6]));
                oListaRegistroPadecimientos.add(oRegistroPadecimiento);
            }
        }
        return oListaRegistroPadecimientos;
    }
    
    public boolean existeRegistroPadecimiento(String pCodigo) {
        return this.existeLinea(pCodigo, this.numeroColumnaLlavePrimaria);
    }
     public List<RegistroPadecimiento> getRegistroPadecimiento(String id) throws ParseException {
        //Definición de variable entidad
        RegistroPadecimiento oRegistroPadecimiento= null;

        //Definir objeto ArrayList para cargar los objetos
        List<RegistroPadecimiento> oArrayListServiciosMedicos = new ArrayList<>();
        
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
            ProductoBLL producto= new ProductoBLL();
            Producto product=null;
            EnfermedadBLL enfermedad= new EnfermedadBLL();
            Enfermedad padecimiento= null;
            ServiciosMedicosBLL serviciosmedicos= new ServiciosMedicosBLL();
            ServiciosMedicos servicio=null;
            DoctorBLL doctor=new DoctorBLL();
            
            //VERIFICACIONES DE SI NO TIENE REGISTROS
            if(vDatosRegistro[3].equals("null")){
                product=null;
            }else{
                product=producto.buscarProducto(Integer.parseInt(vDatosRegistro[3]));
            }
            
            if(vDatosRegistro[2].equals("null")){
                padecimiento=null;
            }else{
                padecimiento=enfermedad.buscarEnfermedad(vDatosRegistro[2]);
            }
            
            if(vDatosRegistro[4].equals("null")){
                servicio=null;
            }else{
                servicio=serviciosmedicos.buscarServiciosMedicos(Integer.parseInt(vDatosRegistro[4]));
            }
            
            oRegistroPadecimiento = new RegistroPadecimiento(Integer.parseInt(vDatosRegistro[0]), Integer.parseInt(vDatosRegistro[1]),
                    padecimiento,  product, servicio, doctor.buscarDoctor(vDatosRegistro[5]), Formatos.obtenerFecha(vDatosRegistro[6]));
            
            //Agregar la instancia a la colección
            oArrayListServiciosMedicos.add(oRegistroPadecimiento);
        }

        return oArrayListServiciosMedicos;
    }
     
     public int getConsecutivo() throws ParseException{
        RegistroPadecimiento oRegistroPadecimiento= null;
        List<RegistroPadecimiento> oListaRegistroPadecimientos = new ArrayList<>();
        int max = 0;
        
        List<String> oListaRegistros = this.getListaRegistros();
        for(String vRegistro : oListaRegistros){
            String[] vDatosRegistro = vRegistro.split(";");
             //Crear la instancia 
            ProductoBLL producto= new ProductoBLL();
            Producto product=null;
            EnfermedadBLL enfermedad= new EnfermedadBLL();
            Enfermedad padecimiento= null;
            ServiciosMedicosBLL serviciosmedicos= new ServiciosMedicosBLL();
            ServiciosMedicos servicio=null;
            DoctorBLL doctor=new DoctorBLL();
            
            //VERIFICACIONES DE SI NO TIENE REGISTROS
            if(vDatosRegistro[3].equals("null")){
                product=null;
            }else{
                product=producto.buscarProducto(Integer.parseInt(vDatosRegistro[3]));
            }
            
            if(vDatosRegistro[2].equals("null")){
                padecimiento=null;
            }else{
                padecimiento=enfermedad.buscarEnfermedad(vDatosRegistro[2]);
            }
            
            if(vDatosRegistro[4].equals("null")){
                servicio=null;
            }else{
                servicio=serviciosmedicos.buscarServiciosMedicos(Integer.parseInt(vDatosRegistro[4]));
            }
            
            oRegistroPadecimiento = new RegistroPadecimiento(Integer.parseInt(vDatosRegistro[0]), Integer.parseInt(vDatosRegistro[1]),
                    padecimiento,  product, servicio, doctor.buscarDoctor(vDatosRegistro[5]), Formatos.obtenerFecha(vDatosRegistro[6]));
                
                oListaRegistroPadecimientos.add(oRegistroPadecimiento);
             
            // Encontrar el número más grande 
            int min = oRegistroPadecimiento.getCodigo();
            if (min > max) {
                max = min;
            }else{
                max=max;
            }
        }
        
        return max;
    } 

}
