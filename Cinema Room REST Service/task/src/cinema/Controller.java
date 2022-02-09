package cinema;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class Controller {
    Cinema cinema = new Cinema(9, 9);
    Stats stats = new Stats(0, cinema.getTotalColumns() * cinema.getTotalRows(),
            0);

    @GetMapping("/seats")
    public Cinema getSeats() {
       return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> postPurchase(@RequestBody CinemaRequest request) {
        if (request.getColumn() > cinema.getTotalColumns() || request.getRow() > cinema.getTotalRows() ||
                request.getColumn() <= 0 || request.getRow() <= 0) {
            return new ResponseEntity<>(Map.of("error", TicketErrors.ROW_COLUMN_ERROR.toString()), HttpStatus.BAD_REQUEST);
        }
        for (Seat element : cinema.getAvailableSeats()) {
            if (Objects.equals(element.getRow(), request.getRow()) &&
                    Objects.equals(element.getColumn(), request.getColumn()) &&
                    element.isAvailable()) {
                element.setAvailable(false);
                Ticket newTicket = new Ticket(element);
                cinema.getTickets().add(newTicket);
                stats.setCurrentIncome(stats.getCurrentIncome() + element.getPrice());
                stats.setNumberOfAvailableSeats(stats.getNumberOfAvailableSeats() - 1);
                stats.setNumberOfPurchasedTickets(stats.getNumberOfPurchasedTickets() + 1);

                return new ResponseEntity<>(newTicket, HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(Map.of("error", TicketErrors.PURCHASED_ERROR.toString()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public Object postReturn(@RequestBody Ticket ticket) {

        for (Ticket element :
                cinema.getTickets()) {
            if (element.getToken().equals(ticket.getToken())) {

                stats.setCurrentIncome(stats.getCurrentIncome() - element.getTicket().getPrice());
                stats.setNumberOfAvailableSeats(stats.getNumberOfAvailableSeats() + 1);
                stats.setNumberOfPurchasedTickets(stats.getNumberOfPurchasedTickets() - 1);
                element.getTicket().setAvailable(true);
                return new ResponseEntity<>(Map.of("returned_ticket", new Seat(element.getTicket().getRow(),
                        element.getTicket().getColumn())), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error", TicketErrors.WRONG_TOKEN.toString()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity postStats(@RequestParam(value = "password", required = false) String password) {
        if ("super_secret".equals(password)) {
            return new ResponseEntity<Stats>(stats, HttpStatus.OK);
        }
        return new ResponseEntity(Map.of("error", TicketErrors.WRONG_PASSWORD.toString()), HttpStatus.UNAUTHORIZED);
    }


}
