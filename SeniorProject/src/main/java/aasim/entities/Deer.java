/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 *
 * @author 14048
 */
public class Deer extends Sprite {

    public Deer(double x, double y, int width, int height) {
        super(x, y, 75, 100);
        name = "deer";
        setWalkAnimations();
        this.maxVelocity = 3;
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

    private void ai() {
        //AI
        //Every 2 seconds, 50% chance of moving in a random direction, or just stopping in place.
        t = t + 0.016;
        if (t > 2) {
            if (rand.nextInt() % 2 == 0) {
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
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Deer/deer";
        if (Sprite.rand.nextInt() % 2 == 0) {
            fileLocation += "Alternate";
        }
        try {
            FileInputStream fis = new FileInputStream(fileLocation + "Idle.gif");
            img = new Image(fis, width, height, false, false);
            this.setImage(img);
            //Left
            fis = new FileInputStream(fileLocation + "Left.gif");
            leftWalk = new Image(fis, width, height, false, false);
            //Right
            fis = new FileInputStream(fileLocation + "Right.gif");
            rightWalk = new Image(fis, width, height, false, false);
            //Dead
//            fis = new FileInputStream(fileLocation + "Dead.gif");
//            deadAnimation = new Image(fis, width, height, false, false);
        } catch (FileNotFoundException ex) {
        }

    }
    //Want laser shooting deer? Well now you can!
    //Deer can test out attacks here!
    //
//    private void attack() {
//        Attack a1 = new Attack(this, Level.player.getX(), Level.player.getY());
//        ((Pane) this.getParent()).getChildren().add(a1);
//    }

}
