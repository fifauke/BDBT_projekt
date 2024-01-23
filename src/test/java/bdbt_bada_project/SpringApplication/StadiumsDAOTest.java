package bdbt_bada_project.SpringApplication;

import bdbt_bada_project.SpringApplication.DAO.FansDAO;
import bdbt_bada_project.SpringApplication.DAO.StadiumsDAO;
import bdbt_bada_project.SpringApplication.Models.Fan;
import bdbt_bada_project.SpringApplication.Models.Stadium;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StadiumsDAOTest {
    private StadiumsDAO dao;

    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:oracle:thin:@//192.168.2.178:1521/xe");
        dataSource.setUsername("system");
        dataSource.setPassword("mininet");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new StadiumsDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Stadium> listContract = dao.list();
        System.out.println(listContract);
        assertTrue(listContract.isEmpty());
    }

    @Test
    void testSave() {
        Stadium stadium = new Stadium(1, 10000, "Test Stadion", LocalDate.of(2000,12,20), true, "N", true, "Warszawa Radarowa 1");
        dao.save(stadium);
    }
}
