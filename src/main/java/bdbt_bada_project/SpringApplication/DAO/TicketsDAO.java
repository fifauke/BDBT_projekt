package bdbt_bada_project.SpringApplication.DAO;

import bdbt_bada_project.SpringApplication.Models.Match;
import bdbt_bada_project.SpringApplication.Models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TicketsDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ticket> list(){
        String sql = "SELECT * FROM Bilety";

        List<Ticket> listTicket = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ticket.class));

        return listTicket;
    }

    public void save(Ticket ticket){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Bilety").usingColumns("Nr_kibica", "Nr_meczu", "Nr_stadionu");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(ticket);
        insertActor.execute(param);
    }

    public Ticket get(int Nr_kibica) {
        String sql = "SELECT * FROM Mecze WHERE Nr_kibica = ?";
        Object[] args = {Nr_kibica};
        Ticket ticket = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Ticket.class));
        return ticket;
    }

    public List<Ticket> getTicketsByUsername(String username) {
        String sql = "SELECT * FROM Bilety WHERE Nr_kibica IN (SELECT Nr_kibica FROM Kibice WHERE USERNAME = ?)";
        Object[] args = {username};

        return jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(Ticket.class));
    }
}
