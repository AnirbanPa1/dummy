package org.openarcadia;

import lombok.Data;

import javax.persistence.*;

@Entity
@DiscriminatorValue("FICTIONAL")
@Data
public class Fictional extends Book {
    @Column
    private String genre;
}
