package edu.hw3;

import java.util.Scanner;

public class t15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            String make = sc.next();
            String model = sc.next();
            int year = sc.nextInt();
            String color = sc.next();
            cars[i] = new Car(make, model, year, color);
        }
        String makeQuery = sc.next();
        String modelQuery = sc.next();
        int nYears = sc.nextInt();
        int currentYear = sc.nextInt();
        Car[] byMake = filterByMakeSortedByYear(cars, makeQuery);
        System.out.println("Cars of make \"" + makeQuery + "\" sorted by year:");
        if (byMake.length == 0) {
            System.out.println("No cars found");
        } else {
            for (Car c : byMake) {
                System.out.println(c);
            }
        }
        Car[] byModelOld = filterByModelOlderThan(cars, modelQuery, nYears, currentYear);
        System.out.println("Cars of model \"" + modelQuery + "\" older than " + nYears + " years:");
        if (byModelOld.length == 0) {
            System.out.println("No cars found");
        } else {
            for (Car c : byModelOld) {
                System.out.println(c);
            }
        }
        sc.close();
    }

    public static Car[] filterByMakeSortedByYear(Car[] arr, String make) {
        int count = 0;
        for (Car c : arr) {
            if (c.make.equals(make)) count++;
        }
        if (count == 0) return new Car[0];
        Car[] res = new Car[count];
        int idx = 0;
        for (Car c : arr) {
            if (c.make.equals(make)) {
                res[idx++] = c;
            }
        }
        java.util.Arrays.sort(res, new java.util.Comparator<Car>() {
            public int compare(Car a, Car b) {
                return Integer.compare(a.year, b.year);
            }
        });
        return res;
    }

    public static Car[] filterByModelOlderThan(Car[] arr, String model, int nYears, int currentYear) {
        int count = 0;
        for (Car c : arr) {
            if (c.model.equals(model) && (currentYear - c.year) > nYears) count++;
        }
        if (count == 0) return new Car[0];
        Car[] res = new Car[count];
        int idx = 0;
        for (Car c : arr) {
            if (c.model.equals(model) && (currentYear - c.year) > nYears) {
                res[idx++] = c;
            }
        }
        return res;
    }
}

class Car {
    String make;
    String model;
    int year;
    String color;
    public Car(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }
    public String toString() {
        return make + " " + model + " " + year + " " + color;
    }
}
