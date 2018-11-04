package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class FileUtils {

    public static List<SongFile> getSongFiles(String pathname) {
        return getFilesAsSongfile(getFilesInDirectory(pathname));
    }

    private static List<SongFile> getFilesAsSongfile(List<File> files) {
        return files.stream()
                .map(file -> new SongFile(file.getName(), retrieveLinesFromFile(file)))
                .collect(Collectors.toList());
    }

    private static List<File> getFilesInDirectory(String pathname) {
        List<File> files = FileUtils.listFilesForFolder(new File(pathname));
        if (files.isEmpty()) {
            throw new IllegalArgumentException(String.format("Could not find any files for path %s", pathname));
        }
        return files;
    }

    private static List<File> listFilesForFolder(File folder) {
        if (folder == null || folder.listFiles() == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .filter(File::isFile)
                .collect(Collectors.toList());
    }

    private static List<String> retrieveLinesFromFile(File file) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return bufferedReader != null
                ? bufferedReader.lines().collect(Collectors.toList())
                : new ArrayList<>();
    }
}