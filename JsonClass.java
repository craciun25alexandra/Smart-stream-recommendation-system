
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonClass {

    private String id;
    private String name;
    private String streamerName;
    private String noOfListenings;
    private String length;
    private String dateAdded;

    public JsonClass(@JsonProperty("id") String id, @JsonProperty("name")String name,
                     @JsonProperty("streamerName")String streamerName, @JsonProperty("noOfListenings")String noOfListenings,
                     @JsonProperty("length")String length, @JsonProperty("dateAdded")String dateAdded) {
        this.id = id;
        this.name = name;
        this.streamerName = streamerName;
        this.noOfListenings = noOfListenings;
        this.length = length;
        this.dateAdded = dateAdded;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreamerName() {
        return streamerName;
    }

    public String getNoOfListenings() {
        return noOfListenings;
    }

    public String getLength() {
        return length;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreamerName(String streamerName) {
        this.streamerName = streamerName;
    }

    public void setNoOfListenings(String noofStreams) {
        this.noOfListenings = noofStreams;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
