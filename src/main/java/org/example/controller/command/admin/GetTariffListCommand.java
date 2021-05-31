package org.example.controller.command.admin;

import org.apache.log4j.Logger;
import org.example.controller.command.Command;
import org.example.model.entity.TariffPage;
import org.example.model.service.TariffService;

import javax.servlet.http.HttpServletRequest;

public class GetTariffListCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetTariffListCommand.class);

    private final TariffService tariffService;

    public GetTariffListCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("Command starts");

        String page = request.getParameter("page");
        if (page == null) {
            page = "0";
        }

        String size = request.getParameter("size");
        if (size == null) {
            size = "5";
        }

        TariffPage tariffPage = tariffService.getPaginated(Integer.parseInt(page), Integer.parseInt(size));
        request.setAttribute("tariffPage", tariffPage);

        logger.trace("Tariff list:" + tariffPage.getTariffList());
        int lel = (int) Math.ceil(tariffPage.getTotal() / tariffPage.getPageSize());

        logger.debug("Commands finished");
        return "/WEB-INF/views/admin/edit_tariff_page.jsp";
    }
}
