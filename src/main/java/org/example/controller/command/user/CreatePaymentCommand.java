package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class CreatePaymentCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreatePaymentCommand.class);

    PaymentService paymentService;

    public CreatePaymentCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Commands starts");

        String errorMessage = null;

        String payment = request.getParameter("payment");

        String userLogin = (String) request.getSession().getAttribute("login");

        if (payment == null || payment.equals("") || !payment.matches("[0-9]+(.[0-9]{1,2})?")
                || new BigDecimal(payment).compareTo(BigDecimal.ZERO) == 0) {
            errorMessage = "Please fill all fields correctly";
            logger.error(errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            return "/WEB-INF/views/user/add_funds_page.jsp";
        }
        try {
            paymentService.createPayment(userLogin, new BigDecimal(payment));
            request.setAttribute("successMessage", "Transaction is finished successfully");
            logger.trace("Transaction is finished successfully");
        } catch (RuntimeException ex) {
            errorMessage = "Transaction isn't finished successfully";
            logger.error(errorMessage);
            request.setAttribute("errorMessage", errorMessage);
        }

        logger.debug("Commands finished");
        return "/WEB-INF/views/user/add_funds_page.jsp";
    }
}
