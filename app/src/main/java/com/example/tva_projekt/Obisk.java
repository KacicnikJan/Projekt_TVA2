package com.example.tva_projekt;

import android.graphics.Bitmap;

public class Obisk {
    Integer idObisk, idPot, idUser;
    String komentar, datum;
    Bitmap slika;

    public Obisk(Integer idObisk, Integer idPot, Integer idUser, String komentar, String datum, Bitmap slika) {
        this.idObisk = idObisk;
        this.idPot = idPot;
        this.idUser = idUser;
        this.komentar = komentar;
        this.datum = datum;
        this.slika = slika;
    }
    public Obisk(){

    }

    public Integer getIdObisk() {
        return idObisk;
    }

    public void setIdObisk(Integer idObisk) {
        this.idObisk = idObisk;
    }

    public Integer getIdPot() {
        return idPot;
    }

    public void setIdPot(Integer idPot) {
        this.idPot = idPot;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Bitmap getSlika() {
        return slika;
    }

    public void setSlika(Bitmap slika) {
        this.slika = slika;
    }
}
