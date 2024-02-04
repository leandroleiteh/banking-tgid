package com.lebanking.api.application.email;

import com.lebanking.api.application.dtos.output.TransactionOutputDTO;
import com.lebanking.api.common.exception.UnableSendEmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class EmailTransaction {

    private final JavaMailSender emailSender;

    public EmailTransaction(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Value("${spring.mail.username}")
    private String fromEmail;
    public static final String NEW_TRANSACTION_DEPOSITO = "Nova Transação - Depósito";
    public static final String NEW_TRANSACTION_SAQUE = "Nova Transação - Saque";
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String EMAIL_TEMPLATE_HTML = "SendEmailTemplate";

    @Async
    public void sendEmailNewTransactionDeposit(TransactionOutputDTO transactionOutputDTO, String clientEmail) {
        transactionEmail(transactionOutputDTO, EMAIL_TEMPLATE_HTML, NEW_TRANSACTION_DEPOSITO, clientEmail);
    }

    @Async
    public void sendEmailNewTransactionWithDrawal(TransactionOutputDTO transactionOutputDTO, String clientEmail) {
        transactionEmail(transactionOutputDTO, EMAIL_TEMPLATE_HTML, NEW_TRANSACTION_SAQUE, clientEmail);
    }

    private void transactionEmail(TransactionOutputDTO transactionOutputDTO, String emailTemplate, String typeEmailMeeting, String clientEmail) {
        try {

            String text = "Transação efetuada com Sucesso, no valor de R$ " + transactionOutputDTO.amount().toString() + "0";

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(typeEmailMeeting);
            helper.setFrom(fromEmail);
            helper.setTo(clientEmail);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (MessagingException emailException) {
            throw new UnableSendEmailException("Não foi possível enviar o e-mail: " + emailException.getMessage());
        }
    }


}
