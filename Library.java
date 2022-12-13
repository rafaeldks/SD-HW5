import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Library {
    private final Map<Book, Integer> books = new HashMap<Book, Integer>();

    public Library() { // Книжки для примера
        books.put(new Book("War and Peace", "Lev Tolstoy", 1865), 1);
        books.put(new Book("Burning daylight", "Jack London", 1910), 1);
        books.put(new Book("Head first Java", "Kathy Sierra", 2003), 2);
        books.put(new Book("Head first Java", "Another author", 1999), 1);
        books.put(new Book("Book", "Author", 2022), 3);
    }

    public void printBooks() {
        for (var key : books.keySet()) {
            System.out.println(key.getName() + " - " + key.getAuthor() + ", " + key.getPublicationYear() + " год, - " + books.get(key) + " шт.");
        }
    }

    public void addBook(Book book) {
        if (books.containsKey(book)) books.merge(book, 1, Integer::sum);
        else {
            books.put(book, 1);
        }
    }

    public boolean checkByName(String name) {
        var booksSet = books.keySet();
        for (var book : booksSet) {
            if (book.getName().equals(name)) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public Book get(String name) {
        int counter = 0;
        var booksSet = books.keySet();
        Book result = null;

        for (var book : booksSet) {
            if (book.getName().equals(name)) {
                counter++;
                result = book;
            }
        }
        if (counter == 1) {
            books.merge(result, -1, Integer::sum);
            if (books.get(result) == 0) books.remove(result);
            return result;
        }
        if (counter > 1) {
            System.out.println("Книги с таким названием:");
            int counter_ = 1;
            for (var book : booksSet) {
                if (book.getName().equals(name)) {
                    System.out.print(counter_ + ": ");
                    System.out.println(book.getName() + " - " + book.getAuthor() + ", " + book.getPublicationYear() + " год, - " + books.get(book) + " шт.");
                    counter_++;
                }
            }
            int action = getAction(1, counter);
            counter = 0;
            for (var book : booksSet) {
                if (book.getName().equals(name)) {
                    counter++;
                    if (counter == action) {
                        books.merge(book, -1, Integer::sum);
                        if (books.get(book) == 0) books.remove(book);
                        return book;
                    }

                }
            }
        }
        System.out.println("Книги с таким названием нет в библиотеке.");
        return null;
    }

    public static int getAction(int a, int b) {
        System.out.println("Введите число от " + a + " до " + b);
        Scanner in = new Scanner(System.in);
        try {
            int action = in.nextInt();
            while (action < a || action > b) {
                System.out.println("Число должно быть в отрезке [" + a + ", " + b + "]");
                System.out.println("Введите число еще раз");
                action = in.nextInt();
            }
            return action;
        } catch (Exception e) {
            System.out.println("Ошибка формата ввода");
            System.out.println("Введите число еще раз");
            return getAction(a, b);
        }
    }
}
