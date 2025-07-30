package model;

public class Suma {

    private int numero1;
    private int numero2;
    private int total;

    public Suma() {
        this.numero1 = 0;
        this.numero2 = 0;
        this.total = 0;
    }

    public int getTotal() {
        return total;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }

    public void calcularSuma() {
        this.total = numero1 + numero2;
    }
}
