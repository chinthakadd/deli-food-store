package com.chinthakad.delifoodstore.presentation.controller;

import com.chinthakad.delifoodstore.presentation.model.InvoiceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Manage the customers invoices as a resource.
 * Currently exposes all the historical invoices of the customer.
 */
@RestController
@RequestMapping("/customers/{customer-id}/invoices")
public class CustomerInvoiceController {

    @GetMapping
    public List<InvoiceDto> getInvoices() {
        return null;
    }
}
