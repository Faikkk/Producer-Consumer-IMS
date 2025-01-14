import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomOrderGeneration {
    static public List<Item> OrderGeneration() {
        List<String> listOfNames = List.of("IPhone X", "IPhone 11", "IPhone 12 Pro", "Macbook Pro 13",
                "Macbook Pro 14");
        List<Item> listOfItems = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < listOfNames.size(); i++) {
            String randomName = listOfNames.get(random.nextInt(listOfNames.size()));
            int randomQuantity = (int)(Math.random() * 101) + 1;
            boolean duplicate = false;

            for (Item item:listOfItems){
                if (randomName.equals(item.getName())){
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate){
                listOfItems.add(new Item(randomName, randomQuantity));
            }

        }
        return listOfItems;
    }
}
