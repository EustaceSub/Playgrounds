package com.justas.project.library.unit;

import com.justas.project.library.TestUtil;
import com.justas.project.library.model.playground.BallPit;
import com.justas.project.library.model.playground.Carousel;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.Slide;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaygroundAddRemoveChildrenTest extends TestUtil {

    @Test
    public void shouldBeAbleToAddChildIntoSlide() {
        testWithoutWaitingQueue(new Slide(), 1);
    }

    @Test
    public void shouldBeNOTAbleToAddTooManyChildsIntoSlide() {
        testWithWaitingQueue(new Slide(), 2);
    }

    @Test
    public void shouldBeAbleToAddChildIntoBallPit() {
        testWithoutWaitingQueue(new BallPit(5), 5);
    }

    @Test
    public void shouldBeNOTAbleToAddTooManyChildsIntoBallPit() {
        testWithWaitingQueue(new BallPit(1), 2);
    }

    @Test
    public void shouldBeAbleToAddChildIntoCarousel() {
        testWithoutWaitingQueue(new BallPit(5), 5);
    }

    @Test
    public void shouldBeNOTAbleToAddTooManyChildsIntoCarousel() {
        testWithWaitingQueue(new BallPit(1), 2);
    }

    @Test
    public void shouldBeAbleToAddChildIntoDoubleSwings() {
        testWithoutWaitingQueue(new Carousel(5), 5);
    }

    @Test
    public void shouldBeNOTAbleToAddTooManyChildsIntoDoubleSwings() {
        testWithWaitingQueue(new Carousel(3), 8);
    }

    private void testWithoutWaitingQueue(Playground playground, int slots) {
        for (int i = 0; i < slots; i++) {
            boolean isAdded1 = playground.addChildIntoPlayground(generateCommonChild.get());
            assertThat(isAdded1).isTrue();

        }
        assertThat(playground.getCurrentKids()).isNotEmpty();
        assertThat(playground.getCurrentKids().size()).isEqualTo(slots);
        assertThat(playground.getCurrentQueue()).isEmpty();
    }

    private void testWithWaitingQueue(Playground playground, int howManyToAdd) {
        for (int i = 0; i < howManyToAdd; i++) {
            playground.addChildIntoPlayground(generateCommonChild.get());

        }
        assertThat(playground.getCurrentKids()).isNotEmpty();
        assertThat(playground.getCurrentQueue()).isNotEmpty();
        assertThat(playground.getCurrentQueue().size()).isEqualTo(howManyToAdd - playground.getMaxSlots());
    }

}
