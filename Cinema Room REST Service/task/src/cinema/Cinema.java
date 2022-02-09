package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Cinema {
    private int totalRows;
    private int totalColumns;
    private ArrayList<Seat> availableSeats;
    @JsonIgnore
    private ArrayList<Ticket> tickets;

    public ArrayList<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(ArrayList<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }



    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Cinema() {
    }

    public void constructAvailableSeats() {
        this.availableSeats = new ArrayList<>();
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                availableSeats.add(new Seat(i + 1, j + 1));
            }
        }
    }
    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.tickets = new ArrayList<>();
        constructAvailableSeats();
    }





}
