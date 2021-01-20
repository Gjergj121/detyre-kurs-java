package yahtzeeGame;

import dice.DiceButton;

import java.security.cert.TrustAnchor;

public class Loja {
    private int numriLojtareve;
    private Lojtar[] lojtaret;
    private int[] diceState;
    private int[][] pikePerKategoriPerLojtar;
    private boolean[][] kategoriteEZgjedhuraPerLojtar;
    private int currentTurn = 0;
    private int currentPlayer = 0;
    private int numriZarave;
    private int numriKategorive;

    public Loja(int numriLojtareve, int numriZarave, int numriKategorive, Lojtar[] lojtaret) {
        this.numriLojtareve = numriLojtareve;
        this.lojtaret = lojtaret;
        this.numriZarave = numriZarave;
        this.numriKategorive = numriKategorive;
        this.diceState = new int[numriZarave];
        this.pikePerKategoriPerLojtar = new int[numriKategorive][numriLojtareve];
        this.kategoriteEZgjedhuraPerLojtar = new boolean[numriKategorive][numriLojtareve];
    }

    public boolean[][] getKategoriteEZgjedhuraPerLojtar() {
        return kategoriteEZgjedhuraPerLojtar;
    }

    public void setKategoriteEZgjedhuraPerLojtar(boolean[][] kategoriteEZgjedhuraPerLojtar) {
        this.kategoriteEZgjedhuraPerLojtar = kategoriteEZgjedhuraPerLojtar;
    }

    public int getNumriLojtareve() {
        return numriLojtareve;
    }

    public Lojtar[] getLojtaret() {
        return lojtaret;
    }

    public int[] getDiceState() {
        return diceState;
    }

    public void setDiceState(int[] diceState) {
        this.diceState = diceState;
    }

    public int[][] getPikePerKategoriPerLojtar() {
        return pikePerKategoriPerLojtar;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumriZarave() {
        return numriZarave;
    }

    public int getNumriKategorive() {
        return numriKategorive;
    }

    public void updateDiceState(int i, int randValue) {
        this.diceState[i] = randValue;
    }

    public void nextTurn() {
        currentTurn++;
    }

    public void updatePiket(int kategoriIndex, int piket) {
        pikePerKategoriPerLojtar[kategoriIndex][currentPlayer] = piket;
        kategoriteEZgjedhuraPerLojtar[kategoriIndex][currentPlayer] = true;

    }

    public void nextPlayer() {
        currentPlayer = currentPlayer == numriLojtareve - 1 ? 0 : currentPlayer + 1;
        currentTurn = 0;
    }

    public int getPrevPlayer() {
        return currentPlayer == 0 ? numriLojtareve - 1 : currentPlayer - 1;
    }

    public int getNextPlayer() {
        return currentPlayer == numriLojtareve - 1 ? 0 : currentPlayer + 1;
    }
}
