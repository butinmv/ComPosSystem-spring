package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pos.system.dto.CategoryDTO;
import pos.system.entities.Category;
import pos.system.service.CategoryService;
import pos.system.service.CompanyService;

import java.util.ArrayList;

@Controller
public class CategoryController {

    private final CompanyService companyService;
    private final CategoryService categoryService;

    private Integer isMessage;
    private String name;
    private String nameCategory;

    @Autowired
    CategoryController(CompanyService companyService, CategoryService categoryService) {
        this.companyService = companyService;
        this.categoryService = categoryService;
        isMessage = 0;
    }

    @GetMapping("/categories")
    public String getCategories(Model model) {
        ArrayList<CategoryDTO> categoriesDTO= new ArrayList<>();
        Iterable<Category> categories;
        categories = categoryService.findAllByCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Category category: categories) {
            categoriesDTO.add(category.convertToDTO());
        }
        model.addAttribute("title", "Категории");
        model.addAttribute("categories", categoriesDTO);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Категория \"" + nameCategory + "\" не может быть удалена, возможно она связа с каким-то продуктом. Удалите все продукты с текущей категорией, после этого удалите категорию.");
        isMessage = 0;
        return "categories";
    }

    @GetMapping("category/create")
    public String getCreateCategory(Model model) {
        model.addAttribute("title", "Категории");
        model.addAttribute("editing", false);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == 0)
            model.addAttribute("message", null);
        if (isMessage == -1)
            model.addAttribute("message", "Категория \"" + name + "\" уже существует");
        if (isMessage == 1)
            model.addAttribute("message", "Категория \"" + name + "\" успешно добавлена");
        isMessage = 0;
        return "createCategory";
    }

    @PostMapping("category/create")
    public String postCreateCategory(CategoryDTO categoryDTO) {
        name = categoryDTO.getName();
        try{
            categoryService.save(categoryDTO.convertToEntity(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
            isMessage =  1;
        } catch (Exception e) {
            isMessage = -1;
        }
        return "redirect:/category/create";
    }

    @GetMapping("category/{id}/edit")
    public String getEditPosition(Model model, @PathVariable Long id) {
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO = category.convertToDTO();
        model.addAttribute("category", categoryDTO);
        model.addAttribute("title", "Категории");
        model.addAttribute("editing", true);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Категория \"" + name + "\" уже существует");
        isMessage = 0;
        return "createCategory";
    }

    @PostMapping("category/{id}/edit")
    public String postEditPosition(@PathVariable Long id, CategoryDTO categoryDTO) {
        Category category = categoryService.findById(id);
        category.setName(categoryDTO.getName());
        try {
            categoryService.save(category);
            return "redirect:/categories";
        } catch(Exception e) {
            isMessage = -1;
            name = categoryDTO.getName();
            return "redirect:/category/" + id + "/edit";
        }
    }

    @PostMapping("category/{id}/delete")
    public String postDeletePosition(@PathVariable Long id) {
        try {
            categoryService.delete(id);
        } catch(Exception e) {
            isMessage = -1;
            nameCategory = categoryService.findById(id).getName();
        }
        return "redirect:/categories";
    }
}
