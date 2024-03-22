package ru.lechiffre.neoflex.neoflex_project.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class HolidayUtils {

    private static final Set<Calendar> holidays = new HashSet<>();

    static {
        makeHolidays();
    }

    private static void makeHolidays() {
        try {
            InputStream inputStream = HolidayUtils.class.getClassLoader().getResourceAsStream("holidays.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            while ((line= reader.readLine()) != null){
                Calendar holiday = new GregorianCalendar();
                holiday.setTime(formatter.parse(line));
                holiday.set(Calendar.YEAR, 0);
                holidays.add(holiday);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isHoliday(Calendar calendar){
        return holidays.stream().anyMatch(holiday -> holiday.get(Calendar.MONTH)==calendar.get(Calendar.MONTH)
                && holiday.get(Calendar.DAY_OF_MONTH)==calendar.get(Calendar.DAY_OF_MONTH));
    }
}
