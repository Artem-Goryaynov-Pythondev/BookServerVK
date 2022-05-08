import java.util.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        //todo 'balance' or 'balanceValue' for example?
        String data = "balance: 1000, books: [('algebra, 10 class', '5', '100'), ('num, 2 class', '42', '500')]";
        List<Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);
        String input = "show bought books";

        System.out.println(main.processing(input, bookList, balance));


    }


    public int parseBalance(String src) { //парсим баланс

        int i = src.indexOf("balance: ") + "balance: ".length(); //делаем что бы парс начинался с balance:
        String balanceStr = "";

        while (src.charAt(i) != ',') { //пока символ не будет ','


            balanceStr = balanceStr + src.charAt(i); //добавляем символ в balanceStr
            i++; //увеличиваем счетчик

        }
        int balance = Integer.decode(balanceStr);//делаем так, чтобы строка превратилась в число
        return balance;//передаем

    }

    public List<Book> parseBooks(String src) {

        List<Book> result = new ArrayList<>();//создаем новый список
        int i = src.indexOf("books: ") + "books: ".length();//делаем так, чтобы парс начинался с books:
        if (src.charAt(i) == '[') {     // если символ = [
            while (src.charAt(i) != ']') {  //пока символ не будет равен ]

                i++;    //прибавляем счетчик
                if (src.charAt(i) == '(') {     // если символ = скобке

                    String bookStr = "";    //создаем новую строку
                    while (src.charAt(i) != ')') {  //пока символ не равен закрытой скобке
                        bookStr = bookStr + src.charAt(i);  //добавляем символ в bokStr
                        i++;    //увеличиваем счетчик
                    }

                    Book book = parseBook(bookStr); //
                    result.add(book);//

                }

            }
        }

        return result;  //

    }


    public Book parseBook(String src) {


        int i = 0;
        i++;
        String nameStr = "";
        String priceStr = "";
        String countStr = "";

        if (src.charAt(i) == '\'') { //если символ = "'"
            i++;    //увеличиваем счетчик


            while (src.charAt(i) != '\'') {     //пока символ не равен "'"
                nameStr = nameStr + src.charAt(i);  //добавляем символ

                i++;

            }
            i++;
        }
        i++;
        i++;
        if (src.charAt(i) == '\'') {   //по второму кругу то же самое, но с ценой
            i++;


            System.out.println("");

            while (src.charAt(i) != '\'') {

                priceStr = priceStr + src.charAt(i);
                i++;

            }
            i++;

        }
        i++;
        i++;
        if (src.charAt(i) == '\'') {    //то же самое, но с количеством

            i++;


            System.out.println("");

            while (src.charAt(i) != '\'') {
                countStr = countStr + src.charAt(i);
                i++;

            }


        }
        int count = Integer.decode(countStr);   //делаем так, чтобы строка превратилась в число
        int price = Integer.decode(priceStr);   //делаем так, чтобы строка превратилась в число
        Book book = new Book(nameStr, price, count, 0);  //создаем новую книгу
        return book;    //возвращаем её
    }


    public static class Book {

        String name;    //создаем новую переменную с типом string
        int price;  //создаем новую переменную с типом int
        int count;  //создаем новую переменную с типом int

        int boughtCount;    //создаем новую переменную с типом int

        public Book(String name, int price, int count, int boughtCount) {
            this.name = name;
            this.price = price;
            this.count = count;
            this.boughtCount = boughtCount;
        }

        @Override
        public String toString() {
            return "\"" + name + "\"" + ", " + price + " pcs." + "," + " " + count  + " rub.";
        }
    }

    public String processing(String request, List<Book> bookList, int balance) {
        String respond = "";
        String res = "";

        if ("print balance".equals(request)) {
            respond = "Your balance is: " + balance;    //выводим баланс
            return respond;
        } else if ("show books in stock".equals(request)) {

            for (Book book : bookList) {
                respond = respond + book + "\n";    //выводим какие книги есть в продаже
            }
            return respond;
        } else if ("exit".equals(request)) {

            respond = "";   //выход из программы
            return respond;
        } else if (request.contains("buy")) {
            String name = "";   //создаем новую переменную с типом string
            int i = 0;
            String countStr = "";   //создаем новую переменную с типом string
            String nameCountStr = request.replaceAll("buy ", "");   //мы вырезаем из nameCountStr символы buy

            if (nameCountStr.charAt(i) == '\"') {
                i++;
                while (nameCountStr.charAt(i) != '\"') {    //пока не равнеется "'" добавляем символы
                    name = name + nameCountStr.charAt(i);
                    i++;

                }
            }
            i++;
            if (nameCountStr.charAt(i) == ' ') {    //если символ равен " "
                i++;
                while (i < nameCountStr.length()) { //повторяем, пока меньше длины nameCountStr и добавляем символы
                    countStr = countStr + nameCountStr.charAt(i);
                    i++;
                }
            }
            int count = Integer.decode(countStr);   //делаем так, чтобы строка превратилась в число

            for (Book book : bookList) {    //проходимся по каждой книги и проверем
                if (name.equals(book.name)) {   //если имена совпадпают
                    if (book.count >= count) {  //если кол-во книг больше или равно количеству

                        respond = "deal";
                        book.count = book.count - count;    //уменьшаем кол-во
                        balance = balance + (count * book.price);   //увеличиваем баланс
                        book.boughtCount = book.boughtCount + count;


                    }
                }
            }
        } else if (request.contains("show bought books")) {
            for (Book book : bookList) {
                if (book.boughtCount != 0) {

                    respond = respond + ("\""+book.name+"\"" + ", " + book.boughtCount + " pcs." + "\n");
                }

            }
            return respond;
        }


        return respond;
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