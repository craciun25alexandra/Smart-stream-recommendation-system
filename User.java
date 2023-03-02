import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private List<Integer> streams;
    public User(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.streams = new ArrayList<>();
    }
    public void adaugaStream(Integer streamId) {
        this.streams.add(streamId);
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getStreams() {
        return streams;
    }
    public List<Integer> getStreamerIds(ApplicationFacade app) {
        List<Integer> streamerIds = new ArrayList<>();
        for(Integer streamId : streams) {
            if(!streamerIds.contains(app.getStreamerIdByStream(streamId)))
                streamerIds.add(app.getStreamerIdByStream(streamId));
        }
        return streamerIds;
    }
}
