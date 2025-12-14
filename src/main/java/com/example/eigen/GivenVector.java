package com.example.eigen;

import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class GivenVector extends Group {
    double a, b, c, d, x1, x2, GivenvectorValue1, GivenvectorValue2, angle;
    int unitSize;
    GivenVector(double[] givenMatrix, double GivenvectorValue1, double GivenvectorValue2, int unitSize) {
        this.a = givenMatrix[0];
        this.b = givenMatrix[1];
        this.c = givenMatrix[2];
        this.d = givenMatrix[3];
        this.GivenvectorValue1 = GivenvectorValue1;
        this.GivenvectorValue2 = GivenvectorValue2;
        this.unitSize = unitSize;
    }

    public void draw(double paneHeight, double paneWidth) {
        x1 = (a * GivenvectorValue1) + (b * GivenvectorValue2);
        x2 = (c * GivenvectorValue1) + (d * GivenvectorValue2);

        x1 *= unitSize;
        x2 *= unitSize;

        System.out.println("x1" + x1);
        System.out.println("x2" + x2);
        Point2D Vector = new Point2D(x1, x2);
        double magnitude = Vector.magnitude();
//        magnitude *= 100;
        double length = magnitude;

        int head_size = 5;

        Line lineT = new Line(length, 0, (length) - head_size, head_size);
        Line lineM = new Line(0, 0, length, 0);
        Line lineD = new Line(length, 0, (length) - head_size, -head_size);


        lineT.setStroke(Color.ROYALBLUE);
        lineD.setStroke(Color.ROYALBLUE);
        lineM.setStroke(Color.ROYALBLUE);

        double stroke_width = 2;
        lineT.setStrokeWidth(stroke_width);
        lineD.setStrokeWidth(stroke_width);
        lineM.setStrokeWidth(stroke_width);
        getChildren().addAll(lineD, lineM, lineT);
        angle = Math.toDegrees(Math.atan2(-x2, x1));
        this.setTranslateY(paneHeight / 2);
        this.setTranslateX(paneWidth / 2);
        Rotate rotate = new Rotate(angle, 0, 0);
        this.getTransforms().add(rotate);
    }

}
