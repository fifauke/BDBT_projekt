package bdbt_bada_project.SpringApplication.Models;

public class Ticket {

    private int Nr_kibica;
    private int Nr_meczu;
    private int Nr_stadionu;

    public Ticket(int nr_kibica, int nr_meczu, int nr_stadionu) {
        Nr_kibica = nr_kibica;
        Nr_meczu = nr_meczu;
        Nr_stadionu = nr_stadionu;
    }

    public Ticket() {
    }

    public int getNr_kibica() {
        return Nr_kibica;
    }

    public void setNr_kibica(int nr_kibica) {
        Nr_kibica = nr_kibica;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "Nr_kibica=" + Nr_kibica +
                ", Nr_meczu=" + Nr_meczu +
                ", Nr_stadionu=" + Nr_stadionu +
                '}';
    }
}
