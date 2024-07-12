package org.example.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.UpdateBillingStatusRequest;
import org.example.entity.Billing;
import org.example.exception.BadRequestException;
import org.example.repository.BillingRepository;
import org.example.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class BillingServiceImpl implements BillingService {

    @Autowired
    private JavaMailSender emailSender;
    final BillingRepository billingRepository;
    final TemplateEngine templateEngine;
    @Override
    @Transactional
    public Billing addBilling(Billing billing) {
        CompletableFuture.runAsync(()->{
            try {
                sendNotiForAdmin(billing);
                sendNotiForBooker(billing);
            } catch (MessagingException e) {
                log.error("Failing when send message {}", e.getMessage());
            }
        });
        return billingRepository.save(billing);
    }

    @Override
    public List<Billing> getAllBilling(String status) {
        if(ObjectUtils.isEmpty(status)){
            return billingRepository.findAllByOrderByCreatedDateDesc();
        }
        return billingRepository.findAllByStatusOrderByCreatedDateDesc(status.toLowerCase());
    }

    @Override
    public Billing updateBilling(UpdateBillingStatusRequest updateBillingStatusRequest) {
        Billing billing = billingRepository.findById(updateBillingStatusRequest.getBillID())
                .orElseThrow(() -> new BadRequestException("Billing not found"));
        billing.setStatus(updateBillingStatusRequest.getStatus().toLowerCase());
        return billingRepository.save(billing);
    }

    private void sendNotiForAdmin(Billing billing) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        Context context = new Context();
        context.setVariable("cakes", billing.getSelectedCakes());
        context.setVariable("accessories", billing.getSelectedAccessories());
        helper.setFrom("noreply@sentbe.com");
        helper.setTo("dieuthuhiencb2002@gmail.com");
        helper.setSubject("Savor Cake Thông Báo: Một đơn hàng đã được đặt");
        String htmlContent = templateEngine.process("admin_notification_email", context);
        helper.setText(htmlContent, true); // true indicates HTML content
        emailSender.send(message);
    }
    private void sendNotiForBooker(Billing billing) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        Context context = new Context();
        context.setVariable("accessories", billing.getSelectedAccessories());
        context.setVariable("name",billing.getBookerName());
        context.setVariable("cakes", billing.getSelectedCakes());
        context.setVariable("receiverName", billing.getReceiverName());
        context.setVariable("receiverPhone", billing.getReceiverPhone());
        helper.setFrom("noreply@sentbe.com");
        helper.setTo(billing.getBookerEmail());
        helper.setSubject("Savor Cake Thông Báo: Một đơn hàng đã được đặt");
        String htmlContent = templateEngine.process("booker_notification_email", context);
        helper.setText(htmlContent, true); // true indicates HTML content
        emailSender.send(message);
    }
}
