package org.example.data;

import java.io.FileWriter;
import java.io.IOException;

public class TxtDataSaver implements DataSaver {

    @Override
    public void save(int[] array, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < array.length; i++) {
                writer.write(Integer.toString(array[i]));
                if (i != array.length - 1) writer.write(",");
            }
            System.out.println("Результат сохранён в файл: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи TXT файла: " + filePath);
        }
    }
}