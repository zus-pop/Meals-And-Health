package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import utils.Address;
import utils.OrderStatus;
import utils.ShipCompany;

/**
 *
 * @author hoang
 */
public class Order implements Serializable {

    private int id;
    private UserAccount customer;
    private Date orderDate;
    private ShipCompany shipVia;
    private Address deliveryAddress;
    private double total;
    private OrderStatus status;
    private List<OrderDetail> details;

    public Order() {
    }

    public Order(
            int id,
            UserAccount customer,
            Date orderDate,
            ShipCompany shipVia,
            Address deliveryAddress,
            double total,
            OrderStatus status,
            List<OrderDetail> details) {
        this.id = id;
        this.customer = customer;
        this.orderDate = orderDate;
        this.shipVia = shipVia;
        this.deliveryAddress = deliveryAddress;
        this.total = total;
        this.status = status;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccount getCustomer() {
        return customer;
    }

    public void setCustomer(UserAccount customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ShipCompany getShipVia() {
        return shipVia;
    }

    public void setShipVia(ShipCompany shipVia) {
        this.shipVia = shipVia;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customer=" + customer + ", orderDate=" + orderDate + ", shipVia=" + shipVia + ", deliveryAddress=" + deliveryAddress + ", total=" + total + ", status=" + status + ", details=" + details + '}';
    }
}
