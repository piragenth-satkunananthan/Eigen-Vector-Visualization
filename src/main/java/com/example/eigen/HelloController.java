package com.example.eigen;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;

import javafx.scene.Group;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


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

    @FXML
    private Button Draw_button;



    public static double scalar = 10;
    Vector EigenVector1;
    Vector EigenVector2;

    public void initialize() {

        Platform.runLater(() -> {
            drawBaseAxis();

//            manager();

        });

    }


    public void manager() {
        if (EigenVector1 != null) {
            pane.getChildren().remove(EigenVector1);
        }
        if (EigenVector2 != null) {
            pane.getChildren().remove(EigenVector2);
        }
        int ma = Integer.parseInt(a.getText());
        int mb = Integer.parseInt(b.getText());
        int mc = Integer.parseInt(c.getText());
        int md = Integer.parseInt(d.getText());
        double[] EigenValueResult = CalculateEigenValue(ma, mb, mc, md);
        double[] EigenVectorResult;
        if (EigenValueResult[0] > 0) {
            System.out.println("2 Eigen vector");
            EigenVectorResult = CalculateEigenVector(EigenValueResult[1], ma, mb, mc, md);
            EigenVector1 = draw(EigenVectorResult[0], EigenVectorResult[1]);
            EigenVectorResult = CalculateEigenVector(EigenValueResult[2], ma, mb, mc, md);
            EigenVector2 = draw(EigenVectorResult[0], EigenVectorResult[1]);

        } else if (EigenValueResult[0] == 0) {
            System.out.println("1 Eigen Vector");
            EigenVectorResult = CalculateEigenVector(EigenValueResult[1], ma, mb, mc, md);
            EigenVector1 = draw(EigenVectorResult[0], EigenVectorResult[1]);
        } else {
            System.out.println("no Eigen Vector");
            System.out.println("No Transformation");
        }


        if ((ma == EigenValueResult[1]) & (md == EigenValueResult[2])) {
            //working with sclaing equaly matrix
            System.out.println("Working with scalling equally matrix working later after vector implemented");
        }

    }


    public void drawBaseAxis() {
        pane.setManaged(false);

        double widthPane = pane.getWidth();
        double heightPane = pane.getHeight();
        Label x_left = new Label("(-" + String.valueOf(widthPane / 2) + ")");
        Label x_right = new Label("(" + String.valueOf(widthPane / 2) + ")");

        Label y_top = new Label("(" + String.valueOf(heightPane / 2) + ")");
        Label y_down = new Label("(-" + String.valueOf(heightPane / 2) + ")");

//        System.out.println(heightPane);
//        System.out.println(widthPane);

        Line baseXaxis = new Line(0, heightPane / 2, widthPane, heightPane / 2);
        x_left.setTranslateY(heightPane / 2);
        x_right.setTranslateY(heightPane / 2);
        x_right.setTranslateX(widthPane);

        y_top.setTranslateX(widthPane / 2);
        y_down.setTranslateX(widthPane / 2);
        y_down.setTranslateY(heightPane);
        baseXaxis.setStroke(Color.BLACK);
        baseXaxis.setStrokeWidth(1);

        Line baseYaxis = new Line(widthPane / 2, 0, widthPane / 2, heightPane);
        baseYaxis.setStroke(Color.BLACK);
        baseYaxis.setStrokeWidth(1);
        pane.getChildren().addAll(baseXaxis, baseYaxis, x_left, x_right, y_down, y_top);
    }


    public Vector draw(double x1, double x2) {

        Vector vector = new Vector(x1, x2);

//        Line line = new Line(0, pane.getHeight(), vector1*scalar, pane.getHeight() - vector2*scalar);
//        line.setStroke(Color.BLUE);
//        line.setStrokeWidth(1);
        double angle = Math.toDegrees(Math.atan2(-x2, x1));
        System.out.println("Angle :-" + angle);
//        vector.setTranslateX(0);
        vector.setTranslateY(pane.getHeight() / 2);
        vector.setTranslateX(pane.getWidth() / 2);
//        vector.setRotate(angle);
        Rotate rotate = new Rotate(0, 0, 0);
        vector.getTransforms().add(rotate);
        pane.getChildren().add(vector);
//        System.out.println(rotate.angleProperty());

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(1.3), new KeyValue(rotate.angleProperty(), -angle, Interpolator.EASE_BOTH))
        );
        timeline.play();
        return vector;
    }

    public double[] CalculateEigenValue(int ma, int mb, int mc, int md) {

//        int ma = 1;
//        int mb = 2;
//        int mc = 0;
//        int md = 1;
        double checkValid = (ma + md) * (ma + md) - 4 * (ma * md - mb * mc);


        double firstSec = ma + md;
        double secondSec = Math.sqrt((ma + md) * (ma + md) - 4 * (ma * md - mb * mc));

        double eigenValue1 = (firstSec + secondSec) / 2;

        double eigenValue2 = (firstSec - secondSec) / 2;

        System.out.println("Eigen Value 1 :- " + eigenValue1);
        System.out.println("Eigen Value 2 :- " + eigenValue2);
        return new double[]{checkValid, eigenValue1, eigenValue2};
        //need to check for eigen vlalue valid


    }

    public double[] CalculateEigenVector(double eigenValue, int ma, int mb, int mc, int md) {

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
            x1 = (-mb * x2) / (ma - eigenValue);
        }

        System.out.println("Eigen Vectors :- [" + x1 + "," + x2 + "]");

        return new double[]{x1, x2};

    }

    public static class Vector extends Group {
        double magnitude;

        Vector(double x1, double x2) {
            Point2D eigen_vector = new Point2D(x1, x2);
            this.magnitude = eigen_vector.magnitude();


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

