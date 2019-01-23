# JPA 2.1 Within Java EE 7
  
In this project i want show, how Java Persistence API 2.1 is integrated with Java EE 7. Persistence becomes easier and more transparent for developers because the Java EE container automates most of the persistent and transactional tasks.

## Code 

The `src/main/` directory contains the main source code while you will find the test class under `src/test/`. The `pom.xml` file is Spring specific describes the project and its dependencies.

* Starts the MySQL database listening on port 8080
* Connect database files (main_project_city.sql, main_project_country.sql, main_project_sight.sql, main_project_university, main_project_user.sql)
* Run project

## Demo 

The structure of the application is:

* Under the `src/main/java` directory:
* The `model` package contains all the entities of our business model (`City`, `Country`...)
* The `dao` package contains the JpaRepositories
* The `controllers` package contains Controllers
* The `configs` package contains Configuration classes
* The `services` package contains Service classes

* Under the `src/main/resources` directory you will find the `aplication.properties` file is Spring specific describes database conection and different properties
* The `static` package contains images, CSS styles and JS files
* The `templates` package you will find the Web resources (Html)
