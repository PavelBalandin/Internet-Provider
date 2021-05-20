package org.example.model;

import java.util.Objects;

public class Tariff extends BaseEntity {

    private static final long serialVersionUID = 1619410103773367970L;

    private String name;

    private String description;

    private String duration;

    private Integer price;

    private int serviceId;

    public Tariff(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return serviceId == tariff.serviceId &&
                Objects.equals(name, tariff.name) &&
                Objects.equals(description, tariff.description) &&
                Objects.equals(duration, tariff.duration) &&
                Objects.equals(price, tariff.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, duration, price, serviceId);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                ", serviceId=" + serviceId +
                '}';
    }
}
