package com.example.eigen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;


public class HelloController {
    @FXML
    private Pane pane;
    @FXML
    private TextField v1;
    @FXML
    private TextField v2;

    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField d;

    public void initialize() {
        Platform.runLater(() -> {
            manager();
        });
    }

    public void manager() {
//        pane.widthProperty().addListener((o,ov,nv) -> drawBaseAxis());
//        pane.heightProperty().addListener((o,ov,nv) -> drawBaseAxis());
        drawBaseAxis();


        v1.textProperty().addListener((o, ov, nv) -> {
            int vector1 = Integer.parseInt(v1.getText());
            int vector2 = Integer.parseInt(v2.getText());
            pane.getChildren().clear();
            draw(vector1, vector2);
        });

        v2.textProperty().addListener((o, ov, nv) -> {
            int vector1 = Integer.parseInt(v1.getText());
            int vector2 = Integer.parseInt(v2.getText());
            pane.getChildren().clear();
            draw(vector1, vector2);
        });

    }

    public void drawBaseAxis() {
        pane.setManaged(false);

        double widthPane = pane.getWidth();
        double heightPane = pane.getHeight();
//        System.out.println(heightPane);
//        System.out.println(widthPane);

        Line baseXaxis = new Line(0, heightPane, widthPane, heightPane);
        baseXaxis.setStroke(Color.BLACK);
        baseXaxis.setStrokeWidth(3);

        Line baseYaxis = new Line(0, 0, 0, heightPane);
        baseYaxis.setStroke(Color.BLACK);
        baseYaxis.setStrokeWidth(3);
        pane.getChildren().addAll(baseXaxis, baseYaxis);
    }

    public void draw(double vector1, double vector2) {
        drawBaseAxis();
        Line line = new Line(0, pane.getHeight(), vector1, pane.getHeight() - vector2);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);
        pane.getChildren().add(line);
    }

    public void CalculateEigen() {
        int ma = Integer.parseInt(a.getText());
        int mb = Integer.parseInt(b.getText());
        int mc = Integer.parseInt(c.getText());
        int md = Integer.parseInt(d.getText());

        float firstSec = ma+md;
        double secondSec =  Math.sqrt((ma+md)*(ma+md)-4*(ma*md-mb*mc));

        double firstVariation = (firstSec+secondSec)/2*ma;

        double secondVariation = (firstSec+(-1*(secondSec)))/2*ma;

        System.out.println(firstVariation);
        System.out.println(secondVariation);



    }
}
