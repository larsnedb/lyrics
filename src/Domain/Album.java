package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Album {

    private final String name;
    private final List<Song> songs;

    public Album(String name, List<Song> songsFromDirectory) {
        this.name = name;
        songs = new ArrayList<>(songsFromDirectory);
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", number of songs=" + songs.size() +
                '}';
    }
}
