package yahtzeeGame;

public class Loja {
    private int numriLojtareve;
    private Lojtar[] lojtaret;
    private int[] diceState;
    private int[][] pikePerKategoriPerLojtar;
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
    }
}
