Описание проекта

Программа предназначена для визуализации алгоритмов сортировки.
Данные загружаются из файла, сортируются в оперативной памяти и отображаются различными способами визуализации.

Основные возможности
Загрузка данных из файлов:
    .txt
    .csv
    .json

Алгоритмы сортировки:
    Bubble Sort
    Quick Sort
    Selection Sort

Варианты визуализации:
    Консольная (Console)
    Графическая (JavaFX)
    Графическая (Swing)

Сохранение результата в файл

Настройки:
    выбор алгоритма
    выбор визуализации
    скорость анимации (JavaFX)

Архитектура проекта
    algorithm — алгоритмы сортировки
    visualization — визуализация
    data / storage — работа с файлами
    factory — создание объектов (Factory Pattern)

Используемые паттерны
    Factory — для выбора алгоритма, визуализации и работы с файлами
    Observer — для обновления визуализации во время сортировки

Логика работы
1. Пользователь выбирает входной файл
2. Данные загружаются в массив (int[])
3. Выбирается алгоритм сортировки
4. Во время сортировки вызывается визуализация
5. Результат сохраняется в файл

Запуск проекта
🔹 1. Сборка
mvn clean package
🔹 2. Запуск (консоль / Swing)
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar forjson.json quick swing output.json
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar forjson.json quick console result.json
🔹 3. Запуск JavaFX
mvn javafx:run

Тестирование
mvn test

Покрытие кода
mvn verify

Статический анализ
mvn checkstyle:check

Пример входных данных
[5, 3, 8, 1, 2]

функция help
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar --help

Проверка ошибки алгоритма
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar forjson.json wrong console result.json

