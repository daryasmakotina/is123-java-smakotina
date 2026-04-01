package org.example.visualization;

import org.example.algorithm.SortObserver;

import javax.swing.*;
import java.awt.*;

public class SwingVisualizer extends JPanel implements SortObserver {

    private int[] array;
    private int index1 = -1;
    private int index2 = -1;

    public SwingVisualizer(int[] data) {
        this.array = data;

        JFrame frame = new JFrame("Swing Sorting Visualizer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    public void update(int[] array, int i, int j) {

        this.array = array;
        this.index1 = i;
        this.index2 = j;

        repaint();

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (array == null) return;

        int width = getWidth() / array.length;
        int height = getHeight();

        int max = 1;
        for (int value : array) {
            if (value > max) max = value;
        }

        for (int i = 0; i < array.length; i++) {

            int barHeight = (array[i] * height) / max;

            if (i == index1 || i == index2) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }

            g.fillRect(
                    i * width,
                    height - barHeight,
                    width - 2,
                    barHeight
            );
        }
    }
}