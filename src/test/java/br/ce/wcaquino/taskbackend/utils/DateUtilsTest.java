package br.ce.wcaquino.taskbackend.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DateUtilsTest{

    @Test
    public void shouldReturnTrueForFutureDate(){
        LocalDate date = LocalDate.of(2040, 1, 1);
        Assertions.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void shouldReturnFalseForPastDate(){
        LocalDate date = LocalDate.of(2020, 1, 1);
        Assertions.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void shouldReturnTrueForPresentDate(){
        LocalDate date = LocalDate.now();
        Assertions.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }
}