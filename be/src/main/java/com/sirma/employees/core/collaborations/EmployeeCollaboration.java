package com.sirma.employees.core.collaborations;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCollaboration {
    @CsvBindByName(column = "EmpID")
    private Long id;
    @CsvBindByName(column = "ProjectID")
    private Long projectId;
    @CsvCustomBindByName(column = "DateFrom", converter = CsvDateConverter.class)
    private LocalDate dateFrom;
    @CsvCustomBindByName(column = "DateTo", converter = CsvDateConverter.class)
    private LocalDate dateTo;

}
