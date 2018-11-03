import Domain.Song;
import Utils.ParseUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/Users/lars.nedberg/Documents/kode/lyrics/vatten/03_elegi.txt");

        Song first = new Song("Elegi", ParseUtils.getLyricsAsSortedMap(file));
        Map<String, Long> occurrencesPerWord = first.getOccurrencesPerWord();
    }
}