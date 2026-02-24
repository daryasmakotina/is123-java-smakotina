package org.example.factory;

import org.example.algorithm.*;

public class AlgorithmFactory {

    public static SortAlgorithm create(String name) {

        switch (name.toLowerCase()) {
            case "bubble":
                return new BubbleSort();
            case "selection":
                return new SelectionSort();
            case "quick":
                return new QuickSort();
            default:
                throw new IllegalArgumentException("Неизвестный алгоритм: " + name);
        }
    }
}