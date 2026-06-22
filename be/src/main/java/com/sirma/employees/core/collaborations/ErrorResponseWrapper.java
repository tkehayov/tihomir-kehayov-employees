package com.sirma.employees.core.collaborations;

import java.util.Map;

public record ErrorResponseWrapper(int status,
                                   String message,
                                   long timestamp,
                                   Map<String, String> errors) {

}
