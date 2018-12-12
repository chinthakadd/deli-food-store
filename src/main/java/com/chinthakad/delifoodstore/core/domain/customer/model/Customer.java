package com.chinthakad.delifoodstore.core.domain.customer.model;

import com.chinthakad.delifoodstore.core.domain.order.model.Order;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCart;
import com.chinthakad.delifoodstore.core.integration.PaymentClient;
import com.chinthakad.delifoodstore.seedwork.exception.ShoppingCartException;
import com.chinthakad.delifoodstore.seedwork.util.DomainExceptionHelper;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE, dependencyCheck = false)
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Customer {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentClient paymentClient;

    public Customer() {
    }

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private long id;

    private String name;

    private ShoppingCart shoppingCart = new ShoppingCart();

    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    public void authorizePayment(Order order, long paymentId) {
        Optional<PaymentMethod> paymentMethodOpt = paymentMethods.stream().filter(
                pm -> paymentId == pm.getId()
        ).findFirst();

        if(!paymentMethodOpt.isPresent()){
            throw DomainExceptionHelper.invalidPaymentMethod(paymentId);
        }

        PaymentMethod paymentMethod = paymentMethodOpt.get();
        paymentClient.authorize(this, paymentMethod, order.getTotal());
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) throws ShoppingCartException {

        if (paymentMethod == null || !paymentMethod.isValid()) {
            throw DomainExceptionHelper.invalidPaymentMethod(paymentMethod);
        }

        if (paymentMethods == null) {
            paymentMethods = new ArrayList<>();
        }

        // Temporarily setting id. Remove once we use JPA repositories.
        paymentMethod.setId(paymentMethods.size() + 1);

        paymentMethods.add(paymentMethod);
        this.customerRepository.save(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
