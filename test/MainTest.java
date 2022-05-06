import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main main;
    @BeforeEach
    void setUp() {
    }

    @Test
    void handle() {
        List<Main.Book> bookList = main.parse("[('book2','123','10'),('book3','452','53'),('book4','53','52')]");
        int balance = 100500;
        String input = "buy book 'book3' 123";
        main.handle(input, bookList, balance);
    }
}