/**
 A class to record the individual/team that creates a book.
 Homework Assignment: BOOKS
 @author Judy Li
 @version 1.01 10/29/2021
 */
package csulb.cecs323.model;

import javax.persistence.*;
import java.util.*;
import javax.persistence.Entity;

@Entity
// check Primary key
@NamedNativeQuery(name = "AuthoringEntities.count.email",
        query = "SELECT count(*)" +
                "FROM AuthoringEntities" +
                "WHERE email = ?")
// returns all authors
@NamedNativeQuery(name = "AuthoringEntities.catalog",
        query = "SELECT *" +
                "FROM AuthoringEntities" +
                "ORDER BY name",
        resultClass = AuthoringEntities.class)

@NamedNativeQuery(name = "AuthoringEntities.select.getPK",
        query = "SELECT email, authoringEntityType " +
                "FROM AuthoringEntities " +
                "ORDER BY name",
        resultClass = AuthoringEntities.class)

@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "authoringEntityType", discriminatorType = DiscriminatorType.STRING)
/** A person or group responsible for preparing books to go on sale. */
public class AuthoringEntities {
    @Column(nullable=false, length = 80)
    private String name;

    @Id
    @Column(nullable=false, length = 30)
    private String email;

    @Column(length = 31)
    private String authoringEntityType;

    @Column(length = 80)
    private String headWriter;

    private int yearFormed;

    public AuthoringEntities() {}

    public AuthoringEntities(String name, String email, String typeOfAuthoringEntity) {
        this.name = name;
        this.email = email;
        this.authoringEntityType = typeOfAuthoringEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeadWriter (String headWriter) {
        this.headWriter = headWriter;
    }

    public void setYearFormed (int yearFormed) {
        this.yearFormed = yearFormed;
    }

    public String getAuthoringEntityType () {
        return this.authoringEntityType;
    }

    @Override
    public String toString () {
        return "Name: " + this.name + "\nEmail: " + this.email;
    }
    @Override
    public boolean equals (Object o) {
        AuthoringEntities authoringEntity = (AuthoringEntities) o;
        return this.getEmail() == authoringEntity.getEmail();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }
}