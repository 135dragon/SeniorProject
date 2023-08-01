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
public class Turtle extends Sprite {

    Image idle;

    public Turtle(double x, double y, int width, int height) {
        super(x, y, 120, 75);
        name = "bear";
        setWalkAnimations();
        this.maxVelocity = 0.5;
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

    private void ai() {
        //AI
        //Every 2 seconds, 50% chance of moving in a random direction, or just stopping in place.
        if (t > 2) {
            if (false) {
                //DO nothing
                upPressed = false;
                downPressed = false;
                leftPressed = false;
                rightPressed = false;
            } else {
                //Choose a direction
                //First X 
                int direction = rand.nextInt() % 2;
                if (direction == 0) {
                    //go left
                    leftPressed = true;
                    rightPressed = false;
                } else {
                    //go right
                    rightPressed = true;
                    leftPressed = false;
                }
                direction = rand.nextInt() % 2;
                if (direction == 0) {
                    //go up
                    upPressed = true;
                    downPressed = false;
                } else {
                    //go down
                    downPressed = true;
                    upPressed = false;
                }
            }
            t = 0;
        }
        //
        if (this.intersects(Level.player.getBoundsInLocal())) {
            this.setxVelocity(Sprite.rand.nextInt(50) - 25);
            this.setyVelocity(Sprite.rand.nextInt(50) - 25);
        }
        t = t + 0.016;
        //
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Turtle/Turtle";

        try {
            FileInputStream fis = new FileInputStream(fileLocation + "WalkRight.gif");
            img = new Image(fis, width, height, false, false);
            this.setImage(img);

            //Left
            fis = new FileInputStream(fileLocation + "WalkLeft.gif");
            leftWalk = new Image(fis, width, height, false, false);
            //Right
            fis = new FileInputStream(fileLocation + "WalkRight.gif");
            rightWalk = new Image(fis, width, height, false, false);

            //Dead
//            fis = new FileInputStream(fileLocation + "Dead.gif");
//            deadAnimation = new Image(fis, width, height, false, false);
        } catch (FileNotFoundException ex) {
        }

    }

}
