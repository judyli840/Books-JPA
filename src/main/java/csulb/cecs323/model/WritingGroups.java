/**
 A class to record the writing groups formed by many individual authors.
 Homework Assignment: BOOKS
 @author Judy Li
 @version 1.01 10/29/2021
 */
package csulb.cecs323.model;

import javax.persistence.Column;


public class WritingGroups extends AuthoringEntities {
    @Column(nullable = true, length = 100)
    private String headWriter;

    @Column(nullable = true)
    private int yearFormed;

    public WritingGroups() {}

    public WritingGroups(String name, String email, String headWriter, int yearFormed) {
        super(name, email, "Writing Group");
        this.headWriter = headWriter;
        this.yearFormed = yearFormed;
    }

    public int getYearFormed() {
        return yearFormed;
    }

    public String getHeadWriter() {
        return headWriter;
    }

    public void setHeadWriter(String headWriter) {
        this.headWriter = headWriter;
    }

    public void setYearFormed(int yearFormed) {
        this.yearFormed = yearFormed;
    }

    @Override
    public String toString () {
        return "email: " + super.getEmail() + "\nname: " + super.getName() + "Head Writer: " + this.headWriter
                + "\nYear Formed: " + this.yearFormed;
    }

    @Override
    public boolean equals (Object o) {
        return super.equals(o);
    }
}
