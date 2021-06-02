package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public class CreatePaymentCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreatePaymentCommand.class);

    PaymentService paymentService;

    public CreatePaymentCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Commands starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);
        String payment = request.getParameter("payment");
        String userLogin = (String) request.getSession().getAttribute("login");

        if (payment == null || payment.equals("") || !payment.matches("[0-9]+(.[0-9]{1,2})?")
                || new BigDecimal(payment).compareTo(BigDecimal.ZERO) == 0) {
            logger.error(rb.getString("message.empty.fields"));
            request.setAttribute("errorMessage", rb.getString("message.empty.fields"));
            return "/WEB-INF/views/user/add_funds_page.jsp";
        }
        try {
            paymentService.createPayment(userLogin, new BigDecimal(payment));
            request.setAttribute("successMessage", rb.getString("message.user.payment"));
            logger.trace("Transaction is finished successfully");
        } catch (RuntimeException ex) {
            logger.error(rb.getString("message.error"));
            request.setAttribute("errorMessage", rb.getString("message.error"));
        }

        logger.debug("Commands finished");
        return "/WEB-INF/views/user/add_funds_page.jsp";
    }
}
