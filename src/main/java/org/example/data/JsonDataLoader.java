package org.example.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDataLoader implements DataLoader {

    @Override
    public int[] load(String filePath) {

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            content = content.replaceAll("\\[|\\]|\\s", "");
            String[] numbers = content.split(",");

            int[] array = new int[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                array[i] = Integer.parseInt(numbers[i]);
            }

            return array;

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath);
        }
    }
}