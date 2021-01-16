import javax.swing.*;
import java.awt.*;

public class CategoryJList extends JList{

    private static final String[] kategorite = { "Njesha", "Dysha", "Tresha", "Katra", "Pesa", "Gjashta", "Piket e siperme", "Bonus (35)",
            "Tre me nje vlere", "Kater me nje vlere", "Tre dhe Dy (25)", "Kater te njepasnjeshme (30)",
            "Pese te njepasnjeshme (40)", "E njejta vlere (50)", "Cdo rast", "Piket e poshtme", "TOTAL"
    };

    private final int PREF_W= 200;
    private final int PREF_H= 50;

    public CategoryJList() {
        super(kategorite);
        this.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
        this.setVisibleRowCount( 18 );

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

}
