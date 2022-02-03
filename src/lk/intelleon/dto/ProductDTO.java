package lk.intelleon.dto;

import java.math.BigDecimal;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class ProductDTO {
    private String productId;
    private String name;
    private String description;
    private String spec;
    private String displayName;
    private boolean availability;
    private boolean activeState;
    private String brands;
    private int qty;
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(String productId, String name, String description, String spec, String displayName, boolean availability, boolean activeState, String brands, int qty, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.displayName = displayName;
        this.availability = availability;
        this.activeState = activeState;
        this.brands = brands;
        this.qty = qty;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isActiveState() {
        return activeState;
    }

    public void setActiveState(boolean activeState) {
        this.activeState = activeState;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", spec='" + spec + '\'' +
                ", displayName='" + displayName + '\'' +
                ", availability=" + availability +
                ", activeState=" + activeState +
                ", brands='" + brands + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
