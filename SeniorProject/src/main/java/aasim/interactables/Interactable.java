/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.interactables;

import aasim.entities.Sprite;
import aasim.maps.Map;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 14048
 */
public class Interactable extends Pane {
    
    public Bounds bounds;
    
    public Interactable(double x1, double y1, double width, double height) {
        Rectangle rect = new Rectangle(x1, y1, width, height);
        bounds = rect.getBoundsInLocal();
    }
    
    public Interactable() {
        
    }

    //Code to run on collision
    //Default onCollision will be to load the next level.
    public void onCollision(Sprite sprite) {
        if (sprite.getName().equals("player")) {
            Map.loadNextMap = true;
        }
    }
}
