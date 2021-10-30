/**
 A class to record the teams formed by many individual authors.
 Homework Assignment: BOOKS
 @author Judy Li
 @version 1.01 10/29/2021
 */
package csulb.cecs323.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import java.util.List;


@NamedNativeQuery(name = "IndividualAuthors.catalog",
                  query = "SELECT *" +
                          "FROM AuthoringEntities" +
                          "WHERE authoringEntityType" +
                          "ORDER BY name",
                  resultClass = IndividualAuthors.class
)

@DiscriminatorValue("Individual Authors")
public class IndividualAuthors extends AuthoringEntities {
    @ManyToMany(mappedBy = "individualAuthorsList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<AdHocTeams> adHocTeamsList;

    public IndividualAuthors() {}

    public IndividualAuthors(String name, String email) {
        super(name, email, "Individual Authors");
    }

    public List<AdHocTeams> getAdHocTeams() {
        return adHocTeamsList;
    }

    public void addAdHocTeam (AdHocTeams adHocTeam) {
        if (!adHocTeamsList.contains(adHocTeam)) {
            adHocTeamsList.add(adHocTeam);
            adHocTeam.addAuthor(this);
        }
    }

    public void removeAdHocTeam (AdHocTeams adHocTeam) {
        if (adHocTeamsList.contains(adHocTeam)) {
            adHocTeamsList.remove(adHocTeam);
            adHocTeam.removeAuthor(this);
        }
    }

    @Override
    public String toString () {
        String temp = "This Author is in the following teams: \n";
        for (Object o: adHocTeamsList) {
            temp += "Team: " + o + "\n";
        }
        return temp;
    }

    @Override
    public boolean equals (Object o) {
        return super.equals(o);
    }
}
