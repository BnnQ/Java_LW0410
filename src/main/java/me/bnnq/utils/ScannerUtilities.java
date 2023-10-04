package me.bnnq.utils;

import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ScannerUtilities {
    private static Scanner scanner = new Scanner(System.in);

    public static int scanInt() {
        return Integer.parseInt(scanString());
    }

    public static double scanDouble() {
        return Double.parseDouble(scanString());
    }

    public static String scanString() {
        return scanner.nextLine();
    }

    public static char scanChar() {
        return scanString().charAt(0);
    }

    public static LocalDate scanDate(DateTimeFormatter formatter) throws ParseException
    {
        String dateString = scanString();
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * @param stream The stream to change to (default is System.in)
     */
    public static void changeStream(InputStream stream) {
        scanner = new Scanner(stream);
    }
}
