package com.faceit.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "state")
    private String state;

    @Column(name = "name")
    private String name;

    @Column(name = "institution_type")
    private String institutionType;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    public University() {
    }
}
