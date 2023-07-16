package aasim.entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Player extends Sprite {

    public boolean upPressed, upDisabled;
    public boolean leftPressed, leftDisabled;
    public boolean rightPressed, rightDisabled;
    public boolean downPressed, downDisabled;
    public double health = 100;
    public double maxHealth = 100;
    public Rectangle currentHealth = new Rectangle(190, 40);
    public Label healthTxt = new Label("" + health);

    double yVelocity = 0, xVelocity = 0;

    double maxVelocity = 10;
    public boolean cameraUp, cameraDown, cameraLeft, cameraRight;
    double startX, startY;

    public Player(double x, double y) {

        super(x, y, 36, 44); //w:h is 3:4
        setWalkAnimations();
        Sprite.players.add(this);
        Sprite.collisions.add(this);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (dead) {
                    this.stop();
                }
                update();
            }
        };
        startX = x;
        startY = y;
        timer.start();
    }

    private void update() {

        //Death
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

        // t += 0.016;
        movementHandling();
        cameraHandling();

        //Collisions
    }

    public void attack(double mouseX, double mouseY) {
//        if (stunned) {
//            return;
//        }
//        Attack a1 = new Attack(this.getX(), this.getY(), this, mouseX, mouseY);
//
//        ((Pane) this.getParent()).getChildren().add(a1);
//        addHealth(-5);
    }

    double movementCounter = 0;

    private void movementHandling() {
        //Handles all player movement
        //Timer for disabling movements
        //Player Movement 
        if (maxVelocity > Math.abs(yVelocity)) {
            if (upPressed && !upDisabled) {
                yVelocity -= 0.5;
            }
            if (downPressed && !downDisabled) {
                yVelocity += 0.5;
            }
        }
        if (maxVelocity > Math.abs(xVelocity)) {
            if (leftPressed && !leftDisabled) {
                xVelocity -= 0.5;
            }
            if (rightPressed && !rightDisabled) {
                xVelocity += 0.5;
            }

        }
        if (yVelocity != 0 || xVelocity != 0) {
            move(xVelocity, yVelocity);
        }

        if (yVelocity > 0) {
            yVelocity -= 0.25;
            this.setImage(downWalk);
        }

        if (xVelocity > 0) {
            xVelocity -= 0.25;
            this.setImage(rightWalk);
        }

        if (yVelocity < 0) {
            yVelocity += 0.25;
            this.setImage(upWalk);
        }

        if (xVelocity < 0) {
            xVelocity += 0.25;
            this.setImage(leftWalk);
        }

        if (Math.abs(xVelocity) < 0.25) {
            xVelocity = 0;
        }
        if (Math.abs(yVelocity) < 0.25) {
            yVelocity = 0;
        }

        if (yVelocity == 0 && xVelocity == 0) {
            this.setImage(img);
        }

        if (upDisabled || downDisabled || leftDisabled || rightDisabled) {
            movementCounter += 0.016;
            if (movementCounter > .1) {
                upDisabled = false;
                downDisabled = false;
                leftDisabled = false;
                rightDisabled = false;
                movementCounter = 0;
            }
        }
    }

    private void cameraHandling() {
        if (cameraUp || upPressed) {
            Pane parent = (Pane) this.getParent();
            parent.setTranslateY(parent.getTranslateY() - yVelocity);
        }
        if (cameraDown || downPressed) {
            Pane parent = (Pane) this.getParent();
            parent.setTranslateY(parent.getTranslateY() - yVelocity);
        }
        if (cameraLeft || leftPressed) {
            Pane parent = (Pane) this.getParent();
            parent.setTranslateX(parent.getTranslateX() - xVelocity);
        }
        if (cameraRight || rightPressed) {
            Pane parent = (Pane) this.getParent();
            parent.setTranslateX(parent.getTranslateX() - xVelocity);
        }
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/Child/child";
        try {
            FileInputStream fis = new FileInputStream(fileLocation + "Base.png");
            img = new Image(fis, width, height, false, false);
            this.setImage(img);
            //Up
            fis = new FileInputStream(fileLocation + "WalkUp.gif");
            upWalk = new Image(fis, width, height, false, false);
            //Down
            fis = new FileInputStream(fileLocation + "WalkDown.gif");
            downWalk = new Image(fis, width, height, false, false);
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
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void resetCamera() {
        Pane parent = (Pane) this.getParent();
        double scaleX = Screen.getPrimary().getBounds().getMaxX();
        double scaleY = Screen.getPrimary().getBounds().getMaxY();
        parent.setTranslateY(-this.getY() + scaleY / 2);
        parent.setTranslateX(-this.getX() + scaleX / 2);
    }

    //Getters and setters
    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

}
