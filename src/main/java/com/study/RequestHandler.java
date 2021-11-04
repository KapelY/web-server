package com.study;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.BufferedWriter;

@AllArgsConstructor
public class RequestHandler {
    public BufferedReader reader;
    public BufferedWriter writer;
    public String staticResourcesPath;

    public void handle() {
        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parse(reader);

        ResourceReader resourceReader = new ResourceReader(staticResourcesPath);
        String content = resourceReader.readContent(request.uri);

        if (content != null) {
            ResponseWriter.writeSuccessResponse(writer, content);
        } else {
            ResponseWriter.writeNotFoundResponse(writer);
        }
    }
}
