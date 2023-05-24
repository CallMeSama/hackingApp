package main;

import java.util.Scanner;
import crack.src.Crack;
import crack.src.PasswordCrackerFactory;
import java.io.IOException;
import java.security.MessageDigest;

public class Main {

    public static String hasher(String password) {
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = password.getBytes();

            digest.reset();
            digest.update(passwordBytes);
            byte[] message = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < message.length; i++) {
                hexString.append(Integer.toHexString(
                        0xFF & message[i]));
            }
            hash = hexString.toString();

        } catch (Exception e) {
        }
        return hash;

    }

    public static void main(String[] args) throws IOException {
        // calling the cmd invite cleaner method
        clearConsole();
        int choice;
        String password;
        String restart = "yes";
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("**********************  WELCOME TO PASSHACK  **********************");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Hashing ");
        System.out.println("\t1 : BruteForce Hasher");
        System.out.println(" ");
        System.out.println("\t2 : Dictionnary Hasher");
        System.out.println(" ");
        System.out.println("Cracking");
        System.out.println("\t3 : BruteForce Cracker");
        System.out.println(" ");
        System.out.println("\t4 : Dictionnary Cracker");
        System.out.println(" ");
        while (restart.equals("yes")) {
            System.out.print("  Select in the menu an option and type the corresponding number:");

            choice = sc.nextInt();
            if (choice == 1 || choice == 2) {
                System.out.println("Type the the hash of the password you want to know :");
            } else {
                System.out.println("Type the password to hack :");
            }
            password = sc.nextLine();
            password = sc.nextLine();

            // We call the getInstance() methode of the Factory class
            Crack crack = null;
            try {
                crack = PasswordCrackerFactory.getInstance(password, choice);
                System.out.println(crack.getResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Would you like to hack another word? yes/no :");
            restart = sc.nextLine();
        }
        sc.close();
    }

    // this method clear the cmd invite
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

}
