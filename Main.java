import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        /// if you have a error in this part, check jdbc driver(.jar file)

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres", "cse3207");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        /// if you have a error in this part, check DB information (db_name, user name, password)

        if (connection != null) {
            System.out.println(connection);
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        // Task 1
        CreateTable(connection);
        InsertInitialData(connection);
        
        // Task 2
        InsertTask2(connection);
        
        // Task 3
        InsertTask3(connection);
        
        // Task 4
        SelectTask4(connection);
        
        // Task 5
        SelectTask5(connection);
        
        // Task 6
        SelectTask6(connection);
        
        // Task 7
        DeleteTask7(connection);
        
        // Task 8
        DeleteTask8(connection);
        
        // Task 9
        DeleteTask9(connection);

        connection.close();
    }

	private static void CreateTable(Connection connection) {
    	try {
	        //Creating the statement
	        Statement stmt = connection.createStatement();
	        String query;
	
	        //Executing the query
	        query = "CREATE TABLE director (\n"
	        		+ "	directorID serial PRIMARY KEY,\n"
	        		+ "	directorName VARCHAR ( 50 ) UNIQUE NOT NULL,\n"
	        		+ "	dateOfBirth DATE NOT NULL,\n"
	        		+ "    dateOfDeath DATE \n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE actor (\n"
	        		+ "	actorID serial PRIMARY KEY,\n"
	        		+ "	actorName VARCHAR ( 50 ) UNIQUE NOT NULL,\n"
	        		+ "	dateOfBirth DATE NOT NULL,\n"
	        		+ "    dateOfDeath DATE,\n"
	        		+ "    gender CHAR(1) NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE movie (\n"
	        		+ "	movieID serial PRIMARY KEY,\n"
	        		+ "	movieName VARCHAR ( 50 ) UNIQUE NOT NULL,\n"
	        		+ "	releaseYear INT NOT NULL,\n"
	        		+ "    releaseMonth INT NOT NULL,\n"
	        		+ "    releaseDate DATE NOT NULL,\n"
	        		+ "    publisherName VARCHAR ( 50 ),\n"
	        		+ "    avgRate DECIMAL DEFAULT 0\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE customer (\n"
	        		+ "	customerID serial PRIMARY KEY,\n"
	        		+ "	customerName VARCHAR ( 50 ) UNIQUE NOT NULL,\n"
	        		+ "    dateOfBirth DATE NOT NULL,\n"
	        		+ "    gender CHAR(1) NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE award (\n"
	        		+ "	awardID serial PRIMARY KEY,\n"
	        		+ "	awardName VARCHAR ( 50 ) UNIQUE NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE genre (\n"
	        		+ "	genreName VARCHAR ( 50 ) UNIQUE NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE movieGenre (\n"
	        		+ "	movieID INT NOT NULL,\n"
	        		+ "	genreName VARCHAR ( 50 ) NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE movieObtain (\n"
	        		+ "	movieID INT NOT NULL,\n"
	        		+ "    awardID INT NOT NULL,\n"
	        		+ "    year INT NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE actorObtain (\n"
	        		+ "	actorID INT NOT NULL,\n"
	        		+ "    awardID INT NOT NULL,\n"
	        		+ "    year INT NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE directorObtain (\n"
	        		+ "	directorID INT NOT NULL,\n"
	        		+ "    awardID INT NOT NULL,\n"
	        		+ "    year INT NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE casting (\n"
	        		+ "	movieID INT NOT NULL,\n"
	        		+ "    actorID INT NOT NULL,\n"
	        		+ "    role VARCHAR ( 50 ) NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE make (\n"
	        		+ "	movieID INT NOT NULL,\n"
	        		+ "    directorID INT NOT NULL\n"
	        		+ ");";
	        stmt.execute(query);
	        
	        query = "CREATE TABLE customerRate (\n"
	        		+ "	customerID INT NOT NULL,\n"
	        		+ "    movieID INT NOT NULL,\n"
	        		+ "    rate DECIMAL DEFAULT 0\n"
	        		+ ");";
	        stmt.execute(query);
    	}
    	catch (SQLException e) {
            System.out.println("Table creation failed");
            e.printStackTrace();
            return;
        }
    	
    	System.out.println("Table created!");
    }
	
    private static void InsertInitialData(Connection connection) {
    	try {
    		//Creating the statement
	        Statement stmt = connection.createStatement();
	        String query;
	
	        //Executing the query
	        query = "INSERT INTO director (directorName, dateOfBirth, dateOfDeath)\n"
	        		+ "VALUES ('Tim Burton', '1958-08-25', NULL);";
	        stmt.execute(query);
	        
	        query = "INSERT INTO director (directorName, dateOfBirth, dateOfDeath)\n"
	        		+ "VALUES ('David Fincher', '1962-08-28', NULL);";
	        stmt.execute(query);
	        
	        query = "INSERT INTO director (directorName, dateOfBirth, dateOfDeath)\n"
	        		+ "VALUES ('Christopher Nolan', '1970-07-30', NULL);";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Johnny Depp', '1963-06-09', NULL, 'M');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Winona Ryder', '1971-10-29', NULL, 'F');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Mia Wasikowska', '1989-10-14', NULL, 'F');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Christian Bale', '1974-01-30', NULL, 'M');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Heath Ledger', '1974-04-04', '2008-01-22', 'M');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Jesse Eisenberg', '1983-10-05', NULL, 'M');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Justin Timberlake', '1981-01-31', NULL, 'M');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Fionn Whitehead', '1997-07-18', NULL, 'M');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO actor (actorName, dateOfBirth, dateOfDeath, gender)\n"
	        		+ "VALUES ('Tom Hardy', '1977-09-15', NULL, 'M');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movie (movieName, releaseYear, releaseMonth, releaseDate, publisherName)\n"
	        		+ "VALUES ('Edward Scissorhands', 1991, 06, '1991-06-29', '20th Century Fox Presents');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movie (movieName, releaseYear, releaseMonth, releaseDate, publisherName)\n"
	        		+ "VALUES ('Alice In Wonderland', 2010, 03, '2010-03-04', 'Korea Sony Pictures');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movie (movieName, releaseYear, releaseMonth, releaseDate, publisherName)\n"
	        		+ "VALUES ('The Social Network', 2010, 11, '2010-11-18', 'Korea Sony Pictures');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movie (movieName, releaseYear, releaseMonth, releaseDate, publisherName)\n"
	        		+ "VALUES ('The Dark Knight', 2008, 08, '2008-08-06', 'Warner Brothers Korea');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movie (movieName, releaseYear, releaseMonth, releaseDate, publisherName)\n"
	        		+ "VALUES ('Dunkirk', 2017, 07, '2017-07-13', 'Warner Brothers Korea');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO customer (customerName, dateOfBirth, gender)\n"
	        		+ "VALUES ('Ethan', '1997-11-14', 'M');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO customer (customerName, dateOfBirth, gender)\n"
	        		+ "VALUES ('John', '1978-01-23', 'M');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO customer (customerName, dateOfBirth, gender)\n"
	        		+ "VALUES ('Hayden', '1980-05-04', 'F');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO customer (customerName, dateOfBirth, gender)\n"
	        		+ "VALUES ('Jill', '1981-04-17', 'F');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO customer (customerName, dateOfBirth, gender)\n"
	        		+ "VALUES ('Bell', '1990-05-14', 'F');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('Fantasy');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('Romance');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('Adventure');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('Family');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('Drama');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('Action');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('Mystery');";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('Thriller');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO genre (genreName)\n"
	        		+ "VALUES ('War');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Edward Scissorhands'), 'Fantasy');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Edward Scissorhands'), 'Romance');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Alice In Wonderland'), 'Fantasy');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Alice In Wonderland'), 'Adventure');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Alice In Wonderland'), 'Family');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Social Network'), 'Drama');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Dark Knight'), 'Action');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Dark Knight'), 'Drama');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Dark Knight'), 'Mystery');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Dark Knight'), 'Thriller');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Dunkirk'), 'Action');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Dunkirk'), 'Drama');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Dunkirk'), 'Thriller');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO movieGenre (movieID, genreName)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Dunkirk'), 'War');\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Edward Scissorhands'), (select actorID from actor where actorName = 'Johnny Depp'), 'Main actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Edward Scissorhands'), (select actorID from actor where actorName = 'Winona Ryder'), 'Main actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Alice In Wonderland'), (select actorID from actor where actorName = 'Johnny Depp'), 'Main actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Alice In Wonderland'), (select actorID from actor where actorName = 'Mia Wasikowska'), 'Main actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Social Network'), (select actorID from actor where actorName = 'Jesse Eisenberg'), 'Main actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Social Network'), (select actorID from actor where actorName = 'Justin Timberlake'), 'Supporting actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Dark Knight'), (select actorID from actor where actorName = 'Christian Bale'), 'Main actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Dark Knight'), (select actorID from actor where actorName = 'Heath Ledger'), 'Main actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Dunkirk'), (select actorID from actor where actorName = 'Fionn Whitehead'), 'Main actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO casting (movieID, actorID, role)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Dunkirk'), (select actorID from actor where actorName = 'Tom Hardy'), 'Supporting actor')\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO make (movieID, directorID)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Edward Scissorhands'), (select directorID from director where directorName = 'Tim Burton'));\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO make (movieID, directorID)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Alice In Wonderland'), (select directorID from director where directorName = 'Tim Burton'));\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO make (movieID, directorID)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Social Network'), (select directorID from director where directorName = 'David Fincher'));\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO make (movieID, directorID)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'The Dark Knight'), (select directorID from director where directorName = 'Christopher Nolan'));\n"
	        		+ "";
	        stmt.execute(query);
	        
	        query = "INSERT INTO make (movieID, directorID)\n"
	        		+ "VALUES ((select movieID from movie where movieName = 'Dunkirk'), (select directorID from director where directorName = 'Christopher Nolan'));\n"
	        		+ "";
	        stmt.execute(query);
    	}
    	catch (SQLException e) {
            System.out.println("Initial data insertion failed");
            e.printStackTrace();
            return;
        }
    	
    	System.out.println("Initial data inserted!");
	}

	private static void InsertTask2(Connection connection) {
		try {
    		//Creating the statement
	        Statement stmt = connection.createStatement();
	        String statement;
	        String query;
	        ResultSet rs;
	        
	        String actorID = "", awardID = "", movieID = "", directorID = "";
	        int year = 0;
	
	        //Executing the query
	        //2.1
	        statement = "Winona Ryder won the “Best supporting actor” award in 1994";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT actorID FROM actor WHERE actorName = 'Winona Ryder';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	actorID = rs.getString(1);
	        
	        
	        query = "INSERT INTO award (awardName) VALUES ('Best supporting actor');";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = "SELECT awardID FROM award WHERE awardName = 'Best supporting actor';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	awardID = rs.getString(1);
	        
	        year = 1994;
	        query = String.format(
	        		"INSERT INTO actorObtain (actorID, awardID, year) VALUES (%s, %s, %s);", 
	        		actorID, awardID, year
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("award", connection);
	        PrintTableContent("actorObtain", connection);
	        System.out.println();
	        
	        //2.2
	        statement = "Tom Hardy won the “Best supporting actor” award in 2018";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT actorID FROM actor WHERE actorName = 'Tom Hardy';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	actorID = rs.getString(1);
	        
	        query = "SELECT awardID FROM award WHERE awardName = 'Best supporting actor';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	awardID = rs.getString(1);
	        
	        year = 2018;
	        query = String.format(
	        		"INSERT INTO actorObtain (actorID, awardID, year) VALUES (%s, %s, %s);", 
	        		actorID, awardID, year
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("award", connection);
	        PrintTableContent("actorObtain", connection);
	        System.out.println();
	        
	        //2.3
	        statement = "Heath Ledger won the “Best villain actor” award in 2009";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT actorID FROM actor WHERE actorName = 'Heath Ledger';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	actorID = rs.getString(1);
	        
	        
	        query = "INSERT INTO award (awardName) VALUES ('Best villain actor');";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = "SELECT awardID FROM award WHERE awardName = 'Best villain actor';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	awardID = rs.getString(1);
	        
	        year = 2009;
	        query = String.format(
	        		"INSERT INTO actorObtain (actorID, awardID, year) VALUES (%s, %s, %s);", 
	        		actorID, awardID, year
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("award", connection);
	        PrintTableContent("actorObtain", connection);
	        System.out.println();
	        
	        //2.4
	        statement = "Johnny Depp won the “Best main actor” award in 2011";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT actorID FROM actor WHERE actorName = 'Johnny Depp';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	actorID = rs.getString(1);
	        
	        
	        query = "INSERT INTO award (awardName) VALUES ('Best main actor');";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = "SELECT awardID FROM award WHERE awardName = 'Best main actor';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	awardID = rs.getString(1);
	        
	        year = 2011;
	        query = String.format(
	        		"INSERT INTO actorObtain (actorID, awardID, year) VALUES (%s, %s, %s);", 
	        		actorID, awardID, year
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("award", connection);
	        PrintTableContent("actorObtain", connection);
	        System.out.println();
	        
	        //2.5
	        statement = "Edward Scissorhands won the “Best fantasy movie” award in 1991";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT movieID FROM movie WHERE movieName = 'Edward Scissorhands';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	movieID = rs.getString(1);
	        
	        
	        query = "INSERT INTO award (awardName) VALUES ('Best fantasy movie');";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = "SELECT awardID FROM award WHERE awardName = 'Best fantasy movie';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	awardID = rs.getString(1);
	        
	        year = 1991;
	        query = String.format(
	        		"INSERT INTO movieObtain (movieID, awardID, year) VALUES (%s, %s, %s);", 
	        		movieID, awardID, year
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("award", connection);
	        PrintTableContent("movieObtain", connection);
	        System.out.println();
	        
	        //2.6
	        statement = "Alice In Wonderland won the “Best fantasy movie” award in 2011";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT movieID FROM movie WHERE movieName = 'Alice In Wonderland';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	movieID = rs.getString(1);
	        
	        query = "SELECT awardID FROM award WHERE awardName = 'Best fantasy movie';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	awardID = rs.getString(1);
	        
	        year = 2011;
	        query = String.format(
	        		"INSERT INTO movieObtain (movieID, awardID, year) VALUES (%s, %s, %s);", 
	        		movieID, awardID, year
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("award", connection);
	        PrintTableContent("movieObtain", connection);
	        System.out.println();
	        
	        //2.7
	        statement = "The Dark Knight won the “Best picture” award in 2009";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT movieID FROM movie WHERE movieName = 'The Dark Knight';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	movieID = rs.getString(1);
	        
	        
	        query = "INSERT INTO award (awardName) VALUES ('Best picture');";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = "SELECT awardID FROM award WHERE awardName = 'Best picture';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	awardID = rs.getString(1);
	        
	        year = 2009;
	        query = String.format(
	        		"INSERT INTO movieObtain (movieID, awardID, year) VALUES (%s, %s, %s);", 
	        		movieID, awardID, year
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("award", connection);
	        PrintTableContent("movieObtain", connection);
	        System.out.println();
	        
	        //2.8
	        statement = "Christopher Nolan won the “Best director” award in 2018";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT directorID FROM director WHERE directorName = 'Christopher Nolan';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	directorID = rs.getString(1);
	        
	        
	        query = "INSERT INTO award (awardName) VALUES ('Best director');";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = "SELECT awardID FROM award WHERE awardName = 'Best director';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	awardID = rs.getString(1);
	        
	        year = 2018;
	        query = String.format(
	        		"INSERT INTO directorObtain (directorID, awardID, year) VALUES (%s, %s, %s);", 
	        		directorID, awardID, year
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("award", connection);
	        PrintTableContent("directorObtain", connection);
	        System.out.println();
    	}
    	catch (SQLException e) {
            System.out.println("Data insertion failed");
            e.printStackTrace();
            return;
    	}
	}

	private static void InsertTask3(Connection connection) {
		try {
			//Creating the statement
	        Statement stmt = connection.createStatement();
	        String statement;
	        String query;
	        ResultSet rs;
	        
	        String customerID = "", movieID = "";
	        ArrayList<String> movieIDs;
	        double rate = 0;
	
	        //Executing the query
	        //3.1
	        statement = "Ethan rates 5 to “Dunkirk”";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT customerID FROM customer WHERE customerName = 'Ethan';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	customerID = rs.getString(1);
	        
	        rate = 5;
	        
	        query = "SELECT movieID FROM movie WHERE movieName = 'Dunkirk';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	movieID = rs.getString(1);
	        
	        query = String.format(
	        		"INSERT INTO customerRate (customerID, movieID, rate) VALUES (%s, %s, %s);", 
	        		customerID, movieID, rate
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = String.format(
	        		"UPDATE movie SET avgRate = (select AVG(rate) from customerRate where movieID = %s) WHERE movieID = %s;", 
	        		movieID, movieID
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("customerRate", connection);
	        PrintTableContent("movie", connection);
	        System.out.println();
	        
	        //3.2 
	        statement = "Bell rates 5 to the movies whose director is “Tim Burton”";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT customerID FROM customer WHERE customerName = 'Bell';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	customerID = rs.getString(1);
	        
	        rate = 5;
	        
	        movieIDs = new ArrayList<>();
	        query = "SELECT movieID FROM make WHERE directorID = (SELECT directorID FROM director WHERE directorName = 'Tim Burton');";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        while (rs.next())
	        	movieIDs.add(rs.getString(1));
	        
	        for (String mID : movieIDs) {
	        	query = String.format(
		        		"INSERT INTO customerRate (customerID, movieID, rate) VALUES (%s, %s, %s);", 
		        		customerID, mID, rate
		        		);
		        System.out.println("Translated SQL : " + query);
		        stmt.execute(query);
		        
		        query = String.format(
		        		"UPDATE movie SET avgRate = (select AVG(rate) from customerRate where movieID = %s) WHERE movieID = %s;", 
		        		mID, mID
		        		);
		        System.out.println("Translated SQL : " + query);
		        stmt.execute(query);
	        }
	        
	        PrintTableContent("customerRate", connection);
	        PrintTableContent("movie", connection);
	        System.out.println();
	        
	        //3.3 
	        statement = "Jill rates 4 to the movies whose main actor is female";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT customerID FROM customer WHERE customerName = 'Jill';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	customerID = rs.getString(1);
	        
	        rate = 4;
	        
	        movieIDs = new ArrayList<>();
	        query = "SELECT m.movieID\n"
	        		+ "FROM movie m\n"
	        		+ "LEFT JOIN casting c ON m.movieID = c.movieID\n"
	        		+ "LEFT JOIN actor a ON a.actorID = c.actorID\n"
	        		+ "WHERE c.role = 'Main actor' and a.gender = 'F';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        while (rs.next())
	        	movieIDs.add(rs.getString(1));
	        
	        for (String mID : movieIDs) {
	        	query = String.format(
		        		"INSERT INTO customerRate (customerID, movieID, rate) VALUES (%s, %s, %s);", 
		        		customerID, mID, rate
		        		);
		        System.out.println("Translated SQL : " + query);
		        stmt.execute(query);
		        
		        query = String.format(
		        		"UPDATE movie SET avgRate = (select AVG(rate) from customerRate where movieID = %s) WHERE movieID = %s;", 
		        		mID, mID
		        		);
		        System.out.println("Translated SQL : " + query);
		        stmt.execute(query);
	        }
	        
	        PrintTableContent("customerRate", connection);
	        PrintTableContent("movie", connection);
	        System.out.println();
	        
	        //3.4
	        statement = "Hayden rates 4 to the fantasy movies";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT customerID FROM customer WHERE customerName = 'Hayden';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	customerID = rs.getString(1);
	        
	        rate = 4;
	        
	        movieIDs = new ArrayList<>();
	        query = "SELECT movieID FROM movieGenre WHERE genreName = 'Fantasy';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        while (rs.next())
	        	movieIDs.add(rs.getString(1));
	        
	        for (String mID : movieIDs) {
	        	query = String.format(
		        		"INSERT INTO customerRate (customerID, movieID, rate) VALUES (%s, %s, %s);", 
		        		customerID, mID, rate
		        		);
		        System.out.println("Translated SQL : " + query);
		        stmt.execute(query);
		        
		        query = String.format(
		        		"UPDATE movie SET avgRate = (select AVG(rate) from customerRate where movieID = %s) WHERE movieID = %s;", 
		        		mID, mID
		        		);
		        System.out.println("Translated SQL : " + query);
		        stmt.execute(query);
	        }
	        
	        PrintTableContent("customerRate", connection);
	        PrintTableContent("movieGenre", connection);
	        System.out.println();
	        
	        //3.5
	        statement = "John rates 5 to the movies whose director won the “Best director” award";
	        System.out.println("Statement : " + statement);
	        
	        query = "SELECT customerID FROM customer WHERE customerName = 'John';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        if (rs.next())
	        	customerID = rs.getString(1);
	        
	        rate = 5;
	        
	        movieIDs = new ArrayList<>();
	        query = "SELECT m.movieID\n"
	        		+ "FROM movie m\n"
	        		+ "LEFT JOIN make mk ON m.movieID = mk.movieID\n"
	        		+ "LEFT JOIN director d ON d.directorID = mk.directorID\n"
	        		+ "LEFT JOIN directorObtain d_o ON d_o.directorID = d.directorID\n"
	        		+ "LEFT JOIN award a ON a.awardID = d_o.awardID\n"
	        		+ "WHERE a.awardName = 'Best director';";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        while (rs.next())
	        	movieIDs.add(rs.getString(1));
	        
	        for (String mID : movieIDs) {
	        	query = String.format(
		        		"INSERT INTO customerRate (customerID, movieID, rate) VALUES (%s, %s, %s);", 
		        		customerID, mID, rate
		        		);
		        System.out.println("Translated SQL : " + query);
		        stmt.execute(query);
		        
		        query = String.format(
		        		"UPDATE movie SET avgRate = (select AVG(rate) from customerRate where movieID = %s) WHERE movieID = %s;", 
		        		mID, mID
		        		);
		        System.out.println("Translated SQL : " + query);
		        stmt.execute(query);
	        }
	        
	        PrintTableContent("customerRate", connection);
	        PrintTableContent("movie", connection);
	        System.out.println();
		}
		catch (SQLException e) {
            System.out.println("Data insertion failed");
            e.printStackTrace();
            return;
    	}
	}
	
	private static void SelectTask4(Connection connection) {
		try {
			//Creating the statement
	        Statement stmt = connection.createStatement();
	        String statement;
	        String query;
	        ResultSet rs;
	        
	        ArrayList<String> movieNames;
	        
	        //Executing the query
	        statement = "Select the names of the movies whose actor are dead";
	        System.out.println("Statement : " + statement);
	        
	        movieNames = new ArrayList<>();
	        query = "SELECT DISTINCT m.movieName\n"
	        		+ "FROM movie m\n"
	        		+ "LEFT JOIN casting c ON c.movieID = m.movieID\n"
	        		+ "LEFT JOIN actor a ON c.actorID = a.actorID\n"
	        		+ "WHERE a.dateOfDeath IS NOT null;";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        while (rs.next())
	        	movieNames.add(rs.getString(1));
	        
	        System.out.println("-----< movie >-----");
	        for (String m : movieNames)
	        	System.out.println(m);
	        
	        System.out.println();
		}
		catch (SQLException e) {
            System.out.println("Data query failed");
            e.printStackTrace();
            return;
    	}
	}
	
	private static void SelectTask5(Connection connection) {
		try {
			//Creating the statement
	        Statement stmt = connection.createStatement();
	        String statement;
	        String query;
	        ResultSet rs;
	        
	        ArrayList<String> directorNames;
	        
	        //Executing the query
	        statement = "Select the names of the directors who cast the same actor more than once";
	        System.out.println("Statement : " + statement);
	        
	        directorNames = new ArrayList<>();
	        query = "SELECT d.directorName, a.actorName, COUNT(a.actorName)\n"
	        		+ "FROM director d\n"
	        		+ "LEFT JOIN make mk on d.directorID = mk.directorID\n"
	        		+ "LEFT JOIN movie m on m.movieID = mk.movieID\n"
	        		+ "LEFT JOIN casting c on c.movieID = m.movieID\n"
	        		+ "LEFT JOIN actor a on c.actorID = a.actorID\n"
	        		+ "GROUP BY d.directorName, a.actorName\n"
	        		+ "HAVING COUNT(a.actorName) > 1;";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        while (rs.next())
	        	directorNames.add(rs.getString(1));
	        
	        System.out.println("-----< director >-----");
	        for (String d : directorNames)
	        	System.out.println(d);
	        
	        System.out.println();
		}
		catch (SQLException e) {
            System.out.println("Data query failed");
            e.printStackTrace();
            return;
    	}
	}

	private static void SelectTask6(Connection connection) {
		try {
			//Creating the statement
	        Statement stmt = connection.createStatement();
	        String statement;
	        String query;
	        ResultSet rs;
	        
	        ArrayList<String> movieNames, genreNames;
	        
	        //Executing the query
	        statement = "Select the names of the movies and the genres, where movies have the common genre";
	        System.out.println("Statement : " + statement);
	        
	        movieNames = new ArrayList<>();
	        genreNames = new ArrayList<>();
	        query = "SELECT m.movieName, mg.genreName\n"
	        		+ "FROM movie m\n"
	        		+ "LEFT JOIN movieGenre mg ON m.movieID = mg.movieID\n"
	        		+ "WHERE mg.genreName IN (\n"
	        		+ "	SELECT mg.genreName\n"
	        		+ "	FROM movieGenre mg\n"
	        		+ "	LEFT JOIN movie m ON m.movieID = mg.movieID\n"
	        		+ "	GROUP BY mg.genreName\n"
	        		+ "	HAVING count(m.movieName) > 1\n"
	        		+ ")\n"
	        		+ "ORDER BY mg.genreName;";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	movieNames.add(rs.getString(1));
	        	genreNames.add(rs.getString(2));
	        }
	        
	        System.out.println("-----< movie, genre >-----");
	        
	        System.out.printf("%-30s %-30s", "movieName", "genreName");
	        System.out.println();
	        
	        for (int i=0; i<movieNames.size(); i++) {
	        	System.out.printf("%-30s %-30s", movieNames.get(i), genreNames.get(i));
	        	System.out.println();
	        }
	        
	        System.out.println();
		}
		catch (SQLException e) {
            System.out.println("Data query failed");
            e.printStackTrace();
            return;
    	}
	}
	


	private static void DeleteTask7(Connection connection) {
		try {
			//Creating the statement
	        Statement stmt = connection.createStatement();
	        String statement;
	        String query;
	        ResultSet rs;
	        
	        ArrayList<String> movieIDs;
	        
	        //Executing the query
	        statement = "Delete the movies whose director or actor did not get any award and delete data from related tables";
	        System.out.println("Statement : " + statement);
	        
	        movieIDs = new ArrayList<>();
	        query = "SELECT DISTINCT m.movieID\n"
	        		+ "FROM movie m\n"
	        		+ "LEFT JOIN make mk ON mk.movieID = m.movieID\n"
	        		+ "LEFT JOIN director d on mk.directorID = d.directorID\n"
	        		+ "LEFT JOIN directorObtain d_o ON d_o.directorID = d.directorID\n"
	        		+ "LEFT JOIN casting c ON c.movieID = m.movieID\n"
	        		+ "LEFT JOIN actor a on c.actorID = a.actorID\n"
	        		+ "LEFT JOIN actorObtain a_o ON a_o.actorID = a.actorID\n"
	        		+ "WHERE d_o.awardID IS null OR a_o.awardID IS null;";
	        System.out.println("Translated SQL : " + query);
	        rs = stmt.executeQuery(query);
	        while (rs.next())
	        	movieIDs.add(rs.getString(1));
	        
	        String movieIDsList = String.join(",", movieIDs);
	        
	        query = String.format(
	        		"DELETE FROM movie WHERE movieID IN (%s);", 
	        		movieIDsList
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = String.format(
	        		"DELETE FROM movieGenre WHERE movieID IN (%s);", 
	        		movieIDsList
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = String.format(
	        		"DELETE FROM movieObtain WHERE movieID IN (%s);", 
	        		movieIDsList
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = String.format(
	        		"DELETE FROM casting WHERE movieID IN (%s);", 
	        		movieIDsList
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = String.format(
	        		"DELETE FROM make WHERE movieID IN (%s);", 
	        		movieIDsList
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = String.format(
	        		"DELETE FROM customerRate WHERE movieID IN (%s);", 
	        		movieIDsList
	        		);
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("movie", connection);
	        PrintTableContent("movieGenre", connection);
	        PrintTableContent("movieObtain", connection);
	        PrintTableContent("casting", connection);
	        PrintTableContent("make", connection);
	        PrintTableContent("customerRate", connection);
		}
		catch (SQLException e) {
            System.out.println("Data deletion failed");
            e.printStackTrace();
            return;
    	}
	}
	
	private static void DeleteTask8(Connection connection) {
		try {
			//Creating the statement
	        Statement stmt = connection.createStatement();
	        String statement;
	        String query;
	        ResultSet rs;
	        
	        //Executing the query
	        statement = "Delete all customers and delete data from related tables";
	        System.out.println("Statement : " + statement);
	        
	        query = "TRUNCATE TABLE customer;";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = "TRUNCATE TABLE customerRate;";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        query = "UPDATE movie SET avgRate = 0;";
	        System.out.println("Translated SQL : " + query);
	        stmt.execute(query);
	        
	        PrintTableContent("customer", connection);
	        PrintTableContent("customerRate", connection);
	        PrintTableContent("movie", connection);
	        System.out.println();
		}
		catch (SQLException e) {
            System.out.println("Data deletion failed");
            e.printStackTrace();
            return;
    	}
	}
	
	private static void DeleteTask9(Connection connection) {
		try {
			//Creating the statement
	        Statement stmt = connection.createStatement();
	        String statement;
	        String query;
	        ResultSet rs;
	        
	        //Executing the query
	        statement = "Delete all tables and data (make the database empty)";
	        System.out.println("Statement : " + statement);
	        
	        query = "DROP SCHEMA public CASCADE;\n"
	        		+ "CREATE SCHEMA public;";
	        System.out.println("Translated SQL : " + query);
	        stmt.executeUpdate(query);
	        
	        System.out.println("Database cleared!");
		}
		catch (SQLException e) {
            System.out.println("Database clearing failed");
            e.printStackTrace();
            return;
    	}
	}
	
	private static void PrintTableContent(String tableName, Connection connection) throws SQLException {
		System.out.println("-----< " + tableName + " >-----");
		
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from " + tableName);

		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		System.out.printf("%-10s", metaData.getColumnLabel(1));
		for(int i = 2; i <= columnCount; i++) {
			System.out.printf("%-30s", metaData.getColumnLabel(i));
	    }
	    System.out.println();
	    
		while(rs.next()) {
			Object object = rs.getObject(1);
	        System.out.printf("%-10s", object == null ? "NULL" : object.toString());
	        
		    for(int columnIndex = 2; columnIndex <= columnCount; columnIndex++) {
		        object = rs.getObject(columnIndex);
		        System.out.printf("%-30s", object == null ? "NULL" : object.toString());
		    }
		    System.out.printf("%n");
		}
		System.out.println();
	}
}
