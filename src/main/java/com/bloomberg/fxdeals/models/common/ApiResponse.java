package com.bloomberg.fxdeals.models.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponse implements Serializable {
    private Response response;
    private Object body;

    @Data
    @Builder
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class Response {
        private String code;
        private String message;
        private String status;
    }

}
