# Jakarta Persistence API (JPA) hands-on

Repository with examples of the Object Relational Mapping - ORM - framework known as Jakarta Persistence API - JPA. The examples are used in the classes about database persistence taught by Rodrigo Martins Pagliares at UNIFAL-MG (Computer Science Undergraduate course).

## Outline

### Part I - Intoduction to this hands-on
<a href="https://github.com/pagliares/jpa-hands-on#jakarta-persistence-api-important">Jakarta Persistence API (Important)</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#mysql">MySQL</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#examples-discussed-in-this-hands-on">Examples discussed in this hands-on</a><br>

### Part II - First steps with JPA

<a href="https://github.com/pagliares/jpa-hands-on#01---a-minimum-jpa-example-in-java-se">01 - A minimum JPA example in Java SE</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#02---persisting-and-finding-entities-by-id">02 - Persisting and finding entities by ID</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#03---updating-entities-and-annotating-properties-and-methods">03 - Updating entities and annotating properties and methods</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#04---removing-an-entity">04 - Removing an entity</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#05---changing-the-default-mapping-for-table-and-column-names-in-a-database">05 - Changing the default mapping for table and column names in a database</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#06---non-persistent-propertiestransient">06 - Non-persistent properties(@Transient)</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#07---mapping-datetime-before-java-se-8">07 - Mapping Date/Time before Java-SE 8</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#08---mapping-datetime-after-java-se-8">08 - Mapping Date/Time after Java-SE 8</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#09---mapping-java-enums">09 - Mapping Java Enums</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#10---refactoring-the-code-to-decouple-a-dao-hiearchy-from-the-controllers">10 - Refactoring the code to decouple a DAO hiearchy from the controllers</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#11---embed-java-objects-inside-an-entity-bean">11 - Embed java objects inside an entity bean</a><br>

### Part III - Mapping relationships between entities

<a href="https://github.com/pagliares/jpa-hands-on#12---mapping-a-unidirectional-one-to-one-relationship">12 - Mapping a unidirectional one-to-one relationship</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#13---mapping-a-bidirectional-one-to-one-relationship">13 - Mapping a bidirectional one-to-one relationship</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#14---mapping-a-unidirectional-one-to-many-relationship">14 - Mapping a unidirectional one-to-many relationship</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#15---mapping-a-unidirectional-one-to-many-relationship-with-a-join-table">15 - Mapping a unidirectional one-to-many relationship with a join table</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#16---mapping-a-unidirectional-many-to-one-relationship">16 - Mapping a unidirectional many-to-one relationship</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#17---refactoring-the-code-to-eliminate-code-duplicate-and-generalize-some-methods">17 - Refactoring the code to eliminate code duplicate and generalize some methods</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#18---mapping-a-bidirectional-one-to-many-relationship">18 - Mapping a bidirectional one-to-many relationship</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#19---mapping-a-bidirectional-many-to-many-relationship">19 - Mapping a bidirectional many-to-many relationship</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#20---mapping-a-unidirectional-many-to-many-relationship">20 - Mapping a unidirectional many-to-many relationship</a><br>

### Part IV - Mapping an inheritance hiearchy

<a href="https://github.com/pagliares/jpa-hands-on#21---mapping-inheritance---the-strategy-single-table">21 - Mapping inheritance - the strategy single-table</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#22---mapping-inheritance---the-strategy-table-per-class">22 - Mapping inheritance - the strategy table per class</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#23---mapping-inheritance---the-strategy-table-per-subclass">23 - Mapping inheritance - the strategy table per subclass</a><br>
<a href="https://github.com/pagliares/jpa-hands-on#24---inheritance-from-a-non-entity-class-with-mappedsuperclass">24 - Inheritance from a non-entity class with @MappedSuperclass</a><br>

### Part V - Queries and JPQL

<a href="https://github.com/pagliares/jpa-hands-on#25---introduction-to-the-query-interface">25 - Introduction to the Query interface</a><br>

## Migrating to Jakarta Persistence API (Important)

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>

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

## MySQL, the MySQL Connector/J JDBC driver, and the EclipseLink JPA implementation
<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>

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

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>

