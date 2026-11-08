package com.patryk;

import java.util.List;

public class Branch {
    private String branchName;
    private List<Laptop> laptopList;
    private int maxCapacity;

    void addLaptop(Laptop laptop) {
        if (this.maxCapacity > this.laptopList.size)
            laptopList.add(laptop);
        else
            System.out.println("nie ma miejscu");
    }

    void removeLaptop(Laptop laptop) {
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList[i].equals(laptop)) {
                laptopList.remove(i);
                break;
            }
        }
        System.out.println("nie ma takiego lapka");
    }

    void sellLaptop(Laptop laptop) {
        removeLaptop(laptop);
    }

    void ChangeCondition(Laptop laptop, LaptopCondition condition) {
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList[i].equals(laptop)) {
                Laptop temp = laptopList[i];
                temp.condition = condition;
                laptopList[i] = temp;
                break;
            }
        }
    }

    void ChangeYear(Laptop laptop, int year) {
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList[i].equals(laptop)) {
                Laptop temp = laptopList[i];
                temp.year = year;
                laptopList[i] = temp;
                break;
            }
        }
    }

    void countByCondition(LaptopCondition condition) {
        int counter = 0;
        for (int i = 0; i < this.laptopList.size(); i++) {
            if (laptopList[i].condition = condition) {
                counter++;
            }
        }

        System.out.println(condition + ": " + counter);
    }

    List<Laptop> sortByName() {

    }

}
