package pl1.lab11;

public class Run {

    public static void main(String[] args) {

        BookManager bkm = new BookManager();

        // 1
        bkm.printVowelCount();

        bkm.printVowelCountByMap();

        // 2
        bkm.printIndexOfCount();

        // 3
        bkm.printWordsByPattern();

        //4
        bkm.printWordsBySplit();

        HtmlManager hm = new HtmlManager();

        // 5
        //hm.printHtmlALLTags();

        //6
        //hm.printHtmlTagsFromWeb();

    }

}
