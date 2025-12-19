package com.example.eigen;

public class EigenCalc {
    double a, b, c, d;

    EigenCalc(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double[] EigenValue(){
        double trace = (this.a+this.d);
        double determinant = (a*d) - (b*c);
        double discriminant = (trace * trace) - (4 * determinant);

        if (discriminant<0){
            return new double[]{};
        }
        double lambda_1 = (trace + Math.sqrt(discriminant))/2;

        if (discriminant==0){
            return new double[]{lambda_1};
        } else if (discriminant>0) {
            double lambda_2 = (trace - Math.sqrt(discriminant))/2;
            return new double[] {lambda_1,lambda_2};
        }
        return new double[]{};
    }

    public double[] EigenVector(double lambda){
        double x1 = -this.b;
        double x2 = this.a - lambda;


//        if ((this.b == 0) || (this.c == 0)) {
//            if (this.a == lambda) {
//                //x1 is free variable so it can go x_axis any long
//                x1 = 1;
//                x2 = 0;
//            } else if (this.d == lambda) {
//                //x2 is free variable so it can go y_axis
//                x2 = 1;
//                x1 = 0;
//            }
//        } else {
//            x1 = (-this.b * x2) / (this.a - lambda);
//        }

        if (Math.abs(x1) < 0.0001 && Math.abs(x2) < 0.0001) {
            x1 = - (this.d - lambda);
            x2 = this.c;


            if (Math.abs(x1) < 0.0001 && Math.abs(x2) < 0.0001) {
                return new double[]{1, 0};
            }
        }

        double magnitude = Math.sqrt(x1*x1 + x2*x2);
        if (magnitude != 0) {
            x1 /= magnitude;
            x2 /= magnitude;
        }
        return new double[]{x1,x2};
    }

}
