package org.example.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -830334692397369281L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseEntity(Long id) {
        this.id = id;
    }
}
