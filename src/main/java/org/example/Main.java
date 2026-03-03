package org.example;

import org.example.algorithm.*;
import org.example.visualization.*;
import org.example.data.*;
import org.example.factory.*;

public class Main {

    public static void main(String[] args) {

        try {

            // Режим помощи
            if (args.length == 1 && args[0].equals("--help")) {
                printHelp();
                return;
            }

            if (args.length != 4) {
                System.out.println("Ошибка: неверное количество аргументов.");
                printHelp();
                return;
            }

            String inputFile = args[0];
            String algorithmName = args[1];
            String visualizationType = args[2];
            String outputFile = args[3];

            // Загрузка данных
            DataLoader loader = DataLoaderFactory.create(inputFile);
            int[] data = loader.load(inputFile);

            // Выбор алгоритма
            SortAlgorithm algorithm = AlgorithmFactory.create(algorithmName);

            // Выбор визуализации
            SortObserver visualizer = VisualizerFactory.create(visualizationType);

            // Сортировка
            algorithm.sort(data, visualizer);

            // Сохранение результата
            DataSaver saver = DataSaverFactory.create(outputFile);
            saver.save(data, outputFile);

            System.out.println("Сортировка завершена.");

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }

    private static void printHelp() {
        System.out.println("Использование:");
        System.out.println("java -jar app.jar <input> <algorithm> <visualization> <output>");
        System.out.println();
        System.out.println("Пример:");
        System.out.println("java -jar app.jar forjson.json quick console result.json");
        System.out.println();
        System.out.println("Поддерживаемые форматы входа: .json .csv .txt");
        System.out.println("Поддерживаемые форматы выхода: .json .csv .txt");
        System.out.println("Алгоритмы: quick, bubble, selection");
        System.out.println("Визуализация: console");
    }
}