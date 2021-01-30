package yahtzeeGame;

import java.util.List;

public class Lojtar {

    private int id;
    private String emri;
    private String mbiemri;
    private int mosha;
    private List<Integer> piketEGrumbulluaraNeCdoLoje; //TODO: me vone si ta ruajme kete
    private int piket;

    public Lojtar(String emri, String mbiemri, int mosha) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.mosha = mosha;
        piket = 0;
    }

    public Lojtar(int id, String emri, String mbiemri, int mosha) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.mosha = mosha;
        piket = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public  List<Integer> getPiketEGrumbulluaraNeCdoLoje() {
        return piketEGrumbulluaraNeCdoLoje;
    }

    public void setPiketEGrumbulluaraNeCdoLoje( List<Integer> piketEGrumbulluaraNeCdoLoje) {
        this.piketEGrumbulluaraNeCdoLoje = piketEGrumbulluaraNeCdoLoje;
    }

    public int getPiket() {
        return piket;
    }

    public void setPiket(int piket) {
        this.piket = piket;
    }

    public int getPiketTotal() {
        int total = 0;

        for (Integer pike : piketEGrumbulluaraNeCdoLoje) {
            total += pike;
        }

        return total;
    }
}
