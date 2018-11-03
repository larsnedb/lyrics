package Domain;

import java.util.Map;

public class Song {

    private final String name;
    private final Map<String, Long> occurrencesPerWord;

    public Song(String name, Map<String, Long> occurrencesPerWord) {
        this.name = name;
        this.occurrencesPerWord = occurrencesPerWord;
    }

    public String getName() {
        return name;
    }

    public Map<String, Long> getOccurrencesPerWord() {
        return occurrencesPerWord;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                '}';
    }
}
