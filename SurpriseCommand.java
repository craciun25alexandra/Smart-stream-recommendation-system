import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import static java.lang.Integer.decode;

class CustomComparator2 implements Comparator<Streams> {

    public int compare(Streams c1, Streams c2) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date date1 = new Date(c1.getDateAdded() * 1000);
        Date date2 = new Date(c2.getDateAdded() * 1000);

        String date1Str = sdf.format(date1);
        String date2Str = sdf.format(date2);

        if(date1Str.equals(date2Str)) {
            if(c1.getNoofStreams().compareTo(c2.getNoofStreams()) < 0) return 1;
            else return -1;
        }
        else if (c1.getDateAdded().compareTo(c2.getDateAdded()) < 0) return 1;
        else return -1;
    }
}
public class SurpriseCommand extends Command {
    public void executa(ApplicationFacade app, String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = app.getUserById(decode(args[0]));
        Integer type;
        if (args[2].equals("SONG")) type = 1;
        else if (args[2].equals("PODCAST")) type = 2;
        else type = 3;
        List<Integer> streamersUser = user.getStreamerIds(app);
        PriorityQueue<Streams> queueSurprise = new PriorityQueue<>(new CustomComparator2());
        for(Streams stream : app.getStreams()) {
            if(stream.getStreamType().equals(type) && !streamersUser.contains(stream.getStreamerId())) {
                queueSurprise.add(stream);
            }
        }
        boolean ok = false;
        if (queueSurprise.size() > 0) {
            ok = true;
            System.out.print("[");
        }
        int numberOfRecomended = 0;
        while(queueSurprise.size() > 0 && numberOfRecomended < 3) {
            Streams stream = queueSurprise.poll();
            JsonClass objForJson = app.getJsonClass(stream);
            String json;
            try {
                json = objectMapper.writeValueAsString(objForJson);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.print(json);
            numberOfRecomended++;
            if(queueSurprise.size() > 0 && numberOfRecomended < 3) {
                System.out.print(",");
            }
        }
        if(ok) {
            System.out.println("]");
        }
    }
}
