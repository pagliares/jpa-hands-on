package xyz.pagliares.jpa.titan.utility;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DateTimeUtility {

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
