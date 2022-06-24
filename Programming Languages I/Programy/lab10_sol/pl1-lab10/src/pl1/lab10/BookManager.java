package pl1.lab10;

import java.util.*;
import java.util.stream.Collectors;

public class BookManager {

    private final List<String> words;

    // konstruktor
    public BookManager(){
        words = new LinkedList<>(new Book().getWords());
    }

    // 2 vytlaci slova > minLength
    public void printWordsByLength(int minLength){

        //List<String> retlist =
        words.stream()
                .filter(word -> word.length() > minLength)
                .forEach(word -> System.out.println(word));
                //.collect(Collectors.toList());
    }


    // 3 vytlaci slova prevedene na male pismena abecedy a tiez odstrani duplicitne slova
    public void printLcUniqueOnly(){

        //List<String> retlist =
        words.stream()
                .map(String::toLowerCase)
                .distinct()
                .forEach(word -> System.out.println(word));
                //.collect(Collectors.toList());
    }

    // 4
    public void printAverageWordsLength(){

        double avgWs = words.stream()
                .map(String::toLowerCase)
                .distinct()
                .mapToInt(p -> p.length())
                .average()
                .getAsDouble();

        System.out.println("Average Word Length is: " + avgWs);
    }

    // 5
    public void printWithDuplicateCount(){

        Set<String> uniqueSet = new HashSet<String>(words);
        for (String s : uniqueSet) {
            System.out.println(s + ": " + Collections.frequency(words, s));
        }

        // with Map
        //Map<String, Integer> map= new HashMap<String, Integer>();
        //for(String s: words){
        //   map.put(s,Collections.frequency(words,s));
        //}

    }

    // 6
    public void printSortedWithDuplicateCount(){

        var csWords = words.stream().map(String::toLowerCase).collect(Collectors.toList());
        Map<String, Integer> map= new HashMap<String, Integer>();

        for(String s: csWords){
            map.put(s,Collections.frequency(csWords,s));
        }

        map.entrySet()
                .stream()
                //.sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .sorted((Map.Entry.<String, Integer>comparingByValue()))
                .forEach(m -> System.out.println("word: " + m.getKey() + "  (" + m.getValue() + ")" ));
    }

    // 7
    // pouzi terminal operaciu collect(Collectors.toList()) namiesto forEach

    // 8
    public void printAverageWordsLengthWithLetterA(){

        double avgWs = words.stream()
                .map(String::toLowerCase)
                .filter(w -> w.contains("a"))
                .mapToInt(p -> p.length())
                .average()
                .getAsDouble();

        System.out.println("Average Word length with A is: " + avgWs);
    }





}