This hands-on uses the examples provided in the book Enterprise JavaBeans 3.0, 5th edition, by Bill Burke and Richard Monson-Haefel. The examples are about a fictitious cruise company.

<p align="center"><a href="https://amzn.to/3B9mffs" target="_blank"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Book_Cover.jpeg" widht=228 height=300 alt="EJB 3.1 book cover"></a></p>

### Titan cruises: a hypothetical company

The examples in this repository are in the context of a hypothetical company, a ship called Titan Cruises. A cruise ship is a particularly interesting example because it incorporates many different businesses: it has staterooms similar to hotel rooms; serves meals like a restaurant; offers various recreational opportunities; and needs to interact with other tour companies.

This type of business is a good candidate for a distributed object system because many of the users of this system are in different geographic areas. Travel agencies, for example, that need to make reservations for tickets on Titan ships, need access to the reservation system. Supporting several - possibly hundreds - travel agencies requires a robust transactional system capable of ensuring that these agencies have access to the reservation system and these, in turn, are properly completed.

The examples in this repository cover a relatively simple part of the Titan system that focuses on the process of making a reservation for a ship. These examples will give you the opportunity to develop Ship, Cabin, TravelAgent, ProcessPayment and other entity beans. In this process you will need to create relational database tables to persist the data used in the examples. We assume that you are already familiar with relational database management systems and that you are able to create tables according to the provided SQL statements.

### 01 - A minimum JPA example in Java SE

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O1/jpa-hands-on

<strong> Introduction </strong>
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

### 02 - Persisting and finding entities by ID

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O2/jpa-hands-on

<strong> Introduction </strong>
- This example builds on the first example by adding a Cabin entity bean
- The example also creates two controllers: CustomerController and CabinController
- The controllers encapsulates the logic to persist and find by id customers and cabins.
- The example demonstrates how to persist and find by id a customer and a cabin stored in the database.

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_02.png" widht=820 height=493 alt="UML class diagram"></a></p>

### 03 - Updating entities and annotating properties and methods

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O3/jpa-hands-on

<strong> Introduction </strong>
- This example builds on the previous example by adding a some update methods to the CabinController class
- The example also illustrates that we can annotate entity beans in two ways: properties annotations and method annotations
- The example demonstrates some database transactions in order to persist and update some cabin beans on the database.

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_03.png" widht=358 height=390 alt="UML class diagram"></a></p>

### 04 - Removing an entity

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O4/jpa-hands-on

<strong> Introduction </strong>
- This example builds on the previous example by adding a method in the CabinController class to remove a cabin
- The example also refactors the main class extracting methods to create a cabin and a customer
- The example refactors the entity beans Cabin and Customer to implement the interface java.io.Serializable

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_04.png" widht=875 height=185 alt="UML class diagram"></a></p>

### 05 - Changing the default mapping for table and column names in a database

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O5/jpa-hands-on

<strong> Introduction </strong>
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

### 06 - Non-persistent properties(@Transient)

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O6/jpa-hands-on

<strong> Introduction </strong>

- This example builds on the previous example by demonstrating that we can indicate that a property must not be persisted in the database by using the JPA annotation @Transient.
- The example also illustrates that a numerical primary key is auto incremented, generating a new value even when we remove an entity from the database. 

<pre>
@Entity
@Table(name="CUSTOMER_TABLE")
public class Customer implements Serializable {
    ...
    ...
    <strong>@Transient</strong>
    private long ssn;
   ...
   ...
}
</pre>

### 07 - Mapping Date/Time before Java-SE 8

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O7/jpa-hands-on

<strong> Introduction </strong>

- This example evolves the previous example by adding a keyboard utility class and aims to produce a simple CRUD (Create, Retrieve, Updte, Delete) command-line program for a customer of the titan cruises.
- From the JPA perpective, the example illustrates the use of the annotation @Temporal and your constants TemporalType.TIMESTAMP and TemporalType.DATE
- The file persistence.xml has some additional comments wit other possible values for the value attribute or the property eclipselink.ddl-generation. Changing theses values is useful for development/testing purposes.
- The examples uses @Temporal that is assigned with Pre Java-SE 8 versions. Hence, i strive to only use date and time manipulation with Pre-Java SE 8 API for dates (not recommended anymore). In the next example, we will refactor this solution by Mapping date/time with JPA to the API used since Java SE 8 (Recommended).
   
