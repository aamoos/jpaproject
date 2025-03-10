package com.setting.jpaProject.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberEditRequest(
        @NotBlank String name,
        AddressDto address
) {
}
