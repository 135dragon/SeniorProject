/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.attacks;

import aasim.entities.Player;
import aasim.entities.Sprite;
import aasim.level.Level;
import aasim.maps.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author 14048
 */
public class Attack extends Pane {

    private boolean deleteThis = false;
    Line line;
    Circle circle;
    private double atkDistance = 50;
    Sprite character;
    AnimationTimer timer;
//Default attack is to draw a line from player to X, Y

    public Attack(Sprite attacker, double mouseX, double mouseY, double attackRadius) {
        line = new Line(attacker.getX(), attacker.getY(), mouseX, mouseY);
        circle = new Circle(attackRadius);
        circle.setTranslateX(attacker.getX() + (attacker.getImage().getWidth() / 2));
        circle.setTranslateY(attacker.getY() + (attacker.getImage().getHeight() / 2));
        circle.setOpacity(0.5);
        circle.setFill(Color.RED);
        this.getChildren().addAll(line, circle);
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
//        //If the character attacking is the player (disables friendly fire)
        if (character.getName().equals("player")) {
            for (int i = 0; i < Sprite.currentMap.spawns.size(); i++) {
                Sprite x = Sprite.currentMap.spawns.get(i);
                if (line.intersects(x.getBoundsInLocal())) {
                    if (circle.getBoundsInParent().intersects(x.getBoundsInParent())) {
                        x.addHealth(-1);
                        x.attackedBy = character;
                        if (x.isDead()) {
                            Sprite.currentMap.spawns.remove(x);
                        }
                    }
                }
            }
        } else {
            //Otherwise, if the character attacking is not the player (disables friendly fire)
            if (line.intersects(Level.player.getBoundsInLocal())) {
                if (circle.getBoundsInParent().intersects(Level.player.getBoundsInParent())) {
                    Level.player.addHealth(-1); //Due to lack of invincibility frames, multiply this by 8 to get true damage
                }
            }
        }

//        //Attacks linger for a tenth of a second. 
        if (counter > 0.1) {
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
