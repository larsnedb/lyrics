import Domain.Album;
import Domain.Song;
import Utils.FileUtils;
import Utils.ParseUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        String pathname = "/Users/lars.nedberg/Documents/kode/lyrics/vatten/";
        List<File> files = FileUtils.listFilesForFolder(new File(pathname));

        Album album = new Album(pathname);
        album.addSongs(getSongsFromDirectory(files));


        // TODO: 03/11/2018 Samle data fra alle sangene til ett enkelt map som har statistikk for hele albumet
    }

    private static List<Song> getSongsFromDirectory(List<File> files) {
        return files.stream()
                .map(file -> new Song(file.getName(), ParseUtils.getLyricsAsSortedMap(file)))
                .collect(Collectors.toList());
    }
}