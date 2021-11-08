package com.study;

import lombok.AllArgsConstructor;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public class RequestHandler {
    public BufferedReader reader;
    public BufferedOutputStream writer;
    public String staticResourcesPath;

    public void handle() throws IOException {
        Request request = RequestParser.parse(reader);
        String content = ResourceReader.readContent(staticResourcesPath, request.uri);

        if (content != null) {
            ResponseWriter.writeSuccessResponse(writer, content);
        } else {
            ResponseWriter.writeNotFoundResponse(writer);
        }
    }
}
