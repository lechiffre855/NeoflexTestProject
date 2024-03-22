package ru.lechiffre.neoflex.neoflex_project.service;

import org.springframework.stereotype.Service;
import ru.lechiffre.neoflex.neoflex_project.util.HolidayUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class SalaryService {

    int workingDaysInYear = 0;
    {
        Calendar calendar = new GregorianCalendar(2024, Calendar.JANUARY, 1);
        while (!calendar.after(new GregorianCalendar(2024, Calendar.DECEMBER, 31))) {
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            if (!(day==Calendar.SATURDAY || day==Calendar.SUNDAY || HolidayUtils.isHoliday(calendar))){
                workingDaysInYear++;
            }
            calendar.add(Calendar.DATE, 1);
        }
    }

    public String calculateVacationPay(Double salary, String startDate, String endDate) {

        if (salary==null || salary==0)
            return "Введите зарплату, при этом она не может равняться нулю!";

        if (startDate ==null)
            return "Введите дату начала отпуска!";

        if (endDate==null)
            return "Введите дату окончания отпуска!";

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Calendar startingDate = new GregorianCalendar();
        Calendar endingDate = new GregorianCalendar();
        int paidDays = 0;
        try {
            startingDate.setTime(formatter.parse(startDate));
            endingDate.setTime(formatter.parse(endDate));


            if (startingDate.after(endingDate))
                return "Дата начала отпуска должна быть раньше, чем дата его окончания!";

//            if (startingDate.before(new GregorianCalendar()))
//                return "Дата начала отпуска не может быть в прошедшем времени!";


            while (!startingDate.after(endingDate)) {
                int day = startingDate.get(Calendar.DAY_OF_WEEK);
                if (!(day == Calendar.SATURDAY || day == Calendar.SUNDAY || HolidayUtils.isHoliday(startingDate))) {
                    paidDays++;
                }
                startingDate.add(Calendar.DATE, 1);
            }

        } catch (ParseException e) {
            return "Введены некорректные даты!";
        }

        double averageDayCountInMonthPerYear = (double) workingDaysInYear/12;
        double averageSalaryPerDay = salary/averageDayCountInMonthPerYear;

        return String.valueOf( (int) Math.round(paidDays * averageSalaryPerDay) );
    }

}
