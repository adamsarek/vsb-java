import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        String[] firstNames = {
                "Liam",
                "Noah",
                "William",
                "James",
                "Oliver",
                "Benjamin",
                "Elijah",
                "Lucas",
                "Mason",
                "Logan"
        };
        String[] lastNames = {
                "Delafaille",
                "Harris",
                "Oberfield",
                "Mura",
                "Locatelli",
                "Bastard",
                "Lucev",
                "Cossignani",
                "Warnement",
                "Krikorian"
        };
        Random random = new Random();
        HavingLength[] havingLengths = new HavingLength[100];
        for(int i = 0; i < 100; i++) {
            String firstName = lastNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            int age = random.nextInt(111);
            havingLengths[i] = new Person(age, firstName, lastName);
        }

        LengthEvaluator e = new LengthEvaluator();
        e.findWithMaxLength(havingLengths);
    }
}
