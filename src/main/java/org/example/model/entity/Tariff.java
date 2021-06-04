package org.example.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Tariff extends BaseEntity {

    private static final long serialVersionUID = 3189516751945246894L;

    private String name;

    private String description;

    private int duration;

    private BigDecimal price;

    private Service service;

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return duration == tariff.duration &&
                Objects.equals(name, tariff.name) &&
                Objects.equals(description, tariff.description) &&
                Objects.equals(price, tariff.price) &&
                Objects.equals(service, tariff.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, duration, price, service);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", service=" + service +
                '}';
    }

    public static class Builder {
        private Tariff tariff;

        public Builder() {
            tariff = new Tariff();
        }

        public Builder withId(int id) {
            tariff.setId(id);
            return this;
        }

        public Builder withName(String name) {
            tariff.setName(name);
            return this;
        }

        public Builder withDescription(String description) {
            tariff.setDescription(description);
            return this;
        }

        public Builder withDuration(int duration) {
            tariff.setDuration(duration);
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            tariff.setPrice(price);
            return this;
        }

        public Builder withService(Service service) {
            tariff.setService(service);
            return this;
        }

        public Tariff build() {
            return tariff;
        }

    }
}
