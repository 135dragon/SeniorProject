/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Bear;
import aasim.entities.Deer;
import aasim.entities.Sprite;
import aasim.entities.Wolf;
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
public class Level2Cave extends Map {

    private String fileLocation = "resources/maps/Level2/Level2.jpg";
    private Map nextLevel = null;

    public Level2Cave(int x, int y) {
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
        //RectangleVector is (x,y,width,height)
        RectangleVector topWall = new RectangleVector(0, 0, 2048, 99);
        RectangleVector bottomWall = new RectangleVector(0, 1452, 2048, 76);
        RectangleVector leftWall = new RectangleVector(0, 0, 99, 1536);
        RectangleVector rightWall = new RectangleVector(1950, 0, 91, 1536);
//        RectangleVector b = new RectangleVector(0, 0, 2048, 142);
//        RectangleVector c = new RectangleVector(545, 133, 301, 545);
//        RectangleVector d = new RectangleVector(549, 999, 309, 473);
        RectangleVector e = new RectangleVector(403, 338, 173, 839);
        RectangleVector f = new RectangleVector(869, 338, 173, 839);
        RectangleVector g = new RectangleVector(1309, 338, 173, 839);
        RectangleVector h = new RectangleVector(405, 330, 1053, 141);
//
        wallCollisions.add(topWall);
        wallCollisions.add(bottomWall);
        wallCollisions.add(leftWall);
        wallCollisions.add(rightWall);
        wallCollisions.add(e);
        wallCollisions.add(f);
        wallCollisions.add(g);
        wallCollisions.add(h);
//        wallCollisions.add(e);
//        wallCollisions.add(f);
//        wallCollisions.add(g);    
    }

    private void setSpawns() {
        for (int i = 0; i < 4; i++) {
            Wolf x = new Wolf(1100, 506 + (100 * i), 75, 100);
            spawns.add(x);
        }
        Deer a = new Deer(223, 158, 75, 100);
        Deer b = new Deer(1700, 220, 75, 100);
        Deer c = new Deer(685, 854, 75, 100);
        Deer d = new Deer(1751, 1294, 75, 100);

        spawns.add(a);
        spawns.add(b);
        spawns.add(c);
        spawns.add(d);
    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(1215, 515, 62, 62);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level3Cave(0, 0);
    }

}
