package bdbt_bada_project.SpringApplication.Controllers;
import bdbt_bada_project.SpringApplication.DAO.FansDAO;
import bdbt_bada_project.SpringApplication.DAO.MatchesDAO;
import bdbt_bada_project.SpringApplication.DAO.StadiumsDAO;
import bdbt_bada_project.SpringApplication.DTO.FanRegistrationDTO;
import bdbt_bada_project.SpringApplication.Exceptions.UserAlreadyExistsException;
import bdbt_bada_project.SpringApplication.Models.*;
import bdbt_bada_project.SpringApplication.DAO.ContractsDAO;
import bdbt_bada_project.SpringApplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Configuration
public class AppController implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/main_admin").setViewName("admin/main_admin");

        registry.addViewController("/main_without_contract_user").setViewName("user/main_without_contract_user");
        registry.addViewController("/main_with_contract_user").setViewName("user/main_with_contract_user");

        registry.addViewController("/main_fan").setViewName("fan/main_fan");
    }

    public String getCurrentlyLoggedInUserUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    @Controller
    public class DashboardController
    {

        @Autowired
        private ContractsDAO dao;
        @Autowired
        private StadiumsDAO stadiumsDAO;
        @Autowired
        private MatchesDAO matchesDAO;

        @RequestMapping("/record_admin")
        public String viewHomePage(Model model){
            List<Contract> listContract = dao.list();
            model.addAttribute("listContract", listContract);
            return "admin/record_admin";
        }

        @RequestMapping("/new_form_admin")
        public String showNewForm(Model model) {
            Contract contract = new Contract();
            model.addAttribute("contract", contract);

            return "admin/new_form_admin";
        }

        @RequestMapping(value="/save", method = RequestMethod.POST)
        public String save(@ModelAttribute("contract") Contract contract){
            dao.save(contract);

            return "redirect:/record_admin";
        }

        @RequestMapping(value="/edit_admin/{Nr_sponsora}")
        public ModelAndView showEditForm(@PathVariable(name = "Nr_sponsora") int Nr_sponsora){
            ModelAndView mav = new ModelAndView("admin/edit_form_admin");
            Contract contract = dao.get(Nr_sponsora);
            mav.addObject("contract", contract);

            return mav;
        }

        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public String update(@ModelAttribute("contract") Contract contract){
            dao.update(contract);

            return "redirect:/record_admin";
        }

        @RequestMapping("/delete_admin/{Nr_sponsora}")
        public String delete(@PathVariable(name = "Nr_sponsora") int Nr_sponsora) {
            dao.delete(Nr_sponsora);

            return "redirect:/record_admin";
        }

        @RequestMapping("/record_stadium_admin")
        public String viewStadiumRecord(Model model){
            List<Stadium> listStadium = stadiumsDAO.list();

            //Wyświetlanie rodzaju murawy w bardziej czytelnej formie
            for(Stadium stadium: listStadium){
                if(stadium.getRodzaj_murawy().equals("N")){
                    stadium.setRodzaj_murawy("Natural");
                } else if(stadium.getRodzaj_murawy().equals("S")){
                    stadium.setRodzaj_murawy("Artificial");
                }
            }
            model.addAttribute("listStadium", listStadium);
            return "admin/record_stadium_admin";
        }

        @RequestMapping("/new_stadium_form_admin")
        public String showNewStadiumForm(Model model) {
            Stadium stadium = new Stadium();
            model.addAttribute("stadium", stadium);

            return "admin/new_stadium_form_admin";
        }

        @RequestMapping("/save_stadium")
        public String saveStadium(@ModelAttribute("stadium") Stadium stadium) {
            stadiumsDAO.save(stadium);

            return "redirect:/record_stadium_admin";
        }

        @RequestMapping(value="/edit_stadium_admin/{Nr_stadionu}")
        public ModelAndView showStadiumEditForm(@PathVariable(name = "Nr_stadionu") int Nr_stadionu) {
            ModelAndView mav = new ModelAndView("admin/edit_stadium_form_admin");
            Stadium stadium = stadiumsDAO.get(Nr_stadionu);
            mav.addObject("stadium", stadium);

            return mav;
        }

        @RequestMapping(value="/update_stadium", method = RequestMethod.POST)
        public String update(@ModelAttribute("stadium") Stadium stadium) {
            stadiumsDAO.update(stadium);

            return "redirect:/record_stadium_admin";
        }

        @RequestMapping(value="/delete_stadium_admin/{Nr_stadionu}")
        public String deleteStadium(@PathVariable(name = "Nr_stadionu") int Nr_stadionu) {
            stadiumsDAO.delete(Nr_stadionu);

            return "redirect:/record_stadium_admin";
        }

        @RequestMapping("/record_matches_admin")
        public String viewMatchesRecord(Model model){
            List<Match> listMatch = matchesDAO.list();
            model.addAttribute("listContract", listMatch);
            return "admin/record_matches_admin";
        }

        @RequestMapping("/new_match_form_admin")
        public String showNewMatchForm(Model model){
            Match match = new Match();
            model.addAttribute("match", match);
            model.addAttribute("stadiums", stadiumsDAO.list());

            return "admin/new_match_form_admin";
        }



        @RequestMapping("/main")
        public String defaultAfterLogin(HttpServletRequest request, Model model) {

            if (request.isUserInRole("ADMIN")) {
                return "redirect:/main_admin";
            } else if (request.isUserInRole("USER")) {

                String username = getCurrentlyLoggedInUserUsername();
                boolean hasSignedContracts = dao.hasSignedContract(username);

                if (!hasSignedContracts){
                    return "user/main_without_contract_user";
                }
                 else {
                     Contract contract = dao.getByUsername(username);
                     model.addAttribute("contract", contract);
                     return "user/main_with_contract_user";
                }
            } else if (request.isUserInRole("FAN")){
                return "redirect:/main_fan";
            }
            else {
                return "redirect:/index";
            }
        }
    }

    @Controller
    public class RegistrationController {

        @Autowired
        private FansDAO fansDAO;
        private final UserService userService;
        public RegistrationController(UserService userService){
            this.userService = userService;
        }

        @GetMapping("/register")
        public String showRegisterForm(Model model){
            model.addAttribute("user", new User());
            return "user/register_user";
        }

        @PostMapping("/register")
        public String registerUser(@ModelAttribute("user") User user, Model model){
            try {
                user.setRole("USER");
                userService.register(user);
                return "redirect:/login";

            } catch (UserAlreadyExistsException e){
                model.addAttribute("error", "User is already registered");
                return "user/register_user";
            }
        }

        @GetMapping("/register_fan")
        public String showRegisterFormFan(Model model) {
            FanRegistrationDTO fanRegistrationDTO = new FanRegistrationDTO();
            fanRegistrationDTO.setUser(new User());
            fanRegistrationDTO.setFan(new Fan());
            model.addAttribute("fanRegistrationDTO", fanRegistrationDTO);
            return "fan/register_fan";
        }

        @PostMapping("/register_fan")
        public String registerFan(@ModelAttribute("fanRegistrationDTO") FanRegistrationDTO fanRegistrationDTO, Model model) {
            try {

                User user = fanRegistrationDTO.getUser();
                Fan fan = fanRegistrationDTO.getFan();

                user.setRole("FAN");
                userService.register(user);

                fansDAO.save(fan);

                return "redirect:/login";
            } catch (UserAlreadyExistsException e){
                model.addAttribute("error", "User is already registered");
                return "fan/register_fan";
            }
        }
    }

    @Controller
    public class UserDashboardController {

        @Autowired
        private ContractsDAO dao;
        @RequestMapping("/new_form_user")
        public String showNewForm(Model model) {
            Contract contract = new Contract();
            contract.setNazwa_sponsora(getCurrentlyLoggedInUserUsername());
            model.addAttribute("contract", contract);

            return "user/new_form_user";
        }
        @RequestMapping(value="/save_user", method = RequestMethod.POST)
        public String save(@ModelAttribute("contract") Contract contract){
            dao.save(contract);

            return "redirect:/main";
        }

        @RequestMapping(value = "/edit_user/{Nazwa_sponsora}")
        public ModelAndView showEditForm(@PathVariable(name = "Nazwa_sponsora") String Nazwa_sponsora){
            ModelAndView mav = new ModelAndView("user/edit_form_user");
            Contract contract = dao.getByUsername(getCurrentlyLoggedInUserUsername());
            mav.addObject("contract", contract);

            return mav;
        }

        @RequestMapping(value = "/update_user", method = RequestMethod.POST)
        public String update(@ModelAttribute("contract") Contract contract){
            dao.updateByUser(contract);

            return "redirect:/main";
        }

        @RequestMapping("/delete_user/{Nazwa_sponsora}")
        public String delete(@PathVariable(name = "Nazwa_sponsora") String Nazwa_sponsora) {
            dao.delete(Nazwa_sponsora);

            return "redirect:/main";
        }

    }
}