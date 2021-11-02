package csulb.cecs323.model;

import javax.persistence.*;
import java.lang.*;
import java.lang.Object;

/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2021 David Brown <david.brown@csulb.edu>
 *
 */

@Entity

@NamedNativeQuery(name = "Publishers.count.name",
        query = "SELECT count(*)" +
                "FROM Publishers" +
                "WHERE name = ?")

@NamedNativeQuery(name = "Publishers.select.getPublisher",
        query = "SELECT * " +
                "FROM Publishers " +
                "WHERE name = ? ",
        resultClass = Publishers.class)

@NamedNativeQuery(name = "Publishers.select.getPK",
        query = "SELECT name " +
                "FROM Publishers " +
                "ORDER BY name ",
        resultClass = Publishers.class)


@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"phone"}),
                            @UniqueConstraint(columnNames = {"email"})})
/** A person or group responsible for preparing books to go on sale. */
public class Publishers {
    @Id
    @Column(nullable=false, length = 80)
    private String name;

    @Column(nullable=false, length = 24)
    private String phone;

    @Column(nullable=false, length = 80)
    private String email;

    public Publishers() {}

    public Publishers (String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString () {
        return "Publisher- \n" +
                "   Name: " + this.name + "\n" +
                "   Phone: " + this.phone + "\n" +
                "   email: " + this.email;
    }

    @Override
    public boolean equals (Object o) {
        Publishers publisher = (Publishers) o;
        return this.getName() == publisher.getName();
    }
}