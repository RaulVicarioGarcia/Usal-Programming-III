package model;

public class Dni {

    private int numero;
    private char letra;

    public Dni(int numero) {
        this.numero = numero;
        this.letra = calcularLetra(numero);
    }

    private char calcularLetra(int numero) {
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 
                         'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int indice = numero % 23;
        return letras[indice];
    }

    public int getNumero() {
        return numero;
    }

    public char getLetra() {
        return letra;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        this.letra = calcularLetra(numero);
    }
}
