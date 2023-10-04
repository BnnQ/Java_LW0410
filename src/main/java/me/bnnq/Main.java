package me.bnnq;

import me.bnnq.models.Client;
import me.bnnq.models.User;
import me.bnnq.utils.ConsoleUtilities;
import me.bnnq.utils.ScannerUtilities;
import me.bnnq.utils.TaskUtilities;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        task3();
    }

    public static void task1()
    {
        ArrayList<Integer> list = new ArrayList<>();

        while (true)
        {
            ConsoleUtilities.clearConsole();

            System.out.println("1. Add element to list");
            System.out.println("2. Remove element from list");
            System.out.println("3. Print list");
            System.out.println("4. Check if element exists in list");
            System.out.println("5. Replace element in list");
            System.out.println("Any other number - exit");

            System.out.print("Make your choice: ");
            int choice = ScannerUtilities.scanInt();

            ConsoleUtilities.clearConsole();
            switch (choice)
            {
                case 1:
                {
                    System.out.print("Enter element to add: ");
                    int element = ScannerUtilities.scanInt();
                    list.add(element);
                    break;
                }
                case 2:
                {
                    System.out.print("Enter element to remove: ");
                    int element = ScannerUtilities.scanInt();
                    list.remove((Integer) element);
                    break;
                }
                case 3:
                {
                    System.out.print("List: ");
                    for (int element : list)
                        System.out.printf("%d ", element);

                    ConsoleUtilities.pause();
                    break;
                }
                case 4:
                {
                    System.out.print("Enter element to check: ");
                    int element = ScannerUtilities.scanInt();

                    System.out.printf("Element %d %s in list\n", element, list.contains(element) ? "exists" : "does not exist");
                    ConsoleUtilities.pause();
                    break;
                }
                case 5:
                {
                    System.out.print("Enter element to replace: ");
                    int element = ScannerUtilities.scanInt();
                    System.out.print("Enter new element: ");
                    int newElement = ScannerUtilities.scanInt();

                    list.set(list.indexOf(element), newElement);
                    break;
                }
                default:
                {
                    return;
                }
            }

        }
    }

    public static void task2()
    {
        ArrayList<User> users = new ArrayList<>();

        while (true)
        {
            ConsoleUtilities.clearConsole();

            System.out.println("1. Add new user");
            System.out.println("2. Remove user");
            System.out.println("3. Check if user exists");
            System.out.println("4. Change username of user");
            System.out.println("5. Change password of user");
            System.out.println("Any other number - exit");

            System.out.print("Make your choice: ");
            int choice = ScannerUtilities.scanInt();

            ConsoleUtilities.clearConsole();
            switch (choice)
            {
                case 1:
                {
                    System.out.print("Enter username: ");
                    String username = ScannerUtilities.scanString();
                    System.out.print("Enter password: ");
                    String password = ScannerUtilities.scanString();

                    users.add(new User(username, password));
                    break;
                }
                case 2:
                {
                    System.out.print("Enter username: ");
                    String username = ScannerUtilities.scanString();
                    System.out.print("Enter password: ");
                    String password = ScannerUtilities.scanString();

                    User user = users.stream().filter(u -> u.username.equals(username) && u.password.equals(password)).findFirst().orElse(null);
                    if (user == null)
                    {
                        System.err.println("User not found or credentials are incorrect");
                        ConsoleUtilities.pause();
                        break;
                    }

                    users.remove(user);
                    break;
                }
                case 3:
                {
                    System.out.print("Enter username: ");
                    String username = ScannerUtilities.scanString();

                    System.out.printf("User %s %s in list\n", username, users.stream().anyMatch(user -> user.username.equals(username)) ? "exists" : "does not exist");
                    ConsoleUtilities.pause();
                    break;
                }
                case 4:
                {
                    System.out.print("Enter username: ");
                    String username = ScannerUtilities.scanString();
                    System.out.print("Enter password: ");
                    String password = ScannerUtilities.scanString();

                    User user = users.stream().filter(u -> u.username.equals(username) && u.password.equals(password)).findFirst().orElse(null);
                    if (user == null)
                    {
                        System.err.println("User not found or credentials are incorrect");
                        ConsoleUtilities.pause();
                        break;
                    }

                    System.out.print("Enter new username: ");
                    String newUsername = ScannerUtilities.scanString();

                    user.setUsername(newUsername);
                    break;
                }
                case 5:
                {
                    System.out.print("Enter username: ");
                    String username = ScannerUtilities.scanString();
                    System.out.print("Enter password: ");
                    String password = ScannerUtilities.scanString();

                    User user = users.stream().filter(u -> u.username.equals(username) && u.password.equals(password)).findFirst().orElse(null);
                    if (user == null)
                    {
                        System.err.println("User not found or credentials are incorrect");
                        ConsoleUtilities.pause();
                        break;
                    }

                    System.out.print("Enter new password: ");
                    String newPassword = ScannerUtilities.scanString();

                    user.setPassword(newPassword);
                    break;
                }
                default:
                {
                    return;
                }
            }
        }
    }

    public static void task3() throws InterruptedException
    {
        Server server = new Server(5);
        Client[] clients = new Client[]
        {
                new Client("Non authorized client", 1),
                new Client("Authorized client", 2),
                new Client("Premium client", 3)
        };

        Thread.sleep(1000);
        clients[0].sendRequest("Non authorized user requests data", server);

        Thread.sleep(1000);
        clients[1].sendRequest("Authorized user requests data", server);

        Thread.sleep(1000);
        clients[2].sendRequest("Premium user requests data", server);

        TaskUtilities.sleepWithDelay(15000, 1000);
        server.printRequestHistory();
        server.printRequestProcessingHistory();

        server.dispose();
    }
}