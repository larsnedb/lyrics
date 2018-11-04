import Domain.Song;
import Utils.FileUtils;
import Utils.ParseUtils;
import Utils.SongFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        String pathname = "./resources/Vatten_under_broarna/";
        List<SongFile> songFiles = FileUtils.getSongFiles(pathname);

        List<Song> songs = ParseUtils.mapFilesToSongs(songFiles);

        Map<String, Long> sortedTotal = ParseUtils.getAggregatedOccurencesForAlbum(songs);

        //Album album = new Album(pathname, songs);

        System.out.println(pathname + "\n" +
                sortedTotal.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue() + "\n")
                .limit(10)
                .collect(Collectors.toList()));
    }
}