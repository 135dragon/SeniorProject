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
public class Level11Temple extends Map {

    private String fileLocation = "resources/maps/Level11/Level11.jpg";
    private Map nextLevel = null;

    public Level11Temple(int x, int y) {
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
        RectangleVector a1 = new RectangleVector(42.0, 45.0, 470.0, 56.0);
        RectangleVector a2 = new RectangleVector(508.0, 49.0, 512.0, 104.0);
        RectangleVector a3 = new RectangleVector(1012.0, 51.0, 424.0, 48.0);
        RectangleVector a4 = new RectangleVector(1430.0, 53.0, 568.0, 100.0);
        RectangleVector a5 = new RectangleVector(50.0, 99.0, 54.0, 1358.0);
        RectangleVector a6 = new RectangleVector(514.0, 151.0, 50.0, 50.0);
        RectangleVector a7 = new RectangleVector(974.0, 149.0, 50.0, 52.0);
        RectangleVector a8 = new RectangleVector(1434.0, 149.0, 50.0, 54.0);
        RectangleVector a9 = new RectangleVector(1898.0, 151.0, 104.0, 1312.0);
        RectangleVector a10 = new RectangleVector(1792.0, 409.0, 102.0, 48.0);
        RectangleVector a11 = new RectangleVector(1794.0, 717.0, 106.0, 50.0);
        RectangleVector a12 = new RectangleVector(1770.0, 1027.0, 128.0, 46.0);
        RectangleVector a13 = new RectangleVector(100.0, 361.0, 458.0, 46.0);
        RectangleVector a14 = new RectangleVector(514.0, 407.0, 504.0, 54.0);
        RectangleVector a15 = new RectangleVector(512.0, 305.0, 48.0, 202.0);
        RectangleVector a16 = new RectangleVector(970.0, 311.0, 56.0, 500.0);
        RectangleVector a17 = new RectangleVector(1026.0, 359.0, 450.0, 46.0);
        RectangleVector a18 = new RectangleVector(1432.0, 311.0, 52.0, 1014.0);
        RectangleVector a19 = new RectangleVector(1486.0, 411.0, 100.0, 48.0);
        RectangleVector a20 = new RectangleVector(1334.0, 665.0, 96.0, 46.0);
        RectangleVector a21 = new RectangleVector(1480.0, 717.0, 104.0, 48.0);
        RectangleVector a22 = new RectangleVector(1332.0, 977.0, 96.0, 42.0);
        RectangleVector a23 = new RectangleVector(1482.0, 1027.0, 118.0, 46.0);
        RectangleVector a24 = new RectangleVector(102.0, 669.0, 100.0, 42.0);
        RectangleVector a25 = new RectangleVector(98.0, 973.0, 100.0, 48.0);
        RectangleVector a26 = new RectangleVector(104.0, 1283.0, 96.0, 48.0);
        RectangleVector a27 = new RectangleVector(514.0, 617.0, 52.0, 706.0);
        RectangleVector a28 = new RectangleVector(408.0, 1281.0, 150.0, 50.0);
        RectangleVector a29 = new RectangleVector(412.0, 971.0, 144.0, 46.0);
        RectangleVector a30 = new RectangleVector(410.0, 669.0, 102.0, 42.0);
        RectangleVector a31 = new RectangleVector(564.0, 719.0, 98.0, 46.0);
        RectangleVector a32 = new RectangleVector(570.0, 1025.0, 406.0, 44.0);
        RectangleVector a33 = new RectangleVector(978.0, 921.0, 38.0, 406.0);
        RectangleVector a34 = new RectangleVector(1026.0, 975.0, 100.0, 42.0);
        RectangleVector a35 = new RectangleVector(1024.0, 1283.0, 406.0, 40.0);
        RectangleVector a36 = new RectangleVector(58.0, 1443.0, 1926.0, 20.0);
        RectangleVector a37 = new RectangleVector(1029.0, 664.0, 91.0, 43.0);
        RectangleVector a38 = new RectangleVector(872.0, 720.0, 101.0, 43.0);
        wallCollisions.add(a1);
        wallCollisions.add(a2);
        wallCollisions.add(a3);
        wallCollisions.add(a4);
        wallCollisions.add(a5);
        wallCollisions.add(a6);
        wallCollisions.add(a7);
        wallCollisions.add(a8);
        wallCollisions.add(a9);
        wallCollisions.add(a10);
        wallCollisions.add(a11);
        wallCollisions.add(a12);
        wallCollisions.add(a13);
        wallCollisions.add(a14);
        wallCollisions.add(a15);
        wallCollisions.add(a16);
        wallCollisions.add(a17);
        wallCollisions.add(a18);
        wallCollisions.add(a19);
        wallCollisions.add(a20);
        wallCollisions.add(a21);
        wallCollisions.add(a22);
        wallCollisions.add(a23);
        wallCollisions.add(a24);
        wallCollisions.add(a25);
        wallCollisions.add(a26);
        wallCollisions.add(a27);
        wallCollisions.add(a28);
        wallCollisions.add(a29);
        wallCollisions.add(a30);
        wallCollisions.add(a31);
        wallCollisions.add(a32);
        wallCollisions.add(a33);
        wallCollisions.add(a34);
        wallCollisions.add(a35);
        wallCollisions.add(a36);
        wallCollisions.add(a37);
        wallCollisions.add(a38);
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
        Interactable stageChange = new Interactable(1344, 1191, 73, 79);
        interactables.add(stageChange);
    }

    @Override
    public void setNextMap() {
        nextMap = new Level12Temple(0, 0);
    }

}