<pre>
@Entity
@Table(name="CUSTOMER_TABLE")
public class Customer implements Serializable {
    ...
    ...
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;

    @Temporal(TemporalType.DATE)
    private Date birthDate;
   ...
   ...
}
</pre>
   
- Notice that the controller is getting bigger, with several new methods. This may indicate that it is losing cohesion. In next examples we will deal with this design problem by extracting from the controller the responsibilities to database integration.

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_07.png" widht=886 height=431 alt="UML class diagram"></a></p>
   
### 08 - Mapping Date/Time after Java-SE 8

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O8/jpa-hands-on

<strong> Introduction </strong>

- This example is almost identical to the previous version. The differences is that instead of using @Temporal(TemporalType.TIMESTAMP) that is associated with java.util.Date  (Pre Java-SE 8), in this example we use  LocalDateTime and LocalDate (Since Java-SE 8).
- Since LocalDateTime and LocalDate are considered type values (@Basic) in JPA, We don't need to put any annotation to indicate this is a date/time field anymore.

<pre>
@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    ...
    
    private LocalDateTime timeCreated;
    private LocalDate birthDate;
    
    ...
}
</pre>

### 09 - Mapping Java Enums

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/O9/jpa-hands-on

<strong> Introduction </strong>

- This example demonstrates how we can persist Java Enums to the database through the use of the annotation @Enumerated
   
<pre>
@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    ...
    
    <strong>@Enumerated(EnumType.STRING)</strong>
    private CustomerType customerType;
    
    ...
}
</pre>

- There are two possible values to the EnumType parameter of @Enumerated: EnumType.STRING or EnumType.ORDINAL. The images below demonstrate the impact of each on the table customer:
  
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Database_Table_Enum_String.png" widht=837 height=360 alt="UML class diagram"></a></p>
 
<br>
 
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Database_Table_Enum_Ordinal.png" widht=837 height=360 alt="UML class diagram"></a></p>
  
  
- Below, the UML Class Diagrama update with the code developed so far:
  
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_09.png" widht=894 height=392 alt="UML class diagram"></a></p>
  
### 10 - Refactoring the code to decouple a DAO hiearchy from the controllers

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/10/jpa-hands-on

<strong> Introduction </strong>

- This example does not include any new feature of JPA. Instead, it refactors the code developed so far in order do extract the persistence logic from the controllers to a DAO (Data Access Object) hierarchy, providing higher cohesion to the controller classes.  

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_10.png" widht=901 height=477 alt="UML class diagram"></a></p>

### 11 - Embed java objects inside an entity bean

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/11/jpa-hands-on

<strong> Introduction </strong>

- This example demonstrates the use of the annotations @Embedded, @AttributeOverrides, @AttibuteOverride e @Embeddable.
- These annotation may be used when the developer wants to embed java objects inside an entity bean and map the properties of this embedable object to columns in the database generated for the entity bean.
- Embedabble objects do not have identity (primary key) and are exclusively owned by the entity bean classes associated with them.
   
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_11.png" widht=901 height=477 alt="UML class diagram"></a></p>

### 12 - Mapping a unidirectional one-to-one relationship

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/12/jpa-hands-on

<strong> Introduction </strong>

