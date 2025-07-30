import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        ArrayList<DatabaseService> services = new ArrayList<>();

        services.add(new MySQLDatabaseService());
        services.add(new SQLiteDatabaseService());
        services.add(new FakeDatabaseService());

        for (DatabaseService service : services) {

            service.connect();
            System.out.println("--------------------");

        }

    } 

}