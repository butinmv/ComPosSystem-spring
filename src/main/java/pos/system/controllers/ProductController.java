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
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CompanyService companyService;

    private Integer isMessage;
    private String name;

    @Autowired
    ProductController(ProductService productService, CategoryService categoryService, CompanyService companyService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this. companyService = companyService;
        isMessage = 0;
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        ArrayList<ProductDTO> productsDTO = new ArrayList<>();
        Iterable<Product> products;
        products = productService.findAllByCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Product product: products) {
            productsDTO.add(product.convertToDTO());
        }
        model.addAttribute("title", "Продукты");
        model.addAttribute("products", productsDTO);
        model.addAttribute("isMessage", isMessage);
        if (isMessage == -1)
            model.addAttribute("message", "Данный продукт не может быть удален.");
        isMessage = 0;
        return "products";
    }

    @GetMapping("product/create")
    public String getCreateProduct(Model model) {
        ArrayList<CategoryDTO> categoriesDTO = new ArrayList<>();
        Iterable<Category> categories;
        categories = categoryService.findAllByCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Category category: categories) {
            categoriesDTO.add(category.convertToDTO());
        }
        model.addAttribute("categories", categoriesDTO);
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
            productService.save(productDTO.convertToEntity(categoryService.findByName(productDTO.getCategory()), companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())));
            isMessage =  1;
        } catch (Exception e) {
            isMessage = -1;
            return "redirect:/product/create";
        }
        return "redirect:/product/create";
    }

    @GetMapping("product/{id}/edit")
    public String getEditProduct(Model model, @PathVariable Long id) {
        ArrayList<CategoryDTO> categoriesDTO = new ArrayList<>();
        Iterable<Category> categories;
        categories = categoryService.findAllByCompany(companyService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        for (Category category: categories) {
            categoriesDTO.add(category.convertToDTO());
        }
        model.addAttribute("categories", categoriesDTO);
        Product product = productService.findById(id);
        ProductDTO productDTO = product.convertToDTO();
        model.addAttribute("product", productDTO);
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
        Product product = productService.findById(id);
        product.setName(productDTO.getName());
        product.setWholePrice(productDTO.getWholePrice());
        product.setMarkup(productDTO.getMarkup());
        product.setRetailPrice(productDTO.getRetailPrice());
        product.setBarcode(productDTO.getBarcode());
        product.setCategory(categoryService.findByName(productDTO.getCategory()));
        try {
            productService.save(product);
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
            productService.delete(id);
        } catch (Exception e) {
            isMessage = -1;
        }
        return "redirect:/products";
    }
}
