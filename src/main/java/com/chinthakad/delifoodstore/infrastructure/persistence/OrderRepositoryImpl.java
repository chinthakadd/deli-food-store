package com.chinthakad.delifoodstore.infrastructure.persistence;

import com.chinthakad.delifoodstore.core.domain.order.model.Order;
import com.chinthakad.delifoodstore.core.domain.order.model.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepositoryImpl.class);

    @Override
    public void save(Order order) {
        LOGGER.info("Saved Order: {}", order);
    }
}
