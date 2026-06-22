package com.sirma.employees.core.collaborations;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PairEmployeesTogether {
    private Long projectId;
    private Long employeeIdOne;
    private Long employeeIdTwo;
    private Long daysTogether;
}
