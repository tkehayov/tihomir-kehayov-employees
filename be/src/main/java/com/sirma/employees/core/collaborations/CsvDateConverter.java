package com.sirma.employees.core.collaborations;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvDateConverter extends AbstractBeanField<LocalDate, String> {

    @Override
    public LocalDate convert(String date) {
        List<DateTimeFormatter> allFormatters = List.of(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
        );

        if ("NULL".equalsIgnoreCase(date)) {
            return LocalDate.now();
        }

        for (DateTimeFormatter formatter : allFormatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (Exception e) {

            }
        }
        throw new DateFormatterException("Error date cannot be parsed");
    }

}
