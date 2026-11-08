package com.patryk;

public class Laptop implements Comparable<Laptop> {
    private String model;
    private String brand;
    private LaptopCondition condition;
    private int year;
    private double cena;
    private int ramGb;
    private int batteryHealth;

    public Laptop(String model, String brand, LaptopCondition condition, int year, double cena) {
        this.model = model;
        this.brand = brand;
        this.condition = condition;
        this.year = year;
        this.cena = cena;
        this.ramGb = 8;
        this.batteryHealth = 100;
    }

    public Laptop(String model, String brand, LaptopCondition condition, int year, double cena, int ramGb, int batteryHealth) {
        this(model, brand, condition, year, cena);
        this.ramGb = ramGb;
        this.batteryHealth = batteryHealth;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public LaptopCondition getCondition() {
        return condition;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return cena;
    }

    public void setCondition(LaptopCondition condition) {
        this.condition = condition;
    }

    public void setYear(int year) {
        this.year = year;
    }

    void print() {
        System.out.println("model: " + this.model);
        System.out.println("brand: " + this.brand);
        System.out.println("condition: " + this.condition);
        System.out.println("year: " + this.year);
        System.out.println("cena: " + this.cena + " zl");
        System.out.println("ram: " + this.ramGb + " GB");
        System.out.println("bateria: " + this.batteryHealth + "%");
    }

    @Override
    public int compareTo(Laptop other) {
        int wynik = this.model.compareTo(other.model);
        if (wynik != 0) {
            return wynik;
        }

        wynik = this.brand.compareTo(other.brand);
        if (wynik != 0) {
            return wynik;
        }

        return Integer.compare(this.year, other.year);
    }

}
