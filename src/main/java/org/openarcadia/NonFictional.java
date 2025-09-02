package org.openarcadia;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NonFictional extends PerBook {
    @Column
    private String subject;
}