- This example is an alternative to the previous example. 
- The example uses the concept of relationship between entities.
- This example uses a <strong>UNIDIRECTIONAL ONE-TO-ONE RELATIONSHIP</strong> between Customer and Address (A customer has one address). 
- JPA has 7 types of relationships: UNIDIRECTIONAL ONE-TO-ONE, BIDIRECTIONAL ONE-TO-ONE, UNIDIRECTIONAL ONE-TO-MANY, BIDIRECTIONAL ONE-TO-MANY, UNIDIRECTIONAL MANY-TO-ONE, UNIDIRECTIONAL MANY-TO-MANY, BIDIRECTIONAL MANY-TO-MANY. We are going to study all of them in this hands-on.
- We recommend you to contrast this solution with the solution presented in the previous example that used @Embedded, @AttributeOverrides, @AttributeOverride, and @Embeddable
- This alternative solution generates two tables in the database and associates them  through the foreign key of the table Address presented in the table Customer.
- If you want, you can customize the name of the column to be created in the table Customer by using the annotation @JoinColumn. For example, @JoinColumn(name="ADDR_ID")
- The attribute cascade with value CascadeType.ALL indicates to JPA that if we remove a customer, we also want to automatically delete its address associated. In the same way, if we persist a customer, it's address will be persisted automatically as well.
- CascadeType has other constants, for example, CascadeType.PERSIST. If you use CascadeType.PERSIST you are telling JPA that if you persist a customer, you also want to persist its address, but if you remove a customer, the associate address WILL NOT be deleted from the database.

### 13 - Mapping a bidirectional one-to-one relationship

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/13/jpa-hands-on

<strong> Introduction </strong>

- This example illustrates the use of <strong>BIDIRECTIONAL ONE-TO-ONE RELATIONSHIP</strong>. In that sense, we create the CredtiCard entiy class and associated in a bidirectional way with the Customer entity class.
- I updated the populateCustomerTable that populates the database with some initial data to include credit card information
- I updated the <property name="eclipselink.ddl-generation" value="create-tables"/> on persistence.xml to allow running both CustomerTest and CabinTest classes, without droping the database tables after execution of one of them.
   
<pre>
@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    ...
    
    <strong>@OneToOne(cascade = {CascadeType.ALL})</strong>
    <strong>@JoinColumn(name="CREDIT_CARD_ID")</strong>
    private CreditCard creditCard;
    
    ...
}
</pre>

<pre>
@Entity
public class CreditCard implements Serializable {
    private int id;
    ...
    private Customer customer;
    
    <strong>@OneToOne(mappedBy = "creditCard")</strong>
    public Customer getCustomer() {
        return customer;
    }
</pre>

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_13.png" widht=650 height=245 alt="UML class diagram"></a></p>


### 14 - Mapping a unidirectional one-to-many relationship

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/14/jpa-hands-on

<strong> Introduction </strong>

-  This example illustrates the use of <strong>UNIDIRECTIONAL ONE-TO-MANY RELATIONSHIP</strong> between Customer and Phone. 
   
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_14.png" widht=650 height=245 alt="UML class diagram"></a></p>

- In that sense, we create a Phone entity class and associtated in a unidirectional way with the Customer entity class.
 
<pre>
@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    ...
    
    <strong>@OneToMany(cascade = CascadeType.ALL)</strong>
    <strong>@JoinColumn(name="CustomerID ")</strong>
    private Collection<Phone> phoneNumbers = new ArrayList<>();
    
    ...
}
</pre>

- I updated the CustomerTest (methods createCustomer and updateCustomer) to read phone information from the keyboard.
- I updated the method populateCustomerTable in the class Database utility to persist some phone numbers associated with customers.
- I updated the <property name="eclipselink.ddl-generation" value="drop-and-create-tables"/> on persistence.xml to allow dropping database tables every time i Run the classes CustomerTest and CabinTest.
 

### 15 - Mapping a unidirectional one-to-many relationship with a join table

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/15/jpa-hands-on

<strong> Introduction </strong>

-  Some developers prefer to use an association table when mapping an <strong>UNIDIRECTIONAL ONE-TO-MANY</strong> relationship, such as Customer and Phone (See previous example in this repository).
- In this scenario, the association table has two columns with foreign keys pointing to both CUSTOMER and PHONE records.
- To have this type of mapping, we need to change from a @JoinColumn annotation (see previous example) in our Customer bean class to a @JoinTable annotation.  

<pre>
@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
    ...
    
    <strong>@OneToMany(cascade = CascadeType.ALL)</strong>
    <strong>@JoinTable(name="CUSTOMER_PHONE",
                       joinColumns = { @JoinColumn(name="CUSTOMER_ID") },
                       inverseJoinColumns = {@JoinColumn(name="PHONE_ID")}) </strong>
    // @JoinColumn(name="CustomerID ")
    private Collection<Phone> phoneNumbers = new ArrayList<>();
    
    ...
}
</pre>

