/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Bee;
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
public class Level10Temple extends Map {

    private String fileLocation = "resources/maps/Level10/Level10.jpg";
    private Map nextLevel = null;

    public Level10Temple(int x, int y) {
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
        //All the horizontals
        RectangleVector a = new RectangleVector(51, 248, 454, 54);
        RectangleVector b = new RectangleVector(661, 248, 1337, 54);
        RectangleVector c = new RectangleVector(50, 607, 467, 47);
        RectangleVector d = new RectangleVector(661, 621, 1337, 54);
        RectangleVector e = new RectangleVector(46, 969, 201, 49);
        RectangleVector f = new RectangleVector(355, 969, 1641, 57);
        //Vertical #1
        RectangleVector g = new RectangleVector(445, 248, 63, 163);
        RectangleVector h = new RectangleVector(664, 248, 63, 163);
        RectangleVector i = new RectangleVector(1172, 248, 63, 163);
        RectangleVector j = new RectangleVector(1625, 248, 63, 163);
        //Vertical #1 B
        RectangleVector k = new RectangleVector(451, 504, 54, 161);
        RectangleVector l = new RectangleVector(664, 504, 54, 161);
        RectangleVector m = new RectangleVector(1172, 504, 54, 161);
        RectangleVector n = new RectangleVector(1625, 504, 54, 161);

        //Vertical #2
        RectangleVector o = new RectangleVector(914, 615, 62, 150);
        RectangleVector p = new RectangleVector(914, 860, 62, 150);
        //Vertical #3
        RectangleVector q = new RectangleVector(573, 967, 54, 150);
        RectangleVector r = new RectangleVector(1119, 967, 54, 150);
        RectangleVector s = new RectangleVector(1582, 967, 54, 150);
        //Vertical #3 B
        RectangleVector t = new RectangleVector(573, 1225, 57, 250);
        RectangleVector u = new RectangleVector(1119, 1225, 57, 250);
        RectangleVector v = new RectangleVector(1582, 1225, 57, 250);

        wallCollisions.add(a);
        wallCollisions.add(b);
        wallCollisions.add(c);
        wallCollisions.add(d);
        wallCollisions.add(e);
        wallCollisions.add(f);
        wallCollisions.add(g);
        wallCollisions.add(h);
        wallCollisions.add(i);
        wallCollisions.add(j);
        wallCollisions.add(k);
        wallCollisions.add(l);
        wallCollisions.add(m);
        wallCollisions.add(n);
        wallCollisions.add(o);
        wallCollisions.add(p);
        wallCollisions.add(q);
        wallCollisions.add(r);
        wallCollisions.add(s);
        wallCollisions.add(t);
        wallCollisions.add(u);
        wallCollisions.add(v);
    }

    private void setSpawns() {
        for (int i = 0; i < 10; i++) {
            int x = Sprite.rand.nextInt(1351) + 350;
            int y = Sprite.rand.nextInt(1177) + 200;
            Statue random = new Statue(x, y, 75, 100);
            spawns.add(random);
        }

        
    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(1843, 1047, 80, 85);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level11Temple(0, 0);
    }

}
