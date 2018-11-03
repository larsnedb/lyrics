package Utils;

import Domain.Album;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class ParseUtils {

    private static List<String> getLyricsAsWords(List<String> alleLinjer) {
        List<String> linesWithContents = removeEmptyLines(alleLinjer);
        return transformToLowerCase(linesWithContents);
    }

    private static List<String> removeEmptyLines(List<String> allLines) {
        return allLines.stream()
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    private static List<String> transformToLowerCase(List<String> linjer) {
        return linjer.stream()
                .map(linje -> linje.split(" "))
                .flatMap(Arrays::stream)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    private static Map<String, Long> groupPerWordAndSort(List<String> allWords) {
        Map<String, Long> wordsGrouped = allWords.stream()
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()));
        return sortByValue(wordsGrouped);
    }

    private static Map<String, Long> sortByValue(Map<String, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    static Map<String, Long> getLyricsAsSortedMap(File file) {
        List<String> allWords = null;
        try {
            allWords = ParseUtils.getLyricsAsWords(FileUtils.retrieveLinesFromFile(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ParseUtils.groupPerWordAndSort(allWords);
    }

    public static Map<String, Long> getAggregatedOccurencesForAlbum(Album album) {
        HashMap<String, Long> total = new HashMap<>();
        album.getSongs().forEach(song -> {
            Map<String, Long> occurrencesPerWord = song.getOccurrencesPerWord();
            occurrencesPerWord.forEach((k, v) -> total.merge(k, v, (aLong, aLong2) -> aLong + aLong2));
        });
        return sortByValue(total);
    }
}
