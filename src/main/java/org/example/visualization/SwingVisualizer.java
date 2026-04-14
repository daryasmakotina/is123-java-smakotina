package org.example.visualization;

import org.example.algorithm.SortObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SwingVisualizer extends JFrame implements SortObserver {

    private int[] array;
    private int index1 = -1;
    private int index2 = -1;

    private final DrawPanel panel = new DrawPanel();

    public SwingVisualizer(int[] data) {

        this.array = data;

        setTitle("Sorting Visualizer (Swing)");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(panel);

        setVisible(true);
    }

    @Override
    public void update(int[] array, int i, int j) {

        this.array = array;
        this.index1 = i;
        this.index2 = j;

        panel.repaint();

        try {
            Thread.sleep(80);
        } catch (InterruptedException ignored) {}
    }

    class DrawPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            if (array == null) return;

            int width = getWidth();
            int height = getHeight();

            int barWidth = width / array.length;

            int max = Arrays.stream(array).max().orElse(1);

            for (int i = 0; i < array.length; i++) {

                int barHeight = (array[i] * (height - 50)) / max;

                int x = i * barWidth;
                int y = height - barHeight;

                if (i == index1 || i == index2) {
                    g.setColor(new Color(169, 107, 255)); // мягкий красный
                } else {
                    g.setColor(new Color(92, 129, 250)); // мягкий синий
                }

                g.fillRect(x, y, barWidth - 2, barHeight);

                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(array[i]), x + barWidth / 4, y - 5);
            }
        }
    }
}