запус программы через консоль:
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar forjson.json quick console result.json
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar forcsv.csv bubble console result.csv
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar text.txt selection console result.txt

есть возможность указать файл для загрузки, вариант сортировки и в какой файл результат будет записан

функция help
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar --help

Проверка ошибки алгоритма
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar forjson.json wrong console result.json

запуск программы
mvn javafx:run

запуск Swing
java -jar target/smakotina_kyrsovaya_java-1.0-SNAPSHOT.jar forjson.json quick swing result.json