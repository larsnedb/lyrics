package Utils;

import Domain.Song;

import java.util.*;
import java.util.stream.Collectors;

public class ParseUtils {

    public static List<Song> mapFilesToSongs(List<SongFile> songFiles) {
        return songFiles.stream()
                .map(songFile -> new Song(songFile.getName(), getSongLyrics(songFile.getWords())))
                .collect(Collectors.toList());
    }

    private static Map<String, Long> getSongLyrics(List<String> lines) {
        return groupPerWordAndSort(transformLinesToWords(lines));
    }
    
    private static List<String> transformLinesToWords(List<String> lines) {
        return lines.stream()
                .filter(line -> !line.isEmpty())
                .map(line -> line.split(" "))
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

    public static Map<String, Long> getAggregatedOccurencesForAlbum(List<Song> songs) {
        HashMap<String, Long> total = new HashMap<>();
        songs.forEach(song -> {
            Map<String, Long> occurrencesPerWord = song.getOccurrencesPerWord();
            occurrencesPerWord.forEach((k, v) -> total.merge(k, v, (first, second) -> first + second));
        });
        return sortByValue(total);
    }
}