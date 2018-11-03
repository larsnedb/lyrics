package Domain;

import java.util.ArrayList;
import java.util.List;


public class Album {

    private final String name;
    private final List<Song> songs;

    public Album(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public String getName() {
        return name;
    }
}
