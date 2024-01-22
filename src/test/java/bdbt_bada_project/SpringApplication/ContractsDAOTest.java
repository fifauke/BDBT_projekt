package bdbt_bada_project.SpringApplication;

import bdbt_bada_project.SpringApplication.DAO.ContractsDAO;
import bdbt_bada_project.SpringApplication.Models.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContractsDAOTest {

    private ContractsDAO dao;
    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setUrl("jdbc:oracle:thin@192.168.2.178:1521:xe");
        dataSource.setUrl("jdbc:oracle:thin:@//192.168.2.178:1521/xe");
        dataSource.setUsername("system");
        dataSource.setPassword("mininet");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new ContractsDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Contract> listContract = dao.list();
        System.out.println(listContract);
        assertFalse(listContract.isEmpty());
    }

    @Test
    void testSave() {
        Contract contract = new Contract(3, "Firma z Warzywoza Energy", LocalDate.of(2024,7,16), 700000, "Energetyczna", false, "673614859", "firmazw@wp.pl");
        dao.save(contract);
    }

    @Test
    void testGet(){
        int id = 3;
        Contract contract = dao.get(id);

        assertNotNull(contract);
    }

    @Test
    void testUpdate() {
        Contract contract = new Contract();
        contract.setNr_sponsora(4);
        contract.setNazwa_sponsora("Ekipa SA");
        contract.setData_zakonczenia_umowy(LocalDate.of(2022, 12, 8));
        contract.setWartosc_finansowa(300000);
        contract.setBranza("Rozrywkowa");
        contract.setNr_telefonu("999998997");
        contract.setEmail("friz@wp.pl");

        dao.update(contract);
    }

    @Test
    void testDelete() {
        int id = 5;
        dao.delete(id);
    }
}
