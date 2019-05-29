package pos.system.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos.system.entities.Check;
import pos.system.service.MainService;

@RestController
public class CheckRestController {

    private final MainService mainService;

    @Autowired
    public CheckRestController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping
    @RequestMapping("api/check/getAll")
    public Iterable<Check> getAll() {
        return mainService.findAllCheckByCompany();
    }
}
