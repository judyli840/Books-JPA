/**
 A class to record the writing groups formed by many individual authors.
 Homework Assignment: BOOKS
 @author Judy Li, Kenneth Valero, Ron Riley Co
 @version 1.01 10/29/2021
 */
package csulb.cecs323.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class WritingGroups extends AuthoringEntities {
    /**
     * The lead writer of the group.
     */
    @Column(nullable = true, length = 100)
    private String headWriter;

    /**
     * The year the group was formed.
     */

    @Column(nullable = true)
    private int yearFormed;

    /**
     * Default constructor of the writing group.
     */
    public WritingGroups() {
    }
    //Ends default constructor

    /**
     * The overloaded constructor of the writing group.
     * @param name the name of the group
     * @param email the group's e-mail
     * @param headWriter The head writer of the group.
     * @param yearFormed The year the group was formed.
     */
    public WritingGroups(String name, String email, String headWriter, int yearFormed) {
        super(name, email, "Writing Group");
        this.headWriter = headWriter;
        super.setHeadWriter(headWriter);
        this.yearFormed = yearFormed;
        super.setYearFormed(yearFormed);
    }
    //Ends overloaded constructor

    /**
     * Retrieve the year that the group was formed.
     * @return the year of formation.
     */
    public int getYearFormed() {
        return yearFormed;
    }
    //Ends yearFormed getter

    /**
     * Retrieve the name of the head writer.
     * @return the name of the head writer.
     */
    public String getHeadWriter() {
        return headWriter;
    }
    //Ends headWriter getter

    /**
     * Update the head writer of the group.
     * @param headWriter name of the new head writer.
     */
    public void setHeadWriter(String headWriter) {
        this.headWriter = headWriter;
    }
    //Ends setHeadWriter method

    /**
     * Retrieve the year that the group was formed.
     * @param yearFormed The formation year.
     */
    public void setYearFormed(int yearFormed) {
        this.yearFormed = yearFormed;
    }
    //Ends setYearFormed method

    /**
     * An overridden function to return the information about the
     * writing group in a string format.
     * @return A listing of information about the writing group.
     */
    @Override
    public String toString () {
        return "Writing Group- \n   email: " + super.getEmail() +
                "\n    name: " + super.getName() +
                "\n    Head Writer: " + this.headWriter +
                "\n    Year Formed: " + this.yearFormed;
    }
    //Ends toString method

    /**
     * Check to see if one writing group is the same as the passed in group.
     * @param object The group to be compared.
     * @return A statement on if both groups are equal.
     */
    @Override
    public boolean equals (Object object) {
        return super.equals(object);
    }
    //Ends equals method
}
//Ends WritingGroups class