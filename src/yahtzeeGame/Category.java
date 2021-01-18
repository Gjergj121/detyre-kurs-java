package yahtzeeGame;

public class Category {

    public static final String[] CATEGORIES = { "Njesha", "Dysha", "Tresha", "Katra", "Pesa", "Gjashta", "Piket e siperme", "Bonus (35)",
            "Tre me nje vlere", "Kater me nje vlere", "Tre dhe Dy (25)", "Kater te njepasnjeshme (30)",
            "Pese te njepasnjeshme (40)", "E njejta vlere (50)", "Cdo rast", "Piket e poshtme", "TOTAL"
    };

    private int numriZarave;

    public Category(int numriZarave) {
        this.numriZarave = numriZarave;
    }

    public int llogaritNjeshat(int[] diceState) {
        // TODO: bej logjiken.
        return 0;
    }
}
