package org.example.visualization;

import org.example.algorithm.*;
import org.example.factory.*;
import org.example.data.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class SwingVisualizer extends JFrame implements SortObserver {

    private int[] data;
    private String outputPath;

    private final DrawPanel drawPanel = new DrawPanel();

    private JComboBox<String> algorithmBox;
    private JSlider speedSlider;

    private int index1 = -1;
    private int index2 = -1;

    public SwingVisualizer() {

        setTitle("Sorting Visualizer (Swing)");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();

        setVisible(true);
    }

    private void initUI() {

        JButton loadButton = new JButton("Выбрать файл");
        JButton saveButton = new JButton("Сохранить результат");
        JButton startButton = new JButton("Старт");

        algorithmBox = new JComboBox<>(new String[]{"quick", "bubble", "selection"});

        speedSlider = new JSlider(1, 100, 50);
        speedSlider.setMajorTickSpacing(20);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        JPanel topPanel = new JPanel();
        topPanel.add(loadButton);
        topPanel.add(saveButton);
        topPanel.add(algorithmBox);
        topPanel.add(startButton);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Скорость"));
        controlPanel.add(speedSlider);

        drawPanel.setPreferredSize(new Dimension(800, 500));
        drawPanel.setBackground(new Color(244, 244, 244));

        add(topPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // загрузка файла
        loadButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                DataLoader loader = DataLoaderFactory.create(file.getName());
                data = loader.load(file.getAbsolutePath());

                repaint();
            }
        });

        //сохранение
        saveButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                outputPath = chooser.getSelectedFile().getAbsolutePath();
            }
        });

        //запуск сортировки
        startButton.addActionListener(e -> {

            if (data == null) {
                JOptionPane.showMessageDialog(this, "Сначала выберите файл");
                return;
            }

            if (outputPath == null) {
                JOptionPane.showMessageDialog(this, "Укажите файл для сохранения");
                return;
            }

            String algorithmName = (String) algorithmBox.getSelectedItem();
            SortAlgorithm algorithm = AlgorithmFactory.create(algorithmName);

            new Thread(() -> {
                algorithm.sort(data, this);

                DataSaver saver = DataSaverFactory.create(outputPath);
                saver.save(data, outputPath);

                JOptionPane.showMessageDialog(this, "Готово!");
            }).start();
        });
    }

    @Override
    public void update(int[] array, int i, int j) {
        this.data = array;
        this.index1 = i;
        this.index2 = j;

        repaint();

        try {
            Thread.sleep((long) ((101 - speedSlider.getValue()) * 5));
        } catch (InterruptedException ignored) {}
    }

    class DrawPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (data == null) return;

            int width = getWidth();
            int height = getHeight();

            int barWidth = width / data.length;

            int max = Arrays.stream(data).max().orElse(1);

            for (int i = 0; i < data.length; i++) {

                int barHeight = (data[i] * (height - 50)) / max;

                int x = i * barWidth;
                int y = height - barHeight;

                if (i == index1 || i == index2) {
                    g.setColor(new Color(255, 107, 107));
                } else {
                    g.setColor(new Color(92, 124, 250));
                }

                g.fillRect(x, y, barWidth - 4, barHeight);

                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(data[i]), x + barWidth / 4, y - 5);
            }
        }
    }
}