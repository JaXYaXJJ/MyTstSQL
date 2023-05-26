package edu.hackeru.evgenyzakalinsky.mytstsql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "bands",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"bandName"})})
public class Band {

    //controller -> service -> repository -> entity

    @GeneratedValue
    @Id
    private long id;

    @NotNull
    private String bandName;

    @NotNull
    private String genre;

    @NotNull
    private String country;
}
