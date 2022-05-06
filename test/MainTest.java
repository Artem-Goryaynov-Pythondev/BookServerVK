import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    Main main;
    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void buyBookTest() {
        //where
        List<Main.Book> bookList = main.parse("[('book2','5','10'),('book3','452','53'),('book4','53','52')]");
        int balance = 100;
        //do
        String input = "buy book 'book2' 1";
        String respond = main.handle(input, bookList, balance);
        //and check
        assertEquals("balance: 105, Name = book2, Price = 5, Count = 9", respond);
    }
    @Test
    void showAllBooksTest() {
        //where
        List<Main.Book> bookList = main.parse("[('book2','123','10'),('book3','452','53')");
        int balance = 100500;
        //do
        String input = "show books in stock";
        String respond = main.handle(input, bookList, balance);
        //and check
        assertEquals("Name = book2, Price = 123, Count = 10" + "/n" +
                             "Name = book3, Price = 452, Count = 53", respond);
    }
    @Test
    void exitTest() {
        //where
        //do
        String input = "exit";
        //and check
        assertEquals("exit", "???");
    }
}