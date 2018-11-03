package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
                .sorted(Map.Entry.comparingByValue((o1, o2) -> o2.compareTo(o1)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public static Map<String, Long> getLyricsAsSortedMap(File file) throws FileNotFoundException {
        //List<String> ordSomSkalIgnoreres = new ArrayList<>(asList("som", "ska", "och"));
        List<String> allWords = ParseUtils.getLyricsAsWords(FileUtils.retrieveLinesFromFile(file));
        return ParseUtils.groupPerWordAndSort(allWords);
    }
}
