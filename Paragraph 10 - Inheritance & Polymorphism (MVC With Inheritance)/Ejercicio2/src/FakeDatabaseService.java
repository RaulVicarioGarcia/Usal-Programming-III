public class FakeDatabaseService extends DatabaseService{

    @Override
    public void connect(){

        super.connect();
        System.out.println("Tipo de BBDD: Fake");

    }
    
}
