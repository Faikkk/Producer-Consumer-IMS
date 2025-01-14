import java.util.List;
import java.util.concurrent.Callable;

public class ProducerThread implements Callable<List<Item>> {
    private PCMessagingQueue messagingQueue;

    public ProducerThread(PCMessagingQueue messagingQueue) {
        this.messagingQueue = messagingQueue;
    }

    @Override
    public List<Item> call() throws Exception {
        List<Item> itemList;
        try {
            itemList = RandomOrderGeneration.OrderGeneration();
            for (Item item : itemList) {
                System.out.println("Producing the item " + item.getName() + " in quantity of " + item.getQuantity());
                this.messagingQueue.produce(item.getName(), item.getQuantity());

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
}
