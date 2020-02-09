package com.justas.project.library.mapper;

import com.google.gson.JsonObject;

import java.util.Collection;

@FunctionalInterface
interface Mapper<T> {
    Collection<T> mapJsonObject(JsonObject jsonObject);
}
