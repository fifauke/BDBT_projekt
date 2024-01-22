package bdbt_bada_project.SpringApplication.DAO;

import bdbt_bada_project.SpringApplication.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UsersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> list() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
    }

    public void save(User user) {
        SimpleJdbcInsert insertAction = new SimpleJdbcInsert(jdbcTemplate);
        insertAction.withTableName("USERS").usingColumns("ID", "USERNAME", "PASSWORD", "ROLE");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);
        insertAction.execute(param);
    }

    public User get(int ID) {
        Object[] args = {ID};
        String sql = "SELECT * FROM USERS WHERE ID = " + args[0];
        User user = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(User.class));
        return user;
    }

    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM USERS WHERE USER = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username}, BeanPropertyRowMapper.newInstance(User.class));

        if(users != null && !users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    public void update(User user) {
        String sql = "UPDATE USERS SET ROLE=:role WHERE ID=:id";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int ID) {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        jdbcTemplate.update(sql, ID);
    }
}
