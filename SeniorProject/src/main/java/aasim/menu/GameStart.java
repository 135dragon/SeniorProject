/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aasim.menu;

import aasim.level.Level;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameStart extends Application {
    
    Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane bp = new BorderPane();
        scene = new Scene(bp);
        scene.getStylesheets().add("file:General.css");

        //Background Image
//        File myFile = new File("background.png");
//        URL url = myFile.toURI().toURL();
//        bp.setStyle("-fx-background-image: url('" + url.toExternalForm() + "'); -fx-background-size: cover;");
        //Menu Item + Options
        VBox container = new VBox();
        container.setMaxSize(250, 225);
        container.setId("MainMenu");
        Label title = new Label("\nGame Demo");
        title.setId("title");
        Label undertitle = new Label("Demo of Game Version 0.01");
        Separator separator1 = new Separator();
        separator1.setPadding(new Insets(10));
        Label play = new Label("\tNew Game");
        Label cont = new Label("\tCredits");
        Label settings = new Label("\tSettings");
        Label exit = new Label("\tExit");
        
        HBox playContainer = new HBox(play);
        HBox contContainer = new HBox(cont);
        HBox settingsContainer = new HBox(settings);
        HBox exitContainer = new HBox(exit);
        
        playContainer.setId("button");
        contContainer.setId("button");
        settingsContainer.setId("button");
        exitContainer.setId("button");
        container.getChildren().addAll(title, undertitle, separator1, playContainer, contContainer, settingsContainer, exitContainer);
        container.setAlignment(Pos.TOP_CENTER);
        bp.setCenter(container);

        //
        //
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        
        playContainer.setOnMouseClicked(eh -> {
            scene = new Level();
            stage.setScene(scene);
            
            stage.hide();
            stage.setMaximized(false);
            stage.setMaximized(true);
            stage.show();
        });
        contContainer.setOnMouseClicked(eh -> {
            Pane creditsRoot = new Pane();
            scene = new Scene(creditsRoot);
            Label calciumTrice = new Label("Art for Statue, Skeleton, Deer and Bear provided by Calciumtrice under Creative Commons License. See more of their work at  work at calciumtrice.tumblr.com!\n"
                    + " https://creativecommons.org/licenses/by/3.0/");
            Label wasp = new Label("Bee animations provided by Spring, Puffolotti");
            Label backgMusic = new Label("Background music is Nocturne in B flat minor, Op. 9 no. 1 found in Public Domain through https://musopen.org/music/108-nocturnes-op-9/#recordings ");
            Label combMusic = new Label("Combat Music is Racing Sport Gaming | RACING by Alex-Productions | https://onsound.eu/\n"
                    + "Music promoted by https://www.chosic.com/free-music/all/\n"
                    + "Creative Commons CC BY 3.0\n"
                    + "https://creativecommons.org/licenses/by/3.0/");
            
            Button goBack = new Button("Go back?");
            VBox list = new VBox(calciumTrice, wasp, backgMusic, combMusic, goBack);
            creditsRoot.getChildren().add(list);
            bp.setCenter(creditsRoot);
            goBack.setOnMouseClicked(e -> {
                bp.setCenter(container);
            });
            
        });
        settingsContainer.setOnMouseClicked(eh -> {
            
        });
        exitContainer.setOnMouseClicked(eh -> {
            System.exit(0);
        });
        
    }
    
    private double counter = 0;
    
    public static void main1(String[] args) {
        launch(args);
    }
}
