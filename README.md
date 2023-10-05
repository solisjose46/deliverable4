# Group 13 Deliverable 4

## Source code
located in ./src/main/java/ser322

## link to video

**https://youtu.be/BE_GDwwrPhc**

## Assumptions
- You have mysql installed
- You have sql driver jar in lib
- Your sql driver jar path matches line 10 of build.gradle

## Database Setup
All relevant db scripts are located in ./db

load these scripts in sql shell: source example.sql;

1. project_init.sql
    - creates user with username username1 and password123
    - creates database for this project
    - creates tables
2. mock_data.sql
    - fills tables with dummy for this application
3. reset_project.sql
    - deletes database associated with this app
    - removes user (username1)

## Running program (gradle)
- Minimum version gradle **7.x**
- Minimum version **Java 8**
- build and run the program with **gradle --console=plain run**

## Building and running program without gradle
1. compile
**javac -cp ./lib/mysql-connector-java-8.1.0.jar ./src/main/java/ser322/*.java ./src/main/java/ser322/entities/*.java ./src/main/java/ser322/states/*.java**

2. Run the program **java -cp ./src/main/java:./lib/mysql-connector-java-8.1.0.jar ser322.Main**