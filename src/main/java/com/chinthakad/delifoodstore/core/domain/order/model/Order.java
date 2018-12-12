package com.chinthakad.delifoodstore.core.domain.order.model;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE, dependencyCheck = false)
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Order {

    private long id;
    private long customerId;
    private Invoice invoice;
    private List<LineItem> lineItems;
    private Instant orderDate;
    private BigDecimal total;

    public Order(long customerId, Instant orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    @Autowired
    private OrderRepository orderRepository;

    public Invoice generateInvoice(long paymentMethodId) {
        this.invoice = new Invoice(customerId, paymentMethodId, total);
        orderRepository.save(this);
        return invoice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
