import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear una lista de objetos Damageable
        ArrayList<Damageable> damageables = new ArrayList<>();

        // Añadir instancias a la lista
        damageables.add(new Wall(500));
        damageables.add(new Character(300));
        damageables.add(new Knight(400));

        // Aplicar daño a cada objeto
        for (Damageable damageable : damageables) {
            System.out.println("Aplicando 100 puntos de daño...");
            damageable.applyDamage(100);
            System.out.println("----------------------------");
        }
    }
}