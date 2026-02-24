package org.example.factory;

import org.example.algorithm.SortObserver;
import org.example.visualization.ConsoleVisualizer;

public class VisualizerFactory {

    public static SortObserver create(String type) {

        switch (type.toLowerCase()) {
            case "console":
                return new ConsoleVisualizer();
            default:
                throw new IllegalArgumentException("Неизвестный тип визуализации: " + type);
        }
    }
}