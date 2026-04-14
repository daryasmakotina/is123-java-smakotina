package org.example.visualization;

import org.example.algorithm.SortObserver;
import java.util.Arrays;

public class ConsoleVisualizer implements SortObserver {

    @Override
    public void update(int[] array, int i, int j) {

        clearConsole();

        for (int k = 0; k < array.length; k++) {

            if (k == i || k == j) {
                // красный цвет для сравниваемых элементов
                System.out.print("\u001B[31m" + array[k] + "\u001B[0m ");
            } else {
                System.out.print(array[k] + " ");
            }
        }

        System.out.println();

        drawBars(array, i, j);

        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}
    }

    private void drawBars(int[] array, int i, int j) {

        int max = java.util.Arrays.stream(array).max().orElse(1);

        for (int level = max; level > 0; level--) {

            for (int k = 0; k < array.length; k++) {

                if (array[k] >= level) {

                    if (k == i || k == j) {
                        System.out.print("\u001B[31m█\u001B[0m ");
                    } else {
                        System.out.print("█ ");
                    }

                } else {
                    System.out.print("  ");
                }

            }

            System.out.println();
        }

        System.out.println();
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}