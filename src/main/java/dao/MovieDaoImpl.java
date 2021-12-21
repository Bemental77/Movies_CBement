package dao;

import model.Movie;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utility.DBUtility;
import utility.WorkbookUtility;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao{

    final static String DROP_TABLE = "drop table if exists movie;";
    final static String CREATE_TABLE = "create table movie (id integer primary key autoincrement, title text, director text, lengthInMinutes integer, IMDB text, thumbnail text);";
    final static String SELECT_ALL_FROM_MOVIE = "select * from movie;";

    @Override
    public void populate(String filePath) throws MovieDaoException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBUtility.createConnection();
            statement = connection.createStatement();

            statement.setQueryTimeout(DBUtility.TIMEOUT);

            statement.executeUpdate(DROP_TABLE);
            statement.executeUpdate(CREATE_TABLE);

            //populate the database from WorkbookUtility
            final File inputFile = new File(filePath);
            final List<Movie> movies = WorkbookUtility.retrieveMovieFromWorkbook(inputFile);

            for(final Movie movie : movies){
                final String insertValues = "insert into movie (title, director, lengthInMinutes, IMDB, thumbnail)" +
                        "values ('" + movie.getTitle() + "','" + movie.getDirector() + "'," + movie.getLengthInMinutes() + ", '"
                        + movie.getIMDB() + "', '"
                        + movie.getThumbnail() + "');";

                System.out.println(insertValues); //log the sql that we execute

                statement.executeUpdate(insertValues);
            }

        } catch (ClassNotFoundException | SQLException | IOException | InvalidFormatException e) {
            e.printStackTrace();
            throw new MovieDaoException("Error: Unable to populate the database.");
        }
    }

    @Override
    public List<Movie> retrieveMovies() throws MovieDaoException {
        //create the list of movies
        final List<Movie> movies = new ArrayList<Movie>();

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBUtility.createConnection();
            statement = connection.createStatement();

            statement.setQueryTimeout(DBUtility.TIMEOUT);

            //fetch all from the movie table
            final ResultSet results = statement.executeQuery(SELECT_ALL_FROM_MOVIE);

            //loop through the result
            while (results.next()){

                final String title = results.getString("title");
                final String director = results.getString("director");
                final int lengthInMinutes = results.getInt("lengthInMinutes");
                final String IMDB = results.getString("IMDB");
                final String thumbnail = results.getString("thumbnail");

                movies.add(new Movie(title, director, lengthInMinutes, IMDB, thumbnail));

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new MovieDaoException("Error: unable to retrieve list of people");
        }

        return movies;
    }

    @Override
    public void insertMovie(Movie movie) throws MovieDaoException {

        //set up database connection and statement
        Connection connection = null;
        PreparedStatement insertStatement = null;

        try {
            connection = DBUtility.createConnection();

            final String sqlString = "insert into movie (title, director, lengthInMinutes, IMDB, thumbnail) values (?, ?, ?, ?, ?)";

            insertStatement = connection.prepareStatement(sqlString);

            insertStatement.setString(1, movie.getTitle());
            insertStatement.setString(2, movie.getDirector());
            insertStatement.setInt(3, movie.getLengthInMinutes());
            insertStatement.setString(4, movie.getIMDB());
            insertStatement.setString(5, movie.getThumbnail());

            insertStatement.setQueryTimeout(DBUtility.TIMEOUT);

            insertStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new MovieDaoException("Error: unable to insert movie.");
        }
    }
}
