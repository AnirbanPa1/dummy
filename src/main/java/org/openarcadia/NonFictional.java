package org.openarcadia;

import lombok.Data;

import javax.persistence.*;

@Entity
@DiscriminatorValue("NON_FICTIONAL")
@Data
public class NonFictional extends Book {
    @Column
    private String subject;
}
