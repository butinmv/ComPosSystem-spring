package pos.system.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pos.system.dto.ProductInCheckAPI;
import pos.system.entities.Category;
import pos.system.entities.Check;
import pos.system.service.MainService;

import java.util.ArrayList;

@RestController
public class APIController {

    private final MainService mainService;

    @Autowired
    public APIController(MainService mainService) {
        this.mainService = mainService;
    }

    // TODO: Сделать объект под api категории
    @GetMapping
    @RequestMapping("api/category/getAll")
    public Iterable<Category> getAllCategories() {
        return mainService.findAllCategory();
    }

    @GetMapping
    @RequestMapping("api/check/getAll")
    public Iterable<Check> getAllChecks() {
        return mainService.findAllCheck();
    }

    @PostMapping
    @RequestMapping("api/check/save")
    public void saveCheck(@RequestBody ArrayList<ProductInCheckAPI> products) {
        mainService.saveCheck(products);
    }
}
