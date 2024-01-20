package bdbt_bada_project.SpringApplication.Controllers;
import bdbt_bada_project.SpringApplication.Models.Contract;
import bdbt_bada_project.SpringApplication.DAO.ContractsDAO;
import bdbt_bada_project.SpringApplication.Models.User;
import bdbt_bada_project.SpringApplication.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
        registry.addViewController("/main_user").setViewName("user/main_user");
    }

    @Controller
    public class DashboardController
    {

        @Autowired
        private ContractsDAO dao;

        @RequestMapping("/record_admin")
        public String testViewHomePage(Model model){
            List<Contract> listContract = dao.list();
            model.addAttribute("listContract", listContract);
            return "admin/record_admin";
        }

        @RequestMapping("/new_form_admin")
        public String testShowNewForm(Model model) {
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
        public ModelAndView testShowEditForm(@PathVariable(name = "Nr_sponsora") int Nr_sponsora){
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
        @RequestMapping("/main")
        public String defaultAfterLogin(HttpServletRequest request) {
            if (request.isUserInRole("ADMIN")) {
                return "redirect:/main_admin";
            }
            else if (request.isUserInRole("USER")) {
                return "redirect:/main_user";
            }
            else {
                return "redirect:/index";
            }
        }
    }

    @Controller
    public class RegistrationController {

        private final UserService userService;
        public RegistrationController(UserService userService){
            this.userService = userService;
        }

        @RequestMapping("/register")
        public String showRegisterForm(Model model){
            model.addAttribute("user", new User());
            return "register";
        }

        @PostMapping("/register")
        public String registerUser(@ModelAttribute("user") User user, Model model){
            try {
                user.setRole("USER");
                userService.register(user);
                return "redirect:/login";
            } catch (Exception e){
                model.addAttribute("error", "Company is already registered");
                return "register";
            }
        }
    }

    @RequestMapping(value={"/main_admin"})
    public String showAdminPage(Model model){
        return "admin/main_admin";
    }

    @RequestMapping(value={"/main_user"})
    public String showUserPage(Model model){
        return "user/main_user";
    }
}