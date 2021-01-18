package yahtzeeGame;

public class Lojtar {
    private String emri;
    private String mbiemri;
    private int mosha;
    private int[] piketEGrumbulluaraNeCdoLoje; //TODO: me vone si ta ruajme kete
    private int piket;

    public Lojtar(String emri, String mbiemri, int mosha) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.mosha = mosha;
        piket = 0;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public int getMosha() {
        return mosha;
    }

    public void setMosha(int mosha) {
        this.mosha = mosha;
    }

    public int[] getPiketEGrumbulluaraNeCdoLoje() {
        return piketEGrumbulluaraNeCdoLoje;
    }

    public void setPiketEGrumbulluaraNeCdoLoje(int[] piketEGrumbulluaraNeCdoLoje) {
        this.piketEGrumbulluaraNeCdoLoje = piketEGrumbulluaraNeCdoLoje;
    }

    public int getPiket() {
        return piket;
    }

    public void setPiket(int piket) {
        this.piket = piket;
    }
}
