package com.example.eigen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;

import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;


public class HelloController {
    @FXML
    private  Pane pane;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField d;
    public static double scalar = 40;

    public void initialize() {

        Platform.runLater(() -> {
            drawBaseAxis();
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
        baseXaxis.setStrokeWidth(1);

        Line baseYaxis = new Line(0, 0, 0, heightPane);
        baseYaxis.setStroke(Color.BLACK);
        baseYaxis.setStrokeWidth(1);
        pane.getChildren().addAll(baseXaxis, baseYaxis);
    }

    public void draw(double vector1, double vector2) {
        pane.getChildren().clear();
        drawBaseAxis();
        Vector vector = new Vector(vector1,vector2);
//        Line line = new Line(0, pane.getHeight(), vector1*scalar, pane.getHeight() - vector2*scalar);
//        line.setStroke(Color.BLUE);
//        line.setStrokeWidth(1);

        double angle = Math.toDegrees(Math.atan2(-vector2,vector1));
        System.out.println(angle);
//        vector.setTranslateX(0);
        vector.setTranslateY(pane.getHeight());
//        vector.setRotate(angle);

        Rotate rotate = new Rotate(angle,vector1,vector2);
        vector.getTransforms().add(rotate);
        pane.getChildren().add(vector);
    }

    public void CalculateEigen() {
        int ma = Integer.parseInt(a.getText());
        int mb = Integer.parseInt(b.getText());
        int mc = Integer.parseInt(c.getText());
        int md = Integer.parseInt(d.getText());

//        int ma = 1;
//        int mb = 2;
//        int mc = 0;
//        int md = 1;
        double checkValid = (ma + md) * (ma + md) - 4 * ma * md - mb * mc;

        if (checkValid < 0) {
            System.out.println("No Eigen Vector in this transfomation");
        } else {
            double firstSec = ma + md;
            double secondSec = Math.sqrt((ma + md) * (ma + md) - 4 * (ma * md - mb * mc));

            double eigenValue1 = (firstSec + secondSec) / (2 * ma);

            double eigenValue2 = (firstSec + (-1 * (secondSec))) / (2 * ma);

            System.out.println(eigenValue1);
            System.out.println(eigenValue2);

            double x1=1, x2, y1 = 1, y2;

            x2 = -1*(ma - eigenValue1)/mb * x1;

            draw(x1, x2);

        }

    }

    public static class Vector extends Group {

        Vector(double vector1, double vector2){

            Line lineT = new Line(vector1*scalar,5,(vector1*scalar)-6,0);
            Line lineM = new Line(0,0,vector1*scalar,5);
            Line lineD = new Line(vector1*scalar,5,(vector1*scalar)-6,10);



            lineT.setStroke(Color.BLUE);
            lineD.setStroke(Color.BLUE);
            lineM.setStroke(Color.BLUE);

            lineT.setStrokeWidth(1);
            lineD.setStrokeWidth(1);
            lineM.setStrokeWidth(1);

            getChildren().addAll(lineT,lineM, lineD);
        }
    }
}

