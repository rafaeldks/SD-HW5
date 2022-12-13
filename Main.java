import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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


    public static String[] getCommand() throws IOException {
        System.out.print("Введите команду: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        if (str.equals("")) {
            System.out.println("Введена пустая строка...");
            return getCommand();
        }
        String[] command = str.split(" ");
        String action = command[0];
        if (action.equals("/get") || action.equals("/put")) {
            if (command.length != 1) return command;
            System.out.println("Не введено название книги...");
            return getCommand();
        }
        if (action.equals("/list") || action.equals("/all")) return command;
        if (action.equals("/exit")) System.exit(0);
        System.out.println("Такой команды не существует...");
        return getCommand();
    }

    public static String getName(String[] command) {
        String name = "";
        for (int i = 1; i < command.length - 1; i++) {
            name += command[i] + " ";
        }
        name += command[command.length - 1];
        return name;
    }

    public static void main(String[] args) throws IOException {
        Library lib = new Library();
        ArrayList<Book> myBooks = new ArrayList<Book>();
        while (true) {
            String[] command = getCommand();
            if (command[0].equals("/get")) {
                if (lib.isEmpty()) {
                    System.out.println("Библиотека пуста...");
                    continue;
                }
                var book = lib.get(getName(command));
                if (book != null) {
                    myBooks.add(book);
                }
            }
            if (command[0].equals("/all")) {
                if (lib.isEmpty()) {
                    System.out.println("Библиотека пуста...");
                    continue;
                }
                System.out.println("Книги в библиотеке: ");
                lib.printBooks();
            }
            if (command[0].equals("/list")) {
                if (myBooks.isEmpty()) {
                    System.out.println("У вас нет книг...");
                    continue;
                }
                System.out.println("Ваши книги:");
                for (var book : myBooks) {
                    System.out.println(book.getName() + " - " + book.getAuthor() + ", " + book.getPublicationYear() + " год");
                }
            }
            if (command[0].equals("/put")) {
                if (myBooks.isEmpty()) {
                    System.out.println("У вас нет книг...");
                    continue;
                }
                String name = getName(command);
                int counter = 0, index = 0;
                Book result = null;
                for (var book : myBooks) {
                    if (book.getName().equals(name)) {
                        result = book;
                        index = counter;
                        counter++;
                    }
                }
                if (counter == 0) {
                    System.out.println("У вас нет книг с таким названием...");
                    continue;
                }
                if (counter == 1) {
                    lib.addBook(result);
                    myBooks.remove(index);
                    continue;
                }
                System.out.println("Ваши книги с таким названием: ");
                int counter_ = 1;
                for (var book : myBooks) {
                    if (book.getName().equals(name)) {
                        System.out.print(counter_ + ": ");
                        System.out.println(book.getName() + " - " + book.getAuthor() + ", " + book.getPublicationYear() + " год");
                        counter_++;
                    }
                }
                int action = getAction(1, counter);
                counter = 0;
                counter_ = 0;
                for (var book : myBooks) {
                    if (book.getName().equals(name)) {
                        counter_++;
                        if (counter_ == action) {
                            lib.addBook(book);
                            myBooks.remove(counter);
                            break;
                        }
                    }
                    counter++;
                }
            }
        }
    }
}