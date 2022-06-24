package pl1.lab09;

import java.io.IOException;

public class Run {

    public static void main(String[] args) throws IOException {

        // 1
        //StreamManager.copyBytes();

        // 2
        //StreamManager.copyWriterReader();

        // 3
        //StreamManager.copyBytesWithBuffer();
        //StreamManager.copyBytesWithBufferStream();

        // 4
        //StreamManager.useDeflate();
        //StreamManager.useDeflatedecompress();

        ScannerMannager scnr = new ScannerMannager();

        // 5
        //scnr.printNumLines();
        //scnr.printNumLinesWithoutEmpty();

        // 6 -> ukazka ako vypisat jednotlive slova knihy do samostatnych riadkov s util Scanner a bez.
        //scnr.printWords();
        //scnr.printWordsWithScanner();

        // 7
        //scnr.calculateAverageFromFile();
        //scnr.calculateAverageFromFileWithScanner();

        // 8
        // Metoda pouziva DataInputStream na ulozenie
        //StreamManager.writeWithDataOutput();
        //StreamManager.writeWithDataOutputUseTypes();
        //StreamManager.readWithDataInput();
    }
}
