import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

    public HashMap<String, ArrayList<Item>> groupItems(ArrayList<Item> items) {
        HashMap<String, ArrayList<Item>> categoriesWithItems = new HashMap<>();
        ArrayList<String> categoriesName = new ArrayList<>();

        for (Item item : items) {
            categoriesName.add(item.getCategory());
        }

        for (String category : categoriesName) {
            ArrayList<Item> matchingItems = new ArrayList<>();
            for (Item item : items) {
                if (item.getCategory().equalsIgnoreCase(category)) {
                    matchingItems.add(item);
                }
            }
            categoriesWithItems.put(category, matchingItems);
        }

        return categoriesWithItems;
    }

    public void showItems(HashMap<String, ArrayList<Item>> categoriesWithItems) {

        for (String category : categoriesWithItems.keySet()) {
            System.out.println(category);

            for (Item item : categoriesWithItems.get(category)) {
                System.out.printf("-> %s \n", item.getName());
            }

            System.out.println("------------------");
        }
    }
}
