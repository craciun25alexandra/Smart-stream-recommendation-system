import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ProiectPOO {
    public static void main(String[] args) throws FileNotFoundException {
        if (args == null) {
            System.out.println("Nothing to read here");
        } else {
            ApplicationFacade app = ApplicationFacade.getInstance();
            String usernamefile = "src/main/resources/" + args[2];
            String streamersname = "src/main/resources/" + args[0];
            String streamsName = "src/main/resources/" + args[1];
            app.InitApp(usernamefile, streamersname, streamsName);

            File file = new File("src/main/resources/" + args[3]);
            Scanner scanner = new Scanner(file);
            CommandFactory f = CommandFactory.getInstance();
            while (scanner.hasNextLine()) {
                String[] arguments = scanner.nextLine().split(" ");
                Command command = f.creeazaComanda(arguments[1]);
                command.executa(app, arguments);
            }
            scanner.close();
            app.deleteInstance();
        }
    }
}
