package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class FileUtils {

    public static List<String> retrieveLinesFromFile(File file) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        return bufferedReader.lines().collect(Collectors.toList());
    }

    public static List<File> listFilesForFolder(File folder) {
        if (folder == null || folder.listFiles() == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(folder.listFiles())
                .filter(File::isFile)
                .collect(Collectors.toList());
    }

}
