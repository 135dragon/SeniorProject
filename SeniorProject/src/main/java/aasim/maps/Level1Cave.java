/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Sprite;
import aasim.interactables.Fire;
import aasim.interactables.Interactable;
import aasim.utilities.RectangleVector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author 14048
 */
public class Level1Cave extends Map {

    private String fileLocation = "resources/maps/Level1/level1.jpg";
    private Map nextLevel;

    public Level1Cave(double x, double y) {
        super(x, y);
        setX(x);
        setY(y);
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
        //RectangleVector2d is (x,y,width,height)
        RectangleVector a = new RectangleVector(0, 0, 124, 1536);
        RectangleVector b = new RectangleVector(0, 0, 2048, 142);
        RectangleVector c = new RectangleVector(545, 133, 301, 545);
        RectangleVector d = new RectangleVector(549, 999, 309, 473);
        RectangleVector e = new RectangleVector(1200, 367, 251, 891);
        RectangleVector f = new RectangleVector(1721, 0, 335, 1536);
        RectangleVector g = new RectangleVector(0, 1469, 2048, 93);

        wallCollisions.add(a);
        wallCollisions.add(b);
        wallCollisions.add(c);
        wallCollisions.add(d);
        wallCollisions.add(e);
        wallCollisions.add(f);
        wallCollisions.add(g);
//        wallCollisions.add(topWall);
//        wallCollisions.add(rightWall);
//        wallCollisions.add(bottomWall);
//        wallCollisions.add(stageChange);
    }

    //This will be overridden in each level
    //All of the spawns, whether enemy, passive, or interactables.
    private void setSpawns() {

    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
//        Interactable stageChange = new Interactable(959, 629, 221, 139);
//        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Map(0, 0);
    }

}
