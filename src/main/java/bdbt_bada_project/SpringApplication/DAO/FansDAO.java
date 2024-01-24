package bdbt_bada_project.SpringApplication.DAO;

import bdbt_bada_project.SpringApplication.Models.Fan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class FansDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public FansDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Fan> list() {
        String sql = "SELECT * FROM Kibice";

        List<Fan> listFan = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Fan.class));

        return listFan;
    }

    public void save(Fan fan) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Kibice").usingColumns("Nr_kibica", "Nr_telefonu", "email", "Liczba_meczy", "Imie", "Nazwisko", "Username");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(fan);
        insertActor.execute(param);
    }

    public Fan getByUsername(String username) {
        String sql = "SELECT * FROM Kibice WHERE Username = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{username}, BeanPropertyRowMapper.newInstance(Fan.class));
    }
}
