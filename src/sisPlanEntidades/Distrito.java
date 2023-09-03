
package sisPlanEntidades;

public class Distrito {
    private int identificador;
    private int numeroProvincia;
    private int numeroCanton;
    private int numeroDistrito;
    private String nombre;

    public Distrito(int pIdentificador, int pNumeroProvincia, int pNumeroCanton, 
                    int pNumeroDistrito, String pNombre) {
        this.identificador = pIdentificador;
        this.numeroProvincia = pNumeroProvincia;
        this.numeroCanton = pNumeroCanton;
        this.numeroDistrito = pNumeroDistrito;
        this.nombre = pNombre;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setNumeroProvincia(int codigo) {
        this.numeroProvincia = codigo;
    }

    public int getNumeroProvincia() {
        return numeroProvincia;
    }

    public void setNumeroCanton(int numeroCanton) {
        this.numeroCanton = numeroCanton;
    }

    public int getNumeroCanton() {
        return numeroCanton;
    }

    public void setNumeroDistrito(int numeroDistrito) {
        this.numeroDistrito = numeroDistrito;
    }

    public int getNumeroDistrito() {
        return numeroDistrito;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString(){
        return this.numeroDistrito + " - " + this.nombre;
    }    
}
