package pos.system.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;

    @Column (name = "username", unique = true)
    @NotNull
    private String username;

    @Column (name = "password")
    @NotNull
    private String password;

    @Column (name = "nameCompany")
    @NotNull
    private String nameCompany;

    @Column (name="site")
    private String site;

    @Column (name="tin", unique = true)
    @NotNull
    private Long tin;

    @Column (name="address")
    @Null
    private String address;

    @Column (name="phone")
    @Null
    private String phone;

    @Column (name="active")
    @NotNull
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Company() {

    }

    public Company(@NotNull String username, @NotNull String password, @NotNull String nameCompany, String site, @NotNull Long tin, @Null String address, @Null String phone, @NotNull boolean active, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.nameCompany = nameCompany;
        this.site = site;
        this.tin = tin;
        this.address = address;
        this.phone = phone;
        this.active = active;
        this.roles = roles;
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

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }
}
