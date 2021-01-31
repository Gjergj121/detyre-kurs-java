package yahtzeeGame;

import java.util.ArrayList;
import java.util.List;

public class Loja {
    private int id;
    private final int PIKET_E_SIPERME_INDEX = 6;
    private final int PIKET_E_POSHTME_INDEX = 15;
    private final int BONUS_INDEX = 7;
    private final int TOTAL_INDEX = 16;
    private final int NUMRI_KATEGORIVE = 17;
    private final int NUMRI_ZARAVE = 5;

    private int numriLojtareve;
    private List<Lojtar> lojtaret;
    private int[] diceState;
    private int[][] pikePerKategoriPerLojtar;
    private boolean[][] kategoriteEZgjedhuraPerLojtar;
    private int currentTurn = 0;
    private int currentPlayerIndex = 0;
    private Category category;



    private DBConnector dbConnector;

    public Loja(int id, int numriLojtareve) {
        this.id = id;
        this.numriLojtareve = numriLojtareve;
        this.lojtaret = new ArrayList<>();
        this.diceState = new int[NUMRI_ZARAVE];
        this.pikePerKategoriPerLojtar = new int[NUMRI_KATEGORIVE][numriLojtareve];
        this.kategoriteEZgjedhuraPerLojtar = new boolean[NUMRI_KATEGORIVE][numriLojtareve];
        this.category = new Category(NUMRI_ZARAVE);
    }

    public boolean[][] getKategoriteEZgjedhuraPerLojtar() {
        return kategoriteEZgjedhuraPerLojtar;
    }

    public void setKategoriteEZgjedhuraPerLojtar(boolean[][] kategoriteEZgjedhuraPerLojtar) {
        this.kategoriteEZgjedhuraPerLojtar = kategoriteEZgjedhuraPerLojtar;
    }

    public DBConnector getDbConnector() {
        return dbConnector;
    }

    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public int getNumriLojtareve() {
        return numriLojtareve;
    }

    public void addLojtar(Lojtar lojtar) {
        lojtaret.add(lojtar);
    }

    public Lojtar getLojtari(int index) {
        return lojtaret.get(index);
    }

    public List<Lojtar> getLojtaret() {
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

    public Lojtar getCurrentPlayer() {
        return lojtaret.get(getCurrentPlayerIndex());
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public int getNUMRI_ZARAVE() {
        return NUMRI_ZARAVE;
    }

    public int getNUMRI_KATEGORIVE() {
        return NUMRI_KATEGORIVE;
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
    
    public int getPIKET_E_SIPERME_INDEX() {
        return PIKET_E_SIPERME_INDEX;
    }

    public int getPIKET_E_POSHTME_INDEX() {
        return PIKET_E_POSHTME_INDEX;
    }
    
    public int getTOTAL_INDEX() {
        return TOTAL_INDEX;
    }


    public boolean isPikeESiperme() {
        boolean result = true;

        for (int i = 0; i < PIKET_E_SIPERME_INDEX; i++) {
            result &= kategoriteEZgjedhuraPerLojtar[i][currentPlayerIndex];
        }

        return result && !kategoriteEZgjedhuraPerLojtar[PIKET_E_SIPERME_INDEX][currentPlayerIndex];
    }
    
    public boolean isPiketEPoshtme() {
        boolean result = true;

        for (int i = 8; i < PIKET_E_POSHTME_INDEX; i++) {
            result &= kategoriteEZgjedhuraPerLojtar[i][currentPlayerIndex];
        }

        return result && !kategoriteEZgjedhuraPerLojtar[PIKET_E_POSHTME_INDEX][currentPlayerIndex];
    }

    public int llogaritDheUpdateBonus() {
        int bonus = category.llogaritBonus(pikePerKategoriPerLojtar, currentPlayerIndex);
        updatePiket(BONUS_INDEX, bonus);
        return bonus;
    }

    public int llogaritDheUpdatePiketESiperme() {
        int piketESiperme = category.llogaritPiketSiperme(pikePerKategoriPerLojtar, currentPlayerIndex);
        updatePiket(PIKET_E_SIPERME_INDEX, piketESiperme);
        return piketESiperme;
    }
    

    public int llogaritDheUpdatePiketEPoshtme() {
        int piketEPoshtme = category.llogaritPiketPoshtme(pikePerKategoriPerLojtar, currentPlayerIndex);
        updatePiket(PIKET_E_POSHTME_INDEX, piketEPoshtme);
        return piketEPoshtme;
    }
    

    public int llogaritDheUpdateTotalin() {
        int total = category.llogaritTotal(pikePerKategoriPerLojtar, currentPlayerIndex);
        updatePiket(TOTAL_INDEX, total);
        return total;
    }
   
    public boolean isEndGameForCurrentPlayer() {
        return kategoriteEZgjedhuraPerLojtar[PIKET_E_POSHTME_INDEX][currentPlayerIndex] && kategoriteEZgjedhuraPerLojtar[PIKET_E_SIPERME_INDEX][currentPlayerIndex];
    }

    public int[] llogaritTeGjithaKategoriteSipasRradhes() {
        return category.llogaritTeGjithaKategoriteSipasRradhes(getDiceState());
    }

    public void updatePiket(int kategoriIndex, int piket) {
        pikePerKategoriPerLojtar[kategoriIndex][currentPlayerIndex] = piket;
        kategoriteEZgjedhuraPerLojtar[kategoriIndex][currentPlayerIndex] = true;

    }

    public void nextPlayer() {
        currentPlayerIndex = currentPlayerIndex == numriLojtareve - 1 ? 0 : currentPlayerIndex + 1;
        currentTurn = 0;
    }

    public int getPrevPlayer() {
        return currentPlayerIndex == 0 ? numriLojtareve - 1 : currentPlayerIndex - 1;
    }

    public int getNextPlayer() {
        return currentPlayerIndex == numriLojtareve - 1 ? 0 : currentPlayerIndex + 1;
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

        return lojtaret.get(bestPlayer);
    }

    public void save_totalin() {
        for (int i = 0; i < numriLojtareve; i++) {
            dbConnector.insertPike(lojtaret.get(i).getId(), id, pikePerKategoriPerLojtar[TOTAL_INDEX][i]);
        }
    }
}
