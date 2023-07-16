/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.level;

import aasim.utilities.Vector;
import aasim.entities.Enemy;
import aasim.interactables.Interactable;
import aasim.maps.Map;
import aasim.entities.Player;
import aasim.utilities.RectangleVector;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

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

    //Player
    Player player = new Player(1024 / 2, 1024 / 2);
    //Player Controls - w  a  s  d
    boolean isEscapeMenu = false;
    Map currentMap;

    public Level() {
        super(new Pane());
        // what does this do???       
        //sp.setEventDispatcher(root.eventDispatcherProperty().get());
        currentMap = new Map(0, 0);
        player.setX(currentMap.spawnLocationX);
        player.setY(currentMap.spawnLocationY);
        root.getChildren().addAll(currentMap, player);
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

        //Add everything to SP
        sp.getChildren().addAll(blackBackground, root, escapeMenu);

        this.setRoot(sp);
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

//        this.setOnMouseClicked(eh -> {
//            Event.fireEvent(root, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
//                    0, 0, 0, MouseButton.PRIMARY, 1, false, false, false, false,
//                    false, false, false, false, false, false, null)
//            );
//        });
//        root.setOnMouseClicked(e -> {
//            double x = e.getX();
//            double y = e.getY();
//            player.attack(x, y);
//        });
    }

    //Variables for the update function
    double counter = 0;
    double safeX = 512, safeY = 512; //'Safe' x and y coordinates of the player, meaning they were not colliding with anything at this point.
    RectangleVector collision;

    private boolean update() {
        counter += .012;
        checkForCollision();
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

    //Probably should move this to the player class or sprite class.
    private void checkForCollision() {
        //Checks to see if Player's currently intersects any of the current map's walls.
        for (RectangleVector bounds : currentMap.getWallCollisions()) {
            if (player.intersects(bounds.rect.getBoundsInLocal())) {

                if (bounds.topLine.intersects(player.getBoundsInLocal())) {
                    player.setyVelocity(player.getyVelocity() * -1.1);
                    player.downDisabled = true;
                }
                if (bounds.bottomLine.intersects(player.getBoundsInLocal())) {
                    player.setyVelocity(player.getyVelocity() * -1.1);
                    player.upDisabled = true;
                }
                if (player.intersects(bounds.leftLine.getBoundsInParent())) {
                    player.setxVelocity(player.getxVelocity() * -1.1);
                    player.rightDisabled = true;

                }
                if (player.intersects(bounds.rightLine.getBoundsInParent())) {
                    player.setxVelocity(player.getxVelocity() * -1.1);
                    player.leftDisabled = true;

                }

            }
        }
        //Should not be intersecting any walls.
        //Check to see if intersecting 'interactables'
        for (Interactable bounds : currentMap.getInteractables()) {
            if (player.intersects(bounds.rect.getBoundsInLocal())) {
                //Player is colliding with the interactable.
                bounds.onCollision(player);
            }
        }

        //If load next map == true
        if (Map.loadNextMap) {
            nextMap();
        }
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
            System.out.println("yes");
        } else {
            escapeMenu.setVisible(false);
            System.out.println("no");
        }

    }

    private void nextMap() {
        Map.loadNextMap = false;
        //

        root.getChildren().clear();
        currentMap.setNextMap();
        currentMap = currentMap.nextMap;
        root.getChildren().addAll(currentMap, player);
        player.setX(currentMap.spawnLocationX);
        player.setY(currentMap.spawnLocationY);
        player.resetCamera();
    }
}
