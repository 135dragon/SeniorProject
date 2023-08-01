/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Bee;
import aasim.entities.Goblin;
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
public class Level6Forest extends Map {

    private String fileLocation = "resources/maps/Level6/Level6.jpg";
    private Map nextLevel = null;

    public Level6Forest(int x, int y) {
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
    }

    private void setSpawns() {
        for (int i = 0; i < 25; i++) {
            int x = Sprite.rand.nextInt(1351) + 350;
            int y = Sprite.rand.nextInt(1177) + 200;
            Goblin random = new Goblin(x, y, 75, 100);
            spawns.add(random);
        }

    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(1711, 1292, 60, 81);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level7Lake(0, 0);
    }

}
