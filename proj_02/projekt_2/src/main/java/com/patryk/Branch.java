package com.patryk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Branch {
    private String branchName;
    private List<Laptop> laptopList;
    private int maxCapacity;

    public Branch(String bname, int cap) {
        this.branchName = bname;
        this.maxCapacity = cap;
        this.laptopList = new ArrayList<>();
    }

    public String getBranchName() {
        return branchName;
    }

    public List<Laptop> getLaptopList() {
        return laptopList;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    void addLaptop(Laptop laptop) {
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList.get(i).compareTo(laptop) == 0) {
                System.out.println("taki lapek juz jest: " + laptop.getModel());
                return;
            }
        }

        if (this.maxCapacity > this.laptopList.size())
            laptopList.add(laptop);
        else
            System.err.println("nie ma miejsca w oddziale " + branchName);
    }

    void removeLaptop(Laptop laptop) {
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList.get(i).equals(laptop)) {
                laptopList.remove(i);
                return;
            }
        }
        System.out.println("nie ma takiego lapka");
    }

    void sellLaptop(Laptop laptop) {
        removeLaptop(laptop);
    }

    void changeCondition(Laptop laptop, LaptopCondition condition) {
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList.get(i).equals(laptop)) {
                laptopList.get(i).setCondition(condition);
                return;
            }
        }
        System.out.println("nie ma takiego lapka");
    }

    void changeYear(Laptop laptop, int year) {
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList.get(i).equals(laptop)) {
                laptopList.get(i).setYear(year);
                return;
            }
        }
        System.out.println("nie ma takiego lapka");
    }

    int countByCondition(LaptopCondition condition) {
        int counter = 0;
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList.get(i).getCondition() == condition) {
                counter++;
            }
        }

        return counter;
    }

    List<Laptop> sortByName() {
        Comparator<Laptop> compByName = (l1, l2) -> {
            return l1.getModel().compareTo(l2.getModel());
        };

        List<Laptop> temp = new ArrayList<>(laptopList);
        temp.sort(compByName);
        return temp;
    }

    List<Laptop> sortByPrice() {
        Comparator<Laptop> compByPrice = (l1, l2) -> {
            return Double.compare(l1.getPrice(), l2.getPrice());
        };

        List<Laptop> temp = new ArrayList<>(laptopList);
        temp.sort(compByPrice);
        return temp;
    }

    Laptop search(String model) {
        Comparator<Laptop> searchComp = (l1, l2) -> {
            return l1.getModel().compareTo(l2.getModel());
        };

        Laptop szukany = new Laptop(model, "", null, 0, 0);

        for (Laptop lap : laptopList) {
            if (searchComp.compare(lap, szukany) == 0) {
                return lap;
            }
        }

        System.out.println("nie ma");
        return null;
    }

    List<Laptop> searchPartial(String fragment) {
        List<Laptop> temp = new ArrayList<>();

        for (Laptop lap : laptopList)
        {
            if (lap.getModel().contains(fragment) || lap.getBrand().contains(fragment)) {
                temp.add(lap);
            }
        }

        return temp;
    }

    double totalValue() {
        double suma = 0;
        for (Laptop l : laptopList) {
            suma += l.getPrice();
        }
        return suma;
    }

    void summary() {
        System.out.println("oddzial: " + branchName + " (" + laptopList.size() + "/" + maxCapacity + ")");
        for (Laptop l : laptopList) {
            System.out.println();
            l.print();
        }
        System.out.println();
    }

    Laptop max() {
        Laptop mostExpensive = Collections.max(laptopList, Comparator.comparingDouble(Laptop::getPrice));

        return mostExpensive;
    }

}
