// ExampleApp.java
// Примерный Java-код для практики Vim.
// Скомпилируется и выполнится: javac ExampleApp.java && java ExampleApp

package edu.hw3;

import java.util.Scanner;
import java.lang.Math;

public class t5 {
    static final double EPS = 1e-6;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Triangle[] arr = new Triangle[n];
        for (int i = 0; i < n; i++) {
            double x1 = sc.nextDouble();
            double y1 = sc.nextDouble();
            double x2 = sc.nextDouble();
            double y2 = sc.nextDouble();
            double x3 = sc.nextDouble();
            double y3 = sc.nextDouble();
            arr[i] = new Triangle(x1, y1, x2, y2, x3, y3);
        }
        int[] counts = countTypes(arr);
        System.out.println("Number of equilateral triangles: " + counts[0]);
        System.out.println("Number of right triangles: " + counts[1]);
        System.out.println("Number of isosceles triangles: " + counts[2]);
        System.out.println("Number of scalene triangles: " + counts[3]);
        Triangle maxT = maxAreaTriangle(arr);
        if (maxT == null) {
            System.out.println("No valid triangles");
        } else {
            System.out.printf("Largest by area: (%.6f, %.6f) (%.6f, %.6f) (%.6f, %.6f)%n",
                maxT.x1, maxT.y1, maxT.x2, maxT.y2, maxT.x3, maxT.y3);
            System.out.printf("Area: %.6f%n", maxT.area());
            System.out.printf("Perimeter: %.6f%n", maxT.perimeter());
        }
        sc.close();
    }

    public static int[] countTypes(Triangle[] arr) {
        int equilateral = 0;
        int right = 0;
        int isosceles = 0;
        int arbitrary = 0;
        for (Triangle t : arr) {
            if (!t.isValid()) continue;
            if (t.isEquilateral()) {
                equilateral++;
            } else if (t.isRight()) {
                right++;
            } else if (t.isIsosceles()) {
                isosceles++;
            } else {
                arbitrary++;
            }
        }
        return new int[]{equilateral, right, isosceles, arbitrary};
    }

    public static Triangle maxAreaTriangle(Triangle[] arr) {
        Triangle best = null;
        double bestArea = -1.0;
        for (Triangle t : arr) {
            if (!t.isValid()) continue;
            double a = t.area();
            if (a > bestArea) {
                bestArea = a;
                best = t;
            }
        }
        return best;
    }
}

class Triangle {
    double x1, y1, x2, y2, x3, y3;
    static final double EPS = 1e-6;
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    double sideA() {
        return Math.hypot(x1 - x2, y1 - y2);
    }
    double sideB() {
        return Math.hypot(x2 - x3, y2 - y3);
    }
    double sideC() {
        return Math.hypot(x3 - x1, y3 - y1);
    }
    double perimeter() {
        if (!isValid()) return 0.0;
        return sideA() + sideB() + sideC();
    }
    double area() {
        double v = Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)) / 2.0);
        return v;
    }
    boolean isValid() {
        return area() > EPS;
    }
    boolean equalD(double a, double b) {
        return Math.abs(a - b) <= EPS;
    }
    boolean isEquilateral() {
        if (!isValid()) return false;
        double a = sideA(), b = sideB(), c = sideC();
        return equalD(a, b) && equalD(b, c);
    }
    boolean isIsosceles() {
        if (!isValid()) return false;
        if (isEquilateral()) return false;
        double a = sideA(), b = sideB(), c = sideC();
        return equalD(a, b) || equalD(b, c) || equalD(a, c);
    }
    boolean isRight() {
        if (!isValid()) return false;
        double a = sideA(), b = sideB(), c = sideC();
        double max = Math.max(a, Math.max(b, c));
        double sumsq = a*a + b*b + c*c;
        return equalD(max*max, sumsq - max*max);
    }
}


/*
in:
4
0 0 1 0 0.5 0.86602540378
0 0 1 0 0 1
0 0 2 0 1 1
0 0 1 1 2 0
 */
/*
out:
Number of equilateral triangles: 1
Number of right triangles: 3
Number of isosceles triangles: 0
Number of scalene triangles: 0
Largest by area: (0.000000, 0.000000) (2.000000, 0.000000) (1.000000, 1.000000)
Area: 1.000000
Perimeter: 4.828427
 */