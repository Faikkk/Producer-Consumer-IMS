public interface PCMessagingQueue {
    void consume(String name, int quantity) throws InterruptedException;
    void produce(String name, int quantity) throws InterruptedException;
}
