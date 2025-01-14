import java.util.ArrayList;
import java.util.List;

public class Inventory implements PCMessagingQueue {
    private final List<Item> itemList = new ArrayList<>();
    private final int maxSize;
    private int currentSize;
    private final Object lock = new Object();


    public Inventory(int size) {
        this.maxSize = size;
    }

    public List<Item> getitemList() {
        return itemList;
    }

    public void produce(String name, int quantity) throws InterruptedException {
        synchronized (lock) {
            while (itemList.size() == maxSize) {
                System.out.println("The inventory is full. Waiting for the available stock to be sold.");
                lock.wait();
            }
        }
        boolean exists = false;
        if (currentSize <= maxSize) {

            for (Item i : itemList) {
                int updatedQty = i.getQuantity() + quantity;
                if (i.getName().equals(name)) {
                    i.setQuantity(updatedQty);
                    currentSize += quantity;
                    exists = true;
                }
            }
            if (!exists) {
                itemList.add(new Item(name, quantity));
                currentSize += quantity;
            }
        } else {
            System.out.println("The max limit of the inventory size is reached.");
            // call the custom exception
        }
        Thread.sleep(1000);
        lock.notifyAll(); //to notify other threads that are waiting
    }


    public void consume(String name, int quantity) throws InterruptedException {
        synchronized (lock) {
            while (itemList.isEmpty()) {
                System.out.println("The queue is empty. Waiting for the restocking.");
                lock.wait();
            }
        }
        if (currentSize != 0) {
            for (Item i : itemList) {
                int updatedQty = i.getQuantity() - quantity;
                if (i.getName().equals(name)) {
                    if (updatedQty >= 0) {
                        i.setQuantity(updatedQty);
                        currentSize -= quantity;
                        System.out.println(quantity + " of " + name + " was sold.");
                    } else {
                        System.out.println("We can't satisfy the demand for " + name +
                                " in the quantity of " + quantity + " right now.");
                    }
                }
            }
        }
        else {
            System.out.println("The inventory is empty.");
        }
            Thread.sleep(1000);
            lock.notifyAll(); //to notify other threads that are waiting
        }
    }
