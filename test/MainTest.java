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
        String data = "balance: 1000, books: [(\"algebra, 10 class\", 5, 150), (\"algorithm, 4 class\", 70, 100)]";
        List<Main.Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);


        String input = "buy \"algebra, 10 class\" 5, \"Artem\"";
        String respond = main.processing(input, bookList, balance);
        assertEquals( "deal", respond);

        String input2 = "buy \"algorithm, 4 class\" 6, \"vinipux\"";
        String respond2 = main.processing(input2, bookList, balance);
        assertEquals("deal", respond2);

        String input3 = "buy \"algorithm, 4 class\" 7, \"Artem\"";
        String respond3 = main.processing(input3, bookList, balance);
        assertEquals("deal", respond3);

        String input1 = "show bought books";
        String respond1 = main.processing(input1, bookList, balance);
        //and check
        assertEquals("\"algebra, 10 class\", 5 pcs.\n" +
                "\"algorithm, 4 class\", 6 pcs.\n" +
                "\"algorithm, 4 class\", 7 pcs.\n", respond1);

        String input4 = "show bought books by Artem";
        String respond4= main.processing(input4, bookList, balance);
        //and check
        assertEquals("\"algebra, 10 class\", 5 pcs.\n" +
                "\"algorithm, 4 class\", 7 pcs.\n", respond4);

        String input5 = "show bought books by vinipux";
        String respond5= main.processing(input5, bookList, balance);
        //and check
        assertEquals("\"algorithm, 4 class\", 6 pcs.\n", respond5);

    }
    @Test
    void buyNonExistsBookTest() {
        //where
        String data = "balance: 1000, books: [(\"algebra, 10 class\", 5, 150), (\"algorithm, 4 class\", 70, 100)]";
        List<Main.Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);


        String input = "buy \"algebra!!!!!, 11 class\" 5, \"Artem\"";
        String respond = main.processing(input, bookList, balance);
        assertEquals( "no deal", respond);
    }@Test
    void buyNonEnoughBookTest() {
        //where
        String data = "balance: 1000, books: [(\"algebra, 10 class\", 5, 150), (\"algorithm, 4 class\", 70, 100)]";
        List<Main.Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);


        String input = "buy \"algebra, 10 class\" 5314432, \"Artem\"";
        String respond = main.processing(input, bookList, balance);
        assertEquals( "no deal", respond);
    }
    @Test
    void showAllBooksTest() {
        //where
        String data = "balance: 1000, books: [(\"algebra, 10 class\", 5, 150), (\"algorithm, 4 class\", 70, 100)]";
        List<Main.Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);
         //do
        String input = "show books in stock";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("\"algebra, 10 class\", 5 pcs., 150 rub.\n" +
                "\"algorithm, 4 class\", 70 pcs., 100 rub.\n", respond);


    }
    @Test
    void balanceTest() {
        //where
        String data = "balance: 1000, books: [(\"algebra, 10 class\", 5, 150), (\"algorithm, 4 class\", 70, 100)]";
        List<Main.Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);
        //do
        String input = "print balance";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("balance: 1000 rub." , respond );
    }
   @Test
    void exitTest() {
        //where
       String data = "balance: 1000, books: [(\"algebra, 10 class\", 5, 150), (\"algorithm, 4 class\", 70, 100)]";
       List<Main.Book> bookList = main.parseBooks(data);
       int balance = main.parseBalance(data);
        //do
        String input = "exit";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("", respond);
    }@Test
    void IDontUnderstandTest() {
        //where
       String data = "balance: 1000, books: [(\"algebra, 10 class\", 5, 150), (\"algorithm, 4 class\", 70, 100)]";
       List<Main.Book> bookList = main.parseBooks(data);
       int balance = main.parseBalance(data);
        //do
        String input = "gihfiofshi";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("I don't understand", respond);
    }
    @Test
    void showNonExistantBoughtBooksTest() {
        //where
        String data = "balance: 1000, books: [(\"algebra, 10 class\", 5, 150), (\"algorithm, 4 class\", 70, 100)]";
        List<Main.Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);
        //do
        String input = "show bought books by Artem";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("nothing", respond);
    }
}
