/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.entities;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 14048
 */
public class Enemy extends Sprite {

    public Enemy(int x, int y) {
        super(x, y, 30, 40);
        currentHealth.setTranslateX(-10);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (dead) {
                    this.stop();
                }
                update();
            }
        };
        Sprite.enemies.add(this);
        Sprite.collisions.add(this);
        timer.start();
        setSpeed(Math.random());
    }

    boolean goLeft = true;
    boolean goUp = true;
    double playerX, playerY;
    double xDist = Math.abs(this.getX() - playerX);
    double yDist = Math.abs(this.getY() - playerY);
    Random rand = new Random();

    // double angle = Math.sin(C);//angle that the player is from enemy
    private void update() {
        double xDist = Math.abs(this.getX() - playerX);
        double yDist = Math.abs(this.getY() - playerY);
        if (dead) {
            PauseTransition hide = new PauseTransition(Duration.seconds(0.5));
            hide.setOnFinished(eh -> {
                this.setVisible(false);
                ((Pane) this.getScene().getRoot()).getChildren().remove(this);
                Sprite.collisions.remove(this);
            });
            hide.play();
            return;
        }

        for (Sprite x : this.players) {
            playerX = x.getX();
            playerY = x.getY();

        }

        // ENEMY AI
//        System.out.println(yDist);
        if (xDist < 300 && yDist < 300) {
            aware();
        } else {
            unaware();
        }
        /**
         * if (playerY > this.getY()) { this.moveDown(); } if (playerY < this.getY()) {
         * this.moveUp(); } if (playerX > this.getX()) { this.moveRight(); } if
         * (playerX < this.getX()) { this.moveLeft(); }
         *
         */

        // Enemy POV (visibility)
//        for (Sprite x : POV) {
//            if (x != this) {
//                if (x.intersects(this.getBoundsInParent())) {
//                    double differenceX = x.getX() - this.getX();
//                    double differenceY = x.getY() - this.getY();
//                    if (differenceX > 10) {//will stop enemies and players from colliding, unless walked into for now
//                        while (x.intersects(this.getBoundsInParent())) {
//
//                        }
//                    }
//
//                }
//            }
//        }
        // Collisions
        
    }

    // enemy unaware
    //double counter = 0;
    int unawareDirection = -1;
    double counter = 0;
    int timer = -1;

    public void unaware() {
        timer = rand.nextInt(10);
        counter += 0.012;
    }

    boolean alreadyAdded = false;

    // enemy aware
    public void aware() {
        if (!alreadyAdded) {
            ((Pane) this.getParent()).getChildren().add(currentHealth);
            alreadyAdded = true;

        }
        if (alreadyAdded) {
            currentHealth.setVisible(true);
            currentHealth.setX(this.getX());
            currentHealth.setY(this.getY());
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
