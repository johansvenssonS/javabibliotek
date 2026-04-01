//meny

import java.util.ArrayList;
import java.util.Scanner;

public class BookController {
    BookService bookService = new BookService();

    Scanner scanner = new Scanner(System.in);


    public void showBookMenu(){

        boolean active = true;

        while(active){
            System.out.println("----Book menu----");
            System.out.println("1. Show all books");
            System.out.println("2. Show all available books");
            System.out.println("3. Search books");
            System.out.println("4. Add books");
            System.out.println("5. Update books");
            System.out.println("6. Delete books");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    ArrayList<Book> books = bookService.getAllBooks();
                    for(Book b : books){
                        System.out.println(b.toString());
                    }
                    break;

                case 2:
                    ArrayList<Book> booksIN = bookService.getAvailableBooks();
                    for(Book b : booksIN){
                        System.out.println(b.toString());
                    }
                    break;

                case 3:
                    scanner.nextLine();
                    System.out.println("Sök efter bok...");
                    String searchTerm = scanner.nextLine();
                    ArrayList<Book> booksMatch = bookService.searchBooks(searchTerm);
                    for(Book b : booksMatch){
                        System.out.println(b.toString());
                    }
                    break;
                case 4:
                case 5:
                case 6:

                case 0:
                    active = false;
                    break;
            }
        }



    }
}
