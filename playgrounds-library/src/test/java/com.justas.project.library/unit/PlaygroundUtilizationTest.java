package com.justas.project.library.unit;

import com.justas.project.library.TestUtil;
import com.justas.project.library.model.playground.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.function.IntConsumer;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class PlaygroundUtilizationTest extends TestUtil {

    @Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {
                        new DoubleSwings(), 0, 0.0
                },
                {
                        new DoubleSwings(), 1, 0.0
                },
                {
                        new DoubleSwings(), 2, 100.0
                },
                {
                        new DoubleSwings(), 4, 100.0
                },
                {
                        new Slide(), 0, 0.0
                },
                {
                        new Slide(), 1, 100.0
                },
                {
                        new Slide(), 4, 100.0
                },
                {
                        new Carousel(0), 0, 100.0
                },
                {
                        new Carousel(1), 1, 100.0
                },
                {
                        new Carousel(5), 4, 80.0
                },
                {
                        new Carousel(9), 2, 22.22
                },
                {
                        new BallPit(0), 0, 100.0
                },
                {
                        new BallPit(1), 1, 100.0
                },
                {
                        new BallPit(5), 4, 80.0
                },
                {
                        new BallPit(9), 2, 22.22
                }
        });
    }

    @Parameter
    public Playground playground;

    @Parameter(1)
    public int childrenToAdd;

    @Parameter(2)
    public double expectedUtility;

    private IntConsumer addChildren = (n) -> {
        for (int i = 0; i < n; i++) {
            playground.addChildIntoPlayground(generateCommonChild.get());
        }
    };

    @Test
    public void testPlaygroundUtilization() {
        addChildren.accept(childrenToAdd);
        assertThat(playground.calculateAndReturnUtilization()).isEqualTo(expectedUtility);
    }


}
