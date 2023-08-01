package aasim.entities;

import aasim.attacks.Attack;
import aasim.level.Level;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.util.Duration;

public class Player extends Sprite {

    public boolean cameraUp, cameraDown, cameraLeft, cameraRight;
    double startX, startY;

    public Player(double x, double y) {
        //Default 36, 44
        super(x, y, 36, 44); //w:h is 3:4
        name = "player";
        setWalkAnimations();
        startX = x;
        startY = y;
    }

    @Override
    void update() {
//        t += 0.016;
        //Movement Handling
//        System.out.println(this.getMoney());
        this.movementHandling();
        this.friction();
        cameraHandling();
        this.checkForCollision();

        if (xVelocity > 5 || yVelocity > 5) {
            resetCamera();
        }

        if (currHealth != maxHealth) {
            Level.backgroundMusic.pause();
            Level.combatMusic.play();
        } else {
            Level.backgroundMusic.play();
            Level.combatMusic.pause();
        }
    }

    private void cameraHandling() {
        Pane parent = (Pane) this.getParent();

//        if (cameraUp || upPressed) {
        parent.setTranslateY(parent.getTranslateY() - yVelocity);
//        }
//        if (cameraDown || downPressed) {
//            parent.setTranslateY(parent.getTranslateY() - yVelocity);
//        }
//        if (cameraLeft || leftPressed) {
        parent.setTranslateX(parent.getTranslateX() - xVelocity);
//        }
//        if (cameraRight || rightPressed) {
//            parent.setTranslateX(parent.getTranslateX() - xVelocity);
//        }
    }

    private void setWalkAnimations() {
        String fileLocation = "resources/entities/Child/child";
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
//            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void resetCamera() {
        Pane parent = (Pane) this.getParent();
        double scaleX = Screen.getPrimary().getBounds().getMaxX();
        double scaleY = Screen.getPrimary().getBounds().getMaxY();
        parent.setTranslateY(-this.getY() + scaleY / 2);
        parent.setTranslateX(-this.getX() + scaleX / 2);
    }

    @Override
    void friction() {
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
        if (yVelocity == 0 && xVelocity == 0) {
            this.setImage(img);
        }
    }
}
