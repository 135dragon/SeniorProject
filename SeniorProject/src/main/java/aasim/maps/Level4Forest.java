/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Deer;
import aasim.entities.FlyTrap;
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
public class Level4Forest extends Map {

    private String fileLocation = "resources/maps/Level4/Level4.jpg";
    private Map nextLevel = null;

    public Level4Forest(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        spawnLocationX = 330;
        spawnLocationY = 200;
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
        RectangleVector topWall = new RectangleVector(100, 0, 1826, 122);
        RectangleVector bottomWall = new RectangleVector(0, 1394, 1970, 133);
        RectangleVector leftWall = new RectangleVector(0, 0, 245, 730);
        RectangleVector leftWall2 = new RectangleVector(0, 774, 181, 604);
        RectangleVector rightWall = new RectangleVector(1758, 0, 174, 1536);
        wallCollisions.add(topWall);
        wallCollisions.add(bottomWall);
        wallCollisions.add(leftWall);
        wallCollisions.add(leftWall2);
        wallCollisions.add(rightWall);

        RectangleVector a = new RectangleVector(557, 361, 100, 712);
        RectangleVector b = new RectangleVector(572, 337, 625, 130);
        RectangleVector c = new RectangleVector(1081, 361, 119, 748);
        wallCollisions.add(a);
        wallCollisions.add(b);
        wallCollisions.add(c);

    }

    private void setSpawns() {
        FlyTrap a = new FlyTrap(657, 1114, 75, 150);
        FlyTrap b = new FlyTrap(657, 1210, 75, 150);
        FlyTrap c = new FlyTrap(657, 1310, 75, 150);
        spawns.add(a);
        spawns.add(b);
        spawns.add(c);
        FlyTrap d = new FlyTrap(1139, 208, 75, 150);
        FlyTrap e = new FlyTrap(1144, 1208, 75, 150);
        spawns.add(d);
        spawns.add(e);

        Deer a1 = new Deer(657, 1114, 75, 150);
        Deer b1 = new Deer(657, 1210, 75, 150);
        Deer c1 = new Deer(657, 1310, 75, 150);
        Deer d1 = new Deer(1139, 208, 75, 150);
        Deer e1 = new Deer(1144, 1208, 75, 150);
        spawns.add(a1);
        spawns.add(b1);
        spawns.add(c1);
        spawns.add(d1);
        spawns.add(e1);

    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(944, 530, 83, 85);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level5Forest(0, 0);
    }

}
