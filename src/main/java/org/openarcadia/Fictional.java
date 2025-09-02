package org.openarcadia;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Fictional extends PerBook {
    @Column
    private String genre;
}
