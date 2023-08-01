/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.entities;

import static aasim.entities.Sprite.rand;
import aasim.level.Level;
import aasim.utilities.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author 14048
 */
public class Bear extends Sprite {

    Image idle;

    public Bear(double x, double y, int width, int height) {
        super(x, y, 120 * 2, 75 * 2);
        name = "bear";
        setWalkAnimations();
        this.maxVelocity = 4;
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
        //If combat, run towards enemy. Once enemy is in range, attack.
        //If not combat, 
        // check in a circle for player
        // enter combat against player
        //else if no non-wolf sprites exist choose a random direction to run in. 
        if (combat) {
            img = idle;

            //Run closer if i'm too far
            double distance = Vector.distanceCalc(this.getX(), this.getY(), target.getX(), target.getY());
            if (distance < 1000) {
                if (target.getY() < this.getY()) {
                    upPressed = true;
                    downPressed = false;
                } else {
                    downPressed = true;
                    upPressed = false;
                }
                if (target.getX() < this.getX()) {
                    leftPressed = true;
                    rightPressed = false;
                } else {
                    rightPressed = true;
                    leftPressed = false;
                }
                if (this.intersects(target.getBoundsInLocal())) {
                    target.addHealth(-1);
                    target.setxVelocity(Sprite.rand.nextInt(50) - 25);
                    target.setyVelocity(Sprite.rand.nextInt(50) - 25);
                }

            } else if (distance >= 1000) {
                //Distance is too far for bear to even bother
                combat = false;
            }
        }
        //If Idle
        if (!combat) {
            Circle radius = new Circle(150);
            radius.setTranslateX(this.getX() + (this.getImage().getWidth() / 2));
            radius.setTranslateY(this.getY() + (this.getImage().getHeight() / 2));
            if (radius.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
                combat = true;
                target = Level.player;
            }
            //Run in random direction
            //Just sleep
        }
        t = t + 0.016;
        //
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Bear/Bear";

        try {
            FileInputStream fis = new FileInputStream(fileLocation + "Sleep.gif");
            img = new Image(fis, width, height, false, false);
            this.setImage(img);

            fis = new FileInputStream(fileLocation + "Idle.gif");
            idle = new Image(fis, width, height, false, false);

            //Left
            fis = new FileInputStream(fileLocation + "RunLeft.gif");
            leftWalk = new Image(fis, width, height, false, false);
            //Right
            fis = new FileInputStream(fileLocation + "RunRight.gif");
            rightWalk = new Image(fis, width, height, false, false);

            //Dead
//            fis = new FileInputStream(fileLocation + "Dead.gif");
//            deadAnimation = new Image(fis, width, height, false, false);
        } catch (FileNotFoundException ex) {
        }

    }

}
