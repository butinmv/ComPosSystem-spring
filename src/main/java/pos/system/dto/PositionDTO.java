package pos.system.dto;

import pos.system.entities.Company;
import pos.system.entities.Position;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class PositionDTO {

    @Null
    private Long id;

    @NotNull
    private String name;

    public PositionDTO(@Null Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
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

}