- The joincolumns( ) attibute should define a foreign key mapping to the primary key of the owning side of the relationship
- The inverseJoinColumns ( ) attribute maps the nonowning side.
- With this definition, the primary key for Customer maps to CUSTOMER_ID join column in the CUSTOMER_PHONE table. The primary key of the Phone entity maps to the PHONE_ID join column in the CUSTOMER_PHONE table.
- Because the relationship between customers and phones is one-to-many, a unique constraint will be put on the PHONE_ID column of the CUSTOMER_PHONE table by the persistence provider if it supports and if you have activated DDL generation.
- As per the definition of the relationship, one customer has many phones, but a phone has only one customer. The unique constraint enforces this.

### 16 - Mapping a unidirectional many-to-one relationship

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/16/jpa-hands-on

<strong> Introduction </strong>

- This example illustrates the use of <strong>UNIDIRECTIONAL MANY-TO-ONE RELATIONSHIP</strong> between Cruise and Ship. 
   
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_16.png" width=559 height=252 alt="UML class diagram"></a></p>

- In that sense, we create a Cruise entity class and associtated in a unidirectional way with the Ship entity class.
 
<pre>
@Entity
public class Cruise implements Serializable {
    ...
    
   <strong>@ManyToOne</strong>
   <strong>@JoinColumn(name="SHIP_ID")</strong>
    public Ship getShip() {
        return ship;
    }
    
    ...
}
</pre>

- The example also includes support to populate the database tables SHIP and CRUISE at startup
- The example includes the classes CruiseTest and CabinTest (along with the controllers, DAOs and Exceptions for Cruise and Ship) in order to  read ship and cruise information from the keyboard.
 
### 17 - Refactoring the code to eliminate code duplicate and generalize some methods

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/17/jpa-hands-on

<strong> Introduction </strong>

-  This example DOES NOT illustrate any new JPA concept. Instead, it is a refactored version of the previous example.
- We decided to refactor the code to eliminate duplicated code in classes CabinTest, CruiseTest, CustomerTest, ShipTest
- We also create a populateDatabase method on DatabaseUtility class to populate ALL database tables with fake data, instead of populating individual tables by running CabinTest, CruiseTest, CustomerTest, ShipTest.
- The example also refactors the DAOs classes hierarchy attempting to generalize some methods, in special persist and update methods.
   
### 18 - Mapping a bidirectional one-to-many relationship

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/18/jpa-hands-on

<strong> Introduction </strong>

- This example illustrates the use of <strong>BIDIRECTIONAL ONE-TO-MANY RELATIONSHIP</strong> between Cruise and Reservation. 
   
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_18.png" width=567 height=252 alt="UML class diagram"></a></p>

- In that sense, we create a Reservation entity class and associtated in a bidirectional way with the Ship entity class.
 
<pre>
@Entity
public class Cruise implements Serializable {
    ...
    
    <strong>@OneToMany(mappedBy = "cruise")</strong>
    public Collection<Reservation> getReservations() {
        return reservations;
    }
    
    ...
}

@Entity
public class Reservation implements Serializable {
    ...
    
    <strong>@ManyToOne
    @JoinColumn(name="CRUISE_ID")</strong>
    public Cruise getCruise() {
        return cruise;
    }
    
    ...
}
</pre>

- Remember to <strong>ALWAYS</strong> wire both sides of a bidirectional relationship in your Java code.
- If a customer wants to book a different cruise, he needs to delete the old reservation and create a new one. So, instead of setting Reservation.setCruise( ) to null, application code would just remove Reservation:
   
<pre>entityManager.remove(reservation);</pre>
- Since the Reservation entity is the owning side of the relationship, the Cruise's reservation property is updated with the removal the next time it is loaded from the database.
   
### 19 - Mapping a bidirectional many-to-many relationship

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/19/jpa-hands-on

<strong> Introduction </strong>

- This example illustrates the use of <strong>BIDIRECTIONAL MANY-TO-MANY RELATIONSHIP</strong> between Customer and Reservation. 
   
 <p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_19.png" width=567 height=252 alt="UML class diagram"></a></p>

