package utility;

import model.Movie;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkbookUtility {

    public final static String INPUT_FILE = "assets//MoviesJavaWebProgrammingSheet.xlsx";


    public static List<Movie> retrieveMovieFromWorkbook(final File inputFile) throws IOException, InvalidFormatException {
        final List<Movie> movies = new ArrayList<>();

        final Workbook workbook = WorkbookFactory.create(inputFile);
        final Sheet sheet = workbook.getSheetAt(0);

        for(final Row row : sheet){
            final Cell titleCell = row.getCell(0);
            final Cell directorCell = row.getCell(1);
            final Cell lengthInMinutesCell = row.getCell(2);
            final Cell IMDBCell = row.getCell(3);
            final Cell thumbnail = row.getCell(4);

            final Movie movie = new Movie(titleCell.getStringCellValue().trim(), directorCell.getStringCellValue().trim(),(int) lengthInMinutesCell.getNumericCellValue(), IMDBCell.getStringCellValue().trim(), thumbnail.getStringCellValue().trim());

            movies.add(movie);
        }

        return movies;
    }
}
