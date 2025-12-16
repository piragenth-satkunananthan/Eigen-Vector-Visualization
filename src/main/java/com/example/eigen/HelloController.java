package com.example.eigen;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
    private TextField v1;
    @FXML
    private TextField v2;

    @FXML
    private Label EigenVector_print_1;

    @FXML
    private Label EigenVector_print_2;

    @FXML
    private Label Eigen_Value_1;

    @FXML
    private Label Eigen_Value_2;

    @FXML
    private Label Eigen_value_num;

//    public static double scalar = 10;
//    Vector EigenVector1;
//    Vector EigenVector2;
//
//    public void initialize() {
//
//        Platform.runLater(() -> {
//            drawBaseAxis();
//
////            manager();
//
//        });
//
//    }
//
//
//    public void manager() {
//        if (EigenVector1 != null) {
//            pane.getChildren().remove(EigenVector1);
//        }
//        if (EigenVector2 != null) {
//            pane.getChildren().remove(EigenVector2);
//        }
//        int ma = Integer.parseInt(a.getText());
//        int mb = Integer.parseInt(b.getText());
//        int mc = Integer.parseInt(c.getText());
//        int md = Integer.parseInt(d.getText());
//        double[] EigenValueResult = CalculateEigenValue(ma, mb, mc, md);
//        double[] EigenVectorResult;
//        if (EigenValueResult[0] > 0) {
//            System.out.println("2 Eigen vector");
//            EigenVectorResult = CalculateEigenVector(EigenValueResult[1], ma, mb, mc, md);
//            EigenVector1 = draw(EigenVectorResult[0], EigenVectorResult[1]);
//            EigenVectorResult = CalculateEigenVector(EigenValueResult[2], ma, mb, mc, md);
//            EigenVector2 = draw(EigenVectorResult[0], EigenVectorResult[1]);
//
//        } else if (EigenValueResult[0] == 0) {
//            System.out.println("1 Eigen Vector");
//            EigenVectorResult = CalculateEigenVector(EigenValueResult[1], ma, mb, mc, md);
//            EigenVector1 = draw(EigenVectorResult[0], EigenVectorResult[1]);
//        } else {
//            System.out.println("no Eigen Vector");
//            System.out.println("No Transformation");
//        }
//
//
//        if ((ma == EigenValueResult[1]) & (md == EigenValueResult[2])) {
//            //working with sclaing equaly matrix
//            System.out.println("Working with scalling equally matrix working later after vector implemented");
//        }
//
//    }
//
//
//    public void drawBaseAxis() {
//        pane.setManaged(false);
//
//        double widthPane = pane.getWidth();
//        double heightPane = pane.getHeight();
//        Label x_left = new Label("(-" + String.valueOf(widthPane / 2) + ")");
//        Label x_right = new Label("(" + String.valueOf(widthPane / 2) + ")");
//
//        Label y_top = new Label("(" + String.valueOf(heightPane / 2) + ")");
//        Label y_down = new Label("(-" + String.valueOf(heightPane / 2) + ")");
//
////        System.out.println(heightPane);
////        System.out.println(widthPane);
//
//        Line baseXaxis = new Line(0, heightPane / 2, widthPane, heightPane / 2);
//        x_left.setTranslateY(heightPane / 2);
//        x_right.setTranslateY(heightPane / 2);
//        x_right.setTranslateX(widthPane);
//
//        y_top.setTranslateX(widthPane / 2);
//        y_down.setTranslateX(widthPane / 2);
//        y_down.setTranslateY(heightPane);
//        baseXaxis.setStroke(Color.BLACK);
//        baseXaxis.setStrokeWidth(1);
//
//        Line baseYaxis = new Line(widthPane / 2, 0, widthPane / 2, heightPane);
//        baseYaxis.setStroke(Color.BLACK);
//        baseYaxis.setStrokeWidth(1);
//        pane.getChildren().addAll(baseXaxis, baseYaxis, x_left, x_right, y_down, y_top);
//    }
//
//
//    public Vector draw(double x1, double x2) {
//
//        Vector vector = new Vector(x1, x2);
//
////        Line line = new Line(0, pane.getHeight(), vector1*scalar, pane.getHeight() - vector2*scalar);
////        line.setStroke(Color.BLUE);
////        line.setStrokeWidth(1);
//        double angle = Math.toDegrees(Math.atan2(-x2, x1));
//        System.out.println("Angle :-" + angle);
////        vector.setTranslateX(0);
//        vector.setTranslateY(pane.getHeight() / 2);
//        vector.setTranslateX(pane.getWidth() / 2);
////        vector.setRotate(angle);
//        Rotate rotate = new Rotate(0, 0, 0);
//        vector.getTransforms().add(rotate);
//        pane.getChildren().add(vector);
////        System.out.println(rotate.angleProperty());
//
//        Timeline timeline = new Timeline();
//        timeline.getKeyFrames().addAll(
//                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
//                new KeyFrame(Duration.seconds(1.3), new KeyValue(rotate.angleProperty(), -angle, Interpolator.EASE_BOTH))
//        );
//        timeline.play();
//        return vector;
//    }
//
//    public double[] CalculateEigenValue(int ma, int mb, int mc, int md) {
//

    /// /        int ma = 1;
    /// /        int mb = 2;
    /// /        int mc = 0;
    /// /        int md = 1;
