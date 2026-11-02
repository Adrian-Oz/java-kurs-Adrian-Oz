package oop;

public class Person implements Identifiable<String>{
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    @Override
    public String getId() {
        return getName();
    }
}
