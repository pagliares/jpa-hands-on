package xyz.pagliares.jpa.titan.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class DateTimeUtility {

    /**
     * In this example, we are going to read from the keyboard dates in Brazilian format (day/month/year)
     * For example, 07/05/1977 for May 7th, 1977.
     *
     * If you rather use a format such as 07-May-1977, you can configure DateTimeFormatter as below:
     * private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.US);
     * In this case, the month initials are: Jan, Fev, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;
     *
     **/
    private static Locale locale = new Locale("pt","BR");
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", locale);


    // SimpleDateFormat was used Pre Java-SE 8. DateTimeFormatter is used since Java-SE 8)
    //    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", locale);

    /** Method to generate random java.util.Date (Pre Java SE 8) **/
    public static Date generateRandomDateBetweenTwoObjectsJavaUtilDates(Date startDateInclusive, Date endDateExclusive) {
        long startMillis = startDateInclusive.getTime();
        long endMillis = endDateExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
        return new Date(randomMillisSinceEpoch);
    }

    /** Method to generate random LocalDate (Since Java SE 8) **/
    public static LocalDate generateRandomDateBetweenTwoObjectsLocalDate(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        long minDay = startDateInclusive.toEpochDay();
        long maxDay = endDateExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return (randomDate);
    }
}
