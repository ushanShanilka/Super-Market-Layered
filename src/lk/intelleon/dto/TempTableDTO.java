package lk.intelleon.dto;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class TempTableDTO {
    private String orderId;
    private String propertyId;
    private String productName;
    private Double unitPrice;
    private int qty;
    private Double discount;
    private Double subTotal;
    private String refId;

    public TempTableDTO() {
    }

    public TempTableDTO(String orderId, String propertyId, String productName, Double unitPrice, int qty, Double discount, Double subTotal, String refId) {
        this.orderId = orderId;
        this.propertyId = propertyId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.discount = discount;
        this.subTotal = subTotal;
        this.refId = refId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    @Override
    public String toString() {
        return "TempTableDTO{" +
                "orderId='" + orderId + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", discount=" + discount +
                ", subTotal=" + subTotal +
                ", refId='" + refId + '\'' +
                '}';
    }
}
