public class Character implements Damageable {

    private int health;

    public Character(int health) {
        this.health=health;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void applyDamage(int damagePoints){
        health -= damagePoints;
        if (health <= 0) {
            System.out.println("El personaje está fuera de combate.");
            health = 0;
        } else {
            System.out.println("Salud restante del personaje: " + health);
        }
    }
    
}
