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
        String data = "balance: 1000, books: [('algebra, 10 class', '5', '150'), ('algorithm, 4 class', '70', '100')]";
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
                             "\"algorithm, 4 class\", 13 pcs.\n", respond1);

        String input4 = "show bought books by Artem";
        String respond4= main.processing(input4, bookList, balance);
        //and check
        assertEquals("\"algebra, 10 class\", 5 pcs.\n" +
                "\"algorithm, 4 class\", 13 pcs.\n", respond4);

        String input5 = "show bought books by vinipux";
        String respond5= main.processing(input5, bookList, balance);
        //and check
        assertEquals("\"algorithm, 4 class\", 13 pcs.\n", respond5);

    }
    @Test
    void showAllBooksTest() {
        //where
        List<Main.Book> bookList = main.parseBooks("balance: 1000, books: [('algebra, 10 class', '5', '150'), ('algorithm, 4 class', '70', '10')]");
        int balance = 100500;
        System.out.println();
         //do
        String input = "show books in stock";
        String respond = main.processing(input, bookList, balance);
        //and check
        assertEquals("\"algebra, 10 class\", 5 pcs., 150 rub.\n" +
                "\"algorithm, 4 class\", 70 pcs., 10 rub.\n", respond);


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
       String data = "balance: 1000, books: [('algebra, 10 class', '5', '150'), ('algorithm, 4 class', '70', '100')]";
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
