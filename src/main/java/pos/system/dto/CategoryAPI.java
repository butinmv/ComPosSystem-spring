package pos.system.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;

public class CategoryAPI {

    @NotNull
    private Long categoryId;

    @NotNull
    private String categoryName;

    @Null
    private ArrayList<ProductAPI> products;

    public CategoryAPI(@NotNull Long categoryId, @NotNull String categoryName, @Null ArrayList<ProductAPI> products) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.products = products;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<ProductAPI> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductAPI> products) {
        this.products = products;
    }
}
