package org.example.model.entity;

import java.util.List;

public class TariffPage {
    private List<Tariff> tariffList;
    private List<Service> serviceList;
    private int page;
    private int pageSize;
    private int total;

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
