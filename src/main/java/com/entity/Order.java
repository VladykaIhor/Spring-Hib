package com.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_table")

public class Order {

    @GeneratedValue
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "zip")
    private String zip;

    public Order() {
    }

    public Order(String name, String surname, String email, String country,
                 String state, String city, String phoneNumber, String zip) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.country = country;
        this.state = state;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(name, order.name) &&
                Objects.equals(surname, order.surname) &&
                Objects.equals(email, order.email) &&
                Objects.equals(country, order.country) &&
                Objects.equals(state, order.state) &&
                Objects.equals(city, order.city) &&
                Objects.equals(phoneNumber, order.phoneNumber) &&
                Objects.equals(zip, order.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, country, state, city, phoneNumber, zip);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}