package com.example.eigen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;


public class HelloController {
    @FXML
    private Pane pane;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField d;

    public static double scalar = 100;

    public void initialize() {

        Platform.runLater(() -> manger());
    }

    public void manger() {
        drawBaseAxis();
        int ma = Integer.parseInt(a.getText());
        int mb = Integer.parseInt(b.getText());
        int mc = Integer.parseInt(c.getText());
        int md = Integer.parseInt(d.getText());
        CalculateEigenValue(ma, mb, mc, md);
    }

    public void drawBaseAxis() {
        pane.setManaged(false);

        double widthPane = pane.getWidth();
        double heightPane = pane.getHeight();
        Label x_left = new Label("(-"+String.valueOf(widthPane/2)+")");
        Label x_right = new Label("("+String.valueOf(widthPane/2)+")");

        Label y_top = new Label("("+String.valueOf(heightPane/2)+")");
        Label y_down = new Label("(-"+String.valueOf(heightPane/2)+")");

//        System.out.println(heightPane);
//        System.out.println(widthPane);

        Line baseXaxis = new Line(0, heightPane/2, widthPane, heightPane/2);
        x_left.setTranslateY(heightPane/2);
        x_right.setTranslateY(heightPane/2);
        x_right.setTranslateX(widthPane);

        y_top.setTranslateX(widthPane/2);
        y_down.setTranslateX(widthPane/2);
        y_down.setTranslateY(heightPane);
        baseXaxis.setStroke(Color.BLACK);
        baseXaxis.setStrokeWidth(1);

        Line baseYaxis = new Line(heightPane/2, 0, widthPane/2, heightPane);
        baseYaxis.setStroke(Color.BLACK);
        baseYaxis.setStrokeWidth(1);
        pane.getChildren().addAll(baseXaxis, baseYaxis,x_left,x_right,y_down,y_top);
    }


    public void draw(double x1, double x2) {
        drawBaseAxis();
        Vector vector = new Vector(x1, x2);
//        Line line = new Line(0, pane.getHeight(), vector1*scalar, pane.getHeight() - vector2*scalar);
//        line.setStroke(Color.BLUE);
//        line.setStrokeWidth(1);

        double angle = Math.toDegrees(Math.atan2(-x2, x1));
        System.out.println("Angle :-" + angle);
//        vector.setTranslateX(0);
        vector.setTranslateY(pane.getHeight()/2);
        vector.setTranslateX(pane.getWidth()/2);
//        vector.setRotate(angle);
        Rotate rotate = new Rotate(angle, 0, 0);
        vector.getTransforms().add(rotate);
        pane.getChildren().add(vector);
    }

    public void CalculateEigenValue(int ma, int mb, int mc, int md) {

//        int ma = 1;
//        int mb = 2;
//        int mc = 0;
//        int md = 1;
        double checkValid = (ma + md) * (ma + md) - 4 * ma * md - mb * mc;


        double firstSec = ma + md;
        double secondSec = Math.sqrt((ma + md) * (ma + md) - 4 * (ma * md - mb * mc));

        double eigenValue1 = (firstSec + secondSec) / 2;

        double eigenValue2 = (firstSec - secondSec) / 2;

        System.out.println("Eigen Value 1 :- " + eigenValue1);
        System.out.println("Eigen Value 2 :- " + eigenValue2);

        //need to check for eigen vlalue valid
        if (checkValid > 0) {
            CalculateEigenVector(eigenValue1, ma, mb, mc, md);
            CalculateEigenVector(eigenValue2, ma, mb, mc, md);
        } else if (checkValid == 0) {


            CalculateEigenVector(eigenValue1, ma, mb, mc, md);
        } else {
            System.out.println("No Transformation");
        }


        if ((ma == eigenValue1) & (md == eigenValue2)) {
            //working with sclaing equaly matrix
            System.out.println("Working with scalling equally matrix working later after vector implemented");
        }


    }

    public void CalculateEigenVector(double eigenValue, int ma, int mb, int mc, int md) {
        pane.getChildren().clear();

        double x1 = 0, x2 = 1;


        if ((mb == 0) || (mc == 0)) {
            if (ma == eigenValue) {
                //x1 is free variable so it can go x_axis any long
                x1 = 1;
                x2 = 0;
            } else if (md == eigenValue) {
                //x2 is free variable so it can go y_axis
                x2 = 1;
                x1 = 0;
            }
        } else {
            x1 = (mb * x2) / ma - eigenValue;
        }

        System.out.println("Eigen Vectors :- [" + x1 + "," + x2 + "]");

        draw(x1, x2);
    }

    public static class Vector extends Group {

        Vector(double x1, double x2) {
            Point2D eigen_vector = new Point2D(x1, x2);
            double magnitude = eigen_vector.magnitude();


            int head_size = 5;

            Line lineT = new Line(magnitude * scalar, 0, (magnitude * scalar) - head_size, head_size);
            Line lineM = new Line(0, 0, magnitude * scalar, 0);
            Line lineD = new Line(magnitude * scalar, 0, (magnitude * scalar) - head_size, -head_size);


            lineT.setStroke(Color.BLUE);
            lineD.setStroke(Color.BLUE);
            lineM.setStroke(Color.BLUE);

            double stroke_width = 2;
            lineT.setStrokeWidth(stroke_width);
            lineD.setStrokeWidth(stroke_width);
            lineM.setStrokeWidth(stroke_width);

            getChildren().addAll(lineT, lineM, lineD);
        }
    }
}

