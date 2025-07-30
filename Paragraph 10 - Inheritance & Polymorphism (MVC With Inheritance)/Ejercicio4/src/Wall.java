public class Wall implements Damageable{

    private int durability;

    public Wall(int durability) {
        this.durability = durability;
    }

    @Override
    public void applyDamage (int damagePoints) {

        durability -= damagePoints;
        if (durability <= 0) {
            System.out.println("La pared ha sido destruida.");
            durability = 0;
        } else {
            System.out.println("Durabilidad restante de la pared: " + durability);

    }
    
} }
