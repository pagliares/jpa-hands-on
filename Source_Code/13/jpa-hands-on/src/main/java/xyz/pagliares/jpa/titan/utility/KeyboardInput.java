package xyz.pagliares.jpa.titan.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class KeyboardInput {
    private static Locale locale = new Locale("pt","BR");

    // SimpleDateFormat was used Pre Java-SE 8. DateTimeFormatter is used since Java-SE 8)
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", locale);

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", locale);

    public static String readInputAsString(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static double readInputAsDouble(String prompt) throws NumberFormatException {
        String input = readInputAsString(prompt);
        return Double.parseDouble(input);
    }

    public static int readInputAsInteger(String prompt) throws NumberFormatException {
        String input = readInputAsString(prompt);
        return Integer.parseInt(input);
    }

    /** Below, an alternative way to create a date informed in the format year/month/day. Just be
     * careful to not forget that an int literal in java that starts with 0 is in octal base
     * Better to dismiss the leading zero of the substring if you want to avoid problems of thinking
     * the number is in the decimal base when it is, actually, in base 8.

                   String[] splitDate = input.split("/");
                   int year = Integer.parseInt(splitDate[0]);
                   int month = Integer.parseInt(splitDate[1]);
                   int day = Integer.parseInt(splitDate[2]);

                   GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);
                   Date birthday = gregorianCalendar.getTime();
     */
    public static Date readStringInputAsJavaUtilDate(String prompt) throws ParseException {
        String input = readInputAsString(prompt);
        Date birthday = null;
        birthday = formatter.parse(input);
        return birthday;
    }

    public static LocalDate readStringInputAsJavaLocalDate(String prompt) throws DateTimeParseException {
        String input = readInputAsString(prompt);
        LocalDate birthday = null;
        try {
            birthday = LocalDate.parse(input, dateTimeFormatter);
        } catch (DateTimeParseException dateTimeParseException){
            throw(dateTimeParseException);
        }
        return birthday;
    }
}