- In Titan cruises, each Reservation entity may reference many Customers (a family can make a single reservation), and each Customer can have many Reservations.
- In this many-to-many bidirectional relationship, the Customer keeps track of all of its reservations, and each reservation may be for many customers.
- Many-to-many bidirectional relationships ALWAYS require an association table in a normalized relational database.

<pre>
@Entity
public class Customer implements Serializable {
    ...
    <strong>private Collection<Reservation> reservations = new ArrayList<>();</strong>

    <strong>@ManyToMany(mappedBy = "customers")</strong>
    public Collection<Reservation> getReservations() {
        return reservations;
    }
    
    ...
}
</pre>

<pre>
@Entity
public class Reservation implements Serializable {
    ...
    <strong>private Set<Customer> customers = new HashSet<>();</strong>

    <strong>@ManyToMany
    @JoinTable(name="RESERVATION_CUSTOMER",
                joinColumns={@JoinColumn(name="RESERVATION_ID")},
                inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID")})</strong>
    public Set<Customer> getCustomers() {
        return customers;
    }
    
    ...
}
</pre>

- The @JoinTable is optional. It can be used to tailor the name of the join table and its columns.
- As with ALL bidirectional relationships, there has to be an owning side. In this example, it is the Reservation entity.
- Since the Reservation owns the relationship, its bean defines the @JoinTable mapping.
- The mappedBy attribute on class Customer identifies the property on the Reservation bean class that defines the relationship. This also identifies the Customer entity as the inverse side of the relationship.
- The Customer/Reservation relationship is something our Titan Cruises application may want to modify after it is established:

<pre>
Reservation reservation = em.find(Reservation.class, id);
reservation.getCustomers().remove(customer);
</pre>

- Since Reservation is the owning side of the relationship, you must remove the Customer from the Reservation's customer property.
- If you instead removed the Reservation from the Customer's reservation property, there would be no database update because the Customer entity is the inverse side of the relationship.

### 20 - Mapping a unidirectional many-to-many relationship

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/20/jpa-hands-on

<strong> Introduction </strong>

- This example illustrates the use of <strong>UNIDIRECTIONAL MANY-TO-MANY RELATIONSHIP</strong> between Reservation and Cabin. 
   
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_20.png" width=675 height=210 alt="UML class diagram"></a></p>

- In Titan's reservation system, every Reservation is assigned a Cabin on the Ship. 
- This allows the Customer to reserve a specific Cabin on the Ship.
- Each reservation may be for more than one cabin, since each Reservation can be for more than one Customer.
   - For example, a family might make a Reservation for five people for two adjacents Cabins.

### 21 - Mapping inheritance - the strategy single-table

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/21/jpa-hands-on

<strong> Introduction </strong>

- This example illustrates how to use the strategy Single-Table to map the concept of inheritance of object orientation to a relational model.
 
<pre>
@Entity
@Table(name="PERSON_HIERARCHY")
<strong>@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Person") </strong> // Optional 
public class Person {
    private int id;
    private String firstName;
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    ...
    ...
} 

@Entity
<strong>@DiscriminatorValue("Customer")</strong>
public class Customer extends Person {
   ...
   ...
}

@Entity
public class Employee extends Customer {
  ...
}

</pre>
 
 - The following image illustrates the inheritance hierachy used in the example.
   <p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Inheritance.png" width=217 height=831 alt="UML class diagram"></a></p>
   
- The following image illustrates the corresponding relational model for the inheritance hierachy used in the example.
   <p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Relational_Model_Single_Table.png" width=975 height=137 alt="UML class diagram"></a></p>
   
<strong>Advantages of single table mapping:</strong>
   - Simplest to implement and performs better than all the inheritance strategies.
   - There is only one table to administer and deal with.
   - The persistence engine does not have to do any complex joins, unions, or subselects when loading the entity or when traversing a polymorphic relationhsip, because all data is stored in one table.
   
<strong>Disadvantages of single table mapping:</strong>
- One huge disadvantage is that all columns of subclass properties must be nullable.
- If you need or want to have NOT-NULL constraints defined on these columns, you cannot.
- Also, because subclass property columns may be unused, the single table strategy is not normalized.

### 22 - Mapping inheritance - the strategy table per class

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/22/jpa-hands-on

<strong> Introduction </strong>

- This example illustrates how to use the strategy table-per-class to map the concept of inheritance of object orientation to a relational model.
 
<pre>
@Entity
<strong>@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)</strong>
public class Person {
   ...
   ...
}

