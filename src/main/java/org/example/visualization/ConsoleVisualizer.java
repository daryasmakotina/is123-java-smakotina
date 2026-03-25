package org.example.visualization;

import org.example.algorithm.SortObserver;
import java.util.Arrays;

public class ConsoleVisualizer implements SortObserver {

    @Override
    public void update(int[] array) {
        System.out.println(Arrays.toString(array));

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}