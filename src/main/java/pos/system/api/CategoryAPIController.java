package pos.system.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos.system.entities.Category;
import pos.system.entities.Check;
import pos.system.service.CategoryService;
import pos.system.service.CheckService;
import pos.system.service.CompanyService;

@RestController
public class CategoryAPIController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryAPIController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @RequestMapping("api/category/getAll")
    public Iterable<Category> getAll() {
        return categoryService.findAll();
    }
}
