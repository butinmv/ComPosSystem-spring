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

    @Null
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Position convertToEntity() {
        Position position = new Position();

        position.setName(this.name);
        position.setCompany(this.company);

        return position;
    }
}
