package com.sirma.employees.core.collaborations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeParseWrapperTest {

    @Autowired
    private EmployeeParseWrapper employeeParseWrapper;

    @Test
    void parsesValidCsv() {
        String csv = "EmpID,ProjectID,DateFrom,DateTo\n143,12,2020-01-01,2020-06-01\n218,12,2020-03-01,2020-09-01";
        MockMultipartFile file = new MockMultipartFile("file", "employees.csv", "text/csv", csv.getBytes());

        List<EmployeeCollaboration> result = employeeParseWrapper.parseCsv(file);

        assertEquals(2, result.size());
        assertEquals(143L, result.get(0).getId());
        assertEquals(12L, result.get(0).getProjectId());
        assertEquals(LocalDate.of(2020, 1, 1), result.get(0).getDateFrom());
        assertEquals(LocalDate.of(2020, 6, 1), result.get(0).getDateTo());
    }
}