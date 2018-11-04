package Utils;

import java.util.List;

public class SongFile {

    private final String name;
    private final List<String> words;

    SongFile(String name, List<String> words) {
        this.name = name;
        this.words = words;
    }

    String getName() {
        return name;
    }

    List<String> getWords() {
        return words;
    }
}
