package com.covinoc.utils;

import com.covinoc.exceptions.BadRequestException;
import com.covinoc.models.dtos.UserDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;

public class UserValidation {

    public static void validateUser(UserDTO userRequest, Validator validator) {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<UserDTO> violation : violations) {
                errors.append(violation.getMessage()).append("\n");
            }
            throw new BadRequestException(errors.toString());
        }
    }
}
