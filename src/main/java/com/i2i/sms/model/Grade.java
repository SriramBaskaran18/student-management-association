package com.i2i.sms.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * The Grade class represents a grade entity with attributes such as id, standard, section, and students.
 * It includes getter and setter methods for accessing and modifying these attributes.
 * </p>
 */
@Builder
@Getter
@Setter
@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "standard")
    private int standard;

    @Column(name = "section")
    private String section;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Student> students;

    public Grade() {
    }

    public Grade(int id, int standard,
                 String section, Set<Student> students) {
        this.id = id;
        this.standard = standard;
        this.section = section;
        this.students = students;
    }
}