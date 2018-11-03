package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class ParseUtils {

    public static List<String> getLyricsAsWords(List<String> alleLinjer) {
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

    public static Map<String, Long> groupPerWordAndSort(List<String> allWords) {
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

    public static Map<String, Long> getLyricsAsSortedMap(File file) {
        //List<String> ordSomSkalIgnoreres = new ArrayList<>(asList("som", "ska", "och"));

        List<String> allWords = null;
        try {
            allWords = ParseUtils.getLyricsAsWords(FileUtils.retrieveLinesFromFile(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ParseUtils.groupPerWordAndSort(allWords);


    }
}
