package org.example.visualization;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import org.example.algorithm.*;
import org.example.factory.*;
import org.example.data.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFxApp extends Application implements SortObserver {

    private int[] data;
    private String outputPath;

    private Pane drawPane = new Pane();
    private List<Rectangle> bars = new ArrayList<>();

    private Slider speedSlider = new Slider(1, 100, 50);

    @Override
    public void start(Stage stage) {

        Button fileButton = new Button("Выбрать входной файл");
        Button saveButton = new Button("Выбрать выходной файл");

        TextField outputField = new TextField();
        outputField.setPromptText("Файл для сохранения результата");

        ComboBox<String> algorithmBox = new ComboBox<>();
        Button startButton = new Button("Начать сортировку");
        fileButton.setStyle("-fx-font-size:14px;");
        saveButton.setStyle("-fx-font-size:14px;");
        startButton.setStyle(
                "-fx-background-color:#4CAF50;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-weight:bold;"
        );

        Label speedLabel = new Label("Скорость");
        drawPane.setPrefSize(800, 500);
        drawPane.setStyle(
                "-fx-background-color:#f4f4f4;" +
                        "-fx-border-color:#cccccc;" +
                        "-fx-border-width:1;"
        );

        algorithmBox.getItems().addAll("quick", "bubble", "selection");
        algorithmBox.setValue("quick");

        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);

        fileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выбор файла");

            File file = fileChooser.showOpenDialog(stage);

            if (file != null) {
                DataLoader loader = DataLoaderFactory.create(file.getName());
                data = loader.load(file.getAbsolutePath());
                drawArray(data, -1, -1);
            }
        });

        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Сохранить файл");

            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                outputPath = file.getAbsolutePath();
                outputField.setText(outputPath);
            }
        });

        startButton.setOnAction(e -> {

            if (data == null) {
                showAlert("Сначала выберите входной файл!");
                return;
            }

            if (outputPath == null || outputPath.isEmpty()) {
                showAlert("Укажите выходной файл!");
                return;
            }

            String algorithmName = algorithmBox.getValue();
            SortAlgorithm algorithm = AlgorithmFactory.create(algorithmName);

            new Thread(() -> {
                algorithm.sort(data, this);

                DataSaver saver = DataSaverFactory.create(outputPath);
                saver.save(data, outputPath);

                Platform.runLater(() ->
                        showAlert("Сортировка завершена и сохранена!")
                );

            }).start();
        });

        BorderPane root = new BorderPane();

        Label title = new Label("Sorting Visualizer");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox topBar = new HBox(10, fileButton, saveButton, algorithmBox, startButton);
        topBar.setStyle("-fx-padding: 10; -fx-alignment: center;");

        HBox speedBar = new HBox(10, speedLabel, speedSlider);
        speedBar.setStyle("-fx-padding: 10; -fx-alignment: center;");

        VBox controls = new VBox(5, title, topBar, speedBar, outputField);
        controls.setStyle("-fx-padding: 10; -fx-alignment: center;");

        root.setTop(controls);
        root.setCenter(drawPane);

        Scene scene = new Scene(root, 900, 650);

        stage.setTitle("Sorting Visualizer");
        stage.setScene(scene);
        stage.show();
    }

    private void drawArray(int[] array, int index1, int index2) {

        Platform.runLater(() -> {

            drawPane.getChildren().clear();
            bars.clear();

            double paneWidth = drawPane.getWidth() > 0 ? drawPane.getWidth() : 800;
            double paneHeight = drawPane.getHeight() > 0 ? drawPane.getHeight() : 500;
            double topPadding = 40; // пространство сверху
            paneHeight -= topPadding;

            double width = paneWidth / array.length;

            int max = java.util.Arrays.stream(array).max().orElse(1);

            for (int i = 0; i < array.length; i++) {

                double height = (array[i] * paneHeight) / max;

                Rectangle rect = new Rectangle(
                        i * width,
                        paneHeight - height + topPadding,
                        width - 4,
                        height
                );

                //цвета
                if (i == index1 || i == index2) {
                    rect.setFill(Color.web("#ff6b6b")); // мягкий красный
                } else {
                    rect.setFill(Color.web("#5c7cfa")); // мягкий синий
                }

                bars.add(rect);

                // текст числа
                Text text = new Text(
                        i * width + width / 4,
                        paneHeight - height + topPadding - 5,
                        String.valueOf(array[i])
                );

                drawPane.getChildren().addAll(rect, text);
            }
        });
    }

    @Override
    public void update(int[] array, int i, int j) {
        drawArray(array, i, j);

        try {
            Thread.sleep((long) ((101 - speedSlider.getValue()) * 5));
        } catch (InterruptedException ignored) {}
    }

    private void showAlert(String text) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
            alert.showAndWait();
        });
    }
}