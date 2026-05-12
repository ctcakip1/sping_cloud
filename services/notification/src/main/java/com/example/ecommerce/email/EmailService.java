package com.example.ecommerce.email;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.ecommerce.kafka.order.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount,
            String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());

        messageHelper.setFrom("tinigaming058@gmail.com");
        final String templateName = EmailTemplates.PAYMEENT_CONFIRMATION.getTemplate();
        Map<String, Object> variables = Map.of(
                "customerName", customerName,
                "amount", amount,
                "orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplates.PAYMEENT_CONFIRMATION.getSubject());
        try {
            String htmlBody = templateEngine.process(templateName, context);
            messageHelper.setText(htmlBody, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Payment success email sent to {}", destinationEmail);
        } catch (Exception e) {
            log.warn("cannot send email to {}", destinationEmail);
            log.error("Fail to send payment success email ", e);
        }
    }

    @Async
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal totalAmount,
            String orderReference, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());

        messageHelper.setFrom("tinigaming058@gmail.com");
        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();
        Map<String, Object> variables = Map.of(
                "customerName", customerName,
                "totalAmount", totalAmount,
                "orderReference", orderReference,
                "products", products);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());
        try {
            String htmlBody = templateEngine.process(templateName, context);
            messageHelper.setText(htmlBody, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Payment success email sent to {}", destinationEmail);
        } catch (Exception e) {
            log.warn("cannot send email to {}", destinationEmail);
            log.error("Fail to send payment success email ", e);
        }
    }
}
