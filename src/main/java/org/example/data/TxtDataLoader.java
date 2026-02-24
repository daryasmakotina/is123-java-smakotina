package org.example.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TxtDataLoader implements DataLoader {

    @Override
    public int[] load(String filePath) {

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Удаляем BOM
            content = content.replace("\uFEFF", "");

            content = content.trim();

            String[] numbers = content.split(",");

            List<Integer> list = new ArrayList<>();

            for (String num : numbers) {
                if (!num.trim().isEmpty()) {
                    list.add(Integer.parseInt(num.trim()));
                }
            }

            return list.stream().mapToInt(Integer::intValue).toArray();

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения TXT файла: " + filePath);
        }
    }
}