package com.justas.project.library.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

/*
This class should be used to get JSON objects from json files.
 */
@UtilityClass
public class ReadingJsonFilesUtil {
    /**
     * Creates Json path from resource file.
     *
     * @param resourcePath - path of resource
     * @return - content of resource as {@link JsonObject}
     */
    public JsonObject getJsonFromResource(String resourcePath) {
        ClassLoader classLoader = ReadingJsonFilesUtil.class.getClassLoader();
        URL resource = classLoader.getResource(resourcePath);
        if (resource == null) {
            throw new RuntimeException(format("Path: {%s} not found!", resourcePath));
        }
        return getContentAsJson(resource);
    }

    private JsonObject getContentAsJson(URL resource) {
        try {
            return JsonParser.parseString(IOUtils.toString(resource.openStream(), UTF_8)).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
