package pos.system.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos.system.entities.Check;
import pos.system.service.CheckService;
import pos.system.service.CompanyService;

@RestController
public class CheckRestController {

    private final CheckService checkService;
    private final CompanyService companyService;

    @Autowired
    public CheckRestController(CheckService checkService, CompanyService companyService) {
        this.checkService = checkService;
        this.companyService = companyService;
    }

    @GetMapping
    @RequestMapping("api/check/getAll")
    public Iterable<Check> getAll() {
        return checkService.findAllByCompany_id(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}
