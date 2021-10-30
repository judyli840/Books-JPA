/**
 A class to record the individual/team that creates a book.
 Homework Assignment: BOOKS
 @author Judy Li
 @version 1.01 10/29/2021
 */
package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

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

@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "authoringEntityType", discriminatorType = DiscriminatorType.STRING)
/** A person or group responsible for preparing books to go on sale. */
public abstract class AuthoringEntities {
    @Column(nullable=false, length = 100)
    private String name;

    @Id
    @Column(nullable=false, length = 50)
    private String email;

    @Column(nullable=false, length = 50)
    private String typeOfAuthoringEntity;

    public AuthoringEntities() {}

    public AuthoringEntities(String name, String email, String typeOfAuthoringEntity) {
        this.name = name;
        this.email = email;
        this.typeOfAuthoringEntity = typeOfAuthoringEntity;
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