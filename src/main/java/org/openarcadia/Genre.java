package org.openarcadia;

import javax.persistence.Entity;

@Entity
public class Genre {
    private String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
