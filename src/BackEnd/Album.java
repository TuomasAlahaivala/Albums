package BackEnd;

public class Album {
    private Integer id;
    private String artist;
    private String name;
    private Integer year;

    public Album(String artist, String name, Integer year) {
        this.artist = artist;
        this.name = name;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getAllData() {
        return artist + " " + name + " " + year;
    }
}
