package lk.intelleon.view.tm;

public class OrderDetailTM {
    private int qty;
    private Double unitPrice;
    private Double disCount;
    private String orderId;
    private String propertyId;
    private String refId;

    public OrderDetailTM ( ) {
    }

    public OrderDetailTM(int qty, Double unitPrice, Double disCount, String orderId, String propertyId, String refId) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.disCount = disCount;
        this.orderId = orderId;
        this.propertyId = propertyId;
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

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", disCount=" + disCount +
                ", orderId='" + orderId + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ", refId='" + refId + '\'' +
                '}';
    }
}
