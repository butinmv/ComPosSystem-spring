package pos.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pos.system.dto.CategoryDTO;
import pos.system.dto.ProductDTO;
import pos.system.entities.Category;
import pos.system.entities.Product;
import pos.system.service.*;

import java.util.ArrayList;

@Controller
public class ProductController {
    private final MainService mainService;

    private Integer isMessage;
    private String name;

    @Autowired
    ProductController(MainService mainService) {
        this.mainService = mainService;
        isMessage = 0;
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("title", "Продукты");
        model.addAttribute("products", mainService.findAllProductByCompany());
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Данный продукт не может быть удален.");
        isMessage = 0;
        return "products";
    }

    @GetMapping("product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("categories", mainService.findAllCategoryByCompany());
        model.addAttribute("title", "Продукты");
        model.addAttribute("editing", false);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == 0)
            model.addAttribute("message", null);
        if (isMessage == -1)
            model.addAttribute("message", "Продукт с таким название \"" + name + "\" уже существует");
        if (isMessage == 1)
            model.addAttribute("message", "Продукт успешно добавлен");
        isMessage = 0;
        return "createProduct";
    }

    @PostMapping("product/create")
    public String postCreateProduct(Model model, ProductDTO productDTO) {
        name = productDTO.getName();
        try {
            mainService.saveProduct(productDTO);
            isMessage =  1;
        } catch (Exception e) {
            isMessage = -1;
            return "redirect:/product/create";
        }
        return "redirect:/product/create";
    }

    @GetMapping("product/{id}/edit")
    public String getEditProduct(Model model, @PathVariable Long id) {
        model.addAttribute("categories", mainService.findAllCategoryByCompany());
        model.addAttribute("product", mainService.findProductById(id));
        model.addAttribute("title", "Продукты");
        model.addAttribute("editing", true);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Продукт \"" + name + "\" уже существует");
        isMessage = 0;
        return "createProduct";
    }

    @PostMapping("product/{id}/edit")
    public String postEditProduct(Model model, @PathVariable Long id, ProductDTO productDTO) {
        try {
            mainService.saveProductById(id, productDTO);
            return "redirect:/products";
        } catch (Exception e) {
            isMessage = -1;
            name = productDTO.getName();
            return "redirect:/product/" + id + "/edit";
        }
    }

    @PostMapping("product/{id}/delete")
    public String postDeleteProduct(@PathVariable Long id) {
        try {
            mainService.deleteProduct(id);
        } catch (Exception e) {
            isMessage = -1;
            name = mainService.findProductById(id).getName();
        }
        return "redirect:/products";
    }
}
