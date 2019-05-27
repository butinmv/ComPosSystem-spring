package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pos.system.dto.CompanyDTO;
import pos.system.entities.Check;
import pos.system.entities.Role;
import pos.system.service.CheckService;
import pos.system.service.CompanyService;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;

/**
 * Класс-контроллер, обрабатывающий регистрацию пользователей
 * @autor Artem Andriukov
 * @version 1.0
 */
@Controller
public class RegistrationController {

    private final CompanyService companyService;

    @Autowired
    RegistrationController(CompanyService companyService) {
        this.companyService = companyService;
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
            companyDTO.setActive(true);
            companyDTO.setRoles(Collections.singleton(Role.USER));
            companyService.save(companyDTO.convertToEntity());
        } catch (Exception e) {
            return "redirect:/registration";
        }
        return "redirect:/login";
    }
}
