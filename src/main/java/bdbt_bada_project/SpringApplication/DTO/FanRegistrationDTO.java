package bdbt_bada_project.SpringApplication.DTO;
import bdbt_bada_project.SpringApplication.Models.Fan;
import bdbt_bada_project.SpringApplication.Models.User;
public class FanRegistrationDTO {
    private User user;
    private Fan fan;

    public User getUser() {
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }

    public Fan getFan() {
        return this.fan;
    }
    public void setFan(Fan fan){
        this.fan = fan;
    }
}
