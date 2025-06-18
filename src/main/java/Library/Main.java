package Library;

import org.example.book;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        org.example.book bk=new book();
        while(true)
        {
            System.out.println("Enter your choice");
            System.out.println("1.Insert a new Book");
            System.out.println("2.Update a Book");
            System.out.println("3.Find a  Book");
            System.out.println("4.Delete a Book");
            int ch=sc.nextInt();
            switch(ch) {
                case 1:
                    bk.insertbook();
                    break;
                case 2:
                    bk.updatebook();
                    break;
                case 3:
                    bk.displaybook();
                    break;
                case 4:
                    bk.deletebook();
                    break;
            }
        }

    }
}
