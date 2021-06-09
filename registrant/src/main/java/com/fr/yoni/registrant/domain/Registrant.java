package com.fr.yoni.registrant.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Registrant class to define a user(=registrant)
 * @author Yoni Baroukh
 */
@Entity
public class Registrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Lastname is mandatory")
    private String lastname;

    @NotEmpty(message = "Firstname is mandatory")
    private String firstname;

    @NotEmpty(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Age is mandatory")
    @Min(value = 18, message = "Only adult ( age > 18 years ) can create account")
    private Integer age;

    @Column(columnDefinition = "boolean default false")
    private boolean student;

    @NotEmpty(message = "Country is mandatory")
    private String country;


    public Registrant(){}

    public Registrant(Long id, String lastname, String firstname, String email, Integer age, boolean student, String country) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.age = age;
        this.student = student;
        this.country = country;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Registrant{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }
}
