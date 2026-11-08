package com.patryk;

import java.util.List;

public class App {

    private static void header(String title) {
        System.out.println();
        System.out.println("========== " + title + " ==========");
    }

    public static void main(String[] args) {

        header("Laptop.print()");
        Laptop t480 = new Laptop("ThinkPad T480", "Lenovo", LaptopCondition.WORKING, 2018, 1199.0, 16, 87);
        Laptop lat7490 = new Laptop("Latitude 7490", "Dell", LaptopCondition.IN_TESTING, 2018, 999.0);
        Laptop mbp = new Laptop("MacBook Pro 13", "Apple", LaptopCondition.WORKING, 2020, 3499.0, 16, 92);
        Laptop elite = new Laptop("EliteBook 840 G5", "HP", LaptopCondition.DAMAGED, 2019, 649.0);
        Laptop x1 = new Laptop("ThinkPad X1 Carbon", "Lenovo", LaptopCondition.IN_REPAIR, 2017, 1499.0);
        t480.print();

        header("Laptop.compareTo() (model, marka, wiek)");
        Laptop t480Copy = new Laptop("ThinkPad T480", "Lenovo", LaptopCondition.DAMAGED, 2018, 500.0);
        System.out.println("T480 vs Latitude 7490: " + t480.compareTo(lat7490));
        System.out.println("Latitude 7490 vs T480: " + lat7490.compareTo(t480));
        System.out.println("T480 vs kopia T480:    " + t480.compareTo(t480Copy));

        header("Branch.addLaptop()");
        Branch krakow = new Branch("Krakow", 5);
        krakow.addLaptop(t480);
        krakow.addLaptop(lat7490);
        krakow.addLaptop(mbp);
        krakow.addLaptop(elite);
        krakow.addLaptop(x1);
        System.out.println("-> proba dodania duplikatu (ten sam model, marka, rok):");
        krakow.addLaptop(t480Copy);
        System.out.println("-> proba dodania ponad pojemnosc (System.err):");
        krakow.addLaptop(new Laptop("IdeaPad 5", "Lenovo", LaptopCondition.WORKING, 2021, 1799.0));

        header("Branch.summary()");
        krakow.summary();

        header("Branch.countByCondition()");
        for (LaptopCondition c : LaptopCondition.values()) {
            System.out.println(c + ": " + krakow.countByCondition(c));
        }

        header("Branch.changeCondition()");
        System.out.println("Latitude 7490 przed: ");
        lat7490.print();
        krakow.changeCondition(lat7490, LaptopCondition.WORKING);
        System.out.println("Latitude 7490 po testach: ");
        lat7490.print();

        header("Branch.changeYear()");
        krakow.changeYear(x1, 2019);
        x1.print();

        header("Branch.sortByName()");
        List<Laptop> byName = krakow.sortByName();
        for (Laptop l : byName) {
            l.print();
            System.out.println();
        }

        header("Branch.sortByPrice()");
        List<Laptop> byPrice = krakow.sortByPrice();
        for (Laptop l : byPrice) {
            l.print();
            System.out.println();
        }

        header("Branch.search()");
        Laptop found = krakow.search("MacBook Pro 13");
        if (found != null) {
            found.print();
        }
        System.out.println("-> szukanie nieistniejacego modelu:");
        Laptop notFound = krakow.search("Surface Laptop 3");
        System.out.println("wynik: " + notFound);

        header("Branch.searchPartial()");
        System.out.println("-> fragment \"ThinkPad\":");
        for (Laptop l : krakow.searchPartial("ThinkPad")) {
            l.print();
            System.out.println();
        }
        System.out.println("-> fragment \"Dell\" (marka):");
        for (Laptop l : krakow.searchPartial("Dell")) {
            l.print();
            System.out.println();
        }

        header("Branch.max()");
        krakow.max().print();

        header("Branch.totalValue()");
        System.out.println("wartosc laptopow w Krakowie: " + krakow.totalValue() + " zl");

        header("Branch.sellLaptop()");
        krakow.sellLaptop(mbp);
        krakow.summary();

        header("Branch.removeLaptop()");
        krakow.removeLaptop(elite);
        System.out.println("-> usuwanie laptopa, ktorego nie ma w oddziale:");
        krakow.removeLaptop(elite);
        krakow.summary();

        header("StorageManager.addBranch()");
        StorageManager manager = new StorageManager();
        manager.addBranch("Warszawa", 4);
        manager.addBranch("Gdansk", 2);
        manager.addBranch("Poznan", 10);
        manager.addBranch("Wroclaw", 3);

        Branch warszawa = manager.getBranch("Warszawa");
        Laptop t14 = new Laptop("ThinkPad T14", "Lenovo", LaptopCondition.WORKING, 2021, 2199.0);
        warszawa.addLaptop(t14);
        warszawa.addLaptop(new Laptop("XPS 13", "Dell", LaptopCondition.IN_TESTING, 2020, 2799.0));
        warszawa.addLaptop(new Laptop("ZenBook 14", "Asus", LaptopCondition.DAMAGED, 2019, 899.0));

        Branch gdansk = manager.getBranch("Gdansk");
        gdansk.addLaptop(new Laptop("Pavilion 15", "HP", LaptopCondition.WORKING, 2020, 1399.0));

        header("StorageManager.summary()");
        manager.summary();

        header("StorageManager.transferLaptop()");
        manager.transferLaptop(t14, "Warszawa", "Gdansk");
        warszawa.summary();
        gdansk.summary();
        System.out.println("-> transfer do pelnego oddzialu (System.err):");
        manager.transferLaptop(warszawa.search("XPS 13"), "Warszawa", "Gdansk");
        manager.summary();

        header("StorageManager.findEmpty()");
        for (Branch b : manager.findEmpty()) {
            b.summary();
        }
        System.out.println("Liczba pustych oddzialow: " + manager.findEmpty().size());

        header("StorageManager.removeBranch()");
        manager.removeBranch("Wroclaw");
        manager.summary();
        System.out.println("Liczba pustych oddzialow po usunieciu Wroclawia: " + manager.findEmpty().size());
    }
}
