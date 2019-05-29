package pos.system.dto;

import pos.system.entities.Company;
import pos.system.entities.Position;
import pos.system.entities.Staff;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class StaffDTO {

    @Null
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String position;

    public StaffDTO(@Null Long id, @NotNull String name, @NotNull String surname, @NotNull String email, @NotNull String password, @NotNull String position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
