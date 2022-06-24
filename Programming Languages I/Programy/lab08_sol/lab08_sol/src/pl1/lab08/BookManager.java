package pl1.lab08;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookManager {

    private Book bk;
    private Collection<String> bkCollection;

    public BookManager(){
        bk = new Book();

        bkCollection = bk.getWords();
    }

    // 1
    public void printAllWordsFromBook(){

        //System.out.println(bkCollection);

        //for(String str : bkCollection)
        //{
        //    System.out.println(str);
        //}

        //Book<String> str = s -> { System.out::println };
        //bkCollection.forEach(str -> { System.out.println(str) };);

        // Lambda Expression
        //bkCollection.forEach(s -> System.out.println(s));

        // Method Reference
        bkCollection.forEach(System.out :: println);

    }

    // 2
    public void printWithoutTHE() {
        //var bkCollection = bk.getWords();

        // iterator ?
        Iterator<String> i = bkCollection.iterator();
        while (i.hasNext()) {
            // musi sa zavolat pred i.remove()
            String s = i.next();
            // odstran objekt z kolekcie
            if(s.equalsIgnoreCase("the"))
                i.remove();
        }


        bkCollection.forEach(System.out::println);

        // predikat
        //Predicate<String> isThe = str -> str.equalsIgnoreCase("the");
        //bkCollection.removeIf(isThe);
    }

    // 3
    public void calculateAverageAndPrint() {
        int collectionSize = bkCollection.size();
        int averageSize, totalSize = 0;

        for (String str : bkCollection) {
            totalSize += str.length();
        }

        averageSize = totalSize / collectionSize;

        // dalsie riesenie
        double avgWs = bkCollection.stream()
                .mapToInt(p -> p.length())
                .average()
                .getAsDouble();


        System.out.println("Average Word Length is: " + averageSize + " alebo: " + avgWs);


        //List<String> result =
        bkCollection.stream()
                .filter(word -> word.length() > averageSize)
                .forEach(word -> System.out.println(word));
                //.collect(Collectors.toList());
    }

    // 4
    public void sortByWordLengthAndPrint(){

        List list = new ArrayList(bkCollection);
        Collections.sort(list, Comparator.comparing(String::length));

        list.forEach(System.out::println);
    }

    // 5
    public void printOrderedWordsWithoutDuplicates(){

        Set<String> treeSetCaseIgnored = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        treeSetCaseIgnored.addAll(bkCollection);

        treeSetCaseIgnored.forEach(System.out::println);
    }

    public void printWordsWithoutDuplicates(){

        //var hashSet = new HashSet<>(bkCollection);
        Set<String> setCaseIgnored = new HashSet<String>(
                bkCollection.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList()));

        setCaseIgnored.forEach(System.out::println);
    }

    // 6
    public void printOrderedUniqueWords(){

        var hashSet = new LinkedHashSet<>(bkCollection.stream().map(String::toLowerCase).collect(Collectors.toList()));
        var listWithoutDuplicates = new ArrayList<>(hashSet);

        listWithoutDuplicates.forEach(System.out::println);
    }

    private List<String> getOrderedUniqueWords(){

        var hashSet = new LinkedHashSet<>(bkCollection.stream().map(String::toLowerCase).collect(Collectors.toList()));
        return new ArrayList<>(hashSet);
    }


    // 7
    public void printMapWithDuplicateCount(){

        var csWords = bkCollection.stream().map(String::toLowerCase).collect(Collectors.toList());
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
}
