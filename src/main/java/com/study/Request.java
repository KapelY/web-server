package com.study;

import lombok.Value;

import java.util.Map;


@Value
public class Request {
    public String uri;
    public Map<String, String> headers;
    public HttpMethod method;
}
