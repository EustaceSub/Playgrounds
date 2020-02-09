package com.justas.project.library.integration;

import com.justas.project.library.TestUtil;
import com.justas.project.library.model.playground.*;
import com.justas.project.library.services.PlaygroundService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaygroundServiceCalculateChildrenTest extends TestUtil {
    private final PlaygroundService playgroundService = new PlaygroundService();

    @Test
    public void calculateMultipleEmptyPlaygroundChildren() {
        playgroundService.registerPlayground(new DoubleSwings());
        playgroundService.registerPlayground(new Slide());
        playgroundService.registerPlayground(new BallPit(5));
        playgroundService.registerPlayground(new Carousel(8));
        assertThat(playgroundService.calculateVisitorsCount()).isEqualTo(0);
    }

    @Test
    public void calculateSinglePlaygroundChildren() {
        Playground swings = new DoubleSwings();
        playgroundService.registerPlayground(swings);

        swings.addChildIntoPlayground(generateCommonChild.get());
        swings.addChildIntoPlayground(generateCommonChild.get());
        assertThat(playgroundService.calculateVisitorsCount()).isEqualTo(2);
    }

    @Test
    public void calculateSamePlaygroundChildren() {
        Playground swings1 = new DoubleSwings();
        Playground swings2 = new DoubleSwings();

        playgroundService.registerPlayground(swings1);
        playgroundService.registerPlayground(swings2);

        swings1.addChildIntoPlayground(generateCommonChild.get());
        swings2.addChildIntoPlayground(generateCommonChild.get());
        assertThat(playgroundService.calculateVisitorsCount()).isEqualTo(2);
    }

    @Test
    public void calculateMultiplePlaygroundChildren() {
        Playground swings1 = new Slide();
        Playground swings2 = new DoubleSwings();
        Playground swings3 = new Carousel(800);
        Playground swings4 = new BallPit(700);

        playgroundService.registerPlayground(swings1);
        playgroundService.registerPlayground(swings2);
        playgroundService.registerPlayground(swings3);
        playgroundService.registerPlayground(swings4);

        for (int i = 0; i < 1000; i++) {
            swings1.addChildIntoPlayground(generateCommonChild.get());
            swings2.addChildIntoPlayground(generateCommonChild.get());
            swings3.addChildIntoPlayground(generateCommonChild.get());
            swings4.addChildIntoPlayground(generateCommonChild.get());
        }
        assertThat(playgroundService.calculateVisitorsCount()).isEqualTo(1503);
    }
}
