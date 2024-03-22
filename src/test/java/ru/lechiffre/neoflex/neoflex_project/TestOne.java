package ru.lechiffre.neoflex.neoflex_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class TestOne {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getCalculationOfNothing() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("Введите зарплату, при этом она не может равняться нулю!"));
    }

    @Test
    void getCalculationOfNullableSalary() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate")
                .param("start_date", "03-04.2024")
                .param("end_date", "05-05-2024");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("Введите зарплату, при этом она не может равняться нулю!"));
    }

    @Test
    void getCalculationOfNullableStartDate() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate")
                .param("salary", "25000")
                .param("end_date", "05-05-2024");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("Введите дату начала отпуска!"));
    }

    @Test
    void getCalculationOfNullableEndDate() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate")
                .param("salary", "25000")
                .param("start_date", "05-05-2024");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("Введите дату окончания отпуска!"));
    }

    @Test
    void getCalculationOfSalaryEquals0() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate")
                .param("salary", "0")
                .param("start_date", "05-05-2024")
                .param("end_date", "06-06-2024");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("Введите зарплату, при этом она не может равняться нулю!"));
    }

    @Test
    void getCalculationOfStartDateAfterEndDate() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate")
                .param("salary", "25000")
                .param("start_date", "05-05-2024")
                .param("end_date", "06-03-2024");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("Дата начала отпуска должна быть раньше, чем дата его окончания!"));
    }

    @Test
    void getCalculationOfIncorrectDates() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate")
                .param("salary", "25000")
                .param("start_date", "32-05-2024")
                .param("end_date", "06.03.2024");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("Введены некорректные даты!"));
    }

    @Test
    void getCalculationOfDatesWithoutHolidays() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate")
                .param("salary", "25000")
                .param("start_date", "01-07-2024")
                .param("end_date", "01-08-2024");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("28685"));
    }

    @Test
    void getCalculationOfDatesWithHolidays() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("/calculate")
                .param("salary", "25000")
                .param("start_date", "01-01-2025")
                .param("end_date", "01-02-2025");
        this.mockMvc.perform(requestBuilder)
                .andExpect(content().string("21514"));
    }
}
