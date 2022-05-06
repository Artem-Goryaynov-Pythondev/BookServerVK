import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        //todo 'balance' or 'balanceValue' for example?
        List<Book> bookList = main.parse("[('book2','123','10'),('book3','452','53'),('book4','53','52')]");
        int balance = 100500;
        String input = "buy book 'book2' 1";

        System.out.println(main.handle(input, bookList, balance));


    }

    public String handle(String request, List<Book> bookList, int balance) {
        String respond = "";

        if ("print balance".equals(request)) {
            respond = "Your balance is: " + balance;
        } else if ("show books in stock".equals(request)) {

            for (Book book : bookList) {
                respond = respond + book + "/n";
            }
            return respond;
        } else if ("exit".equals(request)) {

            respond = "";
        } else if (request.contains("buy book")) {
            String name = "";
            int i = 0;
            String countStr = "";
            String nameCountStr = request.replaceAll("buy book ", "");
            System.out.println(nameCountStr);
            if (nameCountStr.charAt(i) == '\'') {
                i++;
                while (nameCountStr.charAt(i) != '\'') {
                    name = name + nameCountStr.charAt(i);
                    i++;

                }
            }
            i++;
            if (nameCountStr.charAt(i) == ' ') {
                i++;
                while (i < nameCountStr.length()) {
                    countStr = countStr + nameCountStr.charAt(i);
                    i++;
                }
            }
            int count = Integer.decode(countStr);
            System.out.println(count);
            for (Book book : bookList) {
                if (name.equals(book.name)) {

                    book.count = book.count - count;
                    balance = balance + (count * book.price);
                    respond = "balance: " + balance + ", " + book.toString();
                }
            }

        }

        return respond;
    }


    public List<Book> parse(String src) {
        //todo: parse balance
        int balanceValue = 0;
        List<Book> result = new ArrayList<>();
        int i = 0;


        if (src.charAt(i) == '[') {
            while (src.charAt(i) != ']') {

                i++;
                if (src.charAt(i) == '(') {

                    String bookStr = "";
                    while (src.charAt(i) != ')') {

                        bookStr = bookStr + src.charAt(i);
                        i++;
                    }

                    Book book = parseBook(bookStr);
                    result.add(book);

                }

            }
        }

        return result;

    }


    public Book parseBook(String src) {


        int i = 0;
        i++;
        String nameStr = "";
        String priceStr = "";
        String countStr = "";

        if (src.charAt(i) == '\'') {
            i++;


            while (src.charAt(i) != '\'') {
                nameStr = nameStr + src.charAt(i);

                i++;

            }
            i++;
        }
        i++;
        if (src.charAt(i) == '\'') {
            i++;


            System.out.println("");

            while (src.charAt(i) != '\'') {

                priceStr = priceStr + src.charAt(i);
                i++;

            }
            i++;

        }
        i++;
        if (src.charAt(i) == '\'') {

            i++;


            System.out.println("");

            while (src.charAt(i) != '\'') {
                countStr = countStr + src.charAt(i);
                i++;

            }


        }
        int count = Integer.decode(countStr);
        int price = Integer.decode(priceStr);
        Book book = new Book(nameStr, price, count);
        return book;
    }


    public static class Book {
        String name;
        int price;
        int count;

        public Book(String name, int price, int count) {
            this.name = name;
            this.price = price;
            this.count = count;
        }

        @Override
        public String toString() {
            return
                    "Name = " + name +
                            ", Price = " + price +
                            ", Count = " + count
                    ;
        }
    }

}
/*
 * char ch = src.charAt(i);
 * String priceStr="";
 * priceStr = priceStr + src.charAt(i);
 * int price = Integer.decode(priceStr);
 * Book book = new Book(nameStr, price, count);
 * result.add(book);
 */