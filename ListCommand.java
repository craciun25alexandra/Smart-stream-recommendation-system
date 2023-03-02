import java.util.Date;
import java.util.ListIterator;
import static java.lang.Integer.decode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ListCommand extends Command {
    public void executa(ApplicationFacade app, String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = app.getUserById(decode(args[0]));
        int poz = 0;
        if(user != null) {
            System.out.print("[");
            CustomIteratorUsers<Streams> iterator = new CustomIteratorUsers<>(app.getStreams());
            for (Integer streamId : user.getStreams()) {
                poz++;
                Streams streams1 = iterator.next(streamId);
                JsonClass objForJson = app.getJsonClass(streams1);
                String json;
                try {
                    json = objectMapper.writeValueAsString(objForJson);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                System.out.print(json);
                iterator.resetPozition();
                if(poz < user.getStreams().size()) {
                    System.out.print(",");}
            }
            System.out.println("]");
        } else {
            Date date = new Date();
            Long time = date.getTime() / 1000;
            boolean ok = false;
            CustomReverseListIterator<Streams> iterator = new CustomReverseListIterator<>(app.getStreams());
            Streams streams2 = iterator.next(decode(args[0]), time);
            while(streams2 != null) {
                    if(ok) {
                        System.out.print(",");
                    }
                    else {
                        System.out.print("[");
                    }
                    JsonClass objForJson = app.getJsonClass(streams2);
                    String json;
                    try {
                        json = objectMapper.writeValueAsString(objForJson);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print(json);
                    ok = true;

                streams2 = iterator.next(decode(args[0]), time);
            }
            if(ok) {
                System.out.print("]\n");
            }
        }
    }
}
