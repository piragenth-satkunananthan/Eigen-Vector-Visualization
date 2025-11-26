package com.example.eigen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;


public class HelloController {
    @FXML
    private Pane pane;

    public void initialize() {

    }
    public void draw(){
        Line line = new Line(0,pane.getHeight(),200,pane.getHeight()-200);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);
        pane.getChildren().add(line);
    }

    public void CalculateEigen(){

    }
}
