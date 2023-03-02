import java.util.Date;
public class AddCommand extends Command {
    public void executa(ApplicationFacade app, String[] args) {
        String name = "";
        for(int i = 6; i < args.length; i++) {
            name = name + args[i];
            if(i < args.length - 1) {
                name = name + " ";
            }
        }
        Date date = new Date();
        Long time = date.getTime() / 1000;
        app.getStreams() .add(new Streams(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]),
                Long.valueOf("0"), Integer.parseInt(args[0]), Long.valueOf(args[5]), time, name));
    }
}
