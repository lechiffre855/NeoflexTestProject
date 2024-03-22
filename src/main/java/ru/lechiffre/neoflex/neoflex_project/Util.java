package ru.lechiffre.neoflex.neoflex_project;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lechiffre.neoflex.neoflex_project.config.HolidayConfiguration;

import java.util.Calendar;

@Component
public class Util {

    private static HolidayConfiguration holidayConfiguration;

    @Autowired
    private Util(HolidayConfiguration holidayConfiguration){
        Util.holidayConfiguration = holidayConfiguration;
    }

    public static boolean isHoliday(Calendar day){
        return holidayConfiguration.getHolidays().contains(day);
    }

}
