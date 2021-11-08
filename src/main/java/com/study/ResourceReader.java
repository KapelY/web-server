package com.study;

import java.io.*;

public class ResourceReader {
    static final String EXCEPTION_IN_STREAM = "Something went wrong in 'readContent(String uri)')!";

    public static String readContent(String staticResourcesPath, String uri) {
        String path = staticResourcesPath + uri;
        System.out.println("content path ->" + path);
        if (new File(path).exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                   stringBuilder.append(line).append(System.lineSeparator());
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                throw new RuntimeException(EXCEPTION_IN_STREAM, e);
            }
        }
        return null;
    }
}
