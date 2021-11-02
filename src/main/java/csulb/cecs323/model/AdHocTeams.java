/**
 A class to record the teams formed by many individual authors.
 Homework Assignment: BOOKS
 @author Judy Li
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
    private List<IndividualAuthors> individualAuthorsList;

    public AdHocTeams(String name, String email) {
        super(name, email, "Ad Hoc Team");
        this.individualAuthorsList = new ArrayList<>();
    }

    public AdHocTeams() {}

    public List<IndividualAuthors> getIndividualAuthorsList() {
        return individualAuthorsList;
    }

    public void addAuthor (IndividualAuthors author) {
        if (!individualAuthorsList.contains(author)) {
            individualAuthorsList.add(author);
            author.addAdHocTeam(this);
        }
    }

    public void removeAuthor (IndividualAuthors author) {
        if (individualAuthorsList.contains(author)) {
            individualAuthorsList.remove(author);
            author.removeAdHocTeam(this);
        }
    }

    public String toString () {
        String temp = "";
        for (IndividualAuthors o: individualAuthorsList) {
            temp += "   Author: " + o.getName() + "\n";
        }
        return temp;
    }

    @Override
    public boolean equals (Object o) {
        return super.equals(o);
    }
}
