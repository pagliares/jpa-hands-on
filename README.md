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


### The database hostelapp_jdbc used in this course:

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

01/jpa-hands-on  
   - This is a minimum JPA example in Java SE that communicates with a MySQL database.
   - It creates a GUEST table in the jpa_hands_on database.
   - It populates the GUEST table with one guest (Florentino Ariza)