@Entity
public class Customer extends Person {
 ...
 ...
}

@Entity
public class Employee extends Customer{
  ...
  ...
}
</pre>

- The following 3 images illustrate the corresponding relational model for the strategy one-table-per class when mapping the inheritance hierachy used in the example for the classes <strong>Person</strong>, <strong>Customer</strong>, and <strong>Employee</strong>, respectively.

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Table_Per_Class_Person.png" width=264 height=57 alt="UML class diagram"></p>

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Table_Per_Class_Customer.png" width=657 height=52 alt="UML class diagram"></p>

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Table_Per_Class_Employee.png" width=763 height=52 alt="UML class diagram"></p>

<strong>Advantages of table per class mapping:</strong>
- The advantage of this approach over the SINGLE_TABLE strategy is that you can define constraints on subclass properties
- Another advantage is that it might be easier to map a legacy, preexisting schema using this strategy.
  
<strong>Disadvantages of table per class mapping:</strong>
- The strategy is not normalized and it has redundant columns in each of its tables for each of the base class's properties
- Not as fast as the SINGLE_TABLE strategy
- It is probably not wise to pick this strategy when developing your entity beans, unless you absolutely have to.


### 23 - Mapping inheritance - the strategy table per subclass

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/23/jpa-hands-on

<strong> Introduction </strong>

-  This example illustrates how to use the strategy <strong>TABLE_PER_SUBCLASS </strong> to map the concept of inheritance (object oriented model) to a relational model.
 
<pre>
@Entity
<strong>@Inheritance(strategy = InheritanceType.JOINED)</strong>
public class Person {
   ...
   ...
}

@Entity
public class Customer extends Person {
 ...
 ...
}

@Entity
<strong>@PrimaryKeyJoinColumn(name="EMPLOYEE_PK")</strong>
public class Employee extends Customer{
  ...
  ...
}
</pre>

- The following 3 images illustrate the corresponding relational model for the strategy TABLE_PER_SUBCLASS when mapping the inheritance hierachy used in the example for the classes <strong>Person</strong>, <strong>Customer</strong>, and <strong>Employee</strong>, respectively.

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Table_Per_Subclass_Person.png" width=349 height=128 alt="UML class diagram"></p>

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Table_Per_Subclass_Customer.png" width=464 height=98 alt="UML class diagram"></p>

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Table_Per_Subclass_Employee.png" width=251 height=56 alt="UML class diagram"></p>

<strong>Advantages of TABLE_PER_SUBCLASS mapping:</strong>
- Although not as fast as the SINGLE_TABLE strategy, you are able to define NOT NULL constraints on any column of any table.
- The model is normalized.
- This mapping is better than TABLE_PER_CLASS strategy for two reasons:
   - The model is normalized.
   - It performs better if SQL UNIONS are not supported by the subjacent database managemen system.
  
<strong>Disadvantages of TABLE_PER_SUBCLASS mapping:</strong>
- It does not perform so well as the SINGLE_TABLE strategy.

### 24 - Inheritance from a non-entity class with @MappedSuperclass

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/24/jpa-hands-on

<strong> Introduction </strong>

- This example illustrates how to inherit from a non entity class (a class not annotated with @Entity) by the use of the @MappedSuperclass annotation.
- This superclass may be an existing class in your domain model that you do not want to make an entity.
 
<pre>
<strong>@MappedSuperclass</strong>
public class Person {
   ...
   ...
}

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name="lastName", column = @Column(name="SURNAME")) 
public class Customer extends Person {
 ...
 ...
}

@Entity
@PrimaryKeyJoinColumn(name="EMPLOYEE_PK")
public class Employee extends Customer{
  ...
  ...
}
</pre>

### 25 - Introduction to the Query interface

<a href="https://github.com/pagliares/jpa-hands-on#outline">Back to Outline</a></br>
<strong>Project source</strong>: Source_Code/25/jpa-hands-on

