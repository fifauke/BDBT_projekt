package bdbt_bada_project.SpringApplication.DAO;

/* Import java.util.List */
import java.util.List;
/* Import org.springframework.jd....Template */
import bdbt_bada_project.SpringApplication.Models.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


@Repository
public class ContractsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ContractsDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Contract> list(){
        String sql = "SELECT * FROM Sponsorzy";

        List<Contract> listContract =jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Contract.class));

        return listContract;
    }
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Contract contract) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Sponsorzy").usingColumns("Nr_sponsora", "Nazwa_sponsora", "Data_zakonczenia_umowy", "Wartosc_finansowa", "Branza", "Aktywna_umowa", "Nr_telefonu", "email");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(contract);
        insertActor.execute(param);
    }
    /* Read – odczytywanie danych z bazy */
    public Contract get(int Nr_sponsora) {
        String sql = "SELECT * FROM Sponsorzy WHERE Nr_sponsora = ?";
        Object[] args = {Nr_sponsora};
        Contract contract = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Contract.class));
        return contract;
    }

    public Contract get1(int Nr_sponsora) {
        Object[] args = {Nr_sponsora};
        String sql = "SELECT * FROM Sponsorzy WHERE Nr_sponsora = " + args[0];
        Contract contract = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Contract.class));
        return contract;
    }
    /* Update – aktualizacja danych */
    public void update(Contract contract) {
        String sql = "UPDATE Sponsorzy SET Nazwa_sponsora=:Nazwa_sponsora, Data_zakonczenia_umowy=:Data_zakonczenia_umowy, Wartosc_finansowa=:Wartosc_finansowa, Branza=:Branza, Aktywna_umowa=:Aktywna_umowa, Nr_telefonu=:Nr_telefonu, email=:email WHERE Nr_sponsora=:Nr_sponsora";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(contract);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void updateByUser(Contract contract) {
        String sql = "UPDATE Sponsorzy SET Nr_telefonu=:Nr_telefonu, email=:email WHERE Nazwa_sponsora=:Nazwa_sponsora";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(contract);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int Nr_sponsora) {
        String sql = "DELETE FROM Sponsorzy WHERE Nr_sponsora = ?";
        jdbcTemplate.update(sql, Nr_sponsora);
    }

    public void delete(String Nazwa_sponsora) {
        String sql = "DELETE FROM Sponsorzy WHERE Nazwa_sponsora = ?";
        jdbcTemplate.update(sql, Nazwa_sponsora);
    }

    /* Funkcja sprawdzająca czy dany użytkownik jest już sponsorem */
    public boolean hasSignedContract(String username) {
        String sql = "SELECT COUNT(*) FROM Sponsorzy WHERE Nazwa_sponsora = ?";
        Object[] args = {username};
        return jdbcTemplate.queryForObject(sql, args, Integer.class) > 0;
    }

    /* Funkcja zwracająca kontrakt o danej nazwie sponsora */
    public Contract getByUsername(String username) {
        String sql = "SELECT * FROM Sponsorzy WHERE Nazwa_sponsora = ?";
        Object[] args = {username};
        return jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Contract.class));
    }
}
