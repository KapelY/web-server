package com.study;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.System.lineSeparator;

public class ResponseWriter {
    public static final String CRLF = lineSeparator();
    public static void writeSuccessResponse(BufferedOutputStream writer, String content) throws IOException {
        writer.write(HttpResponseCode.OK.getStatus().getBytes(StandardCharsets.UTF_8));
        writer.write(CRLF.getBytes(StandardCharsets.UTF_8));
        writer.write(CRLF.getBytes(StandardCharsets.UTF_8));
        writer.write(content.getBytes(StandardCharsets.UTF_8));
        writer.flush();
    }
    public static void writeNotFoundResponse(BufferedOutputStream writer) throws IOException {
        writer.write(HttpResponseCode.BAD_REQUEST.getStatus().getBytes(StandardCharsets.UTF_8));
        writer.write(CRLF.getBytes(StandardCharsets.UTF_8));
        writer.write(CRLF.getBytes(StandardCharsets.UTF_8));
        writer.write(HttpResponseCode.BAD_REQUEST.getName().getBytes(StandardCharsets.UTF_8));
        writer.flush();
    }
}
