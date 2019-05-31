package pos.system.dto;

import javax.validation.constraints.NotNull;

public class ProductInCheckAPI {

    @NotNull
    private String name;

    @NotNull
    private Long count;

    public ProductInCheckAPI() {

    }

    public ProductInCheckAPI(@NotNull String name, @NotNull Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
