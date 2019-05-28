package pos.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pos.system.dto.CategoryDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="category",
        uniqueConstraints =
        @UniqueConstraint(columnNames={"name", "company_fk"}))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name="company_fk")
    @NotNull
    private Company company;

//    @OneToMany

    public Category() {

    }

    public Category(@NotNull String name, @NotNull Company company) {
        this.name = name;
        this.company = company;
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

    @JsonIgnore
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CategoryDTO convertToDTO() {
        return new CategoryDTO(this.id, this.name);
    }
}
