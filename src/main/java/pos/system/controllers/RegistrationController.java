package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pos.system.dto.CompanyDTO;
import pos.system.entities.Role;
import pos.system.service.MainService;

import java.util.Collections;

/**
 * Класс-контроллер, обрабатывающий регистрацию пользователей
 * @autor Artem Andriukov
 * @version 1.0
 */
@Controller
public class RegistrationController {

    private final MainService mainService;

    @Autowired
    RegistrationController(MainService mainService) {
        this.mainService = mainService;
    }

    /**
     * Функция обработки GET запроса пути /registration
     * @return возвращает страницу регистрации "registration.ftl"
     */
    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    /**
     * Функция обработки POST запроса пути /registration
     * @return возвращает страницу "login.ftl", если регистрация успешна,
     *         в противном случае возвращает страницу "registration.ftl" и
     *         сообщение
     */
    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postRegistration(CompanyDTO companyDTO) {
        try {
            mainService.saveCompany(companyDTO);
        } catch (Exception e) {
            return "redirect:/registration";
        }
        return "redirect:/login";
    }
}
