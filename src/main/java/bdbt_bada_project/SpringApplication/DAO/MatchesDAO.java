package bdbt_bada_project.SpringApplication.DAO;


import bdbt_bada_project.SpringApplication.Models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MatchesDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Match> list(){
        String sql = "SELECT * FROM Mecze";

        List<Match> listMatch = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Match.class));

        return listMatch;
    }

    public void save(Match match){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Mecze").usingColumns("Nr_meczu", "Nr_stadionu", "Data", "Doliczony_czas", "Gospodarze", "Goscie", "Czy_sie_odbyl", "Bramki_gospodarzy", "Bramki_gosci");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(match);
        insertActor.execute(param);
    }

    public Match get(int Nr_meczu) {
        String sql = "SELECT * FROM Mecze WHERE Nr_meczu = ?";
        Object[] args = {Nr_meczu};
        Match match = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Match.class));
        return match;
    }

    public void update(Match match) {
        String sql = "UPDATE Stadiony SET Ilosc_miejsc=:Ilosc_miejsc, Nazwa=:Nazwa, Data_otwarcia=:Data_otwarcia, Czy_parking=:Czy_parking, Rodzaj_murawy=:Rodzaj_murawy, Czy_zamykany_dach=:Czy_zamykany_dach, Adres=:Adres WHERE Nr_stadionu=:Nr_stadionu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(match);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
}
