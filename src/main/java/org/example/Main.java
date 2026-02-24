package org.example;

import org.example.algorithm.*;
import org.example.visualization.*;
import org.example.data.*;
import org.example.factory.*;
import org.example.factory.DataLoaderFactory;

public class Main {

    public static void main(String[] args) {

        if (args.length != 4) {
            System.out.println("Использование:");
            System.out.println("java -jar sortapp.jar <input> <algorithm> <visualization> <output>");
            return;
        }

        String inputFilePath = args[0];
        String algorithmName = args[1];
        String visualizationType = args[2];
        String outputFilePath = args[3];

        // Загрузка данных
        DataLoader loader = DataLoaderFactory.create(inputFilePath);
        int[] data = loader.load(inputFilePath);

        // Выбор алгоритма и визуализации
        SortAlgorithm algorithm = AlgorithmFactory.create(algorithmName);
        SortObserver visualizer = VisualizerFactory.create(visualizationType);

        // Сортировка
        algorithm.sort(data, visualizer);

        // СОХРАНЕНИЕ РЕЗУЛЬТАТА
        DataSaver saver = new JsonDataSaver();
        saver.save(data, outputFilePath);

        System.out.println("Сортировка завершена.");
        System.out.println("Результат сохранён в файл: " + outputFilePath);
    }
}