import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el número de alumnos en el curso:");
        int numAlumnos = sc.nextInt();
        sc.nextLine(); // Consumir la línea restante

        Alumno[] alumnos = new Alumno[numAlumnos];

        // Ingresar datos para cada alumno
        for (int i = 0; i < numAlumnos; i++) {
            System.out.println("Introduce los datos del alumno " + (i + 1) + " separados por comas (Ejemplo: Nombre,Parcial1,Parcial2,Final):");
            String input = sc.nextLine();
            String[] datosAlumno = input.split(",");

            // Usar el factory method proporcionado para crear la instancia de Alumno
            alumnos[i] = Alumno.crearDesdeArray(datosAlumno);
        }

        // Mostrar calificaciones en formato tabla
        System.out.println("------------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-10s | %-10s | %-10s |\n",
                          "Nombre", "Parcial 1", "Parcial 2", "Examen Final", "Nota Final");
        System.out.println("------------------------------------------------------------");

        double sumaParcial1 = 0, sumaParcial2 = 0, sumaFinalExamen = 0, sumaNotasFinales = 0;

        for (Alumno alumno : alumnos) {
            double notaFinal = alumno.calcularNotaFinal();

            System.out.printf("| %-15s | %-10.2f | %-10.2f | %-10.2f | %-10.2f |\n",
                              alumno.getNombre(), alumno.getParcial1(), alumno.getParcial2(),
                              alumno.getFinalExamen(), notaFinal);

            sumaParcial1 += alumno.getParcial1();
            sumaParcial2 += alumno.getParcial2();
            sumaFinalExamen += alumno.getFinalExamen();
            sumaNotasFinales += notaFinal;
        }

        System.out.println("------------------------------------------------------------");

        // Calcular y mostrar las medias
        double mediaParcial1 = sumaParcial1 / numAlumnos;
        double mediaParcial2 = sumaParcial2 / numAlumnos;
        double mediaFinalExamen = sumaFinalExamen / numAlumnos;
        double mediaNotasFinales = sumaNotasFinales / numAlumnos;

        System.out.println("Media de la clase:");
        System.out.printf("Parcial 1: %.2f | Parcial 2: %.2f | Examen Final: %.2f | Nota Final: %.2f\n",
                          mediaParcial1, mediaParcial2, mediaFinalExamen, mediaNotasFinales);

        sc.close();
    }
}
