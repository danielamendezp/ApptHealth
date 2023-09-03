
package sisPlanEntidades;

public class DivisionTerritorial {

    private long identificador;
    private int numeroProvincia;
    private int numeroCanton;
    private int numeroDistrito;
    private String nombre;
    
    /**
     * Constructor de la clase
     * @param pIdentificador Identificación único del registro
     * @param pNumeroProvincia Número de provincia
     * @param pNumeroCanton Número de cantón
     * @param pNumeroDistrito Número de distrito
     * @param pNombre Nombre del lugar
     */
    public DivisionTerritorial(long pIdentificador, int pNumeroProvincia, int pNumeroCanton, int pNumeroDistrito, String pNombre) {
        this.identificador = pIdentificador;
        this.numeroProvincia = pNumeroProvincia;
        this.numeroCanton = pNumeroCanton;
        this.numeroDistrito = pNumeroDistrito;
        this.nombre = pNombre;
    }
    
    public DivisionTerritorial() {
    }
    /**
     * @return the identificador
     */
    public long getIdentificador() {
        return identificador;
    }

    /**
     * @return the numeroProvincia
     */
    public int getNumeroProvincia() {
        return numeroProvincia;
    }

    /**
     * @return the numeroCanton
     */
    public int getNumeroCanton() {
        return numeroCanton;
    }

    /**
     * @return the numeroDistrito
     */
    public int getNumeroDistrito() {
        return numeroDistrito;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    /**
     * @param numeroProvincia the numeroProvincia to set
     */
    public void setNumeroProvincia(int numeroProvincia) {
        this.numeroProvincia = numeroProvincia;
    }

    /**
     * @param numeroCanton the numeroCanton to set
     */
    public void setNumeroCanton(int numeroCanton) {
        this.numeroCanton = numeroCanton;
    }

    /**
     * @param numeroDistrito the numeroDistrito to set
     */
    public void setNumeroDistrito(int numeroDistrito) {
        this.numeroDistrito = numeroDistrito;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toStringArchivo(){
        String vHilera = "";
        
        vHilera = this.identificador + ";" + this.numeroProvincia + ";" + this.numeroCanton + ";" + this.numeroDistrito + ";" + this.nombre;
        
        return vHilera;
    }
}
