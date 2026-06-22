package com.sirma.employees.core.collaborations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CsvDateConverterTest {

    private CsvDateConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CsvDateConverter();
    }

    @Test
    void parsesIsoFormat() {
        assertEquals(LocalDate.of(2020, 5, 15), converter.convert("2020/05/15"));
        assertEquals(LocalDate.of(2020, 5, 15), converter.convert("15/05/2020"));
        assertEquals(LocalDate.of(2020, 5, 15), converter.convert("15-05-2020"));
        assertEquals(LocalDate.now(), converter.convert("NULL"));
        assertThrows(DateFormatterException.class, () -> converter.convert("incorrect-date"));
        assertEquals(LocalDate.of(2020, 5, 15), converter.convert("2020-05-15"));
    }
}