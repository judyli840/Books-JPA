/**
 A class to record the teams formed by many individual authors.
 Homework Assignment: BOOKS
 @author 
 @version 1.01 10/29/2021
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.
import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Logger;
import java.lang.*;



public class BooksMain {
   /**
    * You will likely need the entityManager in a great many functions throughout your application.
    * Rather than make this a global variable, we will make it an instance variable within the CarClub
    * class, and create an instance of CarClub in the main.
    */
   private EntityManager entityManager;

   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(BooksMain.class.getName());

   /**
    * The constructor for the CarClub class.  All that it does is stash the provided EntityManager
    * for use later in the application.
    * @param manager    The EntityManager that we will use.
    */
   public BooksMain(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("Books");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of CarClub and store our new EntityManager as an instance variable.
      BooksMain booksMain = new BooksMain(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();
      // 1. Creating New Objects (Authoring Entities, Publisher, book)
      // AUTHORING ENTITY OBJECTS
      System.out.println ();
      System.out.println ("1. Creating New Objects (Authoring Entities, Publisher, book)");
      List <AuthoringEntities> authoringEntitiesList = new ArrayList<>();
      WritingGroups writingGroup1 = new WritingGroups("Dream Team", "JKRowling@gmail.com", "J. K. Rowling", 2000);
      authoringEntitiesList.add(writingGroup1);
      IndividualAuthors author1 = new IndividualAuthors("Toby J. Teorey", "TTeorey@gmail.com");
      authoringEntitiesList.add(author1);
      AdHocTeams adHocTeam1 = new AdHocTeams("Larry Rockoff", "LRockoff@gmail.com");
      authoringEntitiesList.add(adHocTeam1);
      // Add an Individual Author to an existing Ad Hoc Team
      adHocTeam1.addAuthor(author1);
      author1.addAdHocTeam(adHocTeam1);
      booksMain.createEntity (authoringEntitiesList);
      // PUBLISHER OBJECT
      List <Publishers> publishers = new ArrayList<>();
      Publishers publisher1 = new Publishers("Bloomsbury Publishing", "(888) 330-8477", "onlinesales@bloomsburyprofessional.com");
      publishers.add(publisher1);
      booksMain.createEntity (publishers);
      // BOOKS OBJECT
      List <Books> books = new ArrayList<>();
      Books book1 = new Books("9780590353403", "Harry Potter and the Sorcerer's Stone", 1997, author1, publisher1);
      books.add(book1);
      booksMain.createEntity (books);

      // 2. List all information about Objects (publisher, Book, Writing Group)
      System.out.println ();
      System.out.println ("2. List all information about Objects (publisher, Book, Writing Group)");
      System.out.println(publisher1);
      System.out.println(book1);
      System.out.println(writingGroup1);

      tx.commit();

      // 3. Delete a Book
      // Prompt the user to delete an existing book (ask for title and publisher)
      System.out.println ();
      System.out.println ("3. Delete a Book");

      boolean done = false;
      while (!done) {
         try {
            List<Books> allBooks = manager.createNamedQuery("Books.select.catalog",
                    Books.class).getResultList();
            for (int i = 0; i < allBooks.size(); i++) {
               System.out.println(allBooks.get(i));
            }
            System.out.println("Please enter the title of the book you want to delete: ");
            Scanner removeInput = new Scanner(System.in);
            String titleToDelete = removeInput.nextLine();
            System.out.println(titleToDelete);
            System.out.println("Please enter the name of the publisher of the book you want to delete: ");
            String publisherToDelete = removeInput.nextLine();
            List<Books> booksToDelete = manager.createNamedQuery("Books.select.titlePublisher",
                    Books.class).setParameter(1, titleToDelete)
                    .setParameter(2, publisherToDelete)
                    .getResultList();
            if (booksToDelete.size() > 0) {
               Books book = manager.find(Books.class, booksToDelete.get(0).getISBN());
               if (book != null) {
                  manager.getTransaction().begin();
                  manager.remove(book);
                  manager.getTransaction().commit();
               }
               done = true;
            } else {
               System.out.println("The book you are looking for does not exist within the database.");
               System.out.println("Please try again.");
            }
         } catch (Exception e) {
            System.out.println("This books doesn't exist. Please try again.");
         }
      }

      // 4. Update a book
      System.out.println ();
      System.out.println ("4. Update a book");
      manager.getTransaction().begin();
      book1.setAuthoringEntity(writingGroup1);
      manager.merge(book1);
      manager.getTransaction().commit();

      // 5. List Primary Key of every row of Publishers, Books, authoring entities
      System.out.println ();
      System.out.println ("5. List Primary Key");
      List<Publishers> publisherList = manager.createNamedQuery("Publishers.select.getPK",
                    Publishers.class).getResultList();
      for (int i = 0; i < publisherList.size(); i++) {
         System.out.println("Publisher Name: " + publisherList.get(i).getName() + "\n");
      }
      List<Books> bookList = manager.createNamedQuery("Books.select.getPK",
              Books.class).getResultList();
      for (int i = 0; i < bookList.size(); i++) {
         System.out.println("Book ISBN: " + bookList.get(i).getISBN() + "\n");
      }
      List<AuthoringEntities> authoringEntitiesList1 = manager.createNamedQuery("AuthoringEntities.select.getPK",
              AuthoringEntities.class).getResultList();
      for (int i = 0; i < authoringEntitiesList1.size(); i++) {
         System.out.println("Author E-mail: " + authoringEntitiesList1.get(i).getEmail() + ", Type: " + authoringEntitiesList1.get(i).getAuthoringEntityType() + "\n");
      }

      LOGGER.fine("End of Transaction");

   } // End of the main method

   /**
    * Create and persist a list of objects to the database.
    * @param entities   The list of entities to persist.  These can be any object that has been
    *                   properly annotated in JPA and marked as "persistable."  I specifically
    *                   used a Java generic so that I did not have to write this over and over.
    */
   public <E> void createEntity(List <E> entities) {
      for (E next : entities) {
         LOGGER.info("Persisting: " + next);
         // Use the CarClub entityManager instance variable to get our EntityManager.
         this.entityManager.persist(next);
      }

      // The auto generated ID (if present) is not passed in to the constructor since JPA will
      // generate a value.  So the previous for loop will not show a value for the ID.  But
      // now that the Entity has been persisted, JPA has generated the ID and filled that in.
      for (E next : entities) {
         LOGGER.info("Persisted object after flush (non-null id): " + next);
      }
   } // End of createEntity member method

   /**
    * Think of this as a simple map from a String to an instance of auto_body_styles that has the
    * same name, as the string that you pass in.  To create a new Cars instance, you need to pass
    * in an instance of auto_body_styles to satisfy the foreign key constraint, not just a string
    * representing the name of the style.
    * @param name       The name of the autobody style that you are looking for.
    * @return           The auto_body_styles instance corresponding to that style name.
    */
   public Publishers getPublisher (String name) {
      // Run the native query that we defined in the .
      List<Publishers> publishers = this.entityManager.createNamedQuery("Publishers.select.getPublisher",
              Publishers.class).setParameter(1, name).getResultList();
      if (publishers.size() == 0) {
         // Invalid style name passed in.
         return null;
      } else {
         // Return the style object that they asked for.
         return publishers.get(0);
      }
   }// End of the getStyle method
} // End of CarClub class
