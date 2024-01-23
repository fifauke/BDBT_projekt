package bdbt_bada_project.SpringApplication;

import bdbt_bada_project.SpringApplication.DAO.MatchesDAO;
import bdbt_bada_project.SpringApplication.Models.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchesDAOTest {

    private MatchesDAO dao;
    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:oracle:thin:@//192.168.2.178:1521/xe");
        dataSource.setUsername("system");
        dataSource.setPassword("mininet");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new MatchesDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Match> listMatch = dao.list();
        System.out.println(listMatch);
        assertTrue(listMatch.isEmpty());
    }

    @Test
    void testSave() {
        Match match = new Match(1, 1, LocalDate.of(2000,12,20), 0, "Gospod", "Scie", true, 5, 4);
        dao.save(match);
    }
}
