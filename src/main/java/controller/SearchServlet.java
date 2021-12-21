package controller;

import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import model.Movie;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utility.WorkbookUtility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SearchServlet", value = "/Search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get access to spreadsheet
       // final String filePath = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);
       // final File inputFile = new File(filePath);
        // fetch list of people
        try {
           // final List<Movie> movies = WorkbookUtility.retrieveMovieFromWorkbook(inputFile);

            final MovieDao movieDao = new MovieDaoImpl();
            final List<Movie> movies = movieDao.retrieveMovies();

            String filterString = request.getParameter("title");

            // filter the list
            final List<Movie> filtered = movies.stream().filter( (Movie m) -> m.getTitle().equalsIgnoreCase(filterString)).collect(Collectors.toList());

            // attach the list(model) to the request
            request.setAttribute("movies", filtered);


        } catch (MovieDaoException e) {
            e.printStackTrace();
        }

        // forward the request(to the view)
        getServletContext().getRequestDispatcher("/view-all.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
