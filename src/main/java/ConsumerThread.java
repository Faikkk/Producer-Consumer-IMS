import java.util.List;
import java.util.concurrent.Callable;

public class ConsumerThread implements Callable<List<Item>> {
    private final PCMessagingQueue messagingQueue;

    public ConsumerThread(PCMessagingQueue messagingQueue) {
        this.messagingQueue = messagingQueue;
    }

    @Override
    public List<Item> call() throws Exception {
        List<Item> itemList;
        try {
            itemList = RandomOrderGeneration.OrderGeneration();
            for (Item item : itemList) {
                this.messagingQueue.consume(item.getName(), item.getQuantity());

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
}
