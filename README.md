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

### 06 - jpa-hands-on
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


### 07 - jpa-hands-on
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
   

### 08 - jpa-hands-on
   - This example is almost identical to the previous version. The differences is that instead of using @Temporal(TemporalType.TIMESTAMP) that is associated with java.util. Date  (Pre Java SE8), in this example we use  LocalDateTime and LocalDate (Since Java SE 8).
   - Since LocalDateTime and LocalDate are considered type values (@Basic) in JPA, We don't need to put any annotation to indicate this is a date/time field.anymore.

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

### 09 - jpa-hands-on
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
  
### 10 - jpa-hands-on
   - This example does not include any new feature of JPA. Instead, it refactors the code developed so far in order do extract the persistence logic from the controllers to a DAO (Data Access Object) hierarchy, providing higher cohesion to the controller classes.  

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_10.png" widht=901 height=477 alt="UML class diagram"></a></p>

### 11 - jpa-hands-on
   - This example demonstrates the use of the annotations @Embedded, @AttributeOverrides, @AttibuteOverride e @Embeddable.
   - These annotation may be used when the developer wants to embed java objects inside an entity bean and map the properties of this embedable object to columnds in the database generated for the entity bean.
   - Embedabble objects do not have identy (primary key) and are exclusively owned by the entity bean classes associated with them.
   
<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_11.png" widht=901 height=477 alt="UML class diagram"></a></p>

### 12 - jpa-hands-on
   - This example is an alternative to the previous example. 
   - The example uses the concept of relationship between entities.
   - JPA has 7 types of relationships: UNIDIRECTIONAL ONE-TO-ONE, BIDIRECTIONAL ONE-TO-ONE, UNIDIRECTIONAL ONE-TO-MANY, BIDIRECTIONAL ONE-TO-MANY, UNIDIRECTIONAL MANY-TO-ONE, UNIDIRECTIONAL MANY-TO-MANY, BIDIRECTIONAL MANY-TO-MANY. We are going to study all of them in this hands-on.
   - We recommend you to contrast this solution with the solution presented in the previous example that used @Embedded, @AttributeOverrides, @AttributeOverride, and @Embeddable
   - This example uses a UNIDIRECTIONAL ONE-TO-ONE RELATIONSHIP between Customer and Address (A customer has one address). 
   - This alternative solution generates two tables in the database and associates them  through the foreign key of the table Address presented in the table Customer.
   - If you want, you can customize the name of the column to be created in the table Customer by using the annotation @JoinColumn. For example, @JoinColumn(name="ADDR_ID")
   - The attribute cascade with value CascadeType.ALL indicates to JPA that if we remove a customer, we also want to automatically delete its address associated. In the same way, if we persist a customer, it's address will be persisted automatically as well.
   - CascadeType has other constants, for example, CascadeType.PERSIST. If you use CascadeType.PERSIST you are telling JPA that if you persist a customer, you also want to persist its address, but if you remove a customer, the associate address WILL NOT be deleted from the database.

### 13 - jpa-hands-on
   -  This example illustrates the use of <strong>BIDIRECTIONAL ONE-TO-ONE RELATIONSHIP</strong>. In that sense, we create the CredtiCard entiy class and associtated in a bidirectional way with the Customer entity class.
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


### 14 - jpa-hands-on
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
