package org.example.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvDataLoader implements DataLoader {

    @Override
    public int[] load(String filePath) {

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Убираем пробелы и переносы строк
            content = content.replaceAll("\\s", "");

            String[] numbers = content.split(",");

            int[] array = new int[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                array[i] = Integer.parseInt(numbers[i]);
            }

            return array;

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения CSV файла: " + filePath);
        }
    }
}