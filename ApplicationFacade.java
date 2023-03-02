import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.decode;

public class ApplicationFacade {
    private List<Streamers> streamers;
    private List<Streams> streams;
    private List<User> users;
    private static ApplicationFacade instance;

    private ApplicationFacade() {
    }
    public static void deleteInstance() {
        ApplicationFacade.instance = null;
    }

    public static ApplicationFacade getInstance() {
        if (instance == null) {
            instance = new ApplicationFacade();
            instance.streamers = new ArrayList<>();
            instance.streams = new ArrayList<>();
            instance.users = new ArrayList<>();
        }
        return instance;
    }

    public List<Streamers> getStreamers() {
        return streamers;
    }

    public List<Streams> getStreams() {
        return streams;
    }

    public List<User> getUsers() {
        return users;
    }

    public void InitApp(String usernames, String streamersName, String streamsName) throws FileNotFoundException {
        File fileUsers = new File(usernames);
        Scanner scannerUsers = new Scanner(fileUsers);
        scannerUsers.nextLine();
        while (scannerUsers.hasNextLine()) {
            String[] arguments = scannerUsers.nextLine().replaceAll("\"", "").split(",");
            User user = new User(arguments[1], Integer.parseInt(arguments[0]));
            String[] streamsIds = arguments[2]. split(" ");
            for(String s : streamsIds)
                user.adaugaStream(Integer.parseInt(s));
            this.users.add(user);
        }
        File fileStreams = new File(streamsName);
        Scanner scannerStreams = new Scanner(fileStreams);
        scannerStreams.nextLine();
        while (scannerStreams.hasNextLine()) {
            String[] arguments = scannerStreams.nextLine().replaceAll("\",",
                    "separator").replaceAll("\"", "").split("separator");
            Streams stream = new Streams(decode(arguments[0]), decode(arguments[1]), decode(arguments[2]),
                    Long.valueOf(arguments[3]),  decode(arguments[4]),  Long.valueOf(arguments[5]),
                    Long.valueOf(arguments[6]),  arguments[7]);
            this.streams.add(stream);
        }
        File fileStreamers = new File(streamersName);
        Scanner scannerStreamers = new Scanner(fileStreamers);
        scannerStreamers.nextLine();
        while (scannerStreamers.hasNextLine()) {
            String[] arguments = scannerStreamers.nextLine().replaceAll("\"", "").split(",");
            Streamers streamer= new Streamers(decode(arguments[0]), decode(arguments[1]), arguments[2]);
            this.streamers.add(streamer);
        }
        scannerUsers.close();
        scannerStreams.close();
        scannerStreamers.close();

    }
    public String getNameStreamer(Integer id) {
        for (Streamers streamer : this.streamers) {
            if (streamer.getId().equals(id)) {
                return streamer.getName();
            }
        }
        return null;
    }
    public Streams getStreamById(Integer id) {
        for(Streams s : this.streams)
            if(s.getId().equals(id))
                return s;
        return null;
    }
    public User getUserById(Integer id) {
        for(User user : this.users)
            if(user.getId().equals(id))
                return user;
        return null;
    }

    public Integer getStreamerIdByStream(Integer id) {
        Streams s = this.getStreamById(id);
        return s.getStreamerId();
    }
    public JsonClass getJsonClass(Streams s) {
        String streamerName = this.getNameStreamer(s.getStreamerId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateString = formatter.format(new Date(s.getDateAdded() * 1000));
        String timeString;
        Integer id = s.getId();
        Long length = s.getLength();
        if(length > 3600) {
            timeString = String.format("%02d:%02d:%02d",
                    length / 3600,
                    (length % 3600) / 60,
                    length % 60);
        } else {
            timeString = String.format("%02d:%02d",
                    length / 60,
                    length % 60);
        }
        return new JsonClass(id.toString(),s.getName(),streamerName, s.getNoofStreams().toString(),timeString, dateString);
    }

}
