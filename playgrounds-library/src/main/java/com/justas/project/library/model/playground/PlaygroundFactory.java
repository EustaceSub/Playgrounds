package com.justas.project.library.model.playground;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;


public class PlaygroundFactory {
    /**
     * Creates One type of Playgrounds.
     * If Playground must to have Attributes - takes it from jsonElement.
     *
     * @param jsonElement    - object that contains required information about Playground.
     * @param playgroundType - What kind of Playground to create.
     * @return Collection of one type of Playground with attributes.
     */
    public static List<Playground> createPlaygroundsFromJsonElement(JsonElement jsonElement, PlaygroundType playgroundType) {
        List<Playground> playgrounds = new ArrayList<>();

        if (!playgroundType.isHasAttributes()) {
            int howManyToCreate = jsonElement.getAsJsonObject().get("amount").getAsInt();
            for (int i = 0; i < howManyToCreate; i++) {
                playgrounds.add(PlaygroundFactory.createPlaygroundWithNoParams(playgroundType));
            }
        } else {
            for (JsonElement params : jsonElement.getAsJsonArray()) {
                int maxSpots = params.getAsJsonObject().get("max_spots").getAsInt();
                playgrounds.add(PlaygroundFactory.createPlaygroundWithParams(playgroundType, maxSpots));
            }
        }
        return playgrounds;
    }


    private static Playground createPlaygroundWithNoParams(PlaygroundType playgroundType) {
        switch (playgroundType) {
            case SLIDE:
                return new Slide();
            case DOUBLE_SWINGS:
                return new DoubleSwings();
            default:
                throw new RuntimeException("Bad Playground type for this method..");
        }
    }

    private static Playground createPlaygroundWithParams(PlaygroundType playgroundType, int maxSlots) {
        switch (playgroundType) {
            case CAROUSEL:
                return new Carousel(maxSlots);
            case BALL_PIT:
                return new BallPit(maxSlots);
            default:
                throw new RuntimeException("Bad Playground type for this method..");
        }
    }

}
