/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.entities;

import aasim.attacks.Attack;
import aasim.attacks.HoneyAttack;
import aasim.interactables.Interactable;
import aasim.items.Item;
import aasim.maps.Map;
import aasim.utilities.RectangleVector;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author 14048
 */
public class Sprite extends ImageView {
    //Sprites include player, enemies, and npcs

    //
    //
    String name = "";
    boolean frozen = false;
    boolean dead = false;
    //Where to find sprite images
    private String resourceLocation = "resources/";
    Image img; //Base
    Image leftWalk, rightWalk, downWalk, upWalk, deadAnimation; //
    double height = 16, width = 16; //Aspect ratio is 4/3
    //Health and Stamina
    private final int barWidth = 150;
    private final int barHeight = 25;
    public double currHealth = 100; //Current Health
    public double maxHealth = 100; //Max Health
    public Label healthDisplay = new Label("Health: " + currHealth);
    public Rectangle healthBar = new Rectangle(0, 0, barWidth, barHeight);
    public Label staminaDisplay = new Label("Stamina: " + currHealth);
    public Rectangle staminaBar = new Rectangle(0, 0, barWidth, barHeight);
    public Label sanityDisplay = new Label("Sanity: " + currHealth);
    public Rectangle sanityBar = new Rectangle(0, 0, barWidth, barHeight);
    public double currStamina = 100; //Current Health
    public double maxStamina = 100; //Max Health
    private int money;

    //
    public static Random rand = new Random();

    //Movement handling
    double yVelocity = 0, xVelocity = 0;
    public boolean upDisabled, leftDisabled, rightDisabled, downDisabled;
    public boolean upPressed, leftPressed, rightPressed, downPressed;
    public boolean alreadyAttacking = false;
    double atkCounter = 0;
    double maxVelocity = 5;
    //
    public Sprite target;
    public Sprite attackedBy;
//
    //Current Map
    public static Map currentMap;

    //Timer
    public AnimationTimer timer;

    Sprite(double x, double y, double width, double height) {
        this.height = height;
        this.width = width;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (dead) {
                    this.stop();
                }
                update();
            }
        };
        healthBar.setFill(Color.RED);
        staminaBar.setFill(Color.GREEN);
        sanityBar.setFill(Color.PURPLE);

        healthDisplay.setTranslateX(0);
        healthDisplay.setTranslateY(0);
        staminaDisplay.setTranslateX(0);
        staminaDisplay.setTranslateY(barHeight);
        sanityDisplay.setTranslateX(0);
        sanityDisplay.setTranslateY(barHeight * 2);
        healthBar.setTranslateX(0);
        healthBar.setTranslateY(0);
        staminaBar.setTranslateX(0);
        staminaBar.setTranslateY(barHeight);
        sanityBar.setTranslateX(0);
        sanityBar.setTranslateY(barHeight * 2);
        setX(x);
        setY(y);
        timer.start();
    }

    //Every sprite should override this.
    void update() {
    }

    ArrayList<Item> itemDrop() {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < this.money + 2; i++) {
            Item x = new Item(this.getX(), this.getY(), 16, 16); //Money
            list.add(x);
        }
        return list;
    }

    public void attack(double mouseX, double mouseY, double attackRadius) {

        Attack a1 = new Attack(this, mouseX, mouseY, attackRadius);
        ((Pane) this.getParent()).getChildren().add(a1);

    }

    void friction() {
        if (yVelocity > 0) {
            yVelocity -= 0.25;
        }

        if (xVelocity > 0) {
            xVelocity -= 0.25;
            this.setImage(rightWalk);
        }

        if (yVelocity < 0) {
            yVelocity += 0.25;
        }

        if (xVelocity < 0) {
            xVelocity += 0.25;
            this.setImage(leftWalk);
        }

        if (yVelocity == 0 && xVelocity == 0) {
            this.setImage(img);
        }
    }

    //Should not be overridden
    double movementCounter = 0;

    void movementHandling() {
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

        if (Math.abs(xVelocity) < 0.25) {
            xVelocity = 0;
        }
        if (Math.abs(yVelocity) < 0.25) {
            yVelocity = 0;
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

    void checkForCollision() {
        //Checks to see if this's currently intersects any of the current map's walls.
        for (RectangleVector bounds : currentMap.getWallCollisions()) {
            if (this.intersects(bounds.rect.getBoundsInLocal())) {

                if (bounds.topLine.intersects(this.getBoundsInLocal())) {
                    this.setyVelocity(this.getyVelocity() * -1);
                    this.downDisabled = true;
                }
                if (bounds.bottomLine.intersects(this.getBoundsInLocal())) {
                    this.setyVelocity(this.getyVelocity() * -1);
                    this.upDisabled = true;
                }
                if (this.intersects(bounds.leftLine.getBoundsInParent())) {
                    this.setxVelocity(this.getxVelocity() * -1);
                    this.rightDisabled = true;

                }
                if (this.intersects(bounds.rightLine.getBoundsInParent())) {
                    this.setxVelocity(this.getxVelocity() * -1);
                    this.leftDisabled = true;

                }

            }
        }
        //Should not be intersecting any walls.
        //Check to see if intersecting 'interactables'
        for (int i = 0; i < currentMap.interactables.size(); i++) {
            Interactable bounds = currentMap.interactables.get(i);
            if (bounds != null) {
                if (this.intersects(bounds.bounds)) {
                    //this is colliding with the interactable.
                    bounds.onCollision(this);
                }
            }
        }

    }

    int deathCounter = 0;

    private void onDeath() {
        if (dead) {
            deathCounter++;
            PauseTransition hide = new PauseTransition(Duration.seconds(0.5));
            hide.setOnFinished(eh -> {
                this.setVisible(false);
                ((Pane) this.getScene().getRoot()).getChildren().remove(this);
            });
            hide.play();
            timer.stop();

            ArrayList<Item> drops = itemDrop();
            for (Item x : drops) {
                ((Pane) ((Pane) this.getScene().getRoot()).getChildren().get(1)).getChildren().add(x);
                Sprite.currentMap.interactables.add(x);
            }
        }
    }

    void move(double x, double y) {
        if (dead) {
            return;
        }
        setX(getX() + x);
        setY(getY() + y);
    }

    //
    public void addHealth(int add) {
        currHealth += add;
        if (currHealth > maxHealth) {
            currHealth = maxHealth;
        }
        if (currHealth <= 0) {
            this.dead = true;
            this.onDeath();
        }
        // health / maxHealth
        double percentage = currHealth / maxHealth;
        healthBar.setWidth(percentage * 150);
        healthDisplay.setText("Health: " + currHealth);
    }

    public void addStamina(int add) {
        currStamina += add;
        if (currStamina > maxStamina) {
            currStamina = maxStamina;
        }

        // health / maxHealth
        double percentage = currHealth / maxHealth;
    }

    //
    public boolean isDead() {
        return dead;
    }

    //Getters and Setters
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
