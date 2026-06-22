package com.sirma.employees.core.collaborations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeCollaborationServiceTest {
    @Autowired
    private EmployeeCollaborationService employeeCollaborationService;

    @Test
    public void calculateMaxEmployeeCollaboration() {
        EmployeeCollaboration emp1 = EmployeeCollaboration.builder()
                .id(143L)
                .projectId(12L)
                .dateFrom(LocalDate.of(2026, 12, 4))
                .dateTo(LocalDate.of(2026, 12, 10))
                .build();

        EmployeeCollaboration emp2 = EmployeeCollaboration.builder()
                .id(218L)
                .projectId(12L)
                .dateFrom(LocalDate.of(2026, 11, 5))
                .dateTo(LocalDate.of(2026, 12, 7))
                .build();

        EmployeeCollaboration emp3 = EmployeeCollaboration.builder()
                .id(143L)
                .projectId(13L)
                .dateFrom(LocalDate.of(2026, 12, 4))
                .dateTo(LocalDate.of(2026, 12, 10))
                .build();

        EmployeeCollaboration emp4 = EmployeeCollaboration.builder()
                .id(218L)
                .projectId(13L)
                .dateFrom(LocalDate.of(2026, 11, 5))
                .dateTo(LocalDate.of(2026, 12, 20))
                .build();

        PairEmployeesTogether pairEmployeesTogether = employeeCollaborationService.calculateMaxCollaboration(List.of(emp1, emp2, emp3, emp4));

        assertEquals(pairEmployeesTogether.getEmployeeIdOne(), 143);
        assertEquals(pairEmployeesTogether.getEmployeeIdTwo(), 218);
        assertEquals(pairEmployeesTogether.getProjectId(), 13);
        assertEquals(pairEmployeesTogether.getDaysTogether(), 6);
    }

    @Test
    public void emptyCollaborationsList() {
        PairEmployeesTogether actual = employeeCollaborationService.calculateMaxCollaboration(List.of());
        assertEquals(null, actual);
    }

    @Test
    public void noCollaborationDaysOverlap() {
        EmployeeCollaboration emp1 = EmployeeCollaboration.builder()
                .id(1L)
                .projectId(10L)
                .dateFrom(LocalDate.of(2024, 1, 1))
                .dateTo(LocalDate.of(2024, 3, 1))
                .build();

        EmployeeCollaboration emp2 = EmployeeCollaboration.builder()
                .id(2L)
                .projectId(10L)
                .dateFrom(LocalDate.of(2024, 6, 1))
                .dateTo(LocalDate.of(2024, 9, 1))
                .build();

        PairEmployeesTogether actual = employeeCollaborationService.calculateMaxCollaboration(List.of(emp1, emp2));
        assertEquals(0, actual.getDaysTogether());
    }

    @Test
    public void calculateWithOneEmployee() {
        EmployeeCollaboration emp = EmployeeCollaboration.builder()
                .id(1L)
                .projectId(10L)
                .dateFrom(LocalDate.of(2024, 1, 1))
                .dateTo(LocalDate.of(2024, 6, 1))
                .build();

        PairEmployeesTogether actual = employeeCollaborationService.calculateMaxCollaboration(List.of(emp));
        assertEquals(null, actual);
    }

}