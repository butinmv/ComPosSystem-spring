package pos.system.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos.system.entities.Check;
import pos.system.service.CheckService;

@RestController
public class CheckRestController {

    private final CheckService checkService;

    @Autowired
    public CheckRestController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping
    @RequestMapping("api/check/getLast7Days")
    public Iterable<Check> getLast7Days() {
        return checkService.findLast7Days();
    }

    @GetMapping
    @RequestMapping("api/check/getLast30Days")
    public Iterable<Check> getLast30Days() {
        return checkService.findLast30Days();
    }

    @GetMapping
    @RequestMapping("api/check/getAll")
    public Iterable<Check> getAll() {
        return checkService.findAll();
    }
}
