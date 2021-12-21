package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import model.Movie;
import org.apache.commons.lang3.StringUtils;


@WebServlet(name = "AddNewMovie", urlPatterns = "/AddNewMovie")
public class AddNewMovie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //get the information submitted by the user
        try{
            final String title = request.getParameter("title");
            final String director = request.getParameter("director");
            final String lengthInMinutesString = request.getParameter("lengthInMinutes");
            final String IMDB = request.getParameter("IMDB");
            final String thumbnail = request.getParameter("thumbnail");

            if(StringUtils.isEmpty(title)
                    || StringUtils.isEmpty(director)
                    || StringUtils.isEmpty(lengthInMinutesString)
                    || StringUtils.isEmpty(IMDB)
                    || StringUtils.isEmpty(thumbnail)){

                // user did not submit the necessary information
                request.setAttribute("message", "You must complete all fields to submit the form.");
            }
            else {
                //user submitted all necessary data
                final int lengthInMinutes = Integer.parseInt(lengthInMinutesString);

                final MovieDao movieDao = new MovieDaoImpl();

                //create a movie object using the submitted info
                final Movie movie = new Movie(title, director, lengthInMinutes, IMDB, thumbnail);

                //insert that movie object into the db using movieDao
                movieDao.insertMovie(movie);
                request.setAttribute("message", "The movie was added.");
            }
        } catch (MovieDaoException e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
        }

        getServletContext().getRequestDispatcher("/add-movie.jsp").forward(request,response);
    }
}
