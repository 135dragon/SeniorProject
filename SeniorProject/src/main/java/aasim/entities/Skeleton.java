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
public class Skeleton extends Sprite {

    private Image attackLeft;
    private Image attackRight;

    //Wolf image is 1.6 ratio
    public Skeleton(double x, double y, int width, int height) {
        super(x, y, 60, 32.5);
        name = "bee";
        setWalkAnimations();
        this.maxVelocity = Sprite.rand.nextDouble() + 2;
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

    private boolean targetAnnoyed = false;
    double t = 0;
    double beeCounter = 0;
    boolean combat = false;
    private static Sprite skeletalTarget;

    private void ai() {
        //AI
        if (attackedBy != null || skeletalTarget != null) {
            //  If attacked, ALL BEES turn hostile to attacker.
            if (attackedBy != null) {
                skeletalTarget = attackedBy;
            }
            combat = true;
            //  Fly X distance away from attacker, 
            double distance = Vector.distanceCalc(this.getX(), this.getY(), skeletalTarget.getX(), skeletalTarget.getY());
            if (distance <= 200) {
                if (skeletalTarget.getY() < this.getY()) {
                    upPressed = false;
                    downPressed = true;
                } else {
                    downPressed = false;
                    upPressed = true;
                }
                if (skeletalTarget.getX() < this.getX()) {
                    leftPressed = false;
                    rightPressed = true;
                } else {
                    rightPressed = false;
                    leftPressed = true;
                }
            } else if (distance > 200 && distance < 400) {
                //  attack attacker (projectile)

                if (this.getImage() == rightWalk) {
                    this.setImage(attackRight);
                } else {
                    this.setImage(attackLeft);
                }

                if (!alreadyAttacking) {
                    alreadyAttacking = true;
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(eh -> this.attack(skeletalTarget.getX(), skeletalTarget.getY(), 100));
                    pause.play();
                }
            } else {
                //Not in range, so approach target
                if (skeletalTarget.getY() < this.getY()) {
                    upPressed = true;
                    downPressed = false;
                } else {
                    downPressed = true;
                    upPressed = false;
                }
                if (skeletalTarget.getX() < this.getX()) {
                    leftPressed = true;
                    rightPressed = false;
                } else {
                    rightPressed = true;
                    leftPressed = false;
                }
            }
            if (skeletalTarget.isDead()) {
                combat = false;
                attackedBy = null;
                skeletalTarget = null;
            }
        }

        // If non hostile
        //  If target approaches or approaches target
        //  Fly to target for X seconds, then fly away.
        if (!combat) {
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
            t += 0.016;
        }
        //
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Skeleton/Skeleton";

        try {
            FileInputStream fis = new FileInputStream(fileLocation + "Idle.gif");
            img = new Image(fis, width, height, false, false);
            this.setImage(img);
            //Left
            fis = new FileInputStream(fileLocation + "WalkLeft.gif");
            leftWalk = new Image(fis, width, height, false, false);
            //Right
            fis = new FileInputStream(fileLocation + "WalkRight.gif");
            rightWalk = new Image(fis, width, height, false, false);

            fis = new FileInputStream(fileLocation + "AttackLeft.gif");
            attackLeft = new Image(fis, width, height, false, false);
            fis = new FileInputStream(fileLocation + "AttackRight.gif");
            attackRight = new Image(fis, width, height, false, false);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void attack(double x, double y, double attackRadius) {
        HoneyAttack a1 = new HoneyAttack(this, x, y, 25);
        ((Pane) this.getParent()).getChildren().add(a1);
    }

}
