package com.setting.jpaProject.error;

public record ErrorResponse(
        ErrorCode errorCode,
        String message
) {

}
