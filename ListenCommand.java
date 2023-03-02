import static java.lang.Integer.decode;

public class ListenCommand extends Command {
    public void executa(ApplicationFacade app, String[] args) {
        boolean ok = false;
        User user = app.getUserById(decode(args[0]));
            for(Integer streamId : user.getStreams()) {
                if(streamId.equals(decode(args[2]))) {
                    ok = true;
                    app.getStreamById(streamId).increaseListenings();
                    break;
                }
            }
        if(!ok) {
            app.getStreamById(decode(args[2])).increaseListenings();
            user.getStreams().add(decode(args[2]));

        }
    }
}


