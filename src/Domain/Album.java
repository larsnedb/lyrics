package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Album {

    private final String name;
    private  List<Song> songs;

    public Album(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addSongs(Collection<Song> newSongs) {
        songs.addAll(newSongs);
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                '}';
    }
}
