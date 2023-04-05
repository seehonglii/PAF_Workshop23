package nus.edu.sg.iss.app.workshop23.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderDetails {
    private Integer orderId;
    private DateTime orderDate;
    private Integer customerId;
    private Double totalDiscountedPrice;
    private Double totalCostPrice;
    
    public OrderDetails() {
    }
    public OrderDetails(Integer orderId, DateTime orderDate, Integer customerId, Double totalDiscountedPrice,
            Double totalCostPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.totalDiscountedPrice = totalDiscountedPrice;
        this.totalCostPrice = totalCostPrice;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Double getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }
    public void setTotalDiscountedPrice(Double totalDiscountedPrice) {
        this.totalDiscountedPrice = totalDiscountedPrice;
    }
    public Double getTotalCostPrice() {
        return totalCostPrice;
    }
    public void setTotalCostPrice(Double totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }
    
    @Override
    public String toString() {
        return "OrderDetails [orderId=" + orderId + ", orderDate=" + orderDate + ", customerId=" + customerId
                + ", totalDiscountedPrice=" + totalDiscountedPrice + ", totalCostPrice=" + totalCostPrice + "]";
    }
    public static OrderDetails create(SqlRowSet resultSet) {
        
        OrderDetails orderDetails = new OrderDetails();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime orderDate = formatter.parseDateTime(resultSet.getString("order_date"));

        orderDetails.setOrderId(resultSet.getInt("order_id"));
        orderDetails.setOrderDate(orderDate);
        orderDetails.setCustomerId(resultSet.getInt("customer_id"));
        orderDetails.setTotalDiscountedPrice(resultSet.getDouble("discounted_price"));
        orderDetails.setTotalCostPrice(resultSet.getDouble("cost_price"));

        return orderDetails;
    }
    public JsonObject toJson() {
        return Json.createObjectBuilder().add("order_id", getOrderId())
                                            .add("order_date", getOrderDate().toString(DateTimeFormat.forPattern("dd-MM-yyyy")))
                                            .add("customer_id", getCustomerId())
                                            .add("discounted_price", getTotalDiscountedPrice())
                                            .add("cost_price", getTotalCostPrice())
                                            .build();
    }
    

    
}
