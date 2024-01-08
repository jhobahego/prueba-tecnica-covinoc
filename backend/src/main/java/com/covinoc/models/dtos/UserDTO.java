package com.covinoc.models.dtos;

import lombok.Builder;

@Builder
public record UserDTO(String name, String phoneNumber) {
}
