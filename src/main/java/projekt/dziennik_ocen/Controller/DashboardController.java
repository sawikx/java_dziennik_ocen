package projekt.dziennik_ocen.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projekt.dziennik_ocen.auth.model.AppUserDetails;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String jwtToken = (String) session.getAttribute("token");
        if (jwtToken == null) {
            return "redirect:/login";
        }

        model.addAttribute("token", jwtToken);
        return "dashboard";
    }

}



