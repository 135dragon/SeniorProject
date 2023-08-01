/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Eel;
import aasim.entities.Piranha;
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
public class Level8Lake extends Map {

    private String fileLocation = "resources/maps/Level8/Level8.jpg";
    private Map nextLevel = null;

    public Level8Lake(int x, int y) {
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

        RectangleVector a = new RectangleVector(287, 640, 80, 543);
        RectangleVector b = new RectangleVector(520, 434, 40, 569);
        RectangleVector c = new RectangleVector(850, 654, 111, 489);
        RectangleVector d = new RectangleVector(1220, 472, 41, 500);
        RectangleVector e = new RectangleVector(1467, 629, 74, 563);
        RectangleVector f = new RectangleVector(520, 414, 741, 73);
        RectangleVector g = new RectangleVector(327, 1116, 1232, 71);

        wallCollisions.add(a);
        wallCollisions.add(b);
        wallCollisions.add(c);
        wallCollisions.add(d);
        wallCollisions.add(e);
        wallCollisions.add(f);
        wallCollisions.add(g);
    }

    private void setSpawns() {
        for (int i = 0; i < 25; i++) {
            int x = Sprite.rand.nextInt(1351) + 350;
            int y = Sprite.rand.nextInt(1177) + 200;
            Eel random = new Eel(x, y, 75, 100);
            spawns.add(random);
        }
    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(1087, 520, 65, 69);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level9Lake(0, 0);
    }

}
