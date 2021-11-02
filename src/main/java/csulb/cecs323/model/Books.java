/**
 A class to record information about each book.
 Homework Assignment: BOOKS
 @author Judy Li
 @version 1.01 10/29/2021
 */

package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NamedNativeQuery(name = "Books.select.titlePublisher",
          query = "SELECT* " +
                  "FROM books " +
                  "WHERE title = ? and publisherName = ? ",
          resultClass = Books.class)

@NamedNativeQuery(name = "Books.select.catalog",
        query = "SELECT* " +
                "FROM Books " +
                "ORDER BY title ",
        resultClass = Books.class)

@NamedNativeQuery(name = "Books.count.ISBN",
        query = "SELECT count(*) " +
                "FROM Books " +
                "WHERE IBSN = ?")

@NamedNativeQuery(name = "Books.select.getPK",
        query = "SELECT ISBN " +
                "FROM Books " +
                "ORDER BY title",
        resultClass = Books.class)


@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"publisherName", "title"}),
                            @UniqueConstraint(columnNames = {"title", "authoringEntityName"})})
public class Books
{
    @Id
    @Column(nullable=false, length = 17)
    private String ISBN;

    @Column(nullable=false, length = 80)
    private String title;

    @Column(nullable=false)
    private int yearPublished;

    @ManyToOne
    @JoinColumn(name = "publisherName", columnDefinition="varchar(80)")
    private Publishers publisher;

    @ManyToOne
    @JoinColumn(name = "authoringEntityName", columnDefinition="varchar(30)")
    private AuthoringEntities authoringEntity;

    public Books() {}
    public Books(String ISBN, String title, int year, AuthoringEntities authoring_entity, Publishers publisher) {
        this.ISBN = ISBN;
        this.title = title;
        this.yearPublished = year;
        this.authoringEntity = authoring_entity;
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public AuthoringEntities getAuthoringEntity() {
        return authoringEntity;
    }

    public Publishers getPublisher() {
        return publisher;
    }

    public void setISBN(String x) {
        this.ISBN = x;
    }

    public void setTitle(String x) {
        this.title = x;
    }


    public void setYearPublished(int x) {
        this.yearPublished = x;
    }

    public void setAuthoringEntity(AuthoringEntities x) {
        this.authoringEntity = x;
    }

    public void setPublisherName(Publishers x) {
        this.publisher = x;
    }


    @Override
    public String toString () {
        return "Book - \n   ISBN: " + this.ISBN
                + "\n   Title: " + this.title
                + "\n   Published: " + this.yearPublished
                + "\n   Authoring Entity: " + authoringEntity.getName()
                + "\n   Publisher: " + this.publisher.getName();
    }
    @Override
    public boolean equals (Object o) {
        Books b = (Books) o;
        return this.getISBN() == b.getISBN();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getISBN());
    }
}