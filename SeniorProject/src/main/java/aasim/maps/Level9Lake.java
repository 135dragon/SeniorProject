/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Piranha;
import aasim.entities.Sprite;
import aasim.entities.Turtle;
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
public class Level9Lake extends Map {

    private String fileLocation = "resources/maps/Level9/Level9.jpg";
    private Map nextLevel = null;

    public Level9Lake(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        spawnLocationX = 357;
        spawnLocationY = 180;
        this.wallCollisions.clear();
        this.interactables.clear();
        this.spawns.clear();
        setSpawns();
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

    private void setCollisions() {
        RectangleVector topWall = new RectangleVector(0, 0, 2048, 24);
        RectangleVector bottomWall = new RectangleVector(0, 1516, 2048, 24);
        RectangleVector leftWall = new RectangleVector(0, 0, 25, 1536);
        RectangleVector rightWall = new RectangleVector(2038, 0, 10, 1536);
        wallCollisions.add(topWall);
        wallCollisions.add(bottomWall);
        wallCollisions.add(leftWall);
        wallCollisions.add(rightWall);

    }

    private void setSpawns() {
        for (int i = 0; i < 25; i++) {
            int x = Sprite.rand.nextInt(1351) + 350;
            int y = Sprite.rand.nextInt(1177) + 200;
            Turtle random = new Turtle(x, y, 75, 100);
            spawns.add(random);
        }
    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(1643, 1294, 48, 55);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level10Temple(0, 0);
    }

}
