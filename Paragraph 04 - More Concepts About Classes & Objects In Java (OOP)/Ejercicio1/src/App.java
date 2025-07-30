public class App {
    public static void main(String[]args) throws Exception {
 
        if (args.length != 2) {
                
            System.out.println("Debes introducir al menos 2 argumentos");

        }

        try {

            int numero1 = Integer.parseInt(args[0]);
            int numero2 = Integer.parseInt(args[1]);

            int present_Result = numero1 + numero2;

            System.out.println("La suma es: "+present_Result);

        } catch (NumberFormatException e) {

            System.out.println("Error. "+e.getMessage());

        }

    }

}
