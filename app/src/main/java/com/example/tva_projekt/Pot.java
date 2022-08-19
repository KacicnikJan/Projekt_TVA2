package com.example.tva_projekt;

public class Pot {
    int idPot, idVrha;
    String imePoti, zahtevnost, casHoje, izhodisceLat, izhodisceLong, opisPoti, link, izhodisceDostop;

    public Pot(int idPot, int idVrha, String imePoti, String zahtevnost, String casHoje, String izhodisceLat, String izhodisceLong, String opisPoti, String link, String izhodisceDostop) {
        this.idPot = idPot;
        this.idVrha = idVrha;
        this.imePoti = imePoti;
        this.zahtevnost = zahtevnost;
        this.casHoje = casHoje;
        this.izhodisceLat = izhodisceLat;
        this.izhodisceLong = izhodisceLong;
        this.opisPoti = opisPoti;
        this.link = link;
        this.izhodisceDostop = izhodisceDostop;
    }
    public Pot(){

    }

    public int getIdPot() {
        return idPot;
    }

    public void setIdPot(int idPot) {
        this.idPot = idPot;
    }

    public int getIdVrha() {
        return idVrha;
    }

    public void setIdVrha(int idVrha) {
        this.idVrha = idVrha;
    }

    public String getImePoti() {
        return imePoti;
    }

    public void setImePoti(String imePoti) {
        this.imePoti = imePoti;
    }

    public String getZahtevnost() {
        return zahtevnost;
    }

    public void setZahtevnost(String zahtevnost) {
        this.zahtevnost = zahtevnost;
    }

    public String getCasHoje() {
        return casHoje;
    }

    public void setCasHoje(String casHoje) {
        this.casHoje = casHoje;
    }

    public String getIzhodisceLat() {
        return izhodisceLat;
    }

    public void setIzhodisceLat(String izhodisceLat) {
        this.izhodisceLat = izhodisceLat;
    }

    public String getIzhodisceLong() {
        return izhodisceLong;
    }

    public void setIzhodisceLong(String izhodisceLong) {
        this.izhodisceLong = izhodisceLong;
    }

    public String getOpisPoti() {
        return opisPoti;
    }

    public void setOpisPoti(String opisPoti) {
        this.opisPoti = opisPoti;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIzhodisceDostop() {
        return izhodisceDostop;
    }

    public void setIzhodisceDostop(String izhodisceDostop) {
        this.izhodisceDostop = izhodisceDostop;
    }
}
