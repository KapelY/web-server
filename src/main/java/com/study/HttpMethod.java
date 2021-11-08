package com.study;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum HttpMethod {
    GET("get"), PUT("put"), POST("post"), DELETE("delete");

    private final String name;
}
