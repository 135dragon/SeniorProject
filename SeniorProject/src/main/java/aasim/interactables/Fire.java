/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.interactables;

import aasim.entities.Player;
import aasim.maps.Map;

/**
 *
 * @author 14048
 */
public class Fire extends Interactable {

    public Fire(double x1, double y1, double width, double height) {
        super(x1, y1, width, height);
    }

    @Override
    public void onCollision(Player player) {
        System.out.println("boooo");
    }
}
