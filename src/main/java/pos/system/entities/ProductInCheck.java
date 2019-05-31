package pos.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// TODO: добавить все поля
// время добавления, комментарий, кассир
@Entity
@Table(name="productInCheck")
public class ProductInCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="wholeSale")
    @NotNull
    private Double wholeSale;

    @Column(name="retailPrice")
    @NotNull
    private Double retailPrice;

    @Column(name="count")
    @NotNull
    private Long count;

    @ManyToOne
    @JoinColumn(name="check_fk")
    @NotNull
    private Check check;

    public ProductInCheck() {

    }

    public ProductInCheck(@NotNull Double wholeSale, @NotNull Double retailPrice, @NotNull Long count, @NotNull Check check) {
        this.wholeSale = wholeSale;
        this.retailPrice = retailPrice;
        this.count = count;
        this.check = check;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWholeSale() {
        return wholeSale;
    }

    public void setWholeSale(Double wholeSale) {
        this.wholeSale = wholeSale;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @JsonIgnore
    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
