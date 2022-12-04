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

01 - jpa-hands-on  
   - This is a minimum JPA example in Java SE that communicates with a MySQL database.
   - It creates a CUSTOMER table in the jpa_hands_on database.
   - It populates the CUSTOMER table with one customer (Florentino Ariza)
   - It demonstrates how to use the EclipseLink JPA implementation.

02 - jpa-hands-on  
   - This example builds on the first example by adding a Cabin entity bean
   - The example also creates two controllers: CustomerController and CabinController
   - The controllers encapsulates the logic to persist and find by id customers and cabins.
   - The example demonstrates how to persist and find by id a customer and a cabin stored in the database.

<p align="center"><img src="https://github.com/pagliares/jpa-hands-on/blob/main/Images/Class_Diagram_Example_02.png" widht=820 height=493 alt="UML class diagram"></a></p>

