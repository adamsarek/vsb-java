package pl1.lab09;

import java.io.*;
import java.util.*;

import java.util.zip.InflaterInputStream;

public class ScannerMannager {

    private Collection<String> getWords() {
        String[] tokens = getText().split("[\\- \t\n.,;:()\\[\\]{}\"]+");
        return new ArrayList<>(Arrays.asList(tokens));
    }

    private String getText() {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(ScannerMannager.class.getResourceAsStream("/pl1.lab09/Faust-Goethe.txt")))) {
            String line;
            int i = 1;
            while((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }

    // Vypise do konzoly riadky a ocisluje ich
    public void printNumLines() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(ScannerMannager.class.getResourceAsStream("/pl1.lab09/Faust-Goethe.txt")))) {
            String line;
            int i = 1;
            while((line = br.readLine()) != null) {
                System.out.println(i++ + ". " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // iba ukazka eliminacie prazdnych riadkov
    public void printNumLinesWithoutEmpty() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(ScannerMannager.class.getResourceAsStream("/pl1.lab09/Faust-Goethe.txt")))) {
            String line;
            int i = 1;
            while((line = br.readLine()) != null) {

                if(!line.isBlank()) {
                    System.out.println(i++ + ". " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printWords() {
        var bkCollection = getWords();

        bkCollection.forEach(System.out :: println);
    }


    // Motoda vypise do konzoly slovo na riadok.
    // O rozdelenie viet na slova sa postara regular expression .useDelimiter("[\\- \t\n.,;:()\\[\\]{}\"]+"); .
    public void printWordsWithScanner() {
        Scanner scannerData  = null;

        try {
            FileInputStream inputStream = new FileInputStream("Faust-Goethe.txt");

            scannerData  = new Scanner(inputStream).useDelimiter("[\\- \t\n.,;:()\\[\\]{}\"]+");

            while(scannerData.hasNext()){
                String line = scannerData.next();
                System.out.println(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (scannerData != null) {
                scannerData.close();
            }
        }
    }

    // Priklad vypoctu priemeru hodnot nacitanych zo suboru bez triedy Scanner.
    // Trieda Reader pracuje so Stringom a preto je nutne pretypovat na double.
    // Problemom pretypovania byva, ze hodnota v stringu moze byt s . alebo ,
    // a metoda parseDouble konvertuje na zaklade systemovych nastaveni.
    // Neporadi si automaticky s inym typom, musel by sme mu povedat aky format pouzivame. // NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
    public void calculateAverageFromFile() {
        double average = 0;
        //NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(ScannerMannager.class.getResourceAsStream("/pl1.lab09/doubles.txt")))) {
            String line;
            int i = 0;
            double total = 0;
            while((line = br.readLine()) != null) {
                if(!line.isBlank()) {
                    i++;
                    total += Double.parseDouble(line.replace(',', '.'));
                }
            }
            average = total/i;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Average is: " + average);
    }

    // Priklad pouzitia triedy Scanner.
    // Rozdiel oproti predoslej metody je v tom, ze util Scanner dokaze priamo konvertovat hodnotu nacitanu zo suboru na cielovy datovy typ.
    // V nasom pripade inputStream.nextDouble() vrati priamo hodnotu double.
    public void calculateAverageFromFileWithScanner() {

        Scanner inputStream  = null;
        double average = 0;

        try {
            FileInputStream in = new FileInputStream("doubles.txt");

            inputStream  = new Scanner(in);

            int i = 0;
            double total = 0;
            while(inputStream.hasNext()){
                i++;
                total += inputStream.nextDouble();
            }
            average = total/i;
        } catch(IOException e) {
            e.printStackTrace();
        } finally {

            if (inputStream != null) {
                inputStream.close();
            }
        }
        System.out.println("Average is: " + average);
    }
}
