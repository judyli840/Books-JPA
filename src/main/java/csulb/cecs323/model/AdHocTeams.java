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

@Entity
public class AdHocTeams extends AuthoringEntities {
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable (name = "adHocTeamMember",
            joinColumns = @JoinColumn(name = "adHocTeamEmail", nullable=false, columnDefinition="varchar(30)"),
            inverseJoinColumns = @JoinColumn(name = "individualAuthorEmail", nullable=false, columnDefinition="varchar(30)"))
    /**
     * The list of individual authors in the team.
     */
    private List<IndividualAuthors> individualAuthorsList;

    /**
     * The overloaded constructor of the ad hoc team.
     * Creates instance of ad hoc team in authoring entity superclass.
     * @param name Name of the ad hoc team.
     * @param email E-mail of the ad hoc team.
     */
    public AdHocTeams(String name, String email) {
        super(name, email, "Ad Hoc Team");
        this.individualAuthorsList = new ArrayList<>();
    }
    //Ends overloaded constructor

    /**
     * The default constructor of the ad hoc teams.
     */
    public AdHocTeams() {
    }
    //Ends default constructor

    /**
     * Retrieves the list of authors in the ad hoc team.
     * @return List of authors in the team.
     */
    public List<IndividualAuthors> getIndividualAuthorsList() {
        return individualAuthorsList;
    }
    //Ends getIndividualAuthorsList method

    /**
     * Add an author to the ad hoc team if they are not in the list yet.
     * Adds ad hoc team to author's associated ad hoc team list.
     * @param author The author to be added.
     */
    public void addAuthor (IndividualAuthors author) {
        if (!individualAuthorsList.contains(author)) {
            individualAuthorsList.add(author);
            author.addAdHocTeam(this);
        }
        //Ends if statement
    }
    //Ends addAuthor method

    /**
     * Remove an author from the ad hoc team if they are a member.
     * Removes the ad hoc team from the author's associated ad hoc team list.
     * @param author The author to be removed.
     */
    public void removeAuthor (IndividualAuthors author) {
        if (individualAuthorsList.contains(author)) {
            individualAuthorsList.remove(author);
            author.removeAdHocTeam(this);
        }
        //Ends if statement
    }
    //Ends removeAuthor method

    /**
     * An overridden function to display information about the ad hoc team as a string.
     * @return Information about the ad hoc team.
     */
    public String toString () {
        String temp = "";
        for (IndividualAuthors o: individualAuthorsList) {
            temp += "   Author: " + o.getName() + "\n";
        }
        //Ends for loop
        return temp;
    }
    //Ends toString method

    /**
     * Check if two ad hoc teams are the same.
     * @param object the ad hoc team to be compared.
     * @return A statement on whether or not the two ad hoc teams are the same.
     */
    @Override
    public boolean equals (Object object) {
        return super.equals(object);
    }
    //Ends equals method
}
//Ends AdHocTeams class