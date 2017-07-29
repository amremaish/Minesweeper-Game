/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author Amr
 */
public enum Modes {

    beginner(9,20), intermediate(12,40), advanced(15,60);

    private int numberOfCell ,NumberOfBomb;

    Modes(int numberOfCell,int NumberOfBomb) {
        this.numberOfCell = numberOfCell;
         this.NumberOfBomb = NumberOfBomb;
    }

    public int getNumberOfCell() {
        return numberOfCell;
    }

    public int getNumberOfBomb() {
        return NumberOfBomb;
    }

   
    
}
