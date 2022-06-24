package pl1.lab09;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import  org.javatuples.*;

public class StreamManager {

    static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
    static final int[] units = { 12, 8, 13, 29, 50 };
    static final String[] descs = {
            "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain"
    };

    private static Collection<Triplet<Double,Integer,String>> getTripleLines(){

        // Triplet(Tuple) je genericka trieda definovana v kniznici, ktoru najdete na org.javatuples
        // ako by mohla vyzerat implementacia je mozne vidiet v definicii generickej triedy Tuple
        ArrayList<Triplet<Double,Integer,String>> trps = new ArrayList<>();
        trps.add(new Triplet<>(prices[0], units[0], descs[0]));
        trps.add(new Triplet<>(prices[1], units[1], descs[1]));
        trps.add(new Triplet<>(prices[2], units[2], descs[2]));
        trps.add(new Triplet<>(prices[3], units[3], descs[3]));
        trps.add(new Triplet<>(prices[4], units[4], descs[4]));

        return trps;
    }

    public static void copyBytes() throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        //File file = new File("Faust-Goethe.txt"); //here you make a filehandler - not a filesystem file.

        //if(!file.exists()) {
        //  file.createNewFile(); // create your file on the file system
        //}

        try {
            in = new FileInputStream("Faust-Goethe.txt");
            out = new FileOutputStream("D:/Temp/copyBytes.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }

    public static void copyBytesWithBuffer() throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        byte[] buffer = new byte[1024 * 1024];
        try {
            in = new FileInputStream("Faust-Goethe.txt");
            out = new FileOutputStream("D:/Temp/copyBytes.txt");

            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("done...");
    }

    public static void copyBytesWithBufferStream() throws IOException {
        BufferedInputStream in = null;
        BufferedOutputStream output = null;

        int bufferSize = 1024  * 1024;

        try {
            in = new BufferedInputStream(new FileInputStream("Faust-Goethe.txt"),
                    bufferSize);
            output = new BufferedOutputStream(
                    new FileOutputStream("D:/Temp/copyBytesWithBuffStream.txt"),
                    bufferSize
            );

            int len;
            while ((len = in.read()) != -1) {
                output.write(len);
            }
            output.flush();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (output != null) {
                output.close();
            }
        }
        System.out.println("done...");
    }

    public static void copyCharacters() throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("D:/Temp/copyCharacters.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        }  catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void copyWriterReader() throws IOException {

        BufferedReader readFileInput = null;
        BufferedWriter writeFileOutput = null;

        // read the content from file
        try {
            FileReader fReader = new FileReader("Faust-Goethe.txt");
            readFileInput = new BufferedReader(fReader);

            FileWriter fWriter = new FileWriter("D:/Temp/toUpperCaseTest.txt");
            writeFileOutput = new BufferedWriter(fWriter);

            String textLine  = readFileInput.readLine();
            while(textLine != null)
            {
                // Convert each line to all uppercase, write to file then
                // also write a new line.
                textLine = textLine.toUpperCase();
                writeFileOutput.write(textLine);
                writeFileOutput.newLine();
                textLine = readFileInput.readLine();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(readFileInput != null)
            {
                readFileInput.close();
            }
            if(writeFileOutput != null)
            {
                writeFileOutput.close();
            }
        }
    }

    public static void useDeflate() throws IOException {

        FileInputStream  readFileInput = null;
        DeflaterOutputStream writeFileOutput = null;

        //Deflater d = new Deflater();

        try {
            FileInputStream  fReader = new FileInputStream ("Faust-Goethe.txt");

            FileOutputStream fWriter = new FileOutputStream("D:/Temp/deflatedText.txt.gz");
            writeFileOutput = new DeflaterOutputStream(fWriter);

            int data;
            while ((data=fReader.read())!=-1)
            {
                writeFileOutput.write(data);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(readFileInput != null)
            {
                readFileInput.close();
            }
            if(writeFileOutput != null)
            {
                writeFileOutput.close();
            }
            //d.end();
        }
    }

    // zapisanie dat z kolekcie objektov Triplet priamo do suboru ako string
    public static void writeWithDataOutput() throws IOException {

        DataOutputStream data = null;

        try {
            FileOutputStream file = new FileOutputStream("D:/Temp/dataOutputStreamTest.txt");
            data = new DataOutputStream(file);

            // Triplet(Tuple) je genericka trieda definovana v kniznici, ktoru najdete na org.javatuples
            // ako by mohla vyzerat implementacia je mozne vidiet v definicii generickej triedy Tuple
            Collection<Triplet<Double, Integer, String>> tripleLines = getTripleLines();
            data.writeBytes(tripleLines.toString());

            data.flush();
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(data != null)
            {
                data.close();
            }
        }
    }

    // zapisanie dat z kolekcie objektov Triplet priamo do suboru pomocou metod dataoutputstream
    public static void writeWithDataOutputUseTypes() throws IOException {

        DataOutputStream data = null;

        try {
            FileOutputStream file = new FileOutputStream("D:/Temp/dataOutputStreamByType.txt");
            data = new DataOutputStream(file);

            // Triplet(Tuple) je genericka trieda definovana v kniznici, ktoru najdete na org.javatuples
            // ako by mohla vyzerat implementacia je mozne vidiet v definicii generickej triedy Tuple
            Collection<Triplet<Double, Integer, String>> tripleLines = getTripleLines();
            for(Triplet<Double, Integer, String> tline : tripleLines)
            {
                data.writeDouble(tline.getValue0());
                data.writeInt(tline.getValue1());
                data.writeUTF(tline.getValue2());
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(data != null)
            {
                data.close();
            }
        }
    }

    // Nacitanie dat so suboru pomocou DataInputStream s konverziou na Collection<Triplet<Double, Integer, String>>
    public static void readWithDataInput() throws IOException {

        try ( DataInputStream dis =
                      new DataInputStream(new FileInputStream("D:/Temp/dataOutputStreamByType.txt")) )
        {
            ArrayList<Triplet<Double, Integer, String>> recoveredCollection = new ArrayList<>();
            while(dis.available() > 0) {

                double price = dis.readDouble();
                int unit = dis.readInt();
                String desc = dis.readUTF();

                recoveredCollection.add(new Triplet<>(price, unit, desc));
            }

            recoveredCollection.forEach(System.out::println);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
