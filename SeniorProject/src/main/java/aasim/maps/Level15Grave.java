/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Miasma;
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
public class Level15Grave extends Map {

    private String fileLocation = "resources/maps/Level15/Level15.jpg";
    private Map nextLevel = null;

    public Level15Grave(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        spawnLocationX = 77;
        spawnLocationY = 1426;
        this.wallCollisions.clear();
        this.interactables.clear();
        this.spawns.clear();
        setSpawns();
        setAsset();
//        setCollisions();
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
        //RectangleVector2d is (x,y,width,height)
//        RectangleVector a = new RectangleVector(0, 0, 124, 1536);
//        RectangleVector b = new RectangleVector(0, 0, 2048, 142);
//        RectangleVector c = new RectangleVector(545, 133, 301, 545);
//        RectangleVector d = new RectangleVector(549, 999, 309, 473);
//        RectangleVector e = new RectangleVector(1200, 367, 251, 891);
//        RectangleVector f = new RectangleVector(1721, 0, 335, 1536);
//        RectangleVector g = new RectangleVector(0, 1469, 2048, 93);
//
//        wallCollisions.add(a);
//        wallCollisions.add(b);
//        wallCollisions.add(c);
//        wallCollisions.add(d);
//        wallCollisions.add(e);
//        wallCollisions.add(f);
//        wallCollisions.add(g);    
    }

    private void setSpawns() {
        for (int i = 0; i < 100; i++) {
            int x = Sprite.rand.nextInt(1351) + 350;
            int y = Sprite.rand.nextInt(1177) + 200;
            Miasma random = new Miasma(x, y, 75, 100);
            spawns.add(random);
        }
    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(1943, 1406, 72, 77);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Ending(0, 0);
    }

}
