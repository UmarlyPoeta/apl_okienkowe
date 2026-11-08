package com.patryk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageManager {
    Map<String, Branch> oddzialy = new HashMap<>();

    void addBranch(String name, int capacity) {
        Branch temp = new Branch(name, capacity);

        oddzialy.put(name, temp);
    }

    Branch getBranch(String name) {
        return oddzialy.get(name);
    }

    void removeBranch(String name) {
        oddzialy.remove(name);
    }

    List<Branch> findEmpty() {
        List<Branch> temp = new ArrayList<>();

        for (String key : oddzialy.keySet()) {
            if (oddzialy.get(key).getLaptopList().isEmpty()) {
                temp.add(oddzialy.get(key));
            }
        }

        return temp;
    }

    void transferLaptop(Laptop laptop, String fromBranch, String toBranch) {
        Branch from = oddzialy.get(fromBranch);
        Branch to = oddzialy.get(toBranch);

        if (from == null || to == null) {
            System.err.println("nie ma takiego oddzialu");
            return;
        }

        if (!from.getLaptopList().contains(laptop)) {
            System.out.println("nie ma takiego lapka w oddziale " + fromBranch);
            return;
        }

        if (to.getLaptopList().size() >= to.getMaxCapacity()) {
            System.err.println("nie ma miejsca w oddziale " + toBranch);
            return;
        }

        from.sellLaptop(laptop);
        to.addLaptop(laptop);
    }

    void summary() {
        for (Branch b : oddzialy.values()) {
            double wyp = b.getLaptopList().size() * 100.0 / b.getMaxCapacity();
            System.out.println("nazwa oddzialu: " + b.getBranchName() + ", wypelnienie: " + wyp + "%");
        }
    }
}
