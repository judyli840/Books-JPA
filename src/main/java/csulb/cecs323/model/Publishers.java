/**
 A class to record publishers.
 Homework Assignment: BOOKS
 @author Judy Li, Kenneth Valero, Ron Riley Co
 @version 1.01 10/29/2021
 */
package csulb.cecs323.model;

import javax.persistence.*;
import java.lang.*;
import java.lang.Object;

@Entity
@NamedNativeQuery(name = "Publishers.select.getPublisher",
        query = "SELECT * " +
                "FROM Publishers " +
                "WHERE name = ? ",
        resultClass = Publishers.class)

@NamedNativeQuery(name = "Publishers.select.getPK",
        query = "SELECT name " +
                "FROM Publishers " +
                "ORDER BY name ",
        resultClass = Publishers.class)


@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"phone"}),
                            @UniqueConstraint(columnNames = {"email"})})
public class Publishers {
    /**
     * The name of the publisher.
     */
    @Id
    @Column(nullable=false, length = 80)
    private String name;

    /**
     * The phone number of the publisher.
     */
    @Column(nullable=false, length = 24)
    private String phone;

    /**
     * The email of the publisher.
     */
    @Column(nullable=false, length = 80)
    private String email;

    /**
     * Default constructor of Publishers.
     */
    public Publishers() {} //Ends default constructor

    /**
     * The overloaded constructor of the writing group.
     * @param name The name of this specific publisher.
     * @param phone The phone number of the publisher.
     * @param email The email of the publisher.
     */
    public Publishers (String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    } //Ends overloaded constructor

    /**
     * Retrieve the name of the Publisher.
     * @return name.
     */
    public String getName() {
        return name;
    } //Ends getName Method

    public void setName(String name) {
        this.name = name;
    } //Ends setName Method

    /**
     * Retrieve the phone number of the Publisher.
     * @return string representation of phone number.
     */
    public String getPhone() {
        return phone;
    } //Ends getPhone Method

    /**
     * Retrieve the phone number of the Publisher.
     * @return string representation of phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    } //Ends setPhone method

    /**
     * Retrieve email of the Publisher.
     * @return string of the email.
     */
    public String getEmail() {
        return email;
    } //Ends getEmail method

    /**
     * set email of the Publisher.
     * @param email The email of the publisher.
     */
    public void setEmail(String email) {
        this.email = email;
    } //Ends setEmail method

    /**
     * An overridden function to return the information about the
     * writing group in a string format.
     * @return A listing of all the information about the Publisher.
     */
    @Override
    public String toString () {
        return "Publisher- \n" +
                "   Name: " + this.name + "\n" +
                "   Phone: " + this.phone + "\n" +
                "   email: " + this.email;
    } //Ends toString method

    /**
     * Check to see if one writing group is the same as the passed in group.
     * @param object The group to be compared.
     * @return True if both publishers are equal. False otherwise.
     */
    @Override
    public boolean equals (Object object) {
        Publishers publisher = (Publishers) object;
        return this.getName() == publisher.getName();
    } //Ends equals method
} //Ends Publishers class