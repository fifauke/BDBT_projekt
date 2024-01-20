package bdbt_bada_project.SpringApplication.Repositories;

import bdbt_bada_project.SpringApplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}