//        double checkValid = (ma + md) * (ma + md) - 4 * (ma * md - mb * mc);
//
//
//        double firstSec = ma + md;
//        double secondSec = Math.sqrt((ma + md) * (ma + md) - 4 * (ma * md - mb * mc));
//
//        double eigenValue1 = (firstSec + secondSec) / 2;
//
//        double eigenValue2 = (firstSec - secondSec) / 2;
//
//        System.out.println("Eigen Value 1 :- " + eigenValue1);
//        System.out.println("Eigen Value 2 :- " + eigenValue2);
//        return new double[]{checkValid, eigenValue1, eigenValue2};
//        //need to check for eigen vlalue valid
//
//
//    }
//
//    public double[] CalculateEigenVector(double eigenValue, int ma, int mb, int mc, int md) {
//
//        double x1 = 0, x2 = 1;
//
//
//        if ((mb == 0) || (mc == 0)) {
//            if (ma == eigenValue) {
//                //x1 is free variable so it can go x_axis any long
//                x1 = 1;
//                x2 = 0;
//            } else if (md == eigenValue) {
//                //x2 is free variable so it can go y_axis
//                x2 = 1;
//                x1 = 0;
//            }
//        } else {
//            x1 = (-mb * x2) / (ma - eigenValue);
//        }
//
//        System.out.println("Eigen Vectors :- [" + x1 + "," + x2 + "]");
//
//        return new double[]{x1, x2};
//
//    }
//
//    public static class Vector extends Group {
//        double magnitude;
//
//        Vector(double x1, double x2) {
//            Point2D eigen_vector = new Point2D(x1, x2);
//            this.magnitude = eigen_vector.magnitude();
//
//
//            int head_size = 5;
//
//            Line lineT = new Line(magnitude * scalar, 0, (magnitude * scalar) - head_size, head_size);
//            Line lineM = new Line(0, 0, magnitude * scalar, 0);
//            Line lineD = new Line(magnitude * scalar, 0, (magnitude * scalar) - head_size, -head_size);
//
//
//            lineT.setStroke(Color.BLUE);
//            lineD.setStroke(Color.BLUE);
//            lineM.setStroke(Color.BLUE);
//
//            double stroke_width = 2;
//            lineT.setStrokeWidth(stroke_width);
//            lineD.setStrokeWidth(stroke_width);
//            lineM.setStrokeWidth(stroke_width);
//
//
//            getChildren().addAll(lineT, lineM, lineD);
//        }
//    }


//    Up old


