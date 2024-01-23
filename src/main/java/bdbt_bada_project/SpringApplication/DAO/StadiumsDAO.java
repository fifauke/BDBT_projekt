package bdbt_bada_project.SpringApplication.DAO;


import bdbt_bada_project.SpringApplication.Models.Contract;
import bdbt_bada_project.SpringApplication.Models.Stadium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StadiumsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StadiumsDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Stadium> list(){
        String sql = "SELECT * FROM Stadiony";

        List<Stadium> listStadium = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Stadium.class));

        return listStadium;
    }

    public void save(Stadium stadium){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Stadiony").usingColumns("Nr_stadionu", "Ilosc_miejsc", "Nazwa", "Data_otwarcia", "Czy_parking", "Rodzaj_murawy", "Czy_zamykany_dach", "Adres");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(stadium);
        insertActor.execute(param);
    }

    public Stadium get(int Nr_stadionu) {
        String sql = "SELECT * FROM Stadiony WHERE Nr_stadionu = ?";
        Object[] args = {Nr_stadionu};
        Stadium stadium = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Stadium.class));
        return stadium;
    }

    public void update(Stadium stadium) {
        String sql = "UPDATE Stadiony SET Ilosc_miejsc=:Ilosc_miejsc, Nazwa=:Nazwa, Data_otwarcia=:Data_otwarcia, Czy_parking=:Czy_parking, Rodzaj_murawy=:Rodzaj_murawy, Czy_zamykany_dach=:Czy_zamykany_dach, Adres=:Adres WHERE Nr_stadionu=:Nr_stadionu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(stadium);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void delete(int Nr_stadionu) {
        String sql = "DELETE FROM Stadiony WHERE Nr_stadionu = ?";
        jdbcTemplate.update(sql, Nr_stadionu);
    }
}
