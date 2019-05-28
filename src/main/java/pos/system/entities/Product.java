package pos.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pos.system.dto.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="product",
        uniqueConstraints =
        @UniqueConstraint(columnNames={"name", "company_fk"}))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="wholePrice")
    @NotNull
    private Double wholePrice;

    @Column(name="murkup")
    @NotNull
    private Integer markup;

    @Column(name="retaiPrice")
    @NotNull
    private Double retailPrice;

    @Column(name="barcode")
    @NotNull
    private Long barcode;

    @ManyToOne
    @JoinColumn(name = "company_fk")
    @NotNull
    private Company company;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    @NotNull
    private Category category;

    public Product() {

    }

    public Product(@NotNull String name, @NotNull Double wholePrice, @NotNull Integer markup, @NotNull Double retailPrice, @NotNull Long barcode, @NotNull Company company, @NotNull Category category) {
        this.name = name;
        this.wholePrice = wholePrice;
        this.markup = markup;
        this.retailPrice = retailPrice;
        this.barcode = barcode;
        this.company = company;
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

    @JsonIgnore
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductDTO convertToDTO(){
        return new ProductDTO(this.id, this.name, this.wholePrice, this.markup, this.retailPrice, this.barcode, category.getName());
    }
}
