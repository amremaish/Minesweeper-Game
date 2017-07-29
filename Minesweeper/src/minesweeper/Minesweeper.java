/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Amr
 */
public class Minesweeper {

    public static int TileWidth = 35, TileHeight = 35;
    public static int ScreenWidth, ScreenHeight;
    public static Cell cells[][];
    public static int NTile;
    public static Pane GameRoot = new Pane();
    public static BorderPane root = new BorderPane();
    public static Modes[] Mode = new Modes[]{Modes.beginner, Modes.intermediate, Modes.advanced};
    public static int NumberOfBombRest;
    public static Text bombText;
    public static int ModeIdx = 0;
    //----------------------------------------
    private final int BottomBarHeight = 30;
    private Text Time;
    private int min, sec;

    public void DrawBackGround() {
        root.setCenter(GameRoot);
        NTile = Mode[ModeIdx].getNumberOfCell();
        NumberOfBombRest = Mode[ModeIdx].getNumberOfBomb();
        ScreenWidth = TileWidth * NTile;
        ScreenHeight = TileHeight * NTile;
        cells = new Cell[NTile][NTile];
        for (int i = 0; i < NTile; i++) {
            for (int j = 0; j < NTile; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setX(i);
                cells[i][j].setY(j);
                cells[i][j].setTranslateX(j * (ScreenWidth / NTile));
                cells[i][j].setTranslateY(i * (ScreenHeight / NTile));
                GameRoot.getChildren().add(cells[i][j]);
            }
        }
    }

    private HBox BottomBar() {
        HBox box = new HBox();
        box.setSpacing(50);
        box.setStyle(" -fx-background-image: url(\"res/background.png \")");
        box.setPrefHeight(BottomBarHeight);
        bombText = new Text("  The rest Bomb : " + NumberOfBombRest);
        HBox.setMargin(bombText, new Insets(5, 0, 0, 0));
        bombText.setFont(Font.font(null, FontWeight.BOLD, 13));

        //--------------------------------------------------
        Time = new Text("Time : 0:0");
        min = 0;
        sec = 0;
        HBox.setMargin(Time, new Insets(5, 0, 0, 0));
        Time.setFont(Font.font(null, FontWeight.BOLD, 13));
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame TimeFrame = new KeyFrame(Duration.millis(1000), (ActionEvent event) -> {
            if (sec == 60) {
                min++;
                sec = 0;
            } else {
                sec++;
            }
            Time.setText("Time : " + min + " : " + sec);
        });
        timeline.getKeyFrames().add(TimeFrame);
        timeline.play();

        box.getChildren().addAll(bombText, Time);
        return box;
    }

    public void start(Stage primaryStage) {
        DrawBackGround();
        root.setBottom(BottomBar());
        Scene scene = new Scene(root, TileWidth * NTile, (TileHeight * NTile) + BottomBarHeight);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
        new BuildMap().Build();
    }

}
