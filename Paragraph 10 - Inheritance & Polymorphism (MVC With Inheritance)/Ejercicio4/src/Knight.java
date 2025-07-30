public class Knight extends Character {
    
    public Knight(int health) {
        super(health);
    }

    @Override
    public void applyDamage(int damagePoints) {
        int reducedDamage = (int) (damagePoints * 0.9); // Reduce el daño en un 10%
        super.applyDamage(reducedDamage);
    }

}
