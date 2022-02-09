package cinema;

import java.util.UUID;

public class Ticket {


    private UUID token;
    private Seat ticket;

    public Ticket(Seat seat) {
        this.ticket = seat;
        this.token = UUID.randomUUID();
    }

    public Ticket() {
    }

    public UUID getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat seat) {
        this.ticket = seat;
    }
}
