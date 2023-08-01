/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.utilities;

/**
 *
 * @author 14048
 */
public class Vector {

    private double magnitude, angle;

    public Vector(double magnitude, double angle) {
        this.magnitude = magnitude;
        this.angle = angle;
    }

    //Getters and Setters
    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public static double distanceCalc(double x1, double y1, double x2, double y2) {
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance;
    }

    public static double slopeCalc(double x1, double y1, double x2, double y2) {

        double slope = (y2 - y1) / (x2 - x1);
        return slope;
    }

}
