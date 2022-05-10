import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Main main = new Main();
        System.out.println("введите данные, пожалуйста: ");
        String data = console.nextLine();
        List<Book> bookList = main.parseBooks(data);
        int balance = main.parseBalance(data);
        System.out.println("books: "+bookList);
        System.out.println("balance: "+balance);
        String respond = "dasf";
        while (!respond.equals("")) {
            System.out.println("введите команду, пожалуйста: ");
            String input = console.nextLine();
            respond = main.processing(input, bookList, balance);
            System.out.println(respond);
        }
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

    public String processing(String request, List<Book> bookList, int balance) {
        String respond = "";
        String res = "";
        if ("print balance".equals(request)) {
            respond = "balance: " + balance + " rub.";    //выводим баланс
        } else if ("show books in stock".equals(request)) {
            for (Book book : bookList) {
                respond = respond + book + "\n";    //выводим какие книги есть в продаже
            }
        } else if ("exit".equals(request)) {
            respond = "";   //выход из программы
        } else if (request.contains("buy")) {
            String name = "";   //создаем новую переменную с типом string
            int i = 0;
            String countStr = "";   //создаем новую переменную с типом string
            String nameCountBuyerStr = request.replaceAll("buy ", "");   //мы вырезаем из nameCountBuyerStr символы buy
            if (nameCountBuyerStr.charAt(i) == '\"') {
                i++;
                while (nameCountBuyerStr.charAt(i) != '\"') {    //пока не равнеется "'" добавляем символы
                    name = name + nameCountBuyerStr.charAt(i);
                    i++;
                }
            }
            i++;
            if (nameCountBuyerStr.charAt(i) == ' ') {    //если символ равен " "
                i++;
                while (nameCountBuyerStr.charAt(i) != ',') { //повторяем, пока меньше длины nameCountBuyerStr и добавляем символы
                    countStr = countStr + nameCountBuyerStr.charAt(i);
                    i++;
                }
                i++;
            }
            i++;
            int count = Integer.decode(countStr);   //делаем так, чтобы строка превратилась в число
            String buyer = "";
            if (nameCountBuyerStr.charAt(i) == '\"') {
                i++;
                while (nameCountBuyerStr.charAt(i) != '\"') {
                    buyer = buyer + nameCountBuyerStr.charAt(i);
                    i++;
                }
            }
            respond = "no deal";
            for (Book book : bookList) {    //проходимся по каждой книги и проверем
                if (name.equals(book.name)) {   //если имена совпадпают
                    if (book.count >= count) {  //если кол-во книг больше или равно количеству

                        respond = "deal";
                        book.count = book.count - count;    //уменьшаем кол-во
                        balance = balance + (count * book.price);   //увеличиваем баланс
                        book.boughtCount = book.boughtCount + count;
                        if (!book.buyer.contains(buyer)) {
                            book.buyer.add(buyer);
                        }
                    }
                }
            }
        } else if (request.contains("show bought books")) {
            String buyer;
            //если в request содержится 'by', то вырезаем 'show bought books by ' и таким образом получаем покупателя
            if (request.contains("by")) {
                buyer = request.replaceAll("show bought books by ", "");
            }//иначе buyer = ''
            else {
                buyer = "";
            }
            for (Book book : bookList) {
                //если количество купленных книг не равно нулю и либо покупатель не уточнен либо книга содержит покупателя
                //, то в ответ добавляется книга
                if ((book.boughtCount != 0) && (buyer.isEmpty() || book.buyer.contains(buyer))) {
                    respond = respond + ("\"" + book.name + "\"" + ", " + book.boughtCount + " pcs." + "\n");

                }
            }
        } else {
            respond = "I don't understand";
        }
        return respond;
    }

    public static class Book {
        List<String> buyer = new ArrayList<>();
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
            return "\"" + name + "\"" + ", " + price + " pcs." + "," + " " + count + " rub.";
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