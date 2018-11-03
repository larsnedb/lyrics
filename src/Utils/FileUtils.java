package Utils;

import Domain.Song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class FileUtils {

    static List<String> retrieveLinesFromFile(File file) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        return bufferedReader.lines().collect(Collectors.toList());
    }

    private static List<File> listFilesForFolder(File folder) {
        if (folder == null || folder.listFiles() == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(folder.listFiles())
                .filter(File::isFile)
                .collect(Collectors.toList());
    }

    public static List<Song> getSongsFromDirectory(List<File> files) {
        return files.stream()
                .map(file -> new Song(file.getName(), ParseUtils.getLyricsAsSortedMap(file)))
                .collect(Collectors.toList());
    }

    public static List<File> getFilesInDirectory(String pathname) {
        List<File> files = FileUtils.listFilesForFolder(new File(pathname));
        if (files.isEmpty()) {
            throw new IllegalArgumentException(String.format("Could not find any files for path %s", pathname));
        }
        return files;
    }
}
