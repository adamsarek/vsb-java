public class Person implements HavingLength {
    private final int age;
    private final String firstName;
    private final String lastName;

    public Person(int age, String firstName, String lastName){
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge(){
        return this.age;
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    public int length(){
        String concatenation = this.getName() + Integer.toString(this.getAge());
        return concatenation.length();
    }
}
