/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisPlanEntidades;

/**
 *
 * @author Daniela Fabiola
 */
public enum EIngresos {
    N1(1, "N1", "De 1 a 500,000.00 mensuales"),
    N2(2, "N2", "De más de 500,000.00 a 1,000,000.00 mensuales"),
    N3(3, "N3", "De más de 1,000,000.00 a 1,500,000.00 mensuales"),
    N4(4, "N4", "De más de 1,500,000.00 en adelante");

    private int num;
    private String codigo;
    private String desripcion;

    private EIngresos(int num, String codigo, String desripcion) {
        this.num = num;
        this.codigo = codigo;
        this.desripcion = desripcion;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDesripcion() {
        return desripcion;
    }

    public void setDesripcion(String desripcion) {
        this.desripcion = desripcion;
    }

    public static EIngresos buscarIngreso(String pCodigo) {
        EIngresos vnivel = null;

        switch (pCodigo) {
            case "N1":
                vnivel = EIngresos.N1;
                break;
            case "N2":
                vnivel = EIngresos.N2;
                break;
            case "N3":
                vnivel = EIngresos.N3;
                break;
            case "N4":
                vnivel = EIngresos.N4;
                break;
            default:
                break;
        }
        return vnivel;
    }

    @Override
    public String toString() {
        return num + " - " + desripcion;
    }
}

