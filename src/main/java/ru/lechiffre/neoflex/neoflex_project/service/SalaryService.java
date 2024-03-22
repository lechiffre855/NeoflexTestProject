package ru.lechiffre.neoflex.neoflex_project.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lechiffre.neoflex.neoflex_project.Util;
import ru.lechiffre.neoflex.neoflex_project.config.HolidayConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class SalaryService {


    private int getWorkingDaysInYear(){
        int workingDaysInYear = 0;
        Calendar calendar = new GregorianCalendar(2024, Calendar.JANUARY, 1);
        while (!calendar.after(new GregorianCalendar(2024, Calendar.DECEMBER, 31))) {
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            if (!(day==Calendar.SATURDAY || day==Calendar.SUNDAY || Util.isHoliday(calendar))){
                workingDaysInYear++;
            }
            calendar.add(Calendar.DATE, 1);
        }
        return workingDaysInYear;
    }


    public String calculateVacationPay(Double salary, String startDate, String endDate) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Calendar startingDate = new GregorianCalendar();
        Calendar endingDate = new GregorianCalendar();
        int paidDays = 0;
        try {
            startingDate.setTime(formatter.parse(startDate));
            endingDate.setTime(formatter.parse(endDate));

            if (startingDate.after(endingDate))
                return "Дата начала отпуска должна быть раньше, чем дата его окончания!";

            if (startingDate.before(new GregorianCalendar()))
                return "Дата начала отпуска не может быть в прошедшем времени!";


            while (!startingDate.after(endingDate)) {
                int day = startingDate.get(Calendar.DAY_OF_WEEK);
                if (!(day == Calendar.SATURDAY || day == Calendar.SUNDAY || Util.isHoliday(startingDate))) {
                    paidDays++;
                }
                startingDate.add(Calendar.DATE, 1);
            }

        } catch (ParseException e) {
            return "Введены некорректные даты!";
        }

        double averageDayCountInMonthPerYear = (double) getWorkingDaysInYear()/12;
        double averageSalaryPerDay = salary/averageDayCountInMonthPerYear;

        return String.valueOf(paidDays * averageSalaryPerDay);
    }

}
