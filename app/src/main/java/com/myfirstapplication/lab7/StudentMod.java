package com.myfirstapplication.lab7;

public class StudentMod {
    private int id;
    private String name;
    private int age;

    //constructor
    public StudentMod(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //toString()

    @Override
    public String toString() {
        return "StudentMod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}