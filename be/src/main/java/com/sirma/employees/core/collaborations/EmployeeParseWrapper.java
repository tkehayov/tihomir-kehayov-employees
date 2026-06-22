package com.sirma.employees.core.collaborations;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class EmployeeParseWrapper {
    public List<EmployeeCollaboration> parseCsv(MultipartFile file) {
            List<EmployeeCollaboration> parse;
        try {
            Reader reader = new InputStreamReader(file.getInputStream());
            CsvToBean<EmployeeCollaboration> csvToBean = new CsvToBeanBuilder<EmployeeCollaboration>(reader)
                    .withType(EmployeeCollaboration.class)
                    .build();
            parse = csvToBean.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parse;
    }
}
