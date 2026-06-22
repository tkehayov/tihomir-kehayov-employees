package com.sirma.employees.rest.employees;

import com.sirma.employees.core.collaborations.EmployeeCollaboration;
import com.sirma.employees.core.collaborations.EmployeeCollaborationService;
import com.sirma.employees.core.collaborations.EmployeeParseWrapper;
import com.sirma.employees.core.collaborations.PairEmployeesTogether;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeParseWrapper employeeParseWrapper;
    private final EmployeeCollaborationService employeeCollaborationService;

    @PostMapping("/max-collaboration")
    public ResponseEntity<PairEmployeesTogether> getMaxCollaboration(
            @RequestPart("file") MultipartFile file) {
        List<EmployeeCollaboration> employeeCollaborations = employeeParseWrapper.parseCsv(file);
        PairEmployeesTogether pairEmployeesTogether = employeeCollaborationService.calculateMaxCollaboration(employeeCollaborations);

        return ResponseEntity.ok().body(pairEmployeesTogether);

    }
}
