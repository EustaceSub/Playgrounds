package com.justas.project.library.mapper;

import com.google.gson.JsonObject;

import java.util.Collection;


public interface Mapper<T> {
    /**
     * Maps Json object into custom object
     *
     * @param jsonObject
     * @return - collection of mapped objects
     */
    public Collection<T> mapJsonObject(JsonObject jsonObject);
}
