import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear los 15 partidos de la quiniela
        Partido[] partidos = new Partido[15];
        partidos[0] = new Partido("Levante", "Las Palmas");
        partidos[1] = new Partido("Real Madrid", "Barcelona");
        partidos[2] = new Partido("Betis", "Sevilla");
        partidos[3] = new Partido("Athletic", "Real Sociedad");
        partidos[4] = new Partido("Villarreal", "Celta");
        partidos[5] = new Partido("Espanyol", "Getafe");
        partidos[6] = new Partido("Valencia", "Atlético Madrid");
        partidos[7] = new Partido("Granada", "Alavés");
        partidos[8] = new Partido("Mallorca", "Osasuna");
        partidos[9] = new Partido("Eibar", "Huesca");
        partidos[10] = new Partido("Almería", "Betis");
        partidos[11] = new Partido("Zaragoza", "Rayo Vallecano");
        partidos[12] = new Partido("Leganés", "Las Palmas");
        partidos[13] = new Partido("Sporting Gijón", "Lugo");
        partidos[14] = new Partido("Tenerife", "Mirandés");

        // Mostrar los partidos y pedir resultados
        System.out.println("|----------------------------------------------|");
        System.out.println("| QUINIELA                                    |");
        System.out.println("|----------------------------------------------|");
        
        // Mostrar los partidos
        for (int i = 0; i < partidos.length; i++) {
            System.out.println(partidos[i].toString());
        }
        System.out.println("|----------------------------------------------|");

        // Pedir los resultados de los partidos
        for (int i = 0; i < partidos.length; i++) {
            System.out.println("Introduce el resultado para el partido " + (i + 1) + " (1 = victoria local, X = empate, 2 = victoria visitante):");
            String resultado = sc.nextLine().toUpperCase(); // El resultado puede ser "1", "X" o "2"
            while (!resultado.equals("1") && !resultado.equals("X") && !resultado.equals("2")) {
                System.out.println("Resultado no válido. Introduce 1, X o 2:");
                resultado = sc.nextLine().toUpperCase();
            }
            partidos[i].setResultado(resultado);
        }

        // Mostrar la quiniela con los resultados
        System.out.println("|----------------------------------------------|");
        System.out.println("| QUINIELA RESULTADOS                         |");
        System.out.println("|----------------------------------------------|");
        for (int i = 0; i < partidos.length; i++) {
            System.out.println(partidos[i].toString());
        }
        System.out.println("|----------------------------------------------|");

        sc.close();
    }
}
