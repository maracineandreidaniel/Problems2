package com.company;

public class Caine {
    private String nume;
    private int varsta;

    public Caine(String nume, int varsta){
        this.nume=nume;
        this.varsta=varsta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Caine{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                '}';
    }
}
