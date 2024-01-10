package Controllers;

import Models.Book;
import Models.StandardViewResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class StatisticsController {
    public StandardViewResponse<Integer> numberOfBooksDuringPeriod(LocalDate startDate, LocalDate endDate)
    {
        if(startDate == null || endDate == null)
        {
            return new StandardViewResponse<>(0,"Dates cannot be null");
        }
        Date endDateF = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date startDateF = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Integer result = 0;

        if(endDate.isBefore(startDate))
        {
            return new StandardViewResponse<>(0,"End date cannot be before starting date");
        }
        var books = FileController.books;
        for(Book book : books)
        {
            if(book.getPurchasedDate().before(endDateF) && book.getPurchasedDate().after(startDateF))
            {
                result ++;
            }
        }
        return new StandardViewResponse<>(result,null);
    }
}
