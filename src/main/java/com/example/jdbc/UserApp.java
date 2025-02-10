package com.example.jdbc;

import java.util.List;
import java.util.Scanner;

public class UserApp {
    private static final UserDAO userDAO = new UserDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add user");
            System.out.println("2. Show all users");
            System.out.println("3. Update user");
            System.out.println("4. Delete user");
            System.out.println("5. Exit");
            System.out.print("Select an action: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addUser();
                case 2 -> showUsers();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> {
                    System.out.println("Exit from the program");
                    return;
                }
                default -> System.out.println("Invalid entry, try again");
            }
        }
    }

    private static void addUser() {
        System.out.print("Enter a name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        userDAO.addUser(name, email);
    }

    private static void showUsers() {
        List<String> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("The user list is empty");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void updateUser() {
        System.out.print("Enter the user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter a new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter a new email: ");
        String newEmail = scanner.nextLine();
        userDAO.updateUser(id, newName, newEmail);
    }

    private static void deleteUser() {
        System.out.print("Enter the user ID: ");
        int id = scanner.nextInt();
        userDAO.deleteUser(id);
    }
}
