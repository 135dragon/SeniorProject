/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.attacks;

import aasim.entities.Player;
import aasim.entities.Sprite;
import aasim.level.Level;
import aasim.maps.Map;
import aasim.utilities.Vector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author 14048
 */
public class HoneyAttack extends Pane {

    Line line;
    Sprite character;
    double slope, inverseSlope, xDistanceToTravel, yDistanceToTravel;
    AnimationTimer timer;
    boolean left, up;
    int speed = 10;
    ImageView img;

//Default attack is to draw a line from player to X, Y
    public HoneyAttack(Sprite attacker, double mouseX, double mouseY, double attackRadius) {
        line = new Line(attacker.getX(), attacker.getY(), mouseX, mouseY);
        try {
            FileInputStream fis = new FileInputStream("resources/entities/Bee/bee" + "AttackProjectile.png");
            Image a = new Image(fis, 32, 32, false, false);
            img = new ImageView(a);
            img.setTranslateX(attacker.getX() + (attacker.getImage().getWidth() / 2));
            img.setTranslateY(attacker.getY() + (attacker.getImage().getHeight() / 2));
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        this.getChildren().addAll(line, img);

        slope = Vector.slopeCalc(attacker.getX(), attacker.getY(), mouseX, mouseY);
        inverseSlope = 1 / slope;

        if (mouseX < attacker.getX()) {
            left = true;
        } else {
            left = false;
        }

        if (mouseY < attacker.getY()) {
            up = true;
        } else {
            up = false;
        }
//        
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        character = attacker;
    }

    double counter = 0;

    void update() {
        //If left = true, increment x by -1, and y by -slope
        if (Math.abs(slope) < 1) {
            if (left) {
                img.setTranslateX(img.getTranslateX() - (speed));
                img.setTranslateY(img.getTranslateY() - (slope * speed));
            } else {
                img.setTranslateX(img.getTranslateX() + (speed));
                img.setTranslateY(img.getTranslateY() + (slope * speed));
            }
        } else {
            if (up) {
                img.setTranslateX(img.getTranslateX() - (inverseSlope * speed));
                img.setTranslateY(img.getTranslateY() - (speed));
            } else {
                img.setTranslateX(img.getTranslateX() + (inverseSlope * speed));
                img.setTranslateY(img.getTranslateY() + (speed));
            }
        }
//        //If the character attacking is the player (disables friendly fire)
        if (character.getName().equals("player")) {
            for (int i = 0; i < Sprite.currentMap.spawns.size(); i++) {
                Sprite x = Sprite.currentMap.spawns.get(i);
                if (img.getBoundsInParent().intersects(x.getBoundsInParent())) {
                    x.addHealth(-1);
                    x.attackedBy = character;
                    if (x.isDead()) {
                        Sprite.currentMap.spawns.remove(x);
                    }
                }
            }
        } else {
            //Otherwise, if the character attacking is not the player (disables friendly fire)
            if (img.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
                Level.player.addHealth(-1); //Due to lack of invincibility frames, multiply this by 8 to get true damage
            }

        }

//        //Attacks linger for a tenth of a second. 
        if (counter > 3) {
//            System.out.println(counter);
            try {
                ((Pane) this.getParent()).getChildren().remove(this);
                character.alreadyAttacking = false;
            } catch (NullPointerException e) {

            } finally {
                timer.stop();
            }

//            character.timer.stop();
        }
        counter += 0.016;
    }
//

}
