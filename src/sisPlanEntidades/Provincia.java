
package sisPlanEntidades;

public class Provincia {
    private int identificador;
    private int numeroProvincia;
    private String nombre;

    public Provincia(int pIdentificador, int pNumeroProvincia, String pNombre) {
        this.identificador = pIdentificador;
        this.numeroProvincia = pNumeroProvincia;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString(){
        return this.numeroProvincia + " - " + this.nombre;
    }    
}
