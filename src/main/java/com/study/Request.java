package com.study;

import lombok.Data;

import java.util.Map;


@Data
public class Request {
    public String uri;
    public Map<String, String> headers;
    public HttpMethod method;
    public String body;
}
