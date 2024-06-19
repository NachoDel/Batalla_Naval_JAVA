package src;

import java.util.Scanner;

public class Scanear {
    private static Scanear instance;
    private Scanner scanner;

    private Scanear() {
        this.scanner = new Scanner(System.in);
    }

    public static Scanear getInstance() {
        if (instance == null) {
            instance = new Scanear();
        }
        return instance;
    }

    public Scanner getScanner() {
        return this.scanner;
    }
}
