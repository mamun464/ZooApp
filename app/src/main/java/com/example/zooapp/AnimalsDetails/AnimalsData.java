package com.example.zooapp.AnimalsDetails;

public class AnimalsData {
    private int imageA;
    private String namesA;
    private String specA;
    private String spanA;
    private String descA;

    public AnimalsData(int imageA, String namesA, String specA, String spanA, String descA) {
        this.imageA = imageA;
        this.namesA = namesA;
        this.specA = specA;
        this.spanA = spanA;
        this.descA = descA;
    }

    public int getImageA() {
        return imageA;
    }

    public String getNamesA() {
        return namesA;
    }

    public String getSpecA() {
        return specA;
    }

    public String getSpanA() {
        return spanA;
    }

    public String getDescA() {
        return descA;
    }
}
