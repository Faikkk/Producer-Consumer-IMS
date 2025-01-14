import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService producerService = Executors.newCachedThreadPool();
        ExecutorService consumerService = Executors.newCachedThreadPool();

        Inventory inventoryQueue = new Inventory(500);
        ProducerThread producerThread = new ProducerThread(inventoryQueue);
        ConsumerThread consumerThread = new ConsumerThread(inventoryQueue);
        for (int i = 0; i < 5; i++) {
            producerService.submit(producerThread);
        }
        Thread.sleep(500);
        System.out.println("====================================");
        System.out.println("The whole inventory now contains: ");
        for(Item item:inventoryQueue.getitemList()){
            System.out.println(item.getName() +" - "+ item.getQuantity() + " items");
        }
        System.out.println("====================================");
        for (int i = 0; i < 3; i++) {
            consumerService.submit(consumerThread);
        }
        Thread.sleep(500);

        System.out.println("====================================");
        System.out.println("The whole inventory now contains: ");
        for(Item item:inventoryQueue.getitemList()){
            System.out.println(item.getName() +" - "+ item.getQuantity() + " items");
        }
        System.out.println("====================================");

    }
}
