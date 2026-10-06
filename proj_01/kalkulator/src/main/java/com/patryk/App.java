package com.patryk;

interface Printable {
    void print();
}

abstract class Function {
    abstract double calculateValue(double x);

    abstract double[] calculateZeros();
}

class LinearFunction extends Function implements Printable {
    private double a;
    private double b;

    LinearFunction(double a, double b) {
        if (Math.abs(a) < 1e-9) {
            throw new IllegalArgumentException("funkcja stała, nieskończenie wiele lub brak miejsc zerowych");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    double calculateValue(double x) {
        return a * x + b;
    }

    @Override
    double[] calculateZeros() {
        double[] zeros = new double[1];

        zeros[0] = -b / a;

        return zeros;
    }

    @Override
    public void print() {
        System.out.println("Funkcja liniowa: f(x) = " + a + "x + " + b);
        System.out.println("wspoczynniki: a = " + a + ", b = " + b);
        System.out.println("Miejsce zerowe: " + calculateZeros()[0]);
    }

}

class QuadraticFunction extends Function implements Printable {
    private double a;
    private double b;
    private double c;

    QuadraticFunction(double a, double b, double c) {
        if (Math.abs(a) < 1e-9) {
            throw new IllegalArgumentException("to nie jest funkcja kwadratowa");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double calculateValue(double x) {
        return a * x * x + x * b + c;
    }

    @Override
    double[] calculateZeros() {
        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double[] zeros = new double[2];
            zeros[0] = (-b - Math.sqrt(delta)) / (2 * a);
            zeros[1] = (-b + Math.sqrt(delta)) / (2 * a);
            return zeros;
        } else {
            double[] zeros = new double[1];
            zeros[0] = -b / (2 * a);
            return zeros;
        }
    }

    @Override
    public void print() {
        System.out.println("Funkcja kwadratowa: f(x) = " + a + "x^2 + " + b + "x + " + c);
        System.out.println("wspoczynniki: a = " + a + ", b = " + b + ", c = " + c);
        double[] zeros = calculateZeros();
        for (int i = 0; i < zeros.length; i++) {
            System.out.println("Miejsce zerowe " + (i + 1) + ": " + zeros[i]);
        }
        System.out.println("delta: " + (b * b - 4 * a * c));
        System.out.println("Wierzchołek: (" + (-b / (2 * a)) + ", " + calculateValue(-b / (2 * a)) + ")");
    }
}

class ExponentialFunction extends Function implements Printable {
    private double a;
    private double b;

    ExponentialFunction(double a, double b) {
        if (Math.abs(a) < 1e-9 || a == 1) {
            throw new IllegalArgumentException("podstawa funkcji wykładniczej musi być większa od 0 i różna od 1");
        }
        this.a = a;
        this.b = b;
    }

    @Override
    double calculateValue(double x) {
        return Math.pow(a, x) + b;
    }

    @Override
    double[] calculateZeros() {
        if (b >= 0) {
            return new double[0]; // brak miejsc zerowych
        } else {
            double[] zeros = new double[1];
            zeros[0] = Math.log(-b) / Math.log(a);
            return zeros;
        }
    }

    @Override
    public void print() {
        System.out.println("Funkcja wykładnicza: f(x) = " + a + "^x + " + b);
        System.out.println("wspoczynniki: a = " + a + ", b = " + b);
        double[] zeros = calculateZeros();
        for (int i = 0; i < zeros.length; i++) {
            System.out.println("Miejsce zerowe " + (i + 1) + ": " + zeros[i]);
        }
    }
}

class FunctionOnInterval implements Printable {
    private final Function function;
    private final double start;
    private final double end;
    private final double step;

    FunctionOnInterval(Function function, double start, double end, double step) {
        if (function == null) {
            throw new IllegalArgumentException("Funkcja nie może być nullem");
        }
        if (start >= end) {
            throw new IllegalArgumentException("Początek przedziału musi być mniejszy od końca przedziału");
        }
        if (step <= 0 || step > end - start) {
            throw new IllegalArgumentException("Krok musi być większy od 0 i nie większy od długości przedziału");
        }

        this.function = function;
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public double[] calculateValues() {
        double[] points = calculatePoints();
        double[] values = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            values[i] = function.calculateValue(points[i]);
        }
        return values;
    }

    public double calculateMin() {
        double[] values = calculateValues();
        double result = values[0];
        for (double value : values) {
            result = Math.min(result, value);
        }
        return result;
    }

    public double calculateMax() {
        double[] values = calculateValues();
        double result = values[0];
        for (double value : values) {
            result = Math.max(result, value);
        }
        return result;
    }

    public double calculateIntegral() {
        double[] points = calculatePoints();
        double[] values = calculateValues();
        double result = 0;

        for (int i = 0; i < values.length - 1; i++) {
            result += (values[i] + values[i + 1]) / 2 * (points[i + 1] - points[i]);
        }
        return result;
    }

    private double[] calculatePoints() {
        int numberOfSteps = (int) Math.ceil((end - start) / step);
        double[] points = new double[numberOfSteps + 1];

        for (int i = 0; i < numberOfSteps; i++) {
            points[i] = start + i * step;
        }
        points[numberOfSteps] = end;
        return points;
    }

    @Override
    public void print() {
        System.out.println("Funkcja na przedziale [" + start + ", " + end + "] z krokiem " + step + ":");
        double[] points = calculatePoints();
        double[] values = calculateValues();
        for (int i = 0; i < points.length; i++) {
            System.out.println("f(" + points[i] + ") = " + values[i]);
        }
        System.out.println("Najmniejsza wartość: " + calculateMin());
        System.out.println("Największa wartość: " + calculateMax());
        System.out.println("Przybliżone pole pod wykresem: " + calculateIntegral());
    }
}

public class App {
    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();

        ConsoleUI ui = new ConsoleUI(input);
        ui.run();
    }
}

class ConsoleInput {

    private final java.util.Scanner scanner = new java.util.Scanner(System.in);

    int readInt(String message) {

        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }

            System.out.println("Podaj poprawną liczbę całkowitą.");
            scanner.nextLine();
        }
    }

