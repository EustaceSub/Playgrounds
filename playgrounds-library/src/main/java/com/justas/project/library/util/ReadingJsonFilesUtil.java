package com.justas.project.library.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReadingJsonFilesUtil {

    public static JsonObject getJsonFromResource(String resourcePath) {
        ClassLoader classLoader = ReadingJsonFilesUtil.class.getClassLoader();
        URL resource = classLoader.getResource(resourcePath);
        if (resource == null) {
            throw new RuntimeException(format("Path: {%s} not found!", resourcePath));
        }
        return getContentAsJson(resource);
    }

    private static JsonObject getContentAsJson(URL resource) {
        try {
            return JsonParser.parseString(IOUtils.toString(resource.openStream(), UTF_8)).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
