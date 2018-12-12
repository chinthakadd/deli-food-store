# Deli Food Store - A POC on DDD and Hexagonal Architecture

An attempt to model an Online Retail System following the principles of Hexagonal Architecture and Domain Driven Design.
The following are some of the concepts that were adhered to while building this prototype.

This prototype is built, considering the following requirements in mind.

## Business Requirements

### Product Catalog
- As a customer, I should be able to view of list of items that are on sale - DONE
- As a customer, I should be able to know the following about each item 
  price, name, description, return policy, discounts (if applicable)  - DONE
- Items must only be displayed to the customer, if there is at least item available in the stocks - DONE
- Product Catalog must be represented as a paginated list - PENDING

### Payments
- As a customer, I should be able to view my currently registered payment methods - DONE
- As a customer, I should be able to add new payment methods - DONE
- As a customer, I should be able to use a registered payment method at the point of checkout
- As a customer, I should be notified by an email or by SMS if a payment has been made


### Shopping Cart
- As a customer, I should be able to view the items of shopping cart at any point - DONE
  the shopping cart should indicate how many items are there, individual prices as well as the 
  total price.
- As a customer, I should be able to add an item to the shopping cart - DONE
- If a customer does not have total less than 30 USD in his shopping cart, he would not be able to checkout

### Order
- As a customer, I should be able to make the current shopping cart to an order - DONE
- As a customer I should be able to pay and get an invoice that should include the invoice number, 
  discounts applied and total amount paid. - DONE
- When the order is made, all the stocks must be updated to reflect the right number of orders


# Pending Technical Work

- Convert Repository implementation to JPA with H2 
- Write an exception framework mechanism. Will we carry the same exception from domain to controller? 


# Implementation Details

TODO: Write notes on how we got the Domain Objects to work with Spring Beans using Aspectj Load time Weaving concept.



