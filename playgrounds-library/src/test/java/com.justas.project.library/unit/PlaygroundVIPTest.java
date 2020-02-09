package com.justas.project.library.unit;

import com.justas.project.library.TestUtil;
import com.justas.project.library.model.playground.BallPit;
import org.junit.Test;

import java.util.function.IntConsumer;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PlaygroundVIPTest extends TestUtil {
    private final BallPit slide = new BallPit(0);

    private final IntConsumer addCommonChildren = (n) -> {
        for (int i = 0; i < n; i++) {
            slide.addChildIntoPlayground(generateCommonChild.get());
        }
    };
    private final IntConsumer addVipChild = (skipBy) -> slide.addVipIntoQueue(generateVIPChild.get(), skipBy);

    @Test
    public void vipChildWithMaxSkipShouldGoIntoFirstPosition() {
        addCommonChildren.accept(5);
        addVipChild.accept(5);
        assertThat(slide.getCurrentQueue().get(0).getTicket().isVip()).isTrue();
    }

    @Test
    public void vipChildWithMaxSkipAndDoNotBeInFirstPosition() {
        addCommonChildren.accept(5);
        addVipChild.accept(2);
        assertThat(slide.getCurrentQueue().get(3).getTicket().isVip()).isTrue();
    }

    @Test
    public void vipChildWithTooManySkipsShouldGoIntoFirstPositionAndNotThrowException() {
        addCommonChildren.accept(5);
        addVipChild.accept(1000);
        assertThat(slide.getCurrentQueue().get(0).getTicket().isVip()).isTrue();
    }


    @Test
    public void twoVipsShouldKeepBalanceDistance() {
        addCommonChildren.accept(5);
        addVipChild.accept(1000);
        addVipChild.accept(1000);
        assertThat(slide.getCurrentQueue().get(0).getTicket().isVip()).isTrue();
        assertThat(slide.getCurrentQueue().get(4).getTicket().isVip()).isTrue();
    }

    @Test
    public void twoVipsShouldIgnoreBalanceIfTheyDoNotReachBalance() {
        addCommonChildren.accept(10);
        addVipChild.accept(1000);
        addVipChild.accept(1);
        assertThat(slide.getCurrentQueue().get(0).getTicket().isVip()).isTrue();
        assertThat(slide.getCurrentQueue().get(10).getTicket().isVip()).isTrue();
    }

    @Test
    public void twoVipsWithoutSkipsShouldBeAtTheEnd() {
        addCommonChildren.accept(10);
        addVipChild.accept(0);
        addVipChild.accept(0);
        assertThat(slide.getCurrentQueue().get(10).getTicket().isVip()).isTrue();
        assertThat(slide.getCurrentQueue().get(11).getTicket().isVip()).isTrue();
    }

}
