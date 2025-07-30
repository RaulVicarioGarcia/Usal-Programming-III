import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {

        int numero;
        float flotante;
        float media = 0;
        float suma = 0;

        do {

            numero = Esdia.readInt("Introduce un numero mayor que 0: ");

            if (numero <= 0) {

                System.err.println("Error.");

            }

        } while (numero <= 0);
    
        for (int i = 0; i < numero; i++) {

            flotante = Esdia.readFloat("Introduce un numero en coma flotante: ");
            suma += flotante;

        }

        media = suma/numero;

        System.out.println("La media es: "+media);

    }
    
}
