package com.chinthakad.delifoodstore.core.application;

import com.chinthakad.delifoodstore.core.domain.customer.model.CustomerRepository;
import com.chinthakad.delifoodstore.core.domain.order.model.Invoice;
import com.chinthakad.delifoodstore.core.domain.order.model.Order;
import com.chinthakad.delifoodstore.core.domain.product.model.Product;
import com.chinthakad.delifoodstore.core.domain.product.model.ProductRepository;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCartRepository;
import com.chinthakad.delifoodstore.seedwork.util.DomainExceptionHelper;
import com.chinthakad.delifoodstore.core.domain.customer.model.Customer;
import com.chinthakad.delifoodstore.core.domain.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   ProductRepository productRepository, CustomerRepository customerRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    private ShoppingCartRepository shoppingCartRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    @Override
    public ShoppingCart viewShoppingCart(long customerId) {
        Optional<ShoppingCart> shoppingCartOpt = shoppingCartRepository.findByCustomerId(customerId);
        if (shoppingCartOpt.isPresent()) {
            return shoppingCartOpt.get();
        }
        throw DomainExceptionHelper.shoppingCartNotAvailable(customerId);

    }

    @Override
    public void addItem(long customerId, long productId) {
        Optional<ShoppingCart> shoppingCartOpt = shoppingCartRepository.findByCustomerId(customerId);
        if (!shoppingCartOpt.isPresent()) {
            throw DomainExceptionHelper.shoppingCartNotAvailable(customerId);
        }
        ShoppingCart shoppingCart = shoppingCartOpt.get();
        Optional<Product> productOpt = productRepository.findById(productId);
        if (!productOpt.isPresent()) {
            throw DomainExceptionHelper.productNotFound(productId);
        }
        shoppingCart.addProductToCart(productOpt.get());
    }


    @Override
    public void removeItem(long customerId, long productId) {
        Optional<ShoppingCart> shoppingCartOpt = shoppingCartRepository.findByCustomerId(customerId);
        if (!shoppingCartOpt.isPresent()) {
            throw DomainExceptionHelper.shoppingCartNotAvailable(customerId);
        }
        ShoppingCart shoppingCart = shoppingCartOpt.get();
        shoppingCart.removeItem(productId);
    }

    @Override
    public void emptyCart(long customerId) {
        Optional<ShoppingCart> shoppingCartOpt = shoppingCartRepository.findByCustomerId(customerId);
        if (!shoppingCartOpt.isPresent()) {
            throw DomainExceptionHelper.shoppingCartNotAvailable(customerId);
        }
        ShoppingCart shoppingCart = shoppingCartOpt.get();
        shoppingCart.emptyCart();
    }

    @Override
    public Invoice checkout(long customerId, long paymentMethodId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent()) {
            throw DomainExceptionHelper.customerNotFound(customerId);
        }
        Customer customer = customerOpt.get();

        Optional<ShoppingCart> shoppingCartOpt = shoppingCartRepository.findByCustomerId(customerId);
        if (!shoppingCartOpt.isPresent()) {
            throw DomainExceptionHelper.shoppingCartNotAvailable(customerId);
        }

        //
        // 1. Checkout the shopping cart
        // 2. Authorize the payment for the items
        // 3. Release units from the product store
        // 4. Generate the invoice for the order.
        Order order = shoppingCartOpt.get().checkout();
        customer.authorizePayment(order, paymentMethodId);
        order.getLineItems()
                .stream()
                .forEach(
                        lineItem -> {
                            Product product = productRepository.findById(lineItem.getProductId()).get();
                            // NOTE: Currently only supporting one unit purchases at a time. :)
                            product.releaseUnits(1);
                        }
                );
        return order.generateInvoice(paymentMethodId);
    }

}
