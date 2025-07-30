public class Coche {
    
    private String marca;
    private String matricula;
    private int fechaPrimeraMatriculacion;

    public Coche (String marca, String matricula, int fechaPrimeraMatriculacion) {

        this.marca=marca;
        this.matricula=matricula;
        this.fechaPrimeraMatriculacion=fechaPrimeraMatriculacion;

    }

    public int getFechaPrimeraMatriculacion() {
        return fechaPrimeraMatriculacion;
    }
    public String getMarca() {
        return marca;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setFechaPrimeraMatriculacion(int fechaPrimeraMatriculacion) {
        this.fechaPrimeraMatriculacion = fechaPrimeraMatriculacion;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {

        return ""+marca+" | "+matricula+" | "+fechaPrimeraMatriculacion;

    }

}
