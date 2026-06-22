package com.sirma.employees.core.collaborations;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeCollaborationService {

    public PairEmployeesTogether calculateMaxCollaboration(List<EmployeeCollaboration> employeeCollaborations) {
        Map<Long, List<EmployeeCollaboration>> projectEmployees = getProjectEmployees(employeeCollaborations);

        List<PairEmployeesTogether> pairEmployeesTogether = new ArrayList<>();
        for (Map.Entry<Long, List<EmployeeCollaboration>> entry : projectEmployees.entrySet()) {
            List<EmployeeCollaboration> collaborations = entry.getValue();
            if (collaborations.size() != 2) {
                continue;
            }
            EmployeeCollaboration firstEmployee = collaborations.getFirst();
            EmployeeCollaboration secondEmployee = collaborations.getLast();

            long daysTogether = calculateCollaborationDays(firstEmployee.getDateFrom(),
                    firstEmployee.getDateTo(),
                    secondEmployee.getDateFrom(),
                    secondEmployee.getDateTo());

            pairEmployeesTogether.add(PairEmployeesTogether.builder()
                    .projectId(entry.getKey())
                    .employeeIdOne(firstEmployee.getId())
                    .employeeIdTwo(secondEmployee.getId())
                    .daysTogether(daysTogether)
                    .build());

        }

        return findMaxDaysCollaborators(pairEmployeesTogether);
    }

    private Map<Long, List<EmployeeCollaboration>> getProjectEmployees(List<EmployeeCollaboration> employeeCollaborations) {
        return employeeCollaborations
                .stream()
                .collect(Collectors.groupingBy(EmployeeCollaboration::getProjectId));
    }

    private long calculateCollaborationDays(
            LocalDate fromEmployee1,
            LocalDate toEmployee1,
            LocalDate fromEmployee2,
            LocalDate toEmployee2) {

        LocalDate start;
        if (fromEmployee1.isAfter(fromEmployee2)) {
            start = fromEmployee1;
        } else {
            start = fromEmployee2;
        }

        LocalDate end;
        if (toEmployee1.isBefore(toEmployee2)) {
            end = toEmployee1;
        } else {
            end = toEmployee2;
        }

        if (start.isAfter(end)) {
            return 0;
        } else {
            return Period.between(start, end).getDays();
        }
    }

    private PairEmployeesTogether findMaxDaysCollaborators(List<PairEmployeesTogether> pairEmployeesTogetherList) {
        if (pairEmployeesTogetherList == null || pairEmployeesTogetherList.isEmpty()) {
            return null;
        }

        return pairEmployeesTogetherList.stream()
                .max(Comparator.comparingLong(PairEmployeesTogether::getDaysTogether))
                .map(bestPair -> PairEmployeesTogether.builder()
                        .projectId(bestPair.getProjectId())
                        .employeeIdOne(bestPair.getEmployeeIdOne())
                        .employeeIdTwo(bestPair.getEmployeeIdTwo())
                        .daysTogether(bestPair.getDaysTogether())
                        .build())
                .orElse(null);
    }
}