package com.example.tva_projekt;

public class Stats {
    Integer idVrha, stObiskov;
    String imeVrha;

    public Stats(Integer idVrha, Integer stObiskov, String imeVrha) {
        this.idVrha = idVrha;
        this.stObiskov = stObiskov;
        this.imeVrha = imeVrha;
    }

    public Integer getIdVrha() {
        return idVrha;
    }

    public void setIdVrha(Integer idVrha) {
        this.idVrha = idVrha;
    }

    public Integer getStObiskov() {
        return stObiskov;
    }

    public void setStObiskov(Integer stObiskov) {
        this.stObiskov = stObiskov;
    }

    public String getImeVrha() {
        return imeVrha;
    }

    public void setImeVrha(String imeVrha) {
        this.imeVrha = imeVrha;
    }
    public Stats(){

    }
}
