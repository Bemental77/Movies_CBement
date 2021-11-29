package controller;

import comparator.TitleComparator;
import model.Movie;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utility.WorkbookUtility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "ViewAllServlet", urlPatterns = "/ViewAll")
public class ViewAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String target = "/view-all.jsp";
        //todo get access to our spreadsheet
        final String filePath = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);
        final File inputFile = new File(filePath);

        //todo fetch the information and use it to populate the model
        try {
            final List<Movie> movies = WorkbookUtility.retrieveMovieFromWorkbook(inputFile);

            String sortType = request.getParameter("sortType");

            if(null != sortType && sortType.equals("title")){
                Collections.sort(movies, new TitleComparator());
            }

            //todo attach the model to the request
            request.setAttribute("movies", movies);

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        //todo forward the request to the view.
        getServletContext().getRequestDispatcher(target).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
