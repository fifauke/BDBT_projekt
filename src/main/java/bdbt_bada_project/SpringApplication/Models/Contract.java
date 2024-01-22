package bdbt_bada_project.SpringApplication.Models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Contract {
    private int Nr_sponsora;
    private String Nazwa_sponsora;
    @DateTimeFormat(pattern = "yy/MM/dd")
    private LocalDate Data_zakonczenia_umowy;
    private int Wartosc_finansowa;
    private String Branza;
    //private boolean Aktywna_umowa;
    private String Nr_telefonu;
    private String email;

    public Contract(int Nr_sponsora, String Nazwa_sponsora, LocalDate Data_zakonczenia_umowy, int Wartosc_finansowa, String Branza, boolean Aktywna_umowa, String Nr_telefonu, String email) {
        super();
        this.Nr_sponsora = Nr_sponsora;
        this.Nazwa_sponsora = Nazwa_sponsora;
        this.Data_zakonczenia_umowy = Data_zakonczenia_umowy;
        this.Wartosc_finansowa = Wartosc_finansowa;
        this.Branza = Branza;
        this.Nr_telefonu = Nr_telefonu;
        this.email = email;
    }

    public Contract() {

    }

    public int getNr_sponsora() {
        return Nr_sponsora;
    }

    public void setNr_sponsora(int Nr_sponsora) {
        this.Nr_sponsora = Nr_sponsora;
    }

    public String getNazwa_sponsora() {
        return Nazwa_sponsora;
    }

    public void setNazwa_sponsora(String Nazwa_sponsora) {
        this.Nazwa_sponsora = Nazwa_sponsora;
    }

    public LocalDate getData_zakonczenia_umowy() {
        return Data_zakonczenia_umowy;
    }

    public void setData_zakonczenia_umowy(LocalDate Data_zakonczenia_umowy) {
        this.Data_zakonczenia_umowy = Data_zakonczenia_umowy;
    }

    public int getWartosc_finansowa() {
        return Wartosc_finansowa;
    }

    public void setWartosc_finansowa(int Wartosc_finansowa) {
        this.Wartosc_finansowa = Wartosc_finansowa;
    }

    public String getBranza() {
        return Branza;
    }

    public void setBranza(String Branza) {
        this.Branza = Branza;
    }

    public boolean isAktywna_umowa() {
        return this.Data_zakonczenia_umowy.isAfter(LocalDate.now());
    }

    public String getNr_telefonu() {
        return Nr_telefonu;
    }

    public void setNr_telefonu(String Nr_telefonu) {
        this.Nr_telefonu = Nr_telefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + Nr_sponsora +
                ", sponsorName='" + Nazwa_sponsora + '\'' +
                ", dateOfExpiry=" + Data_zakonczenia_umowy +
                ", monetaryValue=" + Wartosc_finansowa +
                ", industry='" + Branza + '\'' +
                ", isActive=" +  this.isAktywna_umowa() +
                ", phoneNumber='" + Nr_telefonu + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
