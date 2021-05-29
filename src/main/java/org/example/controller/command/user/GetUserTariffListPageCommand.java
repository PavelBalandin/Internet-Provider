package org.example.controller.command.user;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.Tariff;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserTariffListPageCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetUserTariffListPageCommand.class);

    private TariffService tariffService;

    public GetUserTariffListPageCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Commands starts");

        String login = (String) request.getSession().getAttribute("login");

        List<Tariff> tariffList = tariffService.getTariffsByUserLogin(login);

        request.setAttribute("tariffList", tariffList);
        logger.trace("Tariff list: " + tariffList);

        logger.debug("Commands finished");
        return "/WEB-INF/views/user/user_tariff_page.jsp";
    }
}
