package bdbt_bada_project.SpringApplication;

import bdbt_bada_project.SpringApplication.DAO.FansDAO;
import bdbt_bada_project.SpringApplication.Models.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import bdbt_bada_project.SpringApplication.Models.Fan;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FansDAOTest {
    private FansDAO dao;
    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:oracle:thin:@//192.168.2.178:1521/xe");
        dataSource.setUsername("system");
        dataSource.setPassword("mininet");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new FansDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Fan> listContract = dao.list();
        System.out.println(listContract);
        assertTrue(listContract.isEmpty());
    }

    @Test
    void testSave() {
        Fan fan = new Fan(1, "111000222", "fannn@wp.pl", 0, "Paweł", "Kaktus");
        dao.save(fan);
    }
}
