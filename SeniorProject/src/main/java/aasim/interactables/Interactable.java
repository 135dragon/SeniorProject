/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.interactables;

import aasim.entities.Player;
import aasim.maps.Map;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 14048
 */
public class Interactable {

    public Rectangle rect;

    public Interactable(double x1, double y1, double width, double height) {
        rect = new Rectangle(x1, y1, width, height);
    }

    //Code to run on collision
    //Default onCollision will be to load the next level.
    public void onCollision(Player player) {
        Map.loadNextMap = true;
    }
}
