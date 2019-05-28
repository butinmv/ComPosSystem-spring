package pos.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Controller;
import pos.system.dto.StaffDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    @NotNull
    String name;

    @Column(name="surname")
    @NotNull
    private String surname;

    @Column(name="email", unique = true)
    @NotNull
    private String email;

    @Column(name="password")
    @NotNull
    private String password;

    @ManyToOne
    @JoinColumn(name="company_fk")
    @NotNull
    private Company company;

    @ManyToOne
    @JoinColumn(name="position_fk")
    @NotNull
    private Position position;

    public Staff(@NotNull String name, @NotNull String surname, @NotNull String email, @NotNull String password, @NotNull Company company, @NotNull Position position) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.company = company;
        this.position = position;
    }

    public Staff() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public StaffDTO convertToDTO() {
        return new StaffDTO(this.id, this.name, this.surname, this.email, this.password, position.getName());
    }
}
