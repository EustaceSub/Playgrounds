package com.justas.project.library.integration;

import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundType;
import com.justas.project.library.services.PlaygroundService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaygroundServiceLoadTest {
    private static Map<PlaygroundType, List<Playground>> playgrounds;
    private static final PlaygroundService playgroundService = new PlaygroundService();

    @BeforeClass
    public static void setUpPlaygroundService() {
        playgroundService.loadPlaygrounds();
        playgrounds = playgroundService.getPlaygrounds();
    }

    @Test
    public void playgroundsShouldBeLoadedCorrectly() {
        assertThat(playgrounds).isNotEmpty();
    }

    @Test
    public void ballPitsShouldBeLoadedCorrectly() {
        testPlaygroundSize(PlaygroundType.BALL_PIT, 2);
    }

    @Test
    public void swingsShouldBeLoadedCorrectly() {
        testPlaygroundSize(PlaygroundType.DOUBLE_SWINGS, 5);
    }

    @Test
    public void slidesShouldBeLoadedCorrectly() {
        testPlaygroundSize(PlaygroundType.SLIDE, 7);
    }

    @Test
    public void carouselShouldBeLoadedCorrectly() {
        testPlaygroundSize(PlaygroundType.CAROUSEL, 1);
    }

    private void testPlaygroundSize(PlaygroundType playgroundType, int size) {
        List<Playground> specificPlaygrounds = playgrounds.get(playgroundType);
        assertThat(specificPlaygrounds).isNotEmpty();
        assertThat(specificPlaygrounds.size()).isEqualTo(size);
    }
}
