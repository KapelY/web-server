package com.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.lineSeparator;

public class RequestParser {

    public static Request parse(BufferedReader reader) throws IOException {
        Request request = new Request();

        String requestLine = reader.readLine();
        checkRequestLine(requestLine);

        String header;
        Map<String, String> requestHeaders = new HashMap<>();
        int colonPosition;
        while (!(header= reader.readLine()).isEmpty()) {
            System.out.println(header);
            colonPosition = checkHeaderLine(header);
            requestHeaders.put(header.substring(0, colonPosition), header.substring(colonPosition + 1));
        }
        injectUriAndMethod(requestLine, request);
        injectHeaders(requestHeaders, request);

        if (requestHeaders.containsKey("Content-Length")) {
            StringBuilder body = new StringBuilder();
            String bodyLine;
            while (!(bodyLine = reader.readLine()).isEmpty()) {
                System.out.println(bodyLine);
                body.append(bodyLine).append(lineSeparator());
            }
            injectBody(body.toString(), request);
        }

        return request;
    }

    private static void injectBody(String body, Request request) {
        request.body = body;
    }

    private static void injectUriAndMethod(String requestLine, Request request) {
        String[] lines = requestLine.split(" ");
        request.setMethod(HttpMethod.valueOf(lines[0]));
        request.setUri(lines[1]);
    }

    private static void injectHeaders(Map<String, String> headers, Request request) {
        request.setHeaders(headers);
    }

    private static void checkRequestLine(String requestLine) throws HttpFormatException {
        if (requestLine == null || requestLine.length() == 0) {
            throw new HttpFormatException("Request-Line is empty or null: " + requestLine);
        }
    }

    private static int checkHeaderLine(String header) throws HttpFormatException {
        int colonPosition = header.indexOf(":");
        if (colonPosition == -1) {
            throw new HttpFormatException("Invalid Header line, colon is absent: " + header);
        }
        return colonPosition;
    }

    public static class HttpFormatException extends RuntimeException {
        public HttpFormatException(String message) {
            super(message);
        }
    }
}
