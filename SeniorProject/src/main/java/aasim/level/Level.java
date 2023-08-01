/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.level;

import aasim.maps.Map;
import aasim.entities.Player;
import aasim.entities.Sprite;
import aasim.utilities.RectangleVector;
import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author 14048
 */
public class Level extends Scene {
    //This is the actual playable level.
    //This level will load and 'deload' each map.

    //level menu components
    StackPane sp = new StackPane(); //Layering Mechanism
    Pane root = new Pane(); // The Actual Level
    GridPane escapeMenu = new GridPane(); // Escape Menu 
    GridPane inventoryMenu = new GridPane();
    //Player
    public static Player player;
    //Player Controls - w  a  s  d
    boolean isEscapeMenu = false;
    Map currentMap;
    public static MediaPlayer combatMusic;
    public static MediaPlayer backgroundMusic;

    public Level() {
        super(new Pane());
        // what does this do???       
        //sp.setEventDispatcher(root.eventDispatcherProperty().get());
        currentMap = new Map(0, 0);
        Sprite.currentMap = currentMap;
        player = new Player(1024 / 2, 1024 / 2);
        player.setX(currentMap.spawnLocationX);
        player.setY(currentMap.spawnLocationY);
        Pane playerUI = new Pane();
        playerUI.getChildren().addAll(player.healthBar, player.healthDisplay, player.staminaBar, player.staminaDisplay, player.sanityBar, player.sanityDisplay);
        playerUI.setTranslateX(0);
        playerUI.setTranslateY(0);

        root.getChildren().addAll(currentMap, player);
        for (Sprite sprite : currentMap.spawns) {
            root.getChildren().add(sprite);
        }
        player.resetCamera();

        //Creates the escape menu
        createEscapeMenu();

        //Starts the animation timer
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (update()) {
                    this.stop();
                }
            }
        };
        timer.start();
        //Add Listeners to Controls (wasd, lClick)
        setListeners();

        //Set a default black background
        Pane blackBackground = new Pane();
        blackBackground.setStyle("-fx-background-color: black;");
        playerUI.setPickOnBounds(false);
        //Add everything to SP
        sp.getChildren().addAll(blackBackground, root, playerUI, escapeMenu);
        this.setRoot(sp);
        //

        String path = "./resources/music/combat.mp3";
        File initialFile = new File(path);
        path = (initialFile.toURI().toString());
//        
        Media hit = new Media(path);
        combatMusic = new MediaPlayer(hit);
        combatMusic.setVolume(0.1);

        path = "./resources/music/nocturn.mp3";
        initialFile = new File(path);
        path = (initialFile.toURI().toString());
        hit = new Media(path);
        backgroundMusic = new MediaPlayer(hit);
        backgroundMusic.play();
        backgroundMusic.setVolume(1);

    }

    private void setListeners() {
        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    player.leftPressed = true;
                    break;
                case D:
                    player.rightPressed = true;
                    break;
                case W:
                    player.upPressed = true;
                    break;
                case S:
                    player.downPressed = true;
                    break;
                case ESCAPE:
                    isEscapeMenu = !isEscapeMenu;
                    isEscapeMenu();
                    break;
                case SPACE:
                    player.resetCamera();
                    break;
            }
        });
        this.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case A:
                    player.leftPressed = false;
                    break;
                case D:
                    player.rightPressed = false;
                    break;
                case W:
                    player.upPressed = false;
                    break;
                case S:
                    player.downPressed = false;
                    break;
            }
        });
//        this.setOnScroll(eh -> {
//            double scrollAmt = 0;
//            if (eh.getDeltaY() > 0) {
//                scrollAmt = .1;
//            } else {
//                scrollAmt = -.1;
//            }
//            if (root.getScaleY() > 0 && root.getScaleY() < 10) {
//                root.setScaleY(root.getScaleY() + scrollAmt);
//                root.setScaleX(root.getScaleX() + scrollAmt);
//            } else {
//                root.setScaleY(1);
//                root.setScaleX(1);
//            }
//
//        });

        root.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();
            player.attack(x, y, 100);
        });

    }

    //Variables for the update function
    double counter = 0;
    double safeX = 512, safeY = 512; //'Safe' x and y coordinates of the player, meaning they were not colliding with anything at this point.
    RectangleVector collision;

    private boolean update() {
//        counter += .012;
        //Game Over
        if (player.isDead()) {
            for (Sprite x : Sprite.currentMap.spawns) {
                x.timer.stop();
            }
        }

        if (Map.loadNextMap) {
            nextMap();
        }
        //Only runs every 5 seconds
//        if (counter > 5) {
        // This code allows for the new 'map' to load at the player location and places the player on top of it.
//            root.getChildren().remove(player);
//            root.getChildren().add(new Map(player.getX(), player.getY()));
//            root.getChildren().add(player);
//            counter = 0;
//        }
        return false;
    }

    private void createEscapeMenu() {
        //Escape Menu
        escapeMenu.setVisible(false);
        escapeMenu.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), CornerRadii.EMPTY, Insets.EMPTY)));
        escapeMenu.setOpacity(0.9);
        Label contBtn = new Label("Continue");
        Label exitBtn = new Label("Exit");
        VBox buttonContainer = new VBox(contBtn, exitBtn);
        escapeMenu.getChildren().add(buttonContainer);
        contBtn.setOnMouseClicked(eh -> {
            isEscapeMenu = false;
            isEscapeMenu();
        });
        exitBtn.setOnMouseClicked(eh -> {
            System.exit(0);
        });

        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setAlignment(Pos.CENTER);
        escapeMenu.setAlignment(Pos.CENTER);
    }

    private void isEscapeMenu() {

        if (isEscapeMenu) {
            escapeMenu.setVisible(true);
        } else {
            escapeMenu.setVisible(false);
        }

    }

    private void nextMap() {
        Map.loadNextMap = false;
        //

        //
        //
//        for (Object a : root.getChildren()) {
//            System.out.println("This is in root: " + a);
//        }
        root.getChildren().clear();

//        for (Object a : root.getChildren()) {
//            System.out.println("This is in root after clearing: " + a);
//        }
//
        for (Sprite a : currentMap.spawns) {
            System.out.println(a + "'s timer just stopped");
            a.timer.stop();
        }

        currentMap.spawns.clear();

        System.gc();
        //
        currentMap.setNextMap();
        currentMap = currentMap.nextMap;

        root.getChildren().addAll(currentMap, player);
        for (Sprite sprite : currentMap.spawns) {
            root.getChildren().add(sprite);
        }
        player.setX(currentMap.spawnLocationX);
        player.setY(currentMap.spawnLocationY);
        player.resetCamera();
        Sprite.currentMap = currentMap;
    }
}
