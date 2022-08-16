package com.example.tva_projekt;

public class Vrh {
    String imeVrha, opis, lokacijaVrhLong, lokacijaVrhLat;
    Integer idVrha, idHribovja, ndmv;

    public Vrh(String imeVrha, String opis, String lokacijaVrhLong, String lokacijaVrhLat, Integer idVrha, Integer idHribovja, Integer ndmv) {
        this.imeVrha = imeVrha;
        this.opis = opis;
        this.lokacijaVrhLong = lokacijaVrhLong;
        this.lokacijaVrhLat = lokacijaVrhLat;
        this.idVrha = idVrha;
        this.idHribovja = idHribovja;
        this.ndmv = ndmv;
    }
    public Vrh(){

    }

    public String getImeVrha() {
        return imeVrha;
    }

    public void setImeVrha(String imeVrha) {
        this.imeVrha = imeVrha;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getLokacijaVrhLong() {
        return lokacijaVrhLong;
    }

    public void setLokacijaVrhLong(String lokacijaVrhLong) {
        this.lokacijaVrhLong = lokacijaVrhLong;
    }

    public String getLokacijaVrhLat() {
        return lokacijaVrhLat;
    }

    public void setLokacijaVrhLat(String lokacijaVrhLat) {
        this.lokacijaVrhLat = lokacijaVrhLat;
    }

    public Integer getIdVrha() {
        return idVrha;
    }

    public void setIdVrha(Integer idVrha) {
        this.idVrha = idVrha;
    }

    public Integer getIdHribovja() {
        return idHribovja;
    }

    public void setIdHribovja(Integer idHribovja) {
        this.idHribovja = idHribovja;
    }

    public Integer getNdmv() {
        return ndmv;
    }

    public void setNdmv(Integer ndmv) {
        this.ndmv = ndmv;
    }
}
