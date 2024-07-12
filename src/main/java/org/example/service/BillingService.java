package org.example.service;

import jakarta.mail.MessagingException;
import org.example.dto.request.UpdateBillingStatusRequest;
import org.example.entity.Billing;

import java.util.List;

public interface BillingService {
    Billing addBilling(Billing billing) throws MessagingException;
    List<Billing> getAllBilling(String status);
    Billing updateBilling(UpdateBillingStatusRequest updateBillingStatusRequest);
}
