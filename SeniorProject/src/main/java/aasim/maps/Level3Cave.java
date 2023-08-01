/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Bear;
import aasim.entities.Sprite;
import aasim.interactables.Interactable;
import aasim.utilities.RectangleVector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author 14048
 */
public class Level3Cave extends Map {

    private String fileLocation = "resources/maps/Level3/Level3.jpg";
    private Map nextLevel = null;

    public Level3Cave(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        this.wallCollisions.clear();
        this.interactables.clear();
        this.spawns.clear();
        setAsset();
        setSpawns();
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

    private void setCollisions() {
        RectangleVector topWall = new RectangleVector(0, 0, 2048, 99);
        RectangleVector bottomWall = new RectangleVector(0, 1452, 2048, 76);
        RectangleVector leftWall = new RectangleVector(0, 0, 99, 1536);
        RectangleVector rightWall = new RectangleVector(1950, 0, 91, 1536);
        wallCollisions.add(topWall);
        wallCollisions.add(bottomWall);
        wallCollisions.add(leftWall);
        wallCollisions.add(rightWall);
    }

    private void setSpawns() {
        Bear x = new Bear(this.getImage().getWidth() / 2 - 100, this.getImage().getHeight() / 2 - 100, 75, 100);
        spawns.add(x);
    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(1004, 690, 68, 78);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level4Forest(0, 0);
    }

}
