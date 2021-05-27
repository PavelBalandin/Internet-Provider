package org.example.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service extends BaseEntity {

    private static final long serialVersionUID = -6912017878743681130L;

    private String name;

    private List<Tariff> tariffs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(name, service.name) &&
                Objects.equals(tariffs, service.tariffs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tariffs);
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", tariffs=" + tariffs +
                '}';
    }

    public static class Builder {
        private Service service;

        public Builder() {
            service = new Service();
        }

        public Builder withId(int id) {
            service.setId(id);
            return this;
        }

        public Builder withName(String name) {
            service.setName(name);
            return this;
        }

        public Builder withTariffs(List<Tariff> tariffs) {
            service.setTariffs(tariffs);
            return this;
        }

        public Service build() {
            return service;
        }
    }
}
