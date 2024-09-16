package app.controller;

import java.sql.Date;
import java.util.Scanner;

public abstract class Utils {
    private static final Scanner reader = new Scanner(System.in);

    public static Scanner getReader() {
        return reader;
    }

    public static void log(String message) {
        System.out.println(message);
    }

    public static Date getDate() {
        return new Date(System.currentTimeMillis());
    }
}

