package com.covinoc.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserDTO(

        @NotNull(message = "Name cannot be null")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotNull(message = "Phone number cannot be null")
        @Size(min = 10, message = "Phone number must be at least 10 characters")
        String phoneNumber
) {
}
