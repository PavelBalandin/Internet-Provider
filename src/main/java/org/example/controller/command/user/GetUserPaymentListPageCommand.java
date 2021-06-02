package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.controller.command.CommandUtility;
import org.example.model.entity.Payment;
import org.example.model.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GetUserPaymentListPageCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetUserPaymentListPageCommand.class);

    private PaymentService paymentService;

    public GetUserPaymentListPageCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Commands starts");
        ResourceBundle rb = CommandUtility.setResourceBundle(request);

        String userLogin = (String) request.getSession().getAttribute("login");

        List<Payment> paymentList = new ArrayList<>();

        try {
            paymentList = paymentService.getPaymentListByUser(userLogin);
        } catch (RuntimeException ex) {
            request.setAttribute("errorMessage", rb.getString("message.error"));
            logger.error(rb.getString("message.error"));
            logger.error(ex);
        }

        BigDecimal sum = paymentList.stream()
                .map(Payment::getPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        request.getSession().setAttribute("paymentList", paymentList);
        request.getSession().setAttribute("total", sum);


        logger.trace("Total: " + sum);
        logger.trace("Payment list: " + paymentList);

        logger.debug("Commands finished");
        return "/WEB-INF/views/user/user_payment_list_page.jsp";
    }
}
