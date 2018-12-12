package com.chinthakad.delifoodstore.core.domain.shoppingcart.model;

import com.chinthakad.delifoodstore.core.domain.order.model.Order;
import com.chinthakad.delifoodstore.core.domain.product.model.Product;
import com.chinthakad.delifoodstore.seedwork.util.DomainExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ShoppingCart {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCart.class);

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    private long id;

    // One-to-One  mapping with customer ID being the
    // primary key of customer table.
    private long customerId;

    private List<ShoppingCartItem> items;

    private BigDecimal total = new BigDecimal(0);
    private BigDecimal totalDiscount = new BigDecimal(0);

    public static ShoppingCart newFor(long customerId) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomerId(customerId);
        return shoppingCart;
    }

    public void addProductToCart(Product product) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(items.size() + 1, product);
        items.add(shoppingCartItem);
        total = total.add(shoppingCartItem.getPrice());
        totalDiscount = totalDiscount.add(shoppingCartItem.getDiscount());
        shoppingCartRepository.save(this);
    }


    public void removeItem(long productId) {
        Optional<ShoppingCartItem> itemOpt = this.items.stream()
                .filter(shoppingCartItem -> shoppingCartItem.getProduct().getId() == productId).findFirst();
        if (itemOpt.isPresent()) {
            this.items.remove(itemOpt.get());
            shoppingCartRepository.save(this);
        } else {
            LOGGER.warn("No item found for the given product id: %d", productId);
        }
    }

    public void emptyCart() {
        this.total = new BigDecimal(0);
        this.totalDiscount = new BigDecimal(0);
        this.items = new ArrayList<>();
        shoppingCartRepository.save(this);
    }

    /**
     * Perform checkout of the cart, if the order total is greater than 30 dollars.
     * @return
     */
    public Order checkout() {
        if(total.compareTo(new BigDecimal(30)) < 0){
            throw DomainExceptionHelper.insufficientCheckoutTotal(total);
        }
        Order order = convertShoppingCartToOrder();
        this.emptyCart();
        return order;
    }

    private Order convertShoppingCartToOrder() {
        Order order = new Order(customerId, Instant.now());
        return order;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
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

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    /**
     * Two Shopping Carts are equal if they belong to the same customer. Because
     * a customer can have only one shopping cart always.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

}
