/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.maps;

import aasim.entities.Deer;
import aasim.entities.Bee;
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
public class Level5Forest extends Map {

    private String fileLocation = "resources/maps/Level5/Level5.jpg";
    private Map nextLevel = null;

    public Level5Forest(int x, int y) {
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

        //Top horizontal line of 'maze'
        RectangleVector a = new RectangleVector(265, 249, 822, 17);
        RectangleVector b = new RectangleVector(1200, 250, 210, 14);
        RectangleVector c = new RectangleVector(1485, 250, 243, 24);
        wallCollisions.add(a);
        wallCollisions.add(b);
        wallCollisions.add(c);
        //Second horizontal line of maze
        RectangleVector d = new RectangleVector(550, 400, 513, 14);
        RectangleVector e = new RectangleVector(346, 370, 188, 27);
        RectangleVector f = new RectangleVector(1309, 370, 188, 14);
        //
        RectangleVector g = new RectangleVector(220, 450, 214, 14);
        RectangleVector h = new RectangleVector(1180, 470, 342, 14);
        RectangleVector i = new RectangleVector(1692, 470, 114, 529);

        //
        RectangleVector j = new RectangleVector(700, 670, 552, 20);
        RectangleVector k = new RectangleVector(407, 780, 136, 43);
        RectangleVector l = new RectangleVector(739, 816, 500, 14);
        RectangleVector m = new RectangleVector(420, 970, 1144, 20);
        RectangleVector n = new RectangleVector(194, 1025, 114, 30);
        RectangleVector o = new RectangleVector(748, 1075, 1034, 62);
        RectangleVector p = new RectangleVector(562, 1238, 1238, 30);
        RectangleVector q = new RectangleVector(230, 1202, 191, 43);
        //Now for vertical
        RectangleVector r = new RectangleVector(1170, 242, 43, 298);
        RectangleVector s = new RectangleVector(1290, 242, 53, 160);
        RectangleVector t = new RectangleVector(530, 400, 23, 423);
        RectangleVector u = new RectangleVector(426, 461, 24, 371);
        RectangleVector v = new RectangleVector(1370, 464, 37, 384);
        RectangleVector w = new RectangleVector(1528, 500, 37, 152);
        RectangleVector x = new RectangleVector(300, 580, 14, 465);
        RectangleVector y = new RectangleVector(1530, 790, 52, 240);
        RectangleVector z = new RectangleVector(417, 944, 24, 307);
        RectangleVector a1 = new RectangleVector(529, 1070, 36, 188);
        RectangleVector a2 = new RectangleVector(1203, 819, 40, 139);
        RectangleVector a3 = new RectangleVector(675, 512, 480, 45);
        //
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
        wallCollisions.add(w);
        wallCollisions.add(x);
        wallCollisions.add(y);
        wallCollisions.add(z);
        wallCollisions.add(a1);
        wallCollisions.add(a2);

    }

    private void setSpawns() {
        Deer a = new Deer(1617, 1172, 75, 100);
        Deer b = new Deer(265, 1098, 75, 100);
        Bee c = new Bee(371, 864, 75, 100);
        Bee d = new Bee(1369, 894, 75, 100);
        Bee e = new Bee(1079, 304, 75, 100);
        Wolf f = new Wolf(581, 180, 75, 100);

        for (int i = 0; i < 25; i++) {
            int x = Sprite.rand.nextInt(1351) + 350;
            int y = Sprite.rand.nextInt(1177) + 200;
            Bee random = new Bee(x, y, 75, 100);
            spawns.add(random);
        }

        spawns.add(a);
        spawns.add(b);
        spawns.add(c);
        spawns.add(d);
        spawns.add(e);
        spawns.add(f);
    }

    //This will be overridden in each level
    //Set interactables such as 'next level' and 'previous level' checks...or fire
    private void setInteractions() {
        Interactable stageChange = new Interactable(1701, 1292, 71, 69);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level6Forest(0, 0);
    }

}
