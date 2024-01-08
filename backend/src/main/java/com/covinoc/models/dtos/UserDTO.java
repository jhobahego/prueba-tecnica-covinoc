package com.covinoc.models.dtos;

import lombok.Builder;

@Builder
public record UserDTO(Long id, String name, String phoneNumber) {
}
