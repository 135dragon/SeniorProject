/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.items;

import aasim.entities.Sprite;
import aasim.interactables.Interactable;
import aasim.maps.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 14048
 */
public class Item extends Interactable {

    private AnimationTimer timer;
    private ImageView image;
    String resourceLocation = "resources/items/Gold-Coin.png";

    double xMovement;

    public Item(double x, double y, int width, int height) {
        xMovement = Sprite.rand.nextInt(5) - 2.5;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(resourceLocation);
            Image img = new Image(fis, width, height, false, false);
            image = new ImageView(img);
            this.getChildren().add(image);
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    update();
                }
            };
            image.setTranslateX(x);
            image.setTranslateY(y);
            bounds = image.getBoundsInParent();
            timer.start();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    double counter = 0;

    private void update() {
        counter += 0.016;
        if (counter < 1) {
            image.setTranslateY(image.getTranslateY() - 2); //
            image.setTranslateX(image.getTranslateX() + (xMovement * 0.9)); //
            bounds = image.getBoundsInParent();
        } else {
            image.setTranslateY(image.getTranslateY() + 2.5);
            image.setTranslateX(image.getTranslateX() - (xMovement * 1.2)); //
            bounds = image.getBoundsInParent();
        }
        if (counter > 2) {
            bounds = image.getBoundsInParent();
            timer.stop();
            counter = 0;
        }
    }

    @Override
    public void onCollision(Sprite sprite) {
        sprite.setMoney(sprite.getMoney() + 1);
//        System.out.println(sprite.getMoney());
        ((Pane) ((Pane) this.getScene().getRoot()).getChildren().get(1)).getChildren().remove(this);
        Sprite.currentMap.interactables.remove(this);
        timer.stop();
    }
}
