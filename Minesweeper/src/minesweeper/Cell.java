/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static minesweeper.BuildMap.BombMap;
import static minesweeper.ChooseScreen.ChoosePane;
import static minesweeper.Minesweeper.GameRoot;
import static minesweeper.Minesweeper.NTile;
import static minesweeper.Minesweeper.NumberOfBombRest;
import static minesweeper.Minesweeper.bombText;
import static minesweeper.Minesweeper.cells;

/**
 *
 * @author Amr
 */
public class Cell extends NativeCell {

    private int number;
    private boolean Bomb;
    private final String ShowedColor = "#D8D8D8";
    private boolean vis[][];
    private boolean FlepFlag;

    public Cell() {
        vis = new boolean[NTile][NTile];
        ActionClick();
    }

    private void ActionClick() {
        this.getRect().setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                MouseLeftAction();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                MouseRightAction();
            }
        });

    }

    private void MouseLeftAction() {
        this.setShowed(true);
        this.getRect().setFill(Color.web(ShowedColor));
        if (Bomb) { // you lost
            for (int i = 0; i < NTile; i++) {
                for (int j = 0; j < NTile; j++) {
                    if (BombMap[i][j] == '#') {

                        cells[i][j].setImage("res/bomb.png");
                        cells[i][j].ShowImage();
                    }
                }
            }
            FxDialogs.showInformation("You Lost :/", "Sorry you have been lost , press ok to go choose screen");
            restart();
            return;
        } else if (number == 0) {
            Platform.runLater(() -> {
                this.setTxtNum("");
                FindZeros(this.getX(), this.getY());
            });
            return;
        }
        this.setTxtNum(this.number + "");
    }

    private void MouseRightAction() {

        if (isShowed()) {
            return;
        }

        if (!FlepFlag) {
            setImage("res/flag.png");
            this.ShowImage();
            NumberOfBombRest--;
            this.setFlag(true);
            if (NumberOfBombRest == 0) {
                boolean Done = true;
                for (int i = 0; i < NTile; i++) {
                    for (int j = 0; j < NTile; j++) {
                        if (!cells[i][j].isFlag() && cells[i][j].isBomb()) {
                            Done = false;
                        }
                    }
                }
                if (Done) {
                    FxDialogs.showInformation("congratulations , you win :D ", "You are the Winner");
                } else {
                    FxDialogs.showInformation("You Lost :/", "Sorry you have been lost , press ok to go choose screen");

                }
                restart();
            }
        } else {
            if (this.isImageShowed()) {
                NumberOfBombRest++;
            }
            this.setFlag(false);
            this.HideImage();
        }
        FlepFlag = !FlepFlag;
        bombText.setText("  The rest Bomb : " + NumberOfBombRest);
    }

    private void restart() {
        //----------------------------------------------- close this window 
        Stage mins = (Stage) GameRoot.getScene().getWindow();
        mins.close();
        Scene scn = GameRoot.getScene();
        scn.setRoot(new Pane());
        mins = null;
        //----------------------------------------------- reopen choose window
        Stage choose = (Stage) ChoosePane.getScene().getWindow();
        choose.show();
    }

    /* Backtraking Solution 
     *  
     * 
     */
    private void FindZeros(int i, int j) {
        if (!isValid(i, j) || BombMap[i][j] == '#' || vis[i][j]) {
            return;
        }
        if (BombMap[i][j] != '0') {
            if (isValidToSee(i, j)) {
                cells[i][j].setShowed(true);
                cells[i][j].getRect().setFill(Color.web(ShowedColor));
                cells[i][j].setTxtNum(cells[i][j].getNumber() + "");
            }
            return;
        }

        vis[i][j] = true;
        FindZeros(i + 1, j);
        FindZeros(i - 1, j);
        FindZeros(i, j + 1);
        FindZeros(i, j - 1);
        FindZeros(i + 1, j + 1);
        FindZeros(i - 1, j - 1);
        FindZeros(i - 1, j + 1);
        FindZeros(i + 1, j - 1);
        vis[i][j] = false;

        if (BombMap[i][j] == '0') {
            cells[i][j].setShowed(true);
            cells[i][j].getRect().setFill(Color.web(ShowedColor));
            cells[i][j].setTxtNum("");
        }

    }

    private boolean isValidToSee(int i, int j) {

        boolean ret = (isValid(i + 1, j) && BombMap[i + 1][j] == '0')
                || (isValid(i - 1, j) && BombMap[i - 1][j] == '0')
                || (isValid(i, j + 1) && BombMap[i][j + 1] == '0')
                || (isValid(i, j - 1) && BombMap[i][j - 1] == '0')
                || (isValid(i + 1, j + 1) && BombMap[i + 1][j + 1] == '0')
                || (isValid(i - 1, j - 1) && BombMap[i - 1][j - 1] == '0')
                || (isValid(i + 1, j - 1) && BombMap[i + 1][j - 1] == '0')
                || (isValid(i - 1, j + 1) && BombMap[i - 1][j + 1] == '0');
        return ret;
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < NTile && j >= 0 && j < NTile;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBomb() {
        return Bomb;
    }

    public void setBomb(boolean Bomb) {
        this.Bomb = Bomb;
    }

}
