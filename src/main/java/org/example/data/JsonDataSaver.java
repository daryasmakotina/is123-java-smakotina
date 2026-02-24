package org.example.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class JsonDataSaver implements DataSaver {

    @Override
    public void save(int[] array, String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(Arrays.toString(array));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи файла: " + filePath);
        }
    }
}