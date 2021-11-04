package com.study;

import lombok.AllArgsConstructor;

import java.io.*;

@AllArgsConstructor
public class ResourceReader {
    private static final String EXCEPTION_IN_STREAM = "Something went wrong in 'readContent(String uri)')!";
    public String staticResourcesPath;

    public String readContent(String uri) {
        String path = staticResourcesPath + uri;
        if (new File(path).exists()) {
            try (var bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while (!(line = bufferedReader.readLine()).isEmpty()) {
                   stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                throw new RuntimeException(EXCEPTION_IN_STREAM, e);
            }
        }
        return null;
    }
}
