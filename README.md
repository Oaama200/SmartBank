**Developers**
Eric Duncan
JJ Accomando
Muath Alasaadi
Shen Tang

**Architecture**

The architecture of the SmartBank App is multi-faceted. The sections below will discuss the database architecture as well as the project architecture used in this application.

**Database:** 

For this architecture, there is a four table design with \`**User**\` having a one to many relationship with both the \`**Account**\` and \`**LoanApplication**\` tables. With this architecture, a user can have multiple accounts and loan applications. Additionally, the \`**Transaction**\` table has a many to one relationship with \`**Account**\` because each account can have multiple transactions.

**Project:**

Maven was used as the project manager for this project. Additionally, it had the following dependencies used for the project.  
Dependencies**:**

* Junit-bom \- Used for testing of the application  
* Apache.log4j-api \- Used for logging in the application  
* Mysql-connector \- JDBC connector for MySQL database connection


The **database connection** was implemented in the / smart\_bank\_app / connection / DBConnection.java. The **SQL queries** to perform CRUD operations were stored in the /smart\_bank\_app/constant/ProjectConstant.java. The classes in the / smart\_bank\_app / dao / jdbc implemented methods such as create, select, update, and delete to perform CRUD operations. The **SmartBankTest.java** tested the CRUD operations and database integration. The classes in the \`commandline\` package implemented the **command line-based User Interface** providing selection choices and calling the CRUD methods when the user was using the Smart Bank Application and typing inputs to the terminal.

**Design Principles**

### **1\.** 	**Single Responsibility: Each class should have a single responsibility (e.g., UserManager for user-related operations, AccountManager for account operations).**

Each class was created to have a single responsibility, such as MySQLAccountManager.java only contained the SQL operations for the Account Management of the Account Table, and the MySQLLoanApplicationManager.java only contained the SQL operations for the LoanApplication Management of the LoanApplication Table. These classes usually did not contain SQL operations for other tables unless manipulating one table would change the values of another table.   
 

### **2\.** 	**Open-Closed: The application should be designed to allow the addition of new features without modifying existing code.**

Many classes in this application allow the addition of new features such as adding more functions or variables without modifying existing code. The ProjectConstant.java allowed adding more SQL queries without modifying existing code. The Manager classes in the /dao/jdbc allowed adding more CRUD operations, the Interfaces in the /dao allowed adding more functions, and the Services class in the /services allowed adding more methods without modifying existing code.  
 

### **3\.** 	**Liskov Substitution: Ensure derived classes can replace their base classes without affecting application functionality.**

The derived classes or interfaces of the IGenericManagement Interface such as IAccountManagement and ILoanApplicationManagement can replace their base class IGenericManagement without affecting application functionality.  
 

### **4\.** 	**Interface Segregation: Create specific interfaces for different functionalities (e.g., IUserManagement, IAccountManagement) to avoid bloated interfaces.**

The Interfaces in the /dao directory were specific interfaces containing functions for different tables, such as IUserManagement contained specific methods to manipulate the User Table, and ITransactionManagement contained specific methods to manipulate the Transaction Table.   
 

### **5\.** 	**Dependency Inversion: Use abstractions for dependencies to facilitate easier testing and maintenance.**

The implementation of CRUD methods in each class of /dao/jdbc was different from each other, they depended upon interfaces rather than upon concrete functions. It is easier to implement methods of the service classes by using these interfaces. 

**SQL queries**

**CREATE TABLE QUERIES**

/src/main/resources/smart\_bank\_app\_db/create\_smart\_bank\_schema\_sql.sql

**CREATE SCHEMA** \`smart\_bank\`;  
USE \`smart\_bank\`;

**CREATE TABLE** \`**User**\` (  
\`user\_id\` **int NOT NULL** AUTO\_INCREMENT,  
\`user\_name\` **varchar**(45) **NOT NULL**,  
\`user\_type\` **varchar**(45) **NOT NULL CHECK**(\`user\_type\` **IN** (**'NEW\_USER'**, **'REWARD\_USER'**, **'PLATINUM\_USER'**, **'VIP'**)),  
\`password\` **varchar**(45) **NOT NULL CHECK**(**LENGTH**(\`password\`) \>= 6),  
\`first\_name\` **varchar**(45) **NOT NULL**,  
\`last\_name\` **varchar**(45) **NOT NULL**,  
\`phone\` **varchar**(45) **NOT NULL**,  
\`email\` **varchar**(45) **NOT NULL**,  
\`credit\_score\` **int NOT NULL DEFAULT** 300,  
\`annual\_income\` **decimal**(10,2) **NOT NULL CHECK**(\`annual\_income\` \>= 0),  
\`loan\_amount\` **decimal**(10,2) **NOT NULL DEFAULT** 0.00,  
\`registration\_date\` **date NOT NULL DEFAULT '2024-10-30'**,  
**PRIMARY KEY** (\`user\_id\`)  
)ENGINE=InnoDB **DEFAULT** CHARSET=utf8mb4 **COLLATE**\=utf8mb4\_0900\_ai\_ci;

**CREATE TABLE** \`Account\` (  
\`account\_id\` **int NOT NULL** AUTO\_INCREMENT,  
\`user\_id\` **int NOT NULL**,  
\`account\_type\` **varchar**(45) **NOT NULL**,  
\`balance\` **decimal**(10,2) **NOT NULL DEFAULT** 0 **CHECK** (\`balance\` \>= 0),  
**PRIMARY KEY**(\`account\_id\`),  
**CONSTRAINT** \`fk\_account\_user\_id\` **FOREIGN KEY** (\`user\_id\`) **REFERENCES** \`**User**\` (\`user\_id\`)  
)ENGINE=InnoDB **DEFAULT** CHARSET=utf8mb4 **COLLATE**\=utf8mb4\_0900\_ai\_ci;

**CREATE TABLE** \`LoanApplication\` (  
\`application\_id\` **int NOT NULL** AUTO\_INCREMENT,  
\`user\_id\` **int NOT NULL**,  
\`loan\_type\` **varchar**(45) **NOT NULL**,  
\`amount\` **decimal**(10,2) **NOT NULL CHECK**(\`amount\` \>= 0),  
\`start\_date\` **date NOT NULL DEFAULT '2024-10-30'**,  
\`end\_date\` **date NOT NULL DEFAULT '2025-12-31'**,  
\`application\_status\` **varchar**(45) **NOT NULL DEFAULT 'pending' CHECK**(\`application\_status\` **IN** (**'pending'**, **'declined'**, **'approved'**)),  
\`application\_date\` **date NOT NULL DEFAULT '2024-10-30'**,  
**PRIMARY KEY**(\`application\_id\`),  
**CONSTRAINT** \`fk\_loan\_application\_user\_id\` **FOREIGN KEY** (\`user\_id\`) **REFERENCES** \`**User**\` (\`user\_id\`)  
)ENGINE=InnoDB **DEFAULT** CHARSET=utf8mb4 **COLLATE**\=utf8mb4\_0900\_ai\_ci;

**CREATE TABLE** \`**Transaction**\` (  
\`transaction\_id\` **int NOT NULL** AUTO\_INCREMENT,  
\`account\_id\` **int NOT NULL**,  
\`transaction\_type\` **varchar**(45) **NOT NULL CHECK**(\`transaction\_type\` **IN** (**'deposit'**, **'withdrawal'**)),  
\`transaction\_amount\` **decimal**(10,2) **NOT NULL DEFAULT** 0 **CHECK**(\`transaction\_amount\` \>= 0),  
\`transaction\_date\` **date NOT NULL DEFAULT '2024-10-30'**,  
\`account\_balance\_before\` **double NOT NULL DEFAULT** 0,  
\`account\_balance\_after\` **double NOT NULL DEFAULT** 0,  
**PRIMARY KEY**(\`transaction\_id\`),  
**CONSTRAINT** \`fk\_transaction\_account\_id\` **FOREIGN KEY** (\`account\_id\`) **REFERENCES** \`Account\` (\`account\_id\`)  
)ENGINE=InnoDB **DEFAULT** CHARSET=utf8mb4 **COLLATE**\=utf8mb4\_0900\_ai\_ci;

**Application queries (can be found in /smart\_bank\_app/constant/ProjectConstant.java class)**  
For example:  
**public static final** String SQL\_FIND\_BY\_USER\_ID \= **"SELECT \* FROM User WHERE user\_id \= ?;"**;  
**public static final** String SQL\_FIND\_BY\_ACCOUNT\_ID \= **"SELECT \* FROM Account WHERE account\_id \= ?;"**;  
**public static final** String SQL\_FIND\_ACCOUNT\_BY\_USER\_ID \= **"SELECT \* FROM Account WHERE user\_id \= ?;"**;  
**public static final** String SQL\_FIND\_TRANSACTIONS\_BY\_ACCOUNT\_ID \= **"SELECT \* FROM Transaction WHERE account\_id \= ?;"**;  

