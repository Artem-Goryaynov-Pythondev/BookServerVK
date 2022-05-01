import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Book> bookList = parse("[(-book2+,-123+,-10+),(-book3+,-452+,-53+),(-book4+,-53+,-52+)]");
        System.out.println(bookList);

    }

    static List<Book> parse(String src) {
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

    // ('book2','123','10')

    static Book parseBook(String src) {

        System.out.println("src = " + src);
        int i = 0;
        i++;
        String nameStr = "";
        String priceStr = "";
        String countStr = "";

        if (src.charAt(i) == '-') {
            i++;



            while (src.charAt(i) != '+') {
                nameStr = nameStr + src.charAt(i);

                i++;

            }
            i++;
            System.out.println("nameStr = " + nameStr);
        }
        i++;
        if (src.charAt(i) == '-') {
            i++;



            System.out.println("");

            while (src.charAt(i) != '+') {

                priceStr = priceStr + src.charAt(i);
                i++;

            }
            System.out.println("priceStr = " + priceStr);
        i++;

        }
    i++;
        if (src.charAt(i) == '-') {

           i++;


            System.out.println("");

            while (src.charAt(i) != '+') {
                countStr = countStr + src.charAt(i);
                i++;

            }

            System.out.println("countStr = " + countStr);

        }
        int count = Integer.decode(countStr);
        int price = Integer.decode(priceStr);
        System.out.println("nameStr = " + nameStr);
        Book book = new Book(nameStr, price, count);
        return book;
    }


    static class Book {
        String name;
        int price;
        int count;

        public Book(String name, int price, int count) {
            this.name = name;
            this.price = price;
            this.count = count;
            System.out.println(name);
        }

        @Override
        public String toString() {
            return "Book{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    ", count=" + count +
                    '}';
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