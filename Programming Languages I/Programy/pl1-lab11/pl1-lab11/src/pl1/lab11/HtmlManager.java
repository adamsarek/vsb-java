package pl1.lab11;

import cz.kozusznik.pl1.utils.IORoutines;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlManager {

    private String htmlAsString;
    private static final Pattern TAG_REGEX = Pattern.compile("(</?[a-z]*>)", Pattern.MULTILINE);

    public HtmlManager(){
        Html ht = new Html();
        htmlAsString = ht.toString();
    }

    // 5
    public void printHtmlALLTags(){
        //Pattern p1 = Pattern.compile("(</?[a-z]*>)", Pattern.MULTILINE);
        Matcher m = TAG_REGEX.matcher(htmlAsString);

        while(m.find())
            System.out.println(m.group(1));
    }

    // 6
    public void printHtmlTagsFromWeb() {

        String stringFromWeb = IORoutines.readHtmlFromURL("https://katedrainformatiky.cz/");

        Matcher m = TAG_REGEX.matcher(stringFromWeb);

        while(m.find())
            System.out.println(m.group(1));
    }
}
