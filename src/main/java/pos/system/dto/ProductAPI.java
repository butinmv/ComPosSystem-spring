package pos.system.dto;

import javax.validation.constraints.NotNull;

public class ProductAPI {
    @NotNull
    private Long productId;

    @NotNull
    private String productName;

    @NotNull
    private Double productRetailPrice;

    public ProductAPI(@NotNull Long productId, @NotNull String productName, @NotNull Double productRetailPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productRetailPrice = productRetailPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductRetailPrice() {
        return productRetailPrice;
    }

    public void setProductRetailPrice(Double productRetailPrice) {
        this.productRetailPrice = productRetailPrice;
    }
}
