# Jakarta Persistence API (JPA) hands-on

Repository with examples of the Object Relational Mapping - ORM - framework known as Jakarta Persistence API - JPA. The examples are used in the classes about database persistence taught by Rodrigo Martins Pagliares at UNIFAL-MG (Computer Science Undergraduate course).

## Jakarta Persistence API (Important)

Starting in Java EE 8, Java EE has changed its name to Jakarta EE. In addition to changing the platform name, several packages were migrate to a new package hierarchy that starts with jakarta. That's the reason all source code in this repository are using the new package names to import JPA related classes. In other words, we use

            import jakarta.persistence.Entity;
            import jakarta.persistence.GeneratedValue;
            import jakarta.persistence.GenerationType;
            import jakarta.persistence.Id;


Instead of the old package names:

            //import javax.persistence.Entity;
            //import javax.persistence.GeneratedValue;
            //import javax.persistence.GenerationType;
            //import javax.persistence.Id;

## MySQL

### MySQL database used in this course:
   - jpa_hands_on

### MySQL credentials used in this course:
   - user: root
   - password: root


### The database jpa_hands_on used in this course:

In this hands-on, the database jpa_hands_on is created with aid of **PHP MyAdmin** that comes bundled with **MAMP**.

   
### MySQL Connector/J

JPA depends on a JDBC driver to connect to MySQL. To configure the **JDBC Driver for MySQL (Connector/J)** on **Apache Maven**, include the following lines on the dependencies section of pom.xml (Source: https://mvnrepository.com/artifact/mysql/mysql-connector-java):

     <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.30</version>
    </dependency>

### EclipseLink

There are several JPA implementations out there. This hands-on uses EclipseLinkJPA. This dependency is included on the dependencies section of pom.xml (Source: https://mvnrepository.com/artifact/org.eclipse.persistence/org.eclipse.persistence.jpa)

    <dependency>
         <groupId>org.eclipse.persistence</groupId>
          <artifactId>org.eclipse.persistence.jpa</artifactId>
          <version>4.0.0</version>
    </dependency>
 
## Examples discussed in this hands-on

This hands-on uses the examples provided in the book Enterprise JavaBeans 3.0, 5th edition, by Bill Burke and Richard Monson-Haefel. The examples are about a fictitious cruise company.

<p align="center"><a href="https://amzn.to/3B9mffs" target="_blank"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Book_Cover.jpeg" widht=228 height=300 alt="EJB 3.1 book cover"></a></p>

### Titan cruises: a hypothetical company

The examples in this repository are in the context of a hypothetical company, a ship called Titan Cruises. A cruise ship is a particularly interesting example because it incorporates many different businesses: it has staterooms similar to hotel rooms; serves meals like a restaurant; offers various recreational opportunities; and needs to interact with other tour companies.

This type of business is a good candidate for a distributed object system because many of the users of this system are in different geographic areas. Travel agencies, for example, that need to make reservations for tickets on Titan ships, need access to the reservation system. Supporting several - possibly hundreds - travel agencies requires a robust transactional system capable of ensuring that these agencies have access to the reservation system and these, in turn, are properly completed.

The examples in this repository cover a relatively simple part of the Titan system that focuses on the process of making a reservation for a ship. These examples will give you the opportunity to develop Ship, Cabin, TravelAgent, ProcessPayment and other entity beans. In this process you will need to create relational database tables to persist the data used in the examples. We assume that you are already familiar with relational database management systems and that you are able to create tables according to the provided SQL statements.

### 01 - jpa-hands-on
   - This is a minimum JPA example in Java SE that communicates with a MySQL database.
   - It creates a CUSTOMER table in the jpa_hands_on database.
   - It populates the CUSTOMER table with one customer (Florentino Ariza)
   - It demonstrates how to use the EclipseLink JPA implementation.
   - It demonstrates how to generate autoincremented primary keys with MySQL.

<strong>Imporant Note:</strong>

There are several strategies to generate primary keys in a table when using JPA. From a data access perspective, JPA supports two major types of identifiers: (i) assigned, (ii) generated. The assigned identifiers must be manually set on every given entity prior to being persisted. For this reason, assigned identifiers are suitable for natural keys. For synthetic Primary Keys, we need to use a generated entity identifier, which is supported by JPA through the use of the @GeneratedValue annotation. 

There are four types of generated identifier strategies which are defined by the GenerationType enumeration:(i) AUTO, (ii) IDENTITY, (iii) SEQUENCE, (iv) TABLE, and (V) UUID. The AUTO identifier generator strategy chooses one of the other three strategies (IDENTITY, SEQUENCE or TABLE) based on the underlying relational database capabilities. While IDENTITY maps to an auto-incremented column(e.g. IDENTITY in SQL Server or AUTO_INCREMENT in MySQL) and SEQUENCE is used for delegating the identifier generation to a database sequence, the TABLE generator has no direct implementation in relational databases. Since JPA 3.1, you can annotate a primary key attribute with @GeneratedValue and set the strategy to GenerationType.UUID. Based on the specification, your persistence provider shall generate a UUID value based on IETF RFC 4122. 

In general, when using Oracle SGBD, for instance, I recommend using the SEQUENCE strategy because it allows JPA (and hibernate) to use JDBC batching and other optimization strategies that require the delayed execution of SQL INSERT statements. But you can’t use this strategy with a MySQL database. It requires a database sequence, and MySQL doesn’t support this feature. Hence, if you’re working with a MySQL database, you should always use GenerationType.IDENTITY. It uses an autoincremented database column and is the most efficient approach available. You can do that by annotating your primary key attribute with @GeneratedValue(strategy = GenerationType.IDENTITY).

In addition to automatically generated primary keys, there is the possibility of using composite keys with JPA. A composite primary key is made up of more than one property (for example, suppose a Customer entity that is uniquely identified by it's last name and social security number - SSN, rather than by an automatically generated numerical primary key). JPA provides multiple ways to map composite keys. One is through the @jakarta.persistence.IdClass annotation; the other is through @jakarta.persistence.EmbeddedId. We <strong>DO NOT</strong> discuss composite keys in this hands-on. 

This hands-on uses MySQL and hence, all examples in this repository use <strong>@GeneratedValue(strategy = GenerationType.IDENTITY)</strong>.

### 02 - jpa-hands-on 
   - This example builds on the first example by adding a Cabin entity bean
   - The example also creates two controllers: CustomerController and CabinController
   - The controllers encapsulates the logic to persist and find by id customers and cabins.
   - The example demonstrates how to persist and find by id a customer and a cabin stored in the database.

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_02.png" widht=820 height=493 alt="UML class diagram"></a></p>

### 03 - jpa-hands-on
   - This example builds on the previous example by adding a some update methods to the CabinController class
   - The example also illustrates that we can annotate entity beans in two ways: properties annotations and method annotations
   - The example demonstrates some database transactions in order to persist and update some cabin beans on the database.

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_03.png" widht=358 height=390 alt="UML class diagram"></a></p>

### 04 - jpa-hands-on 
   - This example builds on the previous example by adding a method in the CabinController class to remove a cabin
   - The example also refactors the main class extracting methods to create a cabin and a customer
   - The example refactors the entity beans Cabin and Customer to implement the interface java.io.Serializable

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_04.png" widht=875 height=185 alt="UML class diagram"></a></p>

### 05 - jpa-hands-on
   - This example builds on the previous example by demonstrating how we can change the default mapping for table and column names in a database.  
   - Sometimes this is necessary (when, for example, the Database Administrator is enforcing some conventions on columns and table names that the OO developer doesn't want to follow in the entity beans. 

<pre>
@Entity
<strong>@Table(name="CUSTOMER_TABLE")</strong>

public class Customer implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   <strong>@Column(name="CUST_ID", nullable = false)</strong>
   private Long id;
   <strong>@Column(name="FIRST_NAME", length=20, nullable = false)</strong>
   private String firstName;
   ...
   ...
}
</pre>
