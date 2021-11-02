/**
 A class to record the teams formed by many individual authors.
 Homework Assignment: BOOKS
 @author Judy Li, Kenneth Valero, Ron Riley Co
 @version 1.01 10/29/2021
 */
package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

@Entity
@NamedNativeQuery(name = "IndividualAuthors.catalog",
        query = "SELECT * " +
                "FROM AuthoringEntities " +
                "WHERE authoringEntityType = 'Individual Authors' " +
                "ORDER BY email",
        resultClass = IndividualAuthors.class
)

@DiscriminatorValue("Individual Authors")
public class IndividualAuthors extends AuthoringEntities {
    @ManyToMany(mappedBy = "individualAuthorsList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    /**
     * A list of ad hoc teams associated with the author.
     */
    private List<AdHocTeams> adHocTeamsList;

    /**
     * The default constructor of the individual author.
     */
    public IndividualAuthors() {
    }
    //Ends default constructor

    /**
     * The overloaded constructor of the individual author.
     * Creates an instance of author in authoring entity superclass.
     * @param name Author's name.
     * @param email Author's e-mail.
     */
    public IndividualAuthors(String name, String email) {
        super(name, email, "Individual Authors");
        adHocTeamsList = new ArrayList<>();
    }
    //Ends overloaded constructor

    /**
     * Retrieves the list of ad hoc teams.
     * @return the list of ad hoc teams related to the author.
     */
    public List<AdHocTeams> getAdHocTeams() {
        return adHocTeamsList;
    }
    //Ends getAdHocTeams method

    /**
     * Add the specified ad hoc team to the list
     * if not created yet and adds the author to
     * the group.
     * @param adHocTeam The ad hoc team to be added.
     */
    public void addAdHocTeam (AdHocTeams adHocTeam) {
        if (!adHocTeamsList.contains(adHocTeam)) {
            adHocTeamsList.add(adHocTeam);
            adHocTeam.addAuthor(this);
        }
        //Ends if statement
    }
    //Ends addAdHocTeam method

    /**
     * Removes the specified ad hoc team from the list
     * if it exists and removes the author from the group.
     * @param adHocTeam The ad hoc team to be removed.
     */
    public void removeAdHocTeam (AdHocTeams adHocTeam) {
        if (adHocTeamsList.contains(adHocTeam)) {
            adHocTeamsList.remove(adHocTeam);
            adHocTeam.removeAuthor(this);
        }
        //Ends if statement
    }
    //Ends removeAdHocTeam method

    /**
     * An overridden function to return the information about the
     * individual author in a string format.
     * @return A listing of information about the individual author.
     */
    @Override
    public String toString () {
        String temp = "This Author is in the following teams: \n";
        for (AdHocTeams team: adHocTeamsList) {
            temp += "Team: " + team + "\n";
        }
        //Ends for loop
        return temp;
    }
    //Ends toString method

    /**
     * Check if two individual authors are the same.
     * @param object the author to be compared.
     * @return A statement on whether or not they are the same author.
     */
    @Override
    public boolean equals (Object object) {
        return super.equals(object);
    }
    //Ends equals method
}
//Ends IndividualAuthors class