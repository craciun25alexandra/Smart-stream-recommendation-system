import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import static java.lang.Integer.decode;
class CustomComparator implements Comparator<Streams> {

    public int compare(Streams c1, Streams c2) {

        if (c1.getNoofStreams().compareTo(c2.getNoofStreams()) > 0) return -1;
        else return 1;

    }
}

public class RecommendCommand extends Command {
    public void executa(ApplicationFacade app, String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = app.getUserById(decode(args[0]));
        Integer type;
        if (args[2].equals("SONG")) type = 1;
        else if (args[2].equals("PODCAST")) type = 2;
        else type = 3;
        List<Integer> streamersUser = user.getStreamerIds(app);
        PriorityQueue <Streams> queueRecomended = new PriorityQueue<>(new CustomComparator());
        for(Streams stream : app.getStreams()) {
            if(stream.getStreamType().equals(type) && streamersUser.contains(stream.getStreamerId()) &&
                    !user.getStreams().contains(stream.getId())) {
                queueRecomended.add(stream);
            }
        }
        boolean ok = false;
        if(queueRecomended.size() > 0)
        {
             ok = true;
            System.out.print("[");
        }
        int numberOfRecomended = 0;
        while(queueRecomended.size() > 0 && numberOfRecomended < 5) {
            Streams stream = queueRecomended.poll();
            JsonClass objForJson = app.getJsonClass(stream);
            String json;
            try {
                json = objectMapper.writeValueAsString(objForJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.print(json);
            numberOfRecomended++;
            if(queueRecomended.size() > 0) {
                System.out.print(",");
            }
        }
        if(ok) {
            System.out.println("]");
        }
    }
}
