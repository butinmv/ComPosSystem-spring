package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pos.system.dto.CategoryDTO;
import pos.system.service.MainService;

@Controller
public class CategoryController {

    private final MainService mainService;

    private Integer isMessage;
    private String name;

    @Autowired
    CategoryController(MainService mainService) {
        this.mainService = mainService;
        isMessage = 0;
    }

    @GetMapping("/categories")
    public String getCategories(Model model) {
        model.addAttribute("title", "Категории");
        model.addAttribute("categories", mainService.findAllCategoryByCompany());
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Категория \"" + name + "\" не может быть удалена, возможно она связа с каким-то продуктом. Удалите все продукты с текущей категорией, после этого удалите категорию.");
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
            mainService.saveCategory(categoryDTO);
            isMessage =  1;
        } catch (Exception e) {
            isMessage = -1;
        }
        return "redirect:/category/create";
    }

    @GetMapping("category/{id}/edit")
    public String getEditCategory(Model model, @PathVariable Long id) {
        model.addAttribute("category", mainService.findCategoryById(id));
        model.addAttribute("title", "Категории");
        model.addAttribute("editing", true);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Категория \"" + name + "\" уже существует");
        isMessage = 0;
        return "createCategory";
    }

    @PostMapping("category/{id}/edit")
    public String postEditCategory(@PathVariable Long id, CategoryDTO categoryDTO) {
        try {
            mainService.saveCategoryById(id, categoryDTO);
            return "redirect:/categories";
        } catch(Exception e) {
            isMessage = -1;
            name = categoryDTO.getName();
            return "redirect:/category/" + id + "/edit";
        }
    }

    @PostMapping("category/{id}/delete")
    public String postDeleteCategory(@PathVariable Long id) {
        try {
            mainService.deleteCategory(id);
        } catch(Exception e) {
            isMessage = -1;
            name = mainService.findCategoryById(id).getName();
        }
        return "redirect:/categories";
    }
}
