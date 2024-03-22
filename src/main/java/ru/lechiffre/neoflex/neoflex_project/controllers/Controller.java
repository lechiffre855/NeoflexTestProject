package ru.lechiffre.neoflex.neoflex_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lechiffre.neoflex.neoflex_project.service.SalaryService;

@RestController
public class Controller {

    private final SalaryService salaryService;

    @Autowired
    public Controller(SalaryService salaryService) {this.salaryService = salaryService;}

    @GetMapping("/calculate")
    public String getVacationPay(@Nullable @RequestParam("salary") Double salary, @Nullable @RequestParam("start_date") String startDate,
                                 @Nullable @RequestParam("end_date") String endDate) {

        return salaryService.calculateVacationPay(salary, startDate, endDate);
    }
}