/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Random;
import static minesweeper.Minesweeper.Mode;
import static minesweeper.Minesweeper.ModeIdx;
import static minesweeper.Minesweeper.NTile;
import static minesweeper.Minesweeper.cells;

/**
 *
 * @author Amr
 */
public class BuildMap {

    public static char BombMap[][];

    public void Build() {
        BombMap = new char[NTile][NTile];
        PutBombs();
        PutNumbers();
    }

    private void PutBombs() {
        int numberOfBomb = Mode[ModeIdx].getNumberOfBomb();
        while (numberOfBomb-- > 0) {
            while (true) {
                int i = new Random().nextInt(NTile);
                int j = new Random().nextInt(NTile);
                if (BombMap[i][j] != '#') {
                    cells[i][j].setBomb(true);
                    cells[i][j].setImage("res/bomb.png");
                    cells[i][j].HideImage();
                    BombMap[i][j] = '#';
                    break;
                }
            }
        }
    }

    private void PutNumbers() {

        for (int i = 0; i < NTile; i++) {
            for (int j = 0; j < NTile; j++) {
                if (BombMap[i][j] == '#') {
                    BombMap[i][j] = '#';
                    continue;
                }
                int N = 0;
                if (isValid(i + 1, j) && BombMap[i + 1][j] == '#') {
                    N++;
                }

                if (isValid(i - 1, j) && BombMap[i - 1][j] == '#') {
                    N++;
                }

                if (isValid(i, j + 1) && BombMap[i][j + 1] == '#') {
                    N++;
                }

                if (isValid(i, j - 1) && BombMap[i][j - 1] == '#') {
                    N++;
                }

                if (isValid(i + 1, j + 1) && BombMap[i + 1][j + 1] == '#') {
                    N++;
                }

                if (isValid(i - 1, j - 1) && BombMap[i - 1][j - 1] == '#') {
                    N++;
                }

                if (isValid(i + 1, j - 1) && BombMap[i + 1][j - 1] == '#') {
                    N++;
                }

                if (isValid(i - 1, j + 1) && BombMap[i - 1][j + 1] == '#') {
                    N++;
                }
                BombMap[i][j] = (char) (N + '0');
                cells[i][j].setNumber(N);
                cells[i][j].setImage("res/flag.png");
                cells[i][j].HideImage();
            }
        }
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < NTile && j >= 0 && j < NTile;
    }

}
