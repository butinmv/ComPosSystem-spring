package pos.system.dto;

import pos.system.entities.Company;
import pos.system.entities.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

public class CompanyDTO {

    @Null
    private Long id;

    @NotNull
    @Email
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String nameCompany;

    @Null
    private String site;

    @NotNull
    private Long tin;

    @Null
    private String address;

    @Null
    private String phone;

    @Null
    private boolean active;

    @Null
    private Set<Role> roles;

    public CompanyDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Long getTin() {
        return tin;
    }

    public void setTin(Long tin) {
        this.tin = tin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Company convertToEntity() {
        Company company = new Company();

        company.setUsername(this.username);
        company.setPassword(this.password);
        company.setNameCompany(this.nameCompany);
        company.setSite(this.site);
        company.setTin(this.tin);
        company.setAddress(this.address);
        company.setPhone(this.phone);
        company.setActive(this.active);
        company.setRoles(this.roles);
        return company;
    }
}
