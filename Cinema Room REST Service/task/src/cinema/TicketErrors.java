package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public enum TicketErrors {
    
    ROW_COLUMN_ERROR("The number of a row or a column is out of bounds!"),
    PURCHASED_ERROR("The ticket has been already purchased!"),
    WRONG_TOKEN("Wrong token!"),
    WRONG_PASSWORD("The password is wrong!");

    private String error;

    TicketErrors(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return this.error;
    }
}
