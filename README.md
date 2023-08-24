# Project PROG4
This project is an application built on spring boot with Thymeleaf that manages a company's employee information.
It uses two (02) databases simultaneously on postgreSQL to store employee data.
It uses Flyway migration for both databases.
This application is also secured by spring security, and the default user to access it is
username: admin
password: admin

To run this application, you need to : 
1- Clone the source code
2- Download the dependencies
3- Create two databases in postgreSQL
4- Add the connection information to these databases in the environment variables
5- Launch the application and use the default login.
6- To add a new user, modify the user table in the database (note: the table must be encoded in Bcrypt).

Note: the GUI interface still needs improvement.
