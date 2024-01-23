package bdbt_bada_project.SpringApplication.Models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Match {
    private int Nr_meczu;
    private int Nr_stadionu;
    @DateTimeFormat(pattern = "yy/MM/dd")
    private LocalDate Data;
    private int Doliczony_czas;
    private String Gospodarze;
    private String Goscie;
    private boolean Czy_sie_odbyl;
    private int Bramki_gospodarzy;
    private int Bramki_gosci;

    public Match(int nr_meczu, int nr_stadionu, LocalDate data, int doliczony_czas, String gospodarze, String goscie, boolean czy_sie_odbyl, int bramki_gospodarzy, int bramki_gosci) {
        Nr_meczu = nr_meczu;
        Nr_stadionu = nr_stadionu;
        Data = data;
        Doliczony_czas = doliczony_czas;
        Gospodarze = gospodarze;
        Goscie = goscie;
        Czy_sie_odbyl = czy_sie_odbyl;
        Bramki_gospodarzy = bramki_gospodarzy;
        Bramki_gosci = bramki_gosci;
    }

    public Match() {
    }

    public int getNr_meczu() {
        return Nr_meczu;
    }

    public void setNr_meczu(int nr_meczu) {
        Nr_meczu = nr_meczu;
    }

    public int getNr_stadionu() {
        return Nr_stadionu;
    }

    public void setNr_stadionu(int nr_stadionu) {
        Nr_stadionu = nr_stadionu;
    }

    public LocalDate getData() {
        return Data;
    }

    public void setData(LocalDate data) {
        Data = data;
    }

    public int getDoliczony_czas() {
        return Doliczony_czas;
    }

    public void setDoliczony_czas(int doliczony_czas) {
        Doliczony_czas = doliczony_czas;
    }

    public String getGospodarze() {
        return Gospodarze;
    }

    public void setGospodarze(String gospodarze) {
        Gospodarze = gospodarze;
    }

    public String getGoscie() {
        return Goscie;
    }

    public void setGoscie(String goscie) {
        Goscie = goscie;
    }

    public boolean isCzy_sie_odbyl() {
        return Czy_sie_odbyl;
    }

    public void setCzy_sie_odbyl(boolean czy_sie_odbyl) {
        Czy_sie_odbyl = czy_sie_odbyl;
    }

    public int getBramki_gospodarzy() {
        return Bramki_gospodarzy;
    }

    public void setBramki_gospodarzy(int bramki_gospodarzy) {
        Bramki_gospodarzy = bramki_gospodarzy;
    }

    public int getBramki_gosci() {
        return Bramki_gosci;
    }

    public void setBramki_gosci(int bramki_gosci) {
        Bramki_gosci = bramki_gosci;
    }

    @Override
    public String toString() {
        return "Match{" +
                "Nr_meczu=" + Nr_meczu +
                ", Nr_stadionu=" + Nr_stadionu +
                ", Data=" + Data +
                ", Doliczony_czas=" + Doliczony_czas +
                ", Gospodarze='" + Gospodarze + '\'' +
                ", Goscie='" + Goscie + '\'' +
                ", Czy_sie_odbyl=" + Czy_sie_odbyl +
                ", Bramki_gospodarzy=" + Bramki_gospodarzy +
                ", Bramki_gosci=" + Bramki_gosci +
                '}';
    }
}
