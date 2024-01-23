package bdbt_bada_project.SpringApplication.Models;

public class Fan {
    private int Nr_kibica;
    private String Nr_telefonu;
    private String email;
    private int Liczba_meczy;
    private String Imie;
    private String Nazwisko;

    public Fan(int nr_kibica, String nr_telefonu, String email, int liczba_meczy, String imie, String nazwisko) {
        Nr_kibica = nr_kibica;
        Nr_telefonu = nr_telefonu;
        this.email = email;
        Liczba_meczy = liczba_meczy;
        Imie = imie;
        Nazwisko = nazwisko;
    }

    public Fan() {
    }

    public int getNr_kibica() {
        return Nr_kibica;
    }

    public void setNr_kibica(int nr_kibica) {
        Nr_kibica = nr_kibica;
    }

    public String getNr_telefonu() {
        return Nr_telefonu;
    }

    public void setNr_telefonu(String nr_telefonu) {
        Nr_telefonu = nr_telefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLiczba_meczy() {
        return Liczba_meczy;
    }

    public void setLiczba_meczy(int liczba_meczy) {
        Liczba_meczy = liczba_meczy;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return "Fan{" +
                "Nr_kibica=" + Nr_kibica +
                ", Nr_telefonu='" + Nr_telefonu + '\'' +
                ", email='" + email + '\'' +
                ", Liczba_meczy=" + Liczba_meczy +
                ", Imie='" + Imie + '\'' +
                ", Nazwisko='" + Nazwisko + '\'' +
                '}';
    }
}
