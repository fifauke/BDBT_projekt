package bdbt_bada_project.SpringApplication.DTO;

import bdbt_bada_project.SpringApplication.Models.Fan;
import bdbt_bada_project.SpringApplication.Models.Match;
import bdbt_bada_project.SpringApplication.Models.Stadium;
import bdbt_bada_project.SpringApplication.Models.Ticket;

import java.util.List;

public class TicketDTO {

    private Ticket ticket;
    private Stadium stadium;
    private Fan fan;
    private Match match;

    public TicketDTO(Ticket ticket, Stadium stadium, Fan fan, Match match) {
        this.ticket = ticket;
        this.stadium = stadium;
        this.fan = fan;
        this.match = match;
    }

    public Ticket getTickets() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Fan getFan() {
        return fan;
    }

    public void setFan(Fan fan) {
        this.fan = fan;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