//NEW SYSTEM
    private GridSystem gridSystem;
    int unitSize = 30;
    private final DoubleProperty progress = new SimpleDoubleProperty(0);

    GivenVector givenVector;

    public void initialize() {
        Platform.runLater(() -> {

            gridSystem = new GridSystem(pane.getHeight(), pane.getWidth(), unitSize);

            pane.getChildren().add(gridSystem);

            //to hide grid lines that are out of the pane
            javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle();
            clip.widthProperty().bind(pane.widthProperty());
            clip.heightProperty().bind(pane.heightProperty());
            pane.setClip(clip);

            progress.addListener((obs, oldVal, newVal) -> {
                if (gridSystem != null) {
                    gridSystem.updateGridMatrix(newVal.doubleValue());
                }
            });

        });
    }

    public void manager() {

        double paneHeight = gridSystem.getCpaneHeight() * 2;
        double paneWidth = gridSystem.getCpaneWidth() * 2;

        double ma = Double.parseDouble(a.getText());
        double mb = Double.parseDouble(b.getText());
        double mc = Double.parseDouble(c.getText());
        double md = Double.parseDouble(d.getText());

        //testing
//        ma=1;mb=2;mc=3;md=4;
        progress.set(0);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progress, 0)),
                new KeyFrame(Duration.seconds(2.0), new KeyValue(progress, 1, Interpolator.EASE_BOTH))
        );

        if (gridSystem != null) {
            gridSystem.ClearEigenVector();
        }
        EigenCalc eigenCalc = new EigenCalc(ma, mb, mc, md);
        double[] eigenValues = eigenCalc.EigenValue();
        if (eigenValues.length == 0) {
            System.out.println("This matrix have no real eigen solutions");
            Eigen_value_num.setText("No Real Eigen Solutions");
            Eigen_Value_1.setText("");
            EigenVector_print_1.setText("");
            Eigen_Value_2.setText("");
            EigenVector_print_2.setText("");

        } else if (eigenValues.length == 1) {
            System.out.println("one EigenValue");
            Eigen_value_num.setText("One Eigen Value");
            double[] EigenVector1 = eigenCalc.EigenVector(eigenValues[0]);
            Eigen_Value_1.setText(String.valueOf(eigenValues[0]));
            String Eigen_Vector_print_output = String.format("[%.4f, %.4f]",EigenVector1[0],EigenVector1[1]);
            EigenVector_print_1.setText(Eigen_Vector_print_output);
            System.out.println("Eigen Value: "+ eigenValues[0]+"    EigenVector1: "+ EigenVector1[0]+"   "+EigenVector1[1]);
            gridSystem.EigenVectorDraw(EigenVector1);

        } else {
            System.out.println("Two EigenValue");
            Eigen_value_num.setText("Two Eigen Values");
            double[] EigenVector1 = eigenCalc.EigenVector(eigenValues[0]);
            System.out.println("Eigen Value: "+ eigenValues[0]+"   EigenVector1: "+ EigenVector1[0]+"   "+EigenVector1[1]);
            Eigen_Value_1.setText(String.valueOf(eigenValues[0]));
//            String Eigen_Vector_print_output = "["+String.valueOf(EigenVector1[0]) + ","+ String.valueOf(EigenVector1[1])+"]";
            String Eigen_Vector_print_output = String.format("[%.4f, %.4f]",EigenVector1[0],EigenVector1[1]);

            EigenVector_print_1.setText(Eigen_Vector_print_output);

            gridSystem.EigenVectorDraw(EigenVector1);

            double[] EigenVector2 = eigenCalc.EigenVector(eigenValues[1]);
            System.out.println("Eigen Value: "+ eigenValues[1]+"    EigenVector2: "+ EigenVector2[0]+"  "+EigenVector2[1]);
            Eigen_Value_2.setText(String.valueOf(eigenValues[1]));
//            String Eigen_Vector_print_output1 = "["+String.valueOf(EigenVector2[0]) + ","+ String.valueOf(EigenVector2[1])+"]";
            String Eigen_Vector_print_output1 = String.format("[%.4f, %.4f]",EigenVector2[0],EigenVector2[1]);
            EigenVector_print_2.setText(Eigen_Vector_print_output1);
            gridSystem.EigenVectorDraw(EigenVector2);

        }



        if (!(v1.getText().isEmpty() || v2.getText().isEmpty())) {
            if (givenVector != null) {
                pane.getChildren().remove(givenVector);
            }
            double GivenvectorValue1 = Double.parseDouble(v1.getText());
            double GivenvectorValue2 = Double.parseDouble(v2.getText());
            System.out.println("creating Given Vector object ");
            givenVector = new GivenVector(new double[]{ma, mb, mc, md}, GivenvectorValue1, GivenvectorValue2, unitSize);
            givenVector.draw(paneHeight, paneWidth);
            pane.getChildren().add(givenVector);
        }

//        GridSystem gridtest = new GridSystem(paneHeight, paneWidth);

//        pane.getChildren().add(gridtest);

        gridSystem.Caller(ma, mb, mc, md, timeline);
    }


}

