package com.justas.project.library.util;

import lombok.experimental.UtilityClass;

import java.text.DecimalFormat;

import static java.lang.Double.valueOf;

@UtilityClass
public class CalculationUtil {
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    /**
     * Process utilization number by rules we want to define. (e.g show only 2 numbers)
     *
     * @param numberToProcess - number we want to process and show to others.
     * @return processed number
     */
    public double processDouble(double numberToProcess) {
        return valueOf(
                DECIMAL_FORMAT.format(numberToProcess)
        );
    }
}
