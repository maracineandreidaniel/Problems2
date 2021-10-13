package com.company;

import java.util.ArrayList;
import java.util.Optional;

public class Caine {
    private String nume;
    private int varsta;
    private  Optional<String> blana;

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

    public Optional<String> getBlana() {
        return blana;
    }

    public void setBlana(Optional<String> blana) {
        this.blana = blana;
    }

    public static Optional<ArrayList<Caine>> caini(){
        Optional<ArrayList<Caine>> optionalCaini=Optional.of(new ArrayList<>());
        ArrayList<Caine> caini= optionalCaini.get();
        return Optional.of(caini);
    }
}
