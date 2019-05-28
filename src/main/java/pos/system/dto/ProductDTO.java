package pos.system.dto;

import pos.system.entities.Category;
import pos.system.entities.Company;
import pos.system.entities.Product;

import javax.validation.constraints.NotNull;

public class ProductDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double wholePrice;

    @NotNull
    private Integer markup;

    @NotNull
    private Double retailPrice;

    @NotNull
    private Long barcode;

    @NotNull
    private String category;

    public ProductDTO(@NotNull Long id, @NotNull String name, @NotNull Double wholePrice, @NotNull Integer markup, @NotNull Double retailPrice, @NotNull Long barcode, @NotNull String category) {
        this.id = id;
        this.name = name;
        this.wholePrice = wholePrice;
        this.markup = markup;
        this.retailPrice = retailPrice;
        this.barcode = barcode;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(Double wholePrice) {
        this.wholePrice = wholePrice;
    }

    public Integer getMarkup() {
        return markup;
    }

    public void setMarkup(Integer markup) {
        this.markup = markup;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Product convertToEntity(Category category, Company company) {
        return new Product(this.name, this.wholePrice, this.markup, this.retailPrice, this.barcode, company, category);
    }
}
