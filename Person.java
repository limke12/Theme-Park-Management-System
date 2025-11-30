import java.util.Date;

// Part 1: Class & Inheritance + Part 2: Abstract Class
abstract class Person {
    // Instance variables
    private String name;
    private int age;
    private String id;

    // Default constructor
    public Person() {}

    // Parameterized constructor
    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getter/Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    // Abstract method (demonstrates abstract class feature)
    public abstract String getPersonType();

    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, id='%s', type='%s'}",
                name, age, id, getPersonType());
    }
}