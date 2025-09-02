package org.openarcadia;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Author {
    private String firstName;
    private String lastName;
    private String email;

    public Author() {}

    public Author(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
