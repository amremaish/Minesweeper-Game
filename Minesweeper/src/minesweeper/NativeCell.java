/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static minesweeper.Minesweeper.NTile;
import static minesweeper.Minesweeper.ScreenHeight;
import static minesweeper.Minesweeper.ScreenWidth;

/**
 *
 * @author Amr
 */
public class NativeCell extends Pane {

    private final String MouseEnterColor = "#008CBA";
    private final String MouseExitColor = "#00759C";
    private Rectangle rect;
    private Text TxtNum;
    private int x, y;
    private boolean showed , flag ;
    private Color Color;
    private ImageView imageView;

    public NativeCell() {
        // Draw Rectangle
        TxtNum = new Text();
        TxtNum.setFont(Font.font(null, FontWeight.BOLD, 17));
        TxtNum.setTranslateX((ScreenWidth / NTile) / 2 - 5);
        TxtNum.setTranslateY((ScreenHeight / NTile) / 2 + 5);
        rect = new Rectangle(ScreenWidth / NTile, ScreenHeight / NTile);
        rect.setStrokeWidth(1);
        rect.setStroke(Color.WHITE);
        rect.setFill(Color.web(MouseExitColor));
        getChildren().addAll(rect, TxtNum);
        ActionHover();
    }

    private void ActionHover() {
        rect.setOnMouseEntered(e -> {
            if (!showed) {
                rect.setFill(Color.web(MouseEnterColor));
            }
        });
        rect.setOnMouseExited(e -> {
            if (!showed) {
                rect.setFill(Color.web(MouseExitColor));
            }
        });
    }

    public void setImage(String Path) {
        Image image = new Image(Path);
        imageView = new ImageView(image);
        imageView.setX(6);
        imageView.setY(4);
        getChildren().add(imageView);
    }

    public void HideImage() {
        if (!this.getChildren().contains(imageView)) {
            return;
        }
        getChildren().remove(imageView);
    }

    public boolean isImageShowed() {
        return this.getChildren().contains(imageView);
    }

    public void ShowImage() {
        if (this.getChildren().contains(imageView)) {
            return;
        }
        getChildren().add(imageView);
    }

    public ImageView getImage() {
        return imageView;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return Color;
    }

    public void setColor(Color wallColor) {
        rect.setFill(wallColor);
        this.Color = wallColor;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public boolean isShowed() {
        return showed;
    }

    public void setShowed(boolean showed) {
        this.showed = showed;
    }

    public String getTxtNum() {
        return TxtNum.getText();
    }

    public void setTxtNum(String TxtNum) {
        this.TxtNum.setText(TxtNum);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    

}
