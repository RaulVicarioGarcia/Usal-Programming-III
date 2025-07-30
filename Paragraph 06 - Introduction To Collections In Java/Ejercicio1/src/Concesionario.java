import java.util.ArrayList;

public class Concesionario {

    private ArrayList<Coche> coches;

    public Concesionario() {
        this.coches = new ArrayList<>();
    }

    public void nuevoCoche(Coche coche) {
        coches.add(coche);
        System.out.println("Coche agregado correctamente.");
    }

    public void listarCoches() {
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            for (Coche coche : coches) {
                System.out.println(coche);
            }
        }
    }

    public void actualizarCoche(String matricula, String nuevaMarca, int nuevaFechaPrimeraMatriculacion) {
        for (Coche coche : coches) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)) {
                coche.setMarca(nuevaMarca);
                coche.setFechaPrimeraMatriculacion(nuevaFechaPrimeraMatriculacion);
                System.out.println("Coche actualizado correctamente.");
                return;
            }
        }
        System.out.println("Coche no encontrado.");
    }

    public void eliminarCoche(String matricula) {
        for (Coche coche : coches) {
            if (coche.getMatricula().equalsIgnoreCase(matricula)) {
                coches.remove(coche);
                System.out.println("Coche eliminado correctamente.");
                return;
            }
        }
        System.out.println("Coche no encontrado.");
    }
}
