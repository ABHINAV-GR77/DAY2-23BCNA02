package Banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Createaccount ac =new Createaccount();
             while(true)
        {
            System.out.println("Enter your choice");
            System.out.println("1.Create an Account");
            System.out.println("2.Deposit Money");
            System.out.println("3.Widraw Money");
            System.out.println("4.Balance Enquiry");
            int ch=sc.nextInt();
            switch(ch) {
                case 1:
                    ac.Accountcreate();
                    break;
                case 2:
                    ac.Deposit();
                    break;
                case 3:ac.Widraw();
                    break;
                case 4:
                    ac.balance();
                    break;
            }
            }

        }
}