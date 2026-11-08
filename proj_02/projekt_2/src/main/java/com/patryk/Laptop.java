package com.patryk;

public class Laptop implements Comparable<Laptop> {
    private String model;
    private String brand;
    private LaptopCondition condition;
    private int year;
    private double cena;

    public Laptop(String model, String brand, LaptopCondition condition, int year, double cena) {
        this.model = model;
        this.brand = brand;
        this.condition = condition;
        this.year = year;
        this.cena = cena;
    }

    void print() {
        System.out.println("model: " + this.model);
        System.out.println("brand: " + this.brand);
        System.out.println("condition: " + this.condition);
        System.out.println("year: " + this.year);
        System.out.println("cena: " + this.cena);

    }

    @Override
    public int compareTo(Laptop other, String criteria) {
        if (criteria == "model")
            return String.compare(this.model, other.model);// ascending order by marks
        else if (criteria == "brand")
            return String.compare(this.brand, other.brand);
        else
            return Integer.compare(this.year, other.year);
    }
}
