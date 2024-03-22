package ru.lechiffre.neoflex.neoflex_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "holidays")
public class HolidayConfiguration {

    private final List<Calendar> holidaysList = new ArrayList<>();

    public List<Calendar> getHolidays() {
        return holidaysList;
    }

    public void setHolidays(List<String> holidays) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (String holiday: holidays){
            Calendar day = new GregorianCalendar();
            day.setTime(formatter.parse(holiday));
            this.holidaysList.add(day);
        }
    }
}
