package com.study;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpResponseCode {
    OK("HTTP/1.1 200 OK", "Ok"),
    BAD_REQUEST("HTTP/1.1 404 file not found", "404 file not found");

    private String status;
    private String name;
}
