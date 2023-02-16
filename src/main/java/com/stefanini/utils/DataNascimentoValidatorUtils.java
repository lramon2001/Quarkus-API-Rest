package com.stefanini.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext; 

public class DataNascimentoValidatorUtils implements ConstraintValidator<ValidDataNascimento, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsedDate = LocalDate.parse(value.format(dateFormatter), dateFormatter);
            return parsedDate.equals(value);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
