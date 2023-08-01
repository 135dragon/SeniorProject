/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Skeleton;
import aasim.entities.Sprite;
import aasim.entities.Statue;
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
public class Level12Temple extends Map {

    private String fileLocation = "resources/maps/Level12/Level12.jpg";
    private Map nextLevel = null;

    public Level12Temple(int x, int y) {
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
        RectangleVector topWall = new RectangleVector(45, 42, 1939, 57);
        RectangleVector bottomWall = new RectangleVector(57, 1427, 1955, 52);
        RectangleVector leftWall = new RectangleVector(45, 42, 52, 1442);
        RectangleVector rightWall = new RectangleVector(1936, 40, 55, 1452);
        wallCollisions.add(topWall);
        wallCollisions.add(bottomWall);
        wallCollisions.add(leftWall);
        wallCollisions.add(rightWall);
    }

    private void setSpawns() {
        for (int i = 0; i < 10; i++) {
            int x = Sprite.rand.nextInt(1351) + 350;
            int y = Sprite.rand.nextInt(1177) + 200;
            Statue random = new Statue(x, y, 75, 100);
            spawns.add(random);
        }
        for (int i = 0; i < 10; i++) {
            int x = Sprite.rand.nextInt(1351) + 350;
            int y = Sprite.rand.nextInt(1177) + 200;
            Skeleton random = new Skeleton(x, y, 75, 100);
            spawns.add(random);
        }
    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(994, 725, 84, 87);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level13Grave(0, 0);
    }

}
