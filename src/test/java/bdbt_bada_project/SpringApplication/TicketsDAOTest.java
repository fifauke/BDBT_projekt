package bdbt_bada_project.SpringApplication;

import bdbt_bada_project.SpringApplication.DAO.StadiumsDAO;
import bdbt_bada_project.SpringApplication.DAO.TicketsDAO;
import bdbt_bada_project.SpringApplication.Models.Stadium;
import bdbt_bada_project.SpringApplication.Models.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketsDAOTest {

    private TicketsDAO dao;

    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:oracle:thin:@//192.168.2.178:1521/xe");
        dataSource.setUsername("system");
        dataSource.setPassword("mininet");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new TicketsDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Ticket> listTicket = dao.list();
        System.out.println(listTicket);
        assertTrue(listTicket.isEmpty());
    }

    @Test
    void testSave() {
        Ticket ticket = new Ticket(86, 2, 1);
        dao.save(ticket);
    }

    @Test
    void listByUsername() {
        List<Ticket> listTicket = dao.getTicketsByUsername("fan2");
        for(Ticket ticket: listTicket){
            System.out.println(ticket);
        }
        assertFalse(listTicket.isEmpty());
    }
}
