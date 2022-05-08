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
        String data = "balance: 1000, books: [('algebra, 10 class', '5', '150'), ('algoritm, 4 class', '70', '100')]";
        List<Main.Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);
        //do
        String input = "buy \"algebra, 10 class\" 5";
        String respond = main.processing(input, bookList, balance);
        assertEquals( "deal", respond);

        String input2 = "buy \"algoritm, 4 class\" 6";
        String respond2 = main.processing(input2, bookList, balance);
        assertEquals("deal", respond2);

        String input3 = "buy \"algoritm, 4 class\" 7";
        String respond3 = main.processing(input3, bookList, balance);
        assertEquals("deal", respond3);

        String input1 = "show bought books";
        String respond1 = main.processing(input1, bookList, balance);
        //and check
        assertEquals("\"algebra, 10 class\", 5 pcs.\n" +
                             "\"algoritm, 4 class\", 13 pcs.\n", respond1);

    }
    @Test
    void showAllBooksTest() {
        //where
        List<Main.Book> bookList = main.parseBooks("balance: 1000, books: [('algebra, 10 class', '5', '150'), ('algoritm, 4 class', '70', '10')]");
        int balance = 100500;
        System.out.println();
        //do
        String input = "show books in stock";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("\"algebra, 10 class\", 5 pcs., 150 rub.\n" +
                "\"algoritm, 4 class\", 70 pcs., 10 rub.\n", respond);


    }
    @Test
    void balanceTest() {
        //where
        List<Main.Book> bookList = main.parseBooks("[('Совершенный код, Стив Макконелл','123','10'),('Сборник анекдотов про Мариванну','452','53')");
        int balance = 100500;
        //do
        String input = "print balance";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("Your balance is: 100500", respond);
    }
   @Test
    void exitTest() {
        //where
       String data = "balance: 1000, books: [('algebra, 10 class', '5', '150'), ('algoritm, 4 class', '70', '100')]";
       List<Main.Book> bookList = main.parseBooks(data);
       int balance = main.parseBalance(data);
        //do
        String input = "exit";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("", respond);
    }
    //todo Проверить что не продаются книги которые не существуют, и что книг меньше чем пытаются купить

}
