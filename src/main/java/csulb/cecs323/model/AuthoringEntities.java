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
        query = "SELECT * " +
                "FROM AuthoringEntities " +
                "WHERE email = ? ")
// returns all authors
@NamedNativeQuery(name = "AuthoringEntities.catalog",
        query = "SELECT * " +
                "FROM AuthoringEntities " +
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
    /**
     * The name of the authoring entity.
     */
    @Column(nullable=false, length = 80)
    private String name;

    /**
     * The e-mail of the authoring entity.
     */
    @Id
    @Column(nullable=false, length = 30)
    private String email;

    /**
     * The type of the authoring entity.
     */
    @Column(length = 31)
    private String authoringEntityType;

    /**
     * The head writer of the writing group.
     * Only applies if the authoring entity is a writing group.
     */
    @Column(length = 80)
    private String headWriter;

    /**
     * The formation year of the writing group.
     * Only applies if the authoring entity is a writing group.
     */
    private int yearFormed;

    /**
     * The default constructor of AuthoringEntities
     */
    public AuthoringEntities() {}
    //End of default constructor

    /**
     * The overloaded constructor of the authoring entity.
     * @param name of authoring entity.
     * @param email of authoring entity.
     * @param typeOfAuthoringEntity The type of the authoring entity.
     */
    public AuthoringEntities(String name, String email, String typeOfAuthoringEntity) {
        this.name = name;
        this.email = email;
        this.authoringEntityType = typeOfAuthoringEntity;
    }
    //End of overloaded constructor

    /**
     * Retrieve the name of the authoring entity.
     * @return Authoring entity's name.
     */
    public String getName() {
        return name;
    }
    //End of getName method

    /**
     * Change the name of the authoring entity.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }
    //End of setName method

    /**
     * Retrieve the e-mail of the authoring entity.
     * @return Authoring entity's e-mail.
     */
    public String getEmail() {
        return email;
    }
    //End of getEmail method

    /**
     * Change the e-mail of the authoring entity.
     * @param email The new e-mail.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    //End of setEmail method

    /**
     * Update the head writer of the group.
     * Only applies if the authoring entity is a writing group.
     * @param headWriter of the new head writer.
     */
    public void setHeadWriter (String headWriter) {
        this.headWriter = headWriter;
    }
    //End of setHeadWriter method

    /**
     * Update the year that the group was formed.
     * Only applies if the authoring entity is a writing group.
     * @param yearFormed The formation year.
     */
    public void setYearFormed (int yearFormed) {
        this.yearFormed = yearFormed;
    }
    //End of setYearFormed method

    /**
     * Retrieve the type of the authoring entity.
     * @return authoring entity's type.
     */
    public String getAuthoringEntityType () {
        return this.authoringEntityType;
    }
    //End of getAuthoringEntityType method

    /**
     * An overridden function to display information about the authoring entity as a string.
     * @return Information about the authoring entity.
     */
    @Override
    public String toString () {
        return "Name: " + this.name + "\nEmail: " + this.email;
    }
    //End of toString method

    /**
     * Check if two authoring entities are the same.
     * @param object the authoring entity to be compared.
     * @return A statement on whether or not they are the same authoring entity.
     */
    @Override
    public boolean equals (Object object) {
        AuthoringEntities authoringEntity = (AuthoringEntities) object;
        return this.getEmail() == authoringEntity.getEmail();
    }
    //End of equals method

    /**
     * An overridden function to find the hashcode of a given authoring entity.
     * @return the authoring entity's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }
    //End of hashCode method
}
//End of AuthoringEntities class