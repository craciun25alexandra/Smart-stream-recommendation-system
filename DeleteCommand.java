import static java.lang.Integer.decode;

public class DeleteCommand extends Command {
    public void executa(ApplicationFacade app, String[] args) {
        for(Streams stream: app.getStreams())
            if(stream.getId().equals(decode(args[2]))) {
                app.getStreams().remove(stream);
                break;}
        for(User user: app.getUsers())
            for(Integer streamId : user.getStreams())
                if(streamId.equals(decode(args[2]))) {
                    user.getStreams().remove(streamId);
                    break;}
    }
}
