import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Lista {
	
	private static final String[] kategorite = { "Njesha", "Dysha", "Tresha", "Katra", "Pesa", "Gjashta", "Piket e siperme", "Bonus (35)",
												  "Tre me nje vlere", "Kater me nje vlere", "Tre dhe Dy (25)", "Kater te njepasnjeshme (30)",
												  "Pese te njepasnjeshme (40)", "E njejta vlere (50)", "Cdo rast", "Piket e poshtme", "TOTAL"
												};
	
	JList konfigurime;
	
	public Lista() {
		
		konfigurime = new JList(kategorite);
		konfigurime.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
		konfigurime.setVisibleRowCount( 18 );
		
	}
	
}
