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
                  query = "SELECT*" +
                          "FROM books" +
                          "WHERE title = ? and publisher = ?",
                  resultClass = Books.class)

@NamedNativeQuery(name = "Books.select.titleAuthor",
        query = "SELECT*" +
                "FROM books" +
                "WHERE title = ? and authoringEntityName = ?",
        resultClass = Books.class)

@NamedNativeQuery(name = "Books.select.catalog",
        query = "SELECT*" +
                "FROM books" +
                "ORDER BY title",
        resultClass = Books.class)
// check primary key
@NamedNativeQuery(name = "Books.count.ISBN",
        query = "SELECT *" +
                "FROM books" +
                "WHERE IBSN = ?")
// check candidate keys title and publisher
@NamedNativeQuery(name = "Books.count.titlePublisher",
                  query = "SELECT count(*)" +
                          "FROM books" +
                          "WHERE title = ? AND publisher = ?")
@NamedNativeQuery(name = "Books.count.titleAuthor",
        query = "SELECT count(*)" +
                "FROM books" +
                "WHERE title = ? AND author = ?")
// check candidate keys authoring entity name and publisher
@NamedNativeQuery(name = "Books.authoringEntityNamePublisher",
        query = "SELECT count(*)" +
                "FROM books" +
                "WHERE authoringEntityName = ? AND title = ?")


@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"publisher", "title"}),
                            @UniqueConstraint(columnNames = {"title", "authoringEntityName"})})
public class Books
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    @Column(nullable=false, length = 20)
    private String ISBN;

    @Column(nullable=false, length = 64)
    private String title;

    @Column(nullable=false)
    private int year_published;

    @ManyToOne
    @JoinColumn(name = "publisher")
    private Publishers publisher;

    @ManyToOne
    @JoinColumn(name = "authoringEntityName")
    private AuthoringEntities authoring_entity;

    public Books() {}
    public Books(String ISBN, String title, int year, AuthoringEntities authoring_entity, Publishers publisher) {
        this.ISBN = ISBN;
        this.title = title;
        this.year_published = year;
        this.authoring_entity = authoring_entity;
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public int getYearPublished() {
        return year_published;
    }

    public AuthoringEntities getAuthoringEntityName() {
        return authoring_entity;
    }

    public Publishers getPublisherName() {
        return publisher;
    }

    public void setISBN(String x) {
        this.ISBN = x;
    }

    public void setTitle(String x) {
        this.title = x;
    }


    public void setYearPublished(int x) {
        this.year_published = x;
    }

    public void setAuthoringEntity(AuthoringEntities x) {
        this.authoring_entity = x;
    }

    public void setPublisherName(Publishers x) {
        this.publisher = x;
    }


    @Override
    public String toString () {
        return "ISBN: " + this.ISBN
                + "\nTitle: " + this.title
                + "\nPublished: " + this.year_published
                + "\nAuthor: " + this.authoring_entity
                + "\nPublisher: " + this.publisher;
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