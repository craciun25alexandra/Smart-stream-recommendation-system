public class Streamers{
    private Integer streamerType;
    private Integer id;
    private String name;
    public Streamers(Integer streamerType, Integer id, String name) {

        this.streamerType = streamerType;
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