    double readDouble(String message) {
        while (true) {

            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            }

            System.out.println("Podaj poprawną liczbę.");
            scanner.nextLine();
        }
    }
}

class ConsoleUI {
    private final ConsoleInput input;

    ConsoleUI(ConsoleInput input) {
        this.input = input;
    }

    void run() {
        boolean running = true;

        while (running) {
            printMenu();

            int choice = input.readInt("Wybierz opcję: ");

            switch (choice) {
                case 1:
                    handleLinear();
                    break;
                case 2:
                    handleQuadratic();
                    break;
                case 3:
                    handleExponential();
                    break;
                case 4:
                    running = false;
                    System.out.println("Koniec programu.");
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Kalkulator funkcji ===");
        System.out.println("1. Funkcja liniowa");
        System.out.println("2. Funkcja kwadratowa");
        System.out.println("3. Funkcja wykładnicza");
        System.out.println("4. Wyjście");
    }

    private void handleLinear() {
        try {

            double a = input.readDouble("Podaj a: ");
            double b = input.readDouble("Podaj b: ");

            showFunction(new LinearFunction(a, b));
        } catch (IllegalArgumentException exception) {
            System.out.println("Błąd: " + exception.getMessage());

        }
    }

    private void handleQuadratic() {
        try {
            double a = input.readDouble("Podaj a: ");

            double b = input.readDouble("Podaj b: ");

            double c = input.readDouble("Podaj c: ");

            showFunction(new QuadraticFunction(a, b, c));
        } catch (IllegalArgumentException exception) {
            System.out.println("Błąd: " + exception.getMessage());
        }
    }

    private void handleExponential() {
        try {
            double a = input.readDouble("Podaj podstawę a: ");

            double b = input.readDouble("Podaj b: ");

            showFunction(new ExponentialFunction(a, b));
        } catch (IllegalArgumentException exception) {
            System.out.println("Błąd: " + exception.getMessage());

        }
    }

    private void showFunction(Function function) {

        ((Printable) function).print();

        System.out.println("\nParametry przedziału:");
        double start = input.readDouble("Początek: ");
        double end = input.readDouble("Koniec: ");
        double step = input.readDouble("Krok: ");

        try {
            new FunctionOnInterval(function, start, end, step).print();

        } catch (IllegalArgumentException exception) {
            System.out.println("Błąd: " + exception.getMessage());

        }

    }

}
