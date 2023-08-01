/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Player;
import aasim.interactables.Interactable;
import aasim.entities.Sprite;
import aasim.interactables.Fire;
import aasim.utilities.RectangleVector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author 14048
 */
public class Ending extends Map {

    private String fileLocation = "resources/maps/Ending/Ending.jpg";
    private Map nextLevel;

    public Ending(double x, double y) {
        super(x, y);
        setX(x);
        setY(y);
        spawnLocationY = spawnLocationY - 100;
        this.wallCollisions.clear();
        this.interactables.clear();
        this.spawns.clear();
        setAsset();
        setCollisions();
        setInteractions();
    }

    private void setAsset() {
        try {
            FileInputStream fis = new FileInputStream(fileLocation);
            img = new Image(fis);
            this.setImage(img);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //This will be overridden in each level
    //All walls / collisions the player can't go through
    private void setCollisions() {
        //This entire image is 2048 by 1536
        //Using (X,Y) notation.

        //RectangleVector2d is (x,y,width,height)
        //Left Wall is from (0,0) to (342, 1536)
        RectangleVector leftWall = new RectangleVector(0, 0, 342, 1536);
        //Top Wall is from(0,0) to (2048, 304)
        RectangleVector topWall = new RectangleVector(0, 0, 2048, 304);
        //Right Wall is from(2048, 0) to (1895, 1536)
        RectangleVector rightWall = new RectangleVector(2048 - 153, 0, 153, 1536);
        //Bottom Wall is from(615, 1536) to (976, 1497)
        RectangleVector bottomWall = new RectangleVector(0, 1536 - 39, 2048, 39);
        //Stage Change is from (959, 629) to (959+221, 629-139)

        wallCollisions.add(leftWall);
        wallCollisions.add(topWall);
        wallCollisions.add(rightWall);
        wallCollisions.add(bottomWall);
//        wallCollisions.add(stageChange);

    }

    //This will be overridden in each level
    //All of the spawns, whether enemy, passive, or interactables.
    private void setSpawns() {

    }

    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(959, 629, 221, 139);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level1Cave(0, 0);
    }

}
