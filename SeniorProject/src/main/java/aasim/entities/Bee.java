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
public class Bee extends Sprite {

    //Wolf image is 1.6 ratio
    public Bee(double x, double y, int width, int height) {
        super(x, y, 60, 32.5);
        name = "bee";
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

    private boolean targetAnnoyed = false;
    double t = 0;
    double beeCounter = 0;
    boolean combat = false;
    private static Sprite beesTarget;

    private void ai() {
        //AI
        if (attackedBy != null || beesTarget != null) {
            //  If attacked, ALL BEES turn hostile to attacker.
            if (attackedBy != null) {
                beesTarget = attackedBy;
            }
            combat = true;
            //  Fly X distance away from attacker, 
            double distance = Vector.distanceCalc(this.getX(), this.getY(), beesTarget.getX(), beesTarget.getY());
            if (distance <= 200) {
                if (beesTarget.getY() < this.getY()) {
                    upPressed = false;
                    downPressed = true;
                } else {
                    downPressed = false;
                    upPressed = true;
                }
                if (beesTarget.getX() < this.getX()) {
                    leftPressed = false;
                    rightPressed = true;
                } else {
                    rightPressed = false;
                    leftPressed = true;
                }
            } else if (distance > 200 && distance < 400) {
                //  attack attacker (projectile)
                if (!alreadyAttacking) {
                    alreadyAttacking = true;
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(eh -> this.attack(beesTarget.getX(), beesTarget.getY(), 100));
                    pause.play();
                }
            } else {
                //Not in range, so approach target
                if (beesTarget.getY() < this.getY()) {
                    upPressed = true;
                    downPressed = false;
                } else {
                    downPressed = true;
                    upPressed = false;
                }
                if (beesTarget.getX() < this.getX()) {
                    leftPressed = true;
                    rightPressed = false;
                } else {
                    rightPressed = true;
                    leftPressed = false;
                }
            }
            if (beesTarget.isDead()) {
                combat = false;
                attackedBy = null;
                beesTarget = null;
            }
        }

        // If non hostile
        //  If target approaches or approaches target
        //  Fly to target for X seconds, then fly away.
        if (!combat) {
            //Check for target
            if (target == null) {
                Circle radius = new Circle(100);
                radius.setTranslateX(this.getX() + (this.getImage().getWidth() / 2));
                radius.setTranslateY(this.getY() + (this.getImage().getHeight() / 2));
                if (radius.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
                    target = Level.player;
                } else {
                    target = null;
                }
            }
            //Approach the target.
            if (target != null && !targetAnnoyed) {
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
                double distance = Vector.distanceCalc(this.getX(), this.getY(), target.getX(), target.getY());
                //Start the counter
                if (distance < 50) {
                    beeCounter += 0.16;
                    //Annoy target for 5 seconds
                    if (beeCounter > 5) {
                        target = null;
                        targetAnnoyed = true;
                        beeCounter = 0;
                    }
                }
            }
            //  Fly around randomly
            if (targetAnnoyed || target == null) {
                if (t > 2) {
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
                    t = 0;
                }
                t += 0.16;
            }

        }
        //
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Bee/bee";

        try {
            FileInputStream fis = new FileInputStream(fileLocation + "Right.gif");
            img = new Image(fis, width, height, false, false);
            this.setImage(img);
            //Left
            fis = new FileInputStream(fileLocation + "Left.gif");
            leftWalk = new Image(fis, width, height, false, false);
            //Right
            fis = new FileInputStream(fileLocation + "Right.gif");
            rightWalk = new Image(fis, width, height, false, false);
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
