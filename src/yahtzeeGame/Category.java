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
    	
    	int shuma = 0;
    	
    	for(int i = 0; i < numriZarave; i++) {
    		if(diceState[i] == 1)
    			shuma += 1;
    	}
    	
        return shuma;
    }
    
    
    public int llogaritDyshat(int[] diceState) {
    	
    	int shuma = 0;
    	
    	for(int i = 0; i < numriZarave; i++) {
    		if(diceState[i] == 2)
    			shuma += 2;
    	}
    	
        return shuma;
    	
    }
    
    public int llogaritTreshat(int[] diceState) {
    	
    	int shuma = 0;
    	
    	for(int i = 0; i < numriZarave; i++) {
    		if(diceState[i] == 3)
    			shuma += 3;
    	}
    	
        return shuma;
    	
    }
    
    public int llogaritKatrat(int[] diceState) {
    	
    	int shuma = 0;
    	
    	for(int i = 0; i < numriZarave; i++) {
    		if(diceState[i] == 4)
    			shuma += 4;
    	}
    	
        return shuma;
    	
    }
    
    
    public int llogaritPesat(int[] diceState) {
    	
    	int shuma = 0;
    	
    	for(int i = 0; i < numriZarave; i++) {
    		if(diceState[i] == 5)
    			shuma += 5;
    	}
    	
        return shuma;
    	
    }
    
    
    public int llogaritGjashtat(int[] diceState) {
    	
    	int shuma = 0;
    	
    	for(int i = 0; i < numriZarave; i++) {
    		if(diceState[i] == 6)
    			shuma += 6;
    	}
    	
        return shuma;
    	
    }
    
    
    public int llogaritPiketSiperme(int[][] pikePerKategoriPerLojtar, int currentPlayer) {
    	
    	int shuma = 0;
    	
    	for(int i = 0; i < 6; i++) {  // Fillon te 0 apo te 1 ??
    		shuma += pikePerKategoriPerLojtar[i][currentPlayer];
    	}
    	
    	return shuma;
    }
    
    
    public int llogaritBonus(int[][] pikePerKategoriPerLojtar, int currentPlayer) {
    	if(llogaritPiketSiperme(pikePerKategoriPerLojtar, currentPlayer) > 63)
    		return 35;
    				
    	return 0;			
    }
    
    
    public int llogaritTreMeNjeVlere(int[] diceState) {
    	
    	int counter;
    	
    	for(int i = 0; i < numriZarave-2; i++) {
    		
    		counter = 0;
    		
    		for(int j = i+1; j < numriZarave; j++) {
    			if(diceState[i] == diceState[j])
    				counter++;
    		}
    		
    		if(counter >= 2)
    			return diceState[0] + diceState[1] + diceState[2] + diceState[3] + diceState[4];
    		
    	}
    	
    	return 0;
    }
    
    
    public int llogaritKaterMeNjeVlere(int[] diceState) {
    	
    	int counter;
    	
    	for(int i = 0; i < numriZarave-3; i++) {
    		
    		counter = 0;
    		
    		for(int j = i+1; j < numriZarave; j++) {
    			if(diceState[i] == diceState[j])
    				counter++;
    		}
    		
    		if(counter >= 3)
    			return diceState[0] + diceState[1] + diceState[2] + diceState[3] + diceState[4];
    		
    	}
    	
    	return 0;
    }
    
    
    public int llogaritTreDheDy(int[] diceState) {
    	
    	int[] count = new int[6];
    	
    	for(int i = 0; i < numriZarave; i++) {
    		count[diceState[i]-1]++;
    	}
    	
    	boolean njesojDy = false, njesojTre = false;
    	
    	for(int i = 0; i < 6; i++) {
    		
    		if(count[i] == 5) { // 5 numra njesoj
    			return 25;
    		}
    		
    		if(count[i]==3) {
    			njesojTre = true;
    		}
    		else if(count[i]==2) {
    			njesojDy = true;
    		}
    		
    	}
    	
    	if(njesojDy && njesojTre)
    		return 25;
    	
    	
    	return 0;
    }
    
    
    public int llogaritKaterTeNjepasnjeshme(int[] diceState) {
    	
    	if( ( diceState[0]==diceState[1]-1 && diceState[0]==diceState[2]-2 && diceState[0]==diceState[3]-3 ) || 
    		( diceState[1]==diceState[2]-1 && diceState[1]==diceState[3]-2 && diceState[1]==diceState[4]-3 ) )
    		return 30;
    			
    	return 0;
    }
    
    
    public int llogaritPeseTeNjepasnjeshme(int[] diceState) {
    	
    	if( diceState[0]==diceState[1]-1 && diceState[0]==diceState[2]-2 && diceState[0]==diceState[3]-3  && diceState[0]==diceState[4]-4)
    		return 40;
    	
    	return 0;
    }
    
    public int llogaritNjejtaVlere(int[] diceState) {
    	
    	if(diceState[0]==diceState[1] && diceState[0]==diceState[2] && diceState[0]==diceState[3] && diceState[0]==diceState[4])
    		return 50;
    	
    	return 0;
    }
    
    
    public int llogaritCdoRast(int[] diceState) {
    	
    	return diceState[0] + diceState[1] + diceState[2] + diceState[3] + diceState[4]; 
    	
    }
    
    
    public int llogaritPiketPoshtme(int[][] pikePerKategoriPerLojtar, int currentPlayer) {
    	
    	int shuma = 0;
    	
    	for(int i = 8; i < 15; i++) {  
    		shuma += pikePerKategoriPerLojtar[i][currentPlayer];
    	}
    	
    	return shuma;
    	
    }
    
    
    public int llogaritTotal(int[][] pikePerKategoriPerLojtar, int currentPlayer) {
    	
    	return llogaritPiketSiperme(pikePerKategoriPerLojtar, currentPlayer) + llogaritBonus(pikePerKategoriPerLojtar, currentPlayer) + llogaritPiketPoshtme(pikePerKategoriPerLojtar, currentPlayer);
    	
    }
    
}