<strong>Introduction</strong>

- This is the first of a sequence of examples about queries in JPA and JPQL.
- Queries in JPA are done using both JPA Query Language and native Structured Query Language (SQL)
- JPQL is a declarative query language similar to SQL, but it is tailored to work with Java objects rather than a relational schema.
- When an JPQL language is executed, the entity manager translates it to one (or several) SQL query.
   - The generated SQL query is then executed through a JDBC driver directly in the database.
- JPQL is portable across vendor database implementations.
- JPQL and native SQL queries are executed through jakarta.persistence.Quey interface
- The Query interface is analogous to java.sql.PreparedStatement interface of JDBC.
- In this example present an initial example with the <strong>Query Interface</strong>. 

<pre>
public class <strong>QueryAPITest</strong> {
    ...
    public static void main(String[] args) {
        ...
        // Persisting one more customer
        Customer gregor = new Customer();
        gregor.setFirstName("Gregor");
        gregor.setLastName("Samsa");
        gregor.setBirthDate(LocalDate.of(1800, 5, 5));

        customerDAO.persist(gregor);

        // Verifying whether Gregor Samsa was correctly persisted
        try {
            <strong>Query query = entityManager.createQuery(
                    "Select c from Customer c where c.firstName = 'Gregor' and c.lastName = 'Samsa'");
            Customer customer = (Customer)query.getSingleResult();</strong>
            System.out.println("Customer found! (details below)");
            System.out.println(customer);
        }
        catch(NoResultException noResultException){
            System.out.println("No customer found !");
        }
        ...
    }
}
</pre>

- In the following example, we demonstrate the use of parameters in a Query string.
<pre>
public class CustomerDAO {
   ...
   public List<Customer> findByName(String firstName, String lastName) {
        String queryString = "SELECT c FROM Customer c WHERE c.firstName = <strong>:firstName</strong> AND c.lastName = <strong>:lastName</strong>";
        <strong>Query query = this.getEntityManager().createQuery(queryString);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);</strong>
        return query.getResultList();
   }
}
</pre>

- <strong>Note</strong>: It is also possible to use positional parameters (1, 2, ..) over named parameters (discussed in this example), but this is not recommended.

- The example also demonstrate how to use Query named parameters of type LocalDate. This is done by the implementation of findByCustomerByBirthDate feature:

<pre>
public class CustomerDAO extends DAO {
   ...
   public List<Customer> findCustomerByBirthDate(LocalDate birthDate) throws CustomerNotFoundException {
        String queryString = "SELECT c FROM Customer c WHERE <strong>c.birthDate = :birthDate"</strong>;
        Query query = this.getEntityManager().createQuery(queryString);
        <strong>query.setParameter("birthDate", birthDate);</strong>
        if (query.getResultList().size() == 0)
                throw new CustomerNotFoundException("No customer born on " + birthDate);
        return <strong>query.getResultList()</strong>;
    }
   ... 
}
</pre>


- The example also demonstrate how to page the results by limiting the number of result of a Query:

<pre>
public class CustomerDAO extends DAO {
   ...
  public List<Customer> getCustomers(int max, int index) {
        Query query = this.getEntityManager().createQuery("SELECT c FROM Customer c");
        <strong>query.setMaxResults(max);
        query.setFirstResult(index);</strong>
        return query.getResultList();
    }
   ... 
}
</pre>


- The example also demonstrate a strategy that aims to improve performance of queries that returns, for instance, milions of objects.  

<pre>
public class <strong>CustomerPaginationTest</strong> {
   ...
 System.out.println("""
                            
                            List all customers, in small steps aiming performance improvement
        """);
        List results;
        int first = 0;
        <strong>int max =3;</strong>
        do {
            results = <strong>customerDAO.getCustomers(max, first)</strong>;
            Iterator<Customer> it = results.iterator();
            while(it.hasNext()){
                System.out.println(it.next());
            }
            <strong>customerDAO.getEntityManager().clear();</strong>
            System.out.println("---------------------");
            first = first + results.size();
        } while (results.size() > 0);
   ... 
}
</pre>
