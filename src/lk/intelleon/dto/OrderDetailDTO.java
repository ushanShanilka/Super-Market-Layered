package lk.intelleon.dto;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class OrderDetailDTO {
    private int qty;
    private Double unitPrice;
    private Double disCount;
    private String orderId;
    private String productId;
    private String refId;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int qty, Double unitPrice, Double disCount, String orderId, String productId, String refId) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.disCount = disCount;
        this.orderId = orderId;
        this.productId = productId;
        this.refId = refId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getDisCount() {
        return disCount;
    }

    public void setDisCount(Double disCount) {
        this.disCount = disCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", disCount=" + disCount +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", refId='" + refId + '\'' +
                '}';
    }
}
