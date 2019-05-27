package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pos.system.service.CompanyService;

import java.util.Collections;

/**
 * Класс-контроллер, обрабатывающий главную страницу приложения
 * @autor Artem Andriukov
 * @version 1.0
 */
@Controller
public class MainController {

    /**
     * Функция обработки get запроса пути /main
     * @return возвращает страницу "main.ftl"
     */
    @GetMapping("/main")
    public String getMain(Model model) {
        model.addAttribute("title", "Статистика");
        return "main";
    }
}