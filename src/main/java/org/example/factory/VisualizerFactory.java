package org.example.factory;

import org.example.algorithm.SortObserver;
import org.example.visualization.ConsoleVisualizer;
import org.example.visualization.SwingVisualizer;

public class VisualizerFactory {

    public static SortObserver create(String type, int[] data) {

        switch (type.toLowerCase()) {
            case "console":
                return new ConsoleVisualizer();
            case "swing":
                return new SwingVisualizer(data);
            default:
                throw new IllegalArgumentException("Неизвестный тип визуализации: " + type);
        }
    }
}