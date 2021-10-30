/**
 A class to record the teams formed by many individual authors.
 Homework Assignment: BOOKS
 @author Judy Li
 @version 1.01 10/29/2021
 */

package csulb.cecs323.model;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

public class AdHocTeams extends AuthoringEntities {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable (name = "adHocTeam",
                joinColumns = @JoinColumn(name = "adHocTeamEmail"),
                inverseJoinColumns = @JoinColumn(name = "individualAuthorEmail"))
    private List<IndividualAuthors> individualAuthorsList;

    public AdHocTeams(String name, String email) {
        super(name, email, "Ad Hoc Team");
        this.individualAuthorsList = new ArrayList<>();
    }

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
        String temp = "This team contains:\n";
        for (Object o: individualAuthorsList) {
            temp += "Author: " + o + "\n";
        }
        return temp;
    }

    @Override
    public boolean equals (Object o) {
        return super.equals(o);
    }
}
