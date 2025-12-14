package com.example.eigen;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class GridSystem extends Group {
    double a = 1, b = 0, c = 0, d = 1, CpaneHeight, CpaneWidth;

    int unitSize;

    Timeline timeline;


    List<GridLines> VerticalLines = new ArrayList<>();
    List<GridLines> HorizontalLines = new ArrayList<>();

    public GridSystem(double paneHeight, double paneWidth, int unitSize) {
        this.CpaneHeight = paneHeight / 2;
        this.CpaneWidth = paneWidth / 2;
        this.unitSize = unitSize;
        makeGridLines();

        updateGridMatrix(0);
    }
    public double getCpaneHeight(){
        return CpaneHeight;
    }
    public double getCpaneWidth(){
        return CpaneWidth;
    }
    void Caller(double a, double b, double c, double d,Timeline timeline) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.timeline = timeline;
        timeline.play();
    }

    void makeGridLines() {
        System.out.println("making Grid line ");

        // for Vertical Lines.
        int CountLineV = (int) (CpaneWidth) / unitSize;
        System.out.println("CountLineV" + CountLineV);
        int CountLineH = (int) (CpaneHeight) / unitSize;

        for (int i = -CountLineV; i <= CountLineV; i += 1) {
            GridLines line = new GridLines(i, -CountLineH, i, CountLineH);
            StylingLine(line, i);
            VerticalLines.add(line);
//            line.line.setStroke(Color.RED); // testing for vertical line
            this.getChildren().add(line.line);
        }

        //for horizontal Lines

        for (int i = -CountLineH; i <= CountLineH; i += 1) {
            GridLines line = new GridLines(-CountLineV, i, CountLineV, i);
            StylingLine(line, i);
            HorizontalLines.add(line);
            this.getChildren().add(line.line);
        }
    }

    public void StylingLine(GridLines gl, int i) {
        if (i == 0) {
            // Main Axis (Brighter / Thicker)
            gl.line.setStroke(Color.GREEN);
            gl.line.setStrokeWidth(2);
        } else {
            // Regular Grid Line (Faint Cyan-Grey)
            gl.line.setStroke(Color.DARKGRAY);
            gl.line.setStrokeWidth(1);
        }
    }

    void updateGridMatrix(double t) {

        double Ca = 1 + (a - 1) * t;
        double Cb = b * t;
        double Cc = c * t;
        double Cd = 1 + (d - 1) * t;


        for (GridLines gl : VerticalLines) {
            TransformGrid(Ca, Cb, Cc, Cd, gl);

        }
        for (GridLines gl : HorizontalLines) {
            TransformGrid(Ca, Cb, Cc, Cd, gl);
        }


    }

    void TransformGrid(double a, double b, double c, double d, GridLines gl) {
        double Csx = (a * gl.sx) + (b * gl.sy);
        double Csy = (c * gl.sx) + (d * gl.sy);

        double Cex = (a * gl.ex) + (b * gl.ey);
        double Cey = (c * gl.ex) + (d * gl.ey);

        gl.line.setStartX(CpaneWidth + (Csx * unitSize));
        gl.line.setStartY(CpaneHeight - (Csy * unitSize));

        gl.line.setEndX((CpaneWidth) + (Cex * unitSize));
        gl.line.setEndY((CpaneHeight) - (Cey * unitSize));

    }

    public static class GridLines {
        double sx, sy, ex, ey;
        Line line;

        GridLines(double sx, double sy, double ex, double ey) {
//            System.out.println(sx+""+sy);
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
            this.line = new Line();
        }
    }
}
