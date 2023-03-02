public class Streams {
    private Integer streamType;
    private Integer id;
    private Integer streamGendre;
    private Long noofStreams;
    private Integer streamerId;
    private Long length;
    private Long dateAdded;
    private String name;
    public Streams(Integer streamType, Integer id, Integer streamGendre,
                   Long noofStreams, Integer streamerId, Long length, Long dateAdded, String name) {
        this.name = name;
        this.id = id;
        this.streamType = streamType;
        this.streamGendre = streamGendre;
        this.noofStreams = noofStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
    }

    public Integer getId() {
        return id;
    }
    public Integer getStreamerId() {
        return streamerId;
    }

    public String getName() {
        return name;
    }

    public Long getNoofStreams() {
        return noofStreams;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public Integer getStreamType() {
        return streamType;
    }

    public Long getLength() {
        return length;
    }
    public void increaseListenings() {
        this.noofStreams ++;
    }

}
