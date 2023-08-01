/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.entities;

import aasim.attacks.Attack;
import static aasim.entities.Sprite.rand;
import aasim.level.Level;
import aasim.utilities.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author 14048
 */
public class FlyTrap extends Sprite {

    Image idleLeft, idleRight;
    Image attackLeft, attackRight;
    //Wolf image is 1.6 ratio

    public FlyTrap(double x, double y, int width, int height) {
        super(x, y, 128, 128);
        name = "flytrap";
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

    @Override
    void friction() {
    }
    double t = 0;

    boolean hasTarget = false;

    private void ai() {
        //AI

        //Find a target
        if (!hasTarget) {
            Circle radius = new Circle(100);
            radius.setTranslateX(this.getX() + (this.getImage().getWidth() / 2));
            radius.setTranslateY(this.getY() + (this.getImage().getHeight() / 2));
            if (radius.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
                hasTarget = true;
                target = Level.player;
            }
        }
        //Once target is found, attack if target gets too close
        if (hasTarget) {
            double distance = Vector.distanceCalc(this.getX(), this.getY(), target.getX(), target.getY());
            if (distance < 150) {
                //Set Attacking image
                if (target.getX() < this.getX()) {
                    leftPressed = true;
                    this.setImage(attackLeft);
                } else {
                    leftPressed = false;
                    this.setImage(attackRight);
                }
                //Actually attack the player
                if (!alreadyAttacking) {
                    alreadyAttacking = true;
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(eh -> this.attack(target.getX(), target.getY(), 150));
                    pause.play();
                }
            } else {
                //Target is too far away, just give up
                hasTarget = false;
                if (leftPressed = true) {
                    this.setImage(idleLeft);
                } else {
                    this.setImage(idleRight);
                }
            }
        }

//
//        if (distance > 50 && distance < 1000) {
//            if (target.getY() < this.getY()) {
//            } else {
//                downPressed = true;
//                upPressed = false;
//            }
//            if (target.getX() < this.getX()) {
//                leftPressed = true;
//                rightPressed = false;
//            } else {
//                rightPressed = true;
//                leftPressed = false;
//            }
//
//        } else if (distance >= 1000) {
//            //Distance is too far for wolf to even bother
//            combat = false;
//        } else {
//            //Attack if I'm close enough
//            upPressed = false;
//            downPressed = false;
//            leftPressed = false;
//            rightPressed = false;
//
//            //
//            if (this.getImage().equals(rightWalk)) {
//            } else {
//            }
//            int tempX = (int) target.getX();
//            int tempY = (int) target.getY();
//
//            //
//            if (!alreadyAttacking) {
//                alreadyAttacking = true;
//                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
//                pause.setOnFinished(eh -> this.attack(tempX, tempY));
//                pause.play();
//            }
//            //
//        }
//
//        Circle radius = new Circle(100);
//        radius.setTranslateX(this.getX());
//        radius.setTranslateY(this.getY());
//        if (radius.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
//            combat = true;
//            target = Level.player;
//        }
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Flytrap/FlyTrap";

        try {
            FileInputStream fis = new FileInputStream(fileLocation + "IdleRight.gif");
            idleRight = new Image(fis, width, height, false, false);
            this.setImage(idleRight);
            fis = new FileInputStream(fileLocation + "IdleLeft.gif");
            idleLeft = new Image(fis, width, height, false, false);
            fis = new FileInputStream(fileLocation + "AttackRight.gif");
            attackRight = new Image(fis, width, height, false, false);
            fis = new FileInputStream(fileLocation + "AttackLeft.gif");
            attackLeft = new Image(fis, width, height, false, false);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

}
