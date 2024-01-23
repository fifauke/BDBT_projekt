package bdbt_bada_project.SpringApplication.Models;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class Stadium {

    private int Nr_stadionu;
    private int Ilosc_miejsc;
    private String Nazwa;

    @DateTimeFormat(pattern = "yy/MM/dd")
    private LocalDate Data_otwarcia;

    private boolean Czy_parking;
    private String Rodzaj_murawy;
    private boolean Czy_zamykany_dach;
    private String Adres;

    public Stadium(int nr_stadionu, int ilosc_miejsc, String nazwa, LocalDate data_otwarcia, boolean czy_parking, String rodzaj_murawy, boolean czy_zamykany_dach, String adres) {
        Nr_stadionu = nr_stadionu;
        Ilosc_miejsc = ilosc_miejsc;
        Nazwa = nazwa;
        Data_otwarcia = data_otwarcia;
        Czy_parking = czy_parking;
        Rodzaj_murawy = rodzaj_murawy;
        Czy_zamykany_dach = czy_zamykany_dach;
        Adres = adres;
    }

    public Stadium() {
    }

    public int getNr_stadionu() {
        return Nr_stadionu;
    }

    public void setNr_stadionu(int nr_stadionu) {
        Nr_stadionu = nr_stadionu;
    }

    public int getIlosc_miejsc() {
        return Ilosc_miejsc;
    }

    public void setIlosc_miejsc(int ilosc_miejsc) {
        Ilosc_miejsc = ilosc_miejsc;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public LocalDate getData_otwarcia() {
        return Data_otwarcia;
    }

    public void setData_otwarcia(LocalDate data_otwarcia) {
        Data_otwarcia = data_otwarcia;
    }

    public boolean isCzy_parking() {
        return Czy_parking;
    }

    public void setCzy_parking(boolean czy_parking) {
        Czy_parking = czy_parking;
    }

    public String getRodzaj_murawy() {
        return Rodzaj_murawy;
    }

    public void setRodzaj_murawy(String rodzaj_murawy) {
        Rodzaj_murawy = rodzaj_murawy;
    }

    public boolean isCzy_zamykany_dach() {
        return Czy_zamykany_dach;
    }

    public void setCzy_zamykany_dach(boolean czy_zamykany_dach) {
        Czy_zamykany_dach = czy_zamykany_dach;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String adres) {
        Adres = adres;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "Nr_stadionu=" + Nr_stadionu +
                ", Ilosc_miejsc=" + Ilosc_miejsc +
                ", Nazwa='" + Nazwa + '\'' +
                ", Data_otwarcia=" + Data_otwarcia +
                ", Czy_parking=" + Czy_parking +
                ", Rodzaj_murawy='" + Rodzaj_murawy + '\'' +
                ", Czy_zamykany_dach=" + Czy_zamykany_dach +
                ", Adres='" + Adres + '\'' +
                '}';
    }
}
