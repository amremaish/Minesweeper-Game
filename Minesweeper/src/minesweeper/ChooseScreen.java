/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 ***********************************
 * Created By Amr Emaish 8/6/2017 Tel : 101709540 fb/AmrEmaish
 * github.com/amremaish ********************************
 */
public class ChooseScreen extends Application {

    private Stage stage;
    public static BorderPane ChoosePane;

    private final String BegTxt = "   Beginner";
    private final String interTxt = "Intermediate";
    private final String AdvText = "  Advanced";

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        BorderPane root = new BorderPane();
        ChoosePane = root;
        root.setStyle(" -fx-background-image: url(\"res/background.png \")");
        //----------------------------
        Text title = new Text("Minesweeper");
        StackPane sp = new StackPane(title);
        StackPane.setMargin(title, new Insets(10, 0, 0, 0));
        title.setFill(Color.web("#E3E3E3"));
        title.setEffect(new DropShadow());
        title.setFont(Font.font(null, FontWeight.BOLD, 30));
        root.setTop(sp);
        //----------------------------    
        VBox box = new VBox();
        box.setTranslateX(60);
        box.setTranslateY(50);
        root.setCenter(new StackPane(box));
        box.setSpacing(30);
        Text beg = new Text(BegTxt);
        ComConf(beg);
        Text inter = new Text(interTxt);
        ComConf(inter);
        Text adv = new Text(AdvText);
        ComConf(adv);
        box.getChildren().addAll(beg, inter, adv);
        Text Created = new Text("   Created By \n  Amr Emaish ");
        Created.setTranslateX(120);
        Created.setEffect(new DropShadow());
        Created.setFill(Color.BLUE);
        Created.setFont(Font.font(null, FontWeight.BOLD, 13));
        root.setBottom(Created);
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Select Level");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Text ComConf(Text t) {
        t.setFill(Color.web("#01DF01"));
        t.setEffect(new DropShadow());
        t.setFont(Font.font(null, FontWeight.BOLD, 30));
        t.setOnMouseEntered(e -> {
            addEffect(t);
        });
        t.setOnMouseExited(e -> {
            t.setEffect(null);
            t.setEffect(new DropShadow());
        });
        t.setOnMouseClicked(e -> {
            if (t.getText().equals(BegTxt)) {
                Minesweeper.ModeIdx = 0;
            } else if (t.getText().equals(interTxt)) {
                Minesweeper.ModeIdx = 1;
            } else if (t.getText().equals(AdvText)) {
                Minesweeper.ModeIdx = 2;
            }
            stage.close();
            Minesweeper min = new Minesweeper();
            min.start(new Stage());
        });
        return t;
    }

    public static void main(String[] args) {
        launch();
    }

    public void addEffect(Text text) {
        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);
        DropShadow ds = new DropShadow();
        ds.setColor(Color.web("#0B610B"));
        ds.setOffsetX(6);
        ds.setOffsetY(5);
        ds.setRadius(10);
        ds.setSpread(.59);

        blend.setBottomInput(ds);

        DropShadow ds1 = new DropShadow();
        ds1.setColor(Color.web("#31B404"));
        ds1.setRadius(20);
        ds1.setSpread(0.2);

        Blend blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);

        InnerShadow is = new InnerShadow();
        is.setColor(Color.web("#31B404"));
        is.setRadius(20);
        is.setChoke(0.8);
        blend2.setBottomInput(is);

        InnerShadow is1 = new InnerShadow();
        is1.setColor(Color.web("#31B404"));
        is1.setRadius(5);
        is1.setChoke(0.4);
        blend2.setTopInput(is1);

        text.setEffect(blend);
    }

}
