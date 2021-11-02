/**
 A class to record the books.
 Homework Assignment: BOOKS
 @author Judy Li, Kenneth Valero, Ron Riley Co
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

@NamedNativeQuery(name = "Books.select.titleAuthoringEntity",
        query = "SELECT* " +
                "FROM books " +
                "WHERE title = ? and authoringEntityName = ? ",
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
    /**
     * The ISBN of the book.
     */
    @Id
    @Column(nullable=false, length = 17)
    private String ISBN;

    /**
     * The title of the book.
     */
    @Column(nullable=false, length = 80)
    private String title;

    /**
     * The year of publishing of the book.
     */
    @Column(nullable=false)
    private int yearPublished;

    /**
     * The publisher of the book.
     */
    @ManyToOne
    @JoinColumn(name = "publisherName", columnDefinition="varchar(80)")
    private Publishers publisher;

    /**
     * The authoring entity of the book.
     */
    @ManyToOne
    @JoinColumn(name = "authoringEntityName", columnDefinition="varchar(30)")
    private AuthoringEntities authoringEntity;

    /**
     * Default constructor of the writing group.
     */
    public Books() {} //Ends default constructor

    /**
     * The overloaded constructor of the writing group.
     * @param ISBN The ISBN of the book.
     * @param title The title of the book.
     * @param year The year of publishing of the book.
     * @param authoring_entity The authoring entity of the book.
     * @param publisher The publisher of the book.
     */
    public Books(String ISBN, String title, int year, AuthoringEntities authoring_entity, Publishers publisher) {
        this.ISBN = ISBN;
        this.title = title;
        this.yearPublished = year;
        this.authoringEntity = authoring_entity;
        this.publisher = publisher;
    } //Ends overloaded constructor

    /**
     * Retrieve the year that the group was formed.
     * @return the year of formation.
     */
    public String getISBN() {
        return ISBN;
    } //Ends ISBN getter

    /**
     * Retrieve the title of the book.
     * @return the year of formation.
     */
    public String getTitle() {
        return title;
    } //Ends title getter

    /**
     * Retrieve the year published of the book.
     * @return the year of formation.
     */
    public int getYearPublished() {
        return yearPublished;
    } //Ends year published getter

    /**
     * Retrieve the authoring entity of the book.
     * @return the year of formation.
     */
    public AuthoringEntities getAuthoringEntity() {
        return authoringEntity;
    } //Ends authoring entity getter

    /**
     * Retrieve the publisher of the book.
     * @return the year of formation.
     */
    public Publishers getPublisher() {
        return publisher;
    } //Ends publisher getter

    /**
     * Sets the ISBN to a new value.
     * @param isbn The ISBN of a book.
     */
    public void setISBN(String isbn) {
        this.ISBN = isbn;
    } //Ends setISBN method

    /**
     * Sets the title to a new value.
     * @param title The title of a book.
     */
    public void setTitle(String title) {
        this.title = title;
    } //Ends setTitle method

    /**
     * Sets the year published to a new value.
     * @param year The year published of a book.
     */
    public void setYearPublished(int year) {
        this.yearPublished = year;
    } //Ends setYearPublished method

    /**
     * Sets the authoring entity to a new value.
     * @param author The authoring entity of a book.
     */
    public void setAuthoringEntity(AuthoringEntities author) {
        this.authoringEntity = author;
    } //Ends setAuthoringEntity method

    /**
     * Sets the publisher name to a new value.
     * @param name The publisher of a book.
     */
    public void setPublisherName(Publishers name) {
        this.publisher = name;
    } //Ends setPublisherName method

    /**
     * An overridden function to return the information about the Books class.
     * @return A listing of information about the Books class.
     */
    @Override
    public String toString () {
        return "Book - \n   ISBN: " + this.ISBN
                + "\n   Title: " + this.title
                + "\n   Published: " + this.yearPublished
                + "\n   Authoring Entity: " + authoringEntity.getName()
                + "\n   Publisher: " + this.publisher.getName();
    } //Ends toString method

    /**
     * Check to see if one books is the same as the passed in books.
     * @param object The books to be compared.
     * @return  True if both books are equal. False otherwise.
     */
    @Override
    public boolean equals (Object object) {
        Books b = (Books) object;
        return this.getISBN() == b.getISBN();
    } //Ends equals method
} //Ends Books class