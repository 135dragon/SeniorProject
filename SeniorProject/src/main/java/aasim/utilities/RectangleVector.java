/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.utilities;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 14048
 */
public class RectangleVector {

    public Rectangle rect;
    public Line topLine, leftLine, rightLine, bottomLine;

    public RectangleVector(double x1, double y1, double width, double height) {
        rect = new Rectangle(x1, y1, width, height);
        topLine = new Line(x1, y1, x1 + width, y1);
        leftLine = new Line(x1, y1, x1, y1 + height);
        rightLine = new Line(x1 + width, y1, x1 + width, y1 + height);
        bottomLine = new Line(x1, y1 + height, x1 + width, y1 + height);
    }
    

}
