/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import java.util.*;
import javafx.scene.paint.Paint;

/**
 *
 * @author 14048
 */
public class Sprite extends ImageView {

    boolean dead = false;
    double speed = 2;
    Random rand = new Random();
    public static ArrayList<Sprite> collisions = new ArrayList<>();
    public static ArrayList<Sprite> POV = new ArrayList<>();
    public static ArrayList<Sprite> players = new ArrayList<Sprite>();
    public static ArrayList<Sprite> enemies = new ArrayList<Sprite>();

    Image img;
    Image leftWalk, rightWalk, downWalk, upWalk, deadAnimation;
    boolean stunned = false;
    int height = 16, width = 16; //Aspect ratio is 4/3
//
    public double health = 100;
    public double maxHealth = 100;
    Rectangle currentHealth = new Rectangle(50, 10);
    private String resourceLocation = "resources/default/char_walk_left.gif";

    //
    Sprite(double x, double y, int width, int height) {
        currentHealth.setFill(Paint.valueOf("red"));
        this.height = height;
        this.width = width;
        setAssets(resourceLocation);
        setX(x);
        setY(y);
    }

    Sprite(double x, double y) {
        setX(x);
        setY(y);
    }

    void move(double x, double y) {
        if (stunned || dead) {
            return;
        }
        setX(getX() + x);
        setY(getY() + y);
    }

    public void addHealth(int add) {
        health += add;
        if (health > maxHealth) {
            health = maxHealth;
        }
        if (health <= 0) {
            this.dead = true;
        }
        // health / maxHealth
        double percentage = health / maxHealth;
        currentHealth.setWidth(50 * percentage);
    }

    public boolean isDead() {
        return dead;
    }

    //Private so only children of Sprite class
    private void setAssets(String fileLocation) {
        try {
            FileInputStream fis = new FileInputStream(fileLocation);
            img = new Image(fis, width, height, false, false);
            this.setImage(img);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
