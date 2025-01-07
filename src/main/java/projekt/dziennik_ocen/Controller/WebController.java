package projekt.dziennik_ocen.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String loginPage(Model model, HttpServletRequest request) {
        String currentUrl = request.getRequestURI();  // Pobieramy URI
        model.addAttribute("currentUrl", currentUrl); // Przekazujemy do modelu
        return "login"; // Zwrot do szablonu login.html
    }
}

