package pos.system.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos.system.dto.CategoryDTO;
import pos.system.entities.Category;
import pos.system.service.MainService;

@RestController
public class CategoryAPIController {

    private final MainService mainService;

    @Autowired
    public CategoryAPIController(MainService mainService) {
        this.mainService = mainService;
    }

    // TODO: Сделать объект под api категории
    @GetMapping
    @RequestMapping("api/category/getAll")
    public Iterable<CategoryDTO> getAll() {
        return mainService.findAllCategoryByCompany();
    }
}
