package pl1.lab11;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookManager {

    private Book book = new Book();
    private String bookText;
    private String bookLCText;
    private static String VOWELS = "aeiouy";

    public BookManager(){

        bookText = book.toString().toLowerCase();
        bookLCText = bookText.toLowerCase();
    }

    public void printVowelCountByMap(){
        Map<Character, Integer> mp = new HashMap<>();
        mp.put('a', 0);
        mp.put('e', 0);
        mp.put('i', 0);
        mp.put('o', 0);
        mp.put('u', 0);
        mp.put('y', 0);

        for(int i=0; i<bookText.length(); i++){
            char c = Character.toLowerCase(bookText.charAt(i));
            if(mp.containsKey(c))
                mp.put(c, mp.get(c) + 1);
        }
        System.out.println(mp);

    }

    // 1
    public void printVowelCount(){

        int stringLength = bookText.length();
        int vowelCount = 0;
        for (int i = 0; i < stringLength; i++){
            if(isVowel(bookText.charAt(i)))
                vowelCount++;
        }
        System.out.println(vowelCount);
    }

    private static boolean isVowel(char c)
    {
        return VOWELS.indexOf(Character.toLowerCase(c)) >= 0;
    }

    // 2
    public void printIndexOfCount(){

        int start = 0;
        String theoldMan = "the old man";
        String thesea = "the sea";
        int theoldManCount = 0, theseaCount = 0;

        while (true) {
            int found = bookLCText.indexOf(theoldMan, start);
            if (found != -1)
                theoldManCount++;

            if (found == -1) break;
            start = found + theoldMan.length();  // move start up for next iteration
        }
        start = 0;
        while (true) {
            int found = bookLCText.indexOf(thesea, start);
            if (found != -1)
                theseaCount++;

            if (found == -1) break;
            start = found + thesea.length();  // move start up for next iteration
        }

        System.out.println("the old man: " + theoldManCount + " the sea: " + theseaCount);
    }

    // 3
    public void printWordsByPattern(){
        Pattern p1 = Pattern.compile("the old man", Pattern.CASE_INSENSITIVE);
        Matcher m = p1.matcher(bookText);

        int count1 = 0;
        while(m.find())
            count1++;

        Pattern p2 = Pattern.compile("the sea", Pattern.CASE_INSENSITIVE);
        m = p2.matcher(bookText);

        int count2 = 0;
        while(m.find())
            count2++;

        System.out.println("the old man: " + count1 + " the sea: " + count2);
    }

    // 4
    public void printWordsBySplit(){

        //String[] pole = bookText.split("[.,!?:;'\\\"-]|\\s+");
        //String[] pole = bookText.split("\\P{L}+"); // \\P{L} means is not a unicode code point that has the property "Letter" //[ !\"\\#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]+
        String[] pole = bookText.split("[\\p{Punct}\\s]+");

        for(String s : pole)
        {
            //System.out.println(s);
        }
        System.out.println("Words Count: " + pole.length);
    }
}
