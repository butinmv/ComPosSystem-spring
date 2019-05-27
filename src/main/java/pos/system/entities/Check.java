package pos.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="checks")
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "number", unique = true)
    @NotNull
    private Long number;

    @Column(name="timeOpen")
    @NotNull
    private Date timeOpen;

    @Column(name="timeClose")
    @NotNull
    private Date timeClose;

    @Column(name="wholePrice")
    @NotNull
    private Double wholePrice;

    @Column(name = "retailPrice")
    @NotNull
    private Double retailPrice;

    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="company_id")
    @NotNull
    private Company company;

    public Check() {

    }

    public Check(@NotNull Date timeOpen, @NotNull Date timeClose, @NotNull Double wholePrice, @NotNull Double retailPrice, @NotNull Company company) {
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.wholePrice = wholePrice;
        this.retailPrice = retailPrice;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Date timeOpen) {
        this.timeOpen = timeOpen;
    }

    public Date getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(Date timeClose) {
        this.timeClose = timeClose;
    }

    public Double getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(Double wholePrice) {
        this.wholePrice = wholePrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    @JsonIgnore
    public Company getCompany_id() {
        return company;
    }

    public void setCompany_id(Company company_id) {
        this.company = company_id;
    }
}
