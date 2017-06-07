package fr.univ_amu.iut.exercice5;

import fr.univ_amu.iut.exercice3.TriangleArea;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.*;

import static java.awt.Color.GRAY;


public class TriangleAreaCalculatorAndDrawer extends Application {
    private TriangleArea triangleArea = new TriangleArea();

    private Slider x1Slider = new Slider(0, 10, 0);
    private Slider x2Slider = new Slider(0, 10, 0);
    private Slider x3Slider = new Slider(0, 10, 0);

    private Slider y1Slider = new Slider();
    private Slider y2Slider = new Slider();
    private Slider y3Slider = new Slider();

    private Label x1Label = new Label("X1 :");
    private Label x2Label = new Label("X2 :");
    private Label x3Label = new Label("X3 :");

    private Label y1Label = new Label("Y1 :");
    private Label y2Label = new Label("Y2 :");
    private Label y3Label = new Label("Y3 :");

    private Label areaLabel = new Label("Area :");
    private TextField areaTextField = new TextField();

    private Line p1p2 = new Line();
    private Line p2p3 = new Line();
    private Line p3p1 = new Line();

    private Pane drawPane = new Pane();

    private GridPane root = new GridPane();

    @Override
    public void start(Stage stage) throws Exception {
        configGridPane();
        configSliders();
        addSliders();
        addArea();
        addPointLabels();
        addDrawPane();
        createBinding();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Triangle Area Calculator");
        stage.show();
    }

    private void configSliders() {
        configSlider(x1Slider);
        configSlider(y1Slider);
        configSlider(x2Slider);
        configSlider(y2Slider);
        configSlider(x3Slider);
        configSlider(y3Slider);
    }

    private void addDrawPane() {
        drawPane.setPrefSize(500, 500);
        drawPane.setStyle("-fx-background-color: lightslategrey");
        root.add(drawPane, 0, 11, 2, 1);
        drawPane.getChildren().addAll(p1p2, p2p3, p3p1);
    }

    private static void configSlider(Slider slider) {
        slider.setMin(0);
        slider.setMax(10);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(5);
        slider.setBlockIncrement(1);
        slider.setMinorTickCount(4);
        slider.setSnapToTicks(true);
    }

    private void configGridPane() {
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(10);
        root.setVgap(10);

        ColumnConstraints col1 = new ColumnConstraints(50, 50, 50);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setFillWidth(true);
        col2.setHgrow(Priority.ALWAYS);

        root.getColumnConstraints().addAll(col1,col2);

    }

    private void addArea() {
        root.add(areaLabel, 0, 10);
        root.add(areaTextField, 1, 10);
    }

    private void addSliders() {
        root.add(x1Label, 0, 2);
        root.add(x1Slider, 1, 2);

        root.add(y1Slider, 1, 3);
        root.add(y1Label, 0, 3);

        root.add(x2Slider, 1, 5);
        root.add(x2Label, 0, 5);

        root.add(y2Slider, 1, 6);
        root.add(y2Label, 0, 6);

        root.add(x3Slider, 1, 8);
        root.add(x3Label, 0, 8);

        root.add(y3Slider, 1, 9);
        root.add(y3Label, 0, 9);
    }

    private void addPointLabels() {
        Label labelP1 = new Label("P1");
        root.add(labelP1, 1, 1);

        Label labelP2 = new Label("P2");
        root.add(labelP2, 1, 4);

        Label labelP3 = new Label("P3");
        root.add(labelP3, 1, 7);

        root.getColumnConstraints().get(1).setHalignment(HPos.CENTER);
    }

    private void createBinding() {
        triangleArea.x1Property().bind(x1Slider.valueProperty());
        triangleArea.x2Property().bind(x2Slider.valueProperty());
        triangleArea.x3Property().bind(x3Slider.valueProperty());
        triangleArea.y1Property().bind(y1Slider.valueProperty());
        triangleArea.y2Property().bind(y2Slider.valueProperty());
        triangleArea.y3Property().bind(y3Slider.valueProperty());

        areaTextField.textProperty().bind(triangleArea.areaProperty().asString());

        p1p2.startXProperty().bind(triangleArea.x1Property().multiply(50));
        p1p2.startYProperty().bind(triangleArea.y1Property().multiply(50));
        p1p2.endXProperty().bind(triangleArea.x2Property().multiply(50));
        p1p2.endYProperty().bind(triangleArea.y2Property().multiply(50));

        p2p3.startXProperty().bind(triangleArea.x2Property().multiply(50));
        p2p3.startYProperty().bind(triangleArea.y2Property().multiply(50));
        p2p3.endXProperty().bind(triangleArea.x3Property().multiply(50));
        p2p3.endYProperty().bind(triangleArea.y3Property().multiply(50));

        p3p1.startXProperty().bind(triangleArea.x3Property().multiply(50));
        p3p1.startYProperty().bind(triangleArea.y3Property().multiply(50));
        p3p1.endXProperty().bind(triangleArea.x1Property().multiply(50));
        p3p1.endYProperty().bind(triangleArea.y1Property().multiply(50));

    }

    public static void main (String [] args)
    {
        launch(args);
    }

}
