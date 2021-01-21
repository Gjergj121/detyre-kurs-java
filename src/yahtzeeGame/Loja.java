package yahtzeeGame;

import dice.DiceButton;

import java.security.cert.TrustAnchor;

public class Loja {
    private final int PIKET_E_SIPERME_INDEX = 6;
    private final int PIKET_E_POSHTME_INDEX = 15;
    private final int BONUS_INDEX = 7;
    private final int TOTAL_INDEX = 16;

    private int numriLojtareve;
    private Lojtar[] lojtaret;
    private int[] diceState;
    private int[][] pikePerKategoriPerLojtar;
    private boolean[][] kategoriteEZgjedhuraPerLojtar;
    private int currentTurn = 0;
    private int currentPlayer = 0;
    private int numriZarave;
    private int numriKategorive;
    private Category category;

    public Loja(int numriLojtareve, int numriZarave, int numriKategorive, Lojtar[] lojtaret) {
        this.numriLojtareve = numriLojtareve;
        this.lojtaret = lojtaret;
        this.numriZarave = numriZarave;
        this.numriKategorive = numriKategorive;
        this.diceState = new int[numriZarave];
        this.pikePerKategoriPerLojtar = new int[numriKategorive][numriLojtareve];
        this.kategoriteEZgjedhuraPerLojtar = new boolean[numriKategorive][numriLojtareve];
        this.category = new Category(numriZarave);
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

    public int getBONUS_INDEX() {
        return BONUS_INDEX;
    }

    public boolean isPikeESiperme() {
        boolean result = true;

        for (int i = 0; i < PIKET_E_SIPERME_INDEX; i++) {
            result &= kategoriteEZgjedhuraPerLojtar[i][currentPlayer];
        }

        return result && !kategoriteEZgjedhuraPerLojtar[PIKET_E_SIPERME_INDEX][currentPlayer];
    }

    public int llogaritDheUpdateBonus() {
        int bonus = category.llogaritBonus(pikePerKategoriPerLojtar, currentPlayer);
        updatePiket(BONUS_INDEX, bonus);
        return bonus;
    }

    public int llogaritDheUpdatePiketESiperme() {
        int piketESiperme = category.llogaritPiketSiperme(pikePerKategoriPerLojtar, currentPlayer);
        updatePiket(PIKET_E_SIPERME_INDEX, piketESiperme);
        return piketESiperme;
    }

    public int llogaritDheUpdateTotalin() {
        int total = category.llogaritTotal(pikePerKategoriPerLojtar, currentPlayer);
        updatePiket(TOTAL_INDEX, total);
        return total;
    }

    public int getTOTAL_INDEX() {
        return TOTAL_INDEX;
    }

    public int llogaritDheUpdatePiketEPoshtme() {
        int piketEPoshtme = category.llogaritPiketPoshtme(pikePerKategoriPerLojtar, currentPlayer);
        updatePiket(PIKET_E_POSHTME_INDEX, piketEPoshtme);
        return piketEPoshtme;
    }

    public boolean isPiketEPoshtme() {
        boolean result = true;

        for (int i = 8; i < PIKET_E_POSHTME_INDEX; i++) {
            result &= kategoriteEZgjedhuraPerLojtar[i][currentPlayer];
        }

        return result && !kategoriteEZgjedhuraPerLojtar[PIKET_E_POSHTME_INDEX][currentPlayer];
    }

    public int getPIKET_E_SIPERME_INDEX() {
        return PIKET_E_SIPERME_INDEX;
    }

    public int getPIKET_E_POSHTME_INDEX() {
        return PIKET_E_POSHTME_INDEX;
    }

    public boolean isEndGameForCurrentPlayer() {
        return kategoriteEZgjedhuraPerLojtar[PIKET_E_POSHTME_INDEX][currentPlayer] && kategoriteEZgjedhuraPerLojtar[PIKET_E_SIPERME_INDEX][currentPlayer];
    }

    public int[] llogaritTeGjithaKategoriteSipasRradhes() {
        return category.llogaritTeGjithaKategoriteSipasRradhes(getDiceState());
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

    public Lojtar lojtariFitues() {
        int max = -1;
        int bestPlayer = -1;

        for (int i = 0; i < getNumriLojtareve(); i++) {
            int temp = pikePerKategoriPerLojtar[TOTAL_INDEX][i];
            if (temp > max) {
                max = temp;
                bestPlayer = i;
            }
        }

        return lojtaret[bestPlayer];
    }

    public void save_totalin() {
        // TODO: Update databazen
        return;
    }
}
