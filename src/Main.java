import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Utils utlils = new Utils();
        Scanner scanner = new Scanner(System.in);

        database.connect();
        ArrayList<Item> items = database.getItems();
        HashMap<String, ArrayList<Item>> categoriesWithItems = utlils.groupItems(items);

        String productName;
        String category;
        int answer;
        boolean success;

        while (true) {
            System.out.println();
            System.out.println("""
                    1. show items
                    2. delete item
                    3. add item""");
            System.out.println("Choose option: ");
            answer = scanner.nextInt();

            if (answer == 1) {
                utlils.showItems(categoriesWithItems);
            } else if (answer == 2) {
                scanner.nextLine();
                System.out.println("Enter product name to delete: ");
                productName = scanner.nextLine().trim().toLowerCase();
                success = database.deleteItem(productName);

                if (success) {
                    System.out.println("Item has been deleted successfully");
                     items = database.getItems();
                } else {
                    System.out.println("Failed to delete item");
                }
            } else if (answer == 3) {
                scanner.nextLine();
                System.out.println("Enter category name: ");
                category = scanner.nextLine();
                System.out.println("Enter product name: ");
                productName = scanner.nextLine();

                success = database.addItem(productName, category);

                if (success) {
                    System.out.println("Product has been added successfully");
                    items = database.getItems();
                }
                else {
                    System.out.println("Product has not been added successfully");
                }
            }

        }
    }


}
