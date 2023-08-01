/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.entities;

import aasim.level.Level;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 *
 * @author 14048
 */
public class Miasma extends Sprite {

    Image idle;

    public Miasma(double x, double y, int width, int height) {
        super(x, y, 120, 75);
        name = "bear";
        setWalkAnimations();
        this.maxVelocity = 0;
    }

    @Override
    void update() {
        //Death
        ai();
        //Handles movement and collisions
        movementHandling();
        friction();
        this.checkForCollision();
    }

    double t = 0;

    boolean combat = false;
    boolean check = false;

    private void ai() {
        if (check) {
            if (t < 5) {
                t += 0.016;
            }
            if (t > 3) {
                check = false;
            }
        } else {
            if (t > 0.5) {
                t -= 0.016;
            }
            if (t < 1) {
                check = true;
            }
        }

        this.setScaleX(t);
        this.setScaleY(t);

    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Miasma/Miasma";

        try {
            FileInputStream fis = new FileInputStream(fileLocation + ".png");
            img = new Image(fis, width, height, false, false);
            this.setImage(img);
            //Dead
//            fis = new FileInputStream(fileLocation + "Dead.gif");
//            deadAnimation = new Image(fis, width, height, false, false);
        } catch (FileNotFoundException ex) {
        }

    }

}
