import Domain.Album;
import Domain.Song;
import Utils.FileUtils;
import Utils.ParseUtils;
import com.sun.xml.internal.ws.server.ServerRtException;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.*;


public class Main {

    public static void main(String[] args) {
        String pathname = "./resources/Vatten_under_broarna/";
        List<File> files = FileUtils.getFilesInDirectory(pathname);

        Album album = new Album(pathname, FileUtils.getSongsFromDirectory(files));
        Map<String, Long> sortedTotal = ParseUtils.getAggregatedOccurencesForAlbum(album);

        System.out.println(album.getName() + "\n" +
                sortedTotal.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue() + "\n")
                .limit(10)
                .collect(Collectors.toList()));
    }
}