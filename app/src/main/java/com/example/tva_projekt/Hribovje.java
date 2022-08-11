package com.example.tva_projekt;

public class Hribovje {
    int idHribovja;
    String ime;


    public Hribovje(int idHribovja, String ime) {
        this.idHribovja = idHribovja;
        this.ime = ime;
    }
    public Hribovje(){

    }

    public int getIdHribovja() {
        return idHribovja;
    }

    public void setIdHribovja(int idHribovja) {
        this.idHribovja = idHribovja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
