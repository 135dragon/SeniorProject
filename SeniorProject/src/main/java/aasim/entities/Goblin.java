/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.entities;

import aasim.attacks.Attack;
import aasim.attacks.HoneyAttack;
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
public class Goblin extends Sprite {

    //Wolf image is 1.6 ratio
    public Goblin(double x, double y, int width, int height) {
        super(x, y, 60, 32.5);
        name = "goblin";
        setWalkAnimations();
        this.maxVelocity = Sprite.rand.nextDouble() + 4;
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
        if (combat) {
            //Run closer if i'm too far
            double distance = Vector.distanceCalc(this.getX(), this.getY(), target.getX(), target.getY());

            if (distance > 200 && distance < 500) {
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

            } else if (distance >= 500) {
                //Distance is too far for wolf to even bother
                combat = false;
            } else {
                //Attack if I'm close enough
                upPressed = false;
                downPressed = false;
                leftPressed = false;
                rightPressed = false;

                //
                //
                if (!alreadyAttacking) {
                    alreadyAttacking = true;
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(eh -> this.attack(target.getX(), target.getY(), 100));
                    pause.play();
                }
                //
            }

        }
        if (!combat) {
            Circle radius = new Circle(150);
            radius.setTranslateX(this.getX() + (this.getImage().getWidth() / 2));
            radius.setTranslateY(this.getY() + (this.getImage().getHeight() / 2));
            if (radius.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
                combat = true;
                target = Level.player;
            }
            //Run in random direction
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
        }
        t = t + 0.016;        //
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Goblin/goblin";

        try {
            FileInputStream fis = new FileInputStream(fileLocation + "Right.png");
            img = new Image(fis, width, height, false, false);
            this.setImage(img);
            //Left
            fis = new FileInputStream(fileLocation + "Left.png");
            leftWalk = new Image(fis, width, height, false, false);
            //Right
            fis = new FileInputStream(fileLocation + "Right.png");
            rightWalk = new Image(fis, width, height, false, false);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void attack(double x, double y, double attackRadius) {
        HoneyAttack a1 = new HoneyAttack(this, x, y, 50);
        ((Pane) this.getParent()).getChildren().add(a1);
    }

}